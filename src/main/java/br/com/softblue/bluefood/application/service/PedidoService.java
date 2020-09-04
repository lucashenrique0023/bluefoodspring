package br.com.softblue.bluefood.application.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.softblue.bluefood.domain.pagamento.DadosCartao;
import br.com.softblue.bluefood.domain.pagamento.StatusPagamento;
import br.com.softblue.bluefood.domain.pedido.Carrinho;
import br.com.softblue.bluefood.domain.pagamento.DadosCartao;
import br.com.softblue.bluefood.domain.pedido.ItemPedido;
import br.com.softblue.bluefood.domain.pedido.ItemPedidoPK;
import br.com.softblue.bluefood.domain.pedido.ItemPedidoRepository;
import br.com.softblue.bluefood.domain.pedido.Pedido;
import br.com.softblue.bluefood.domain.pedido.Pedido.Status;
import br.com.softblue.bluefood.domain.pedido.PedidoRepository;
import br.com.softblue.bluefood.util.SecurityUtils;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Value("${bluefood.sbpay.url}")
	private String sbPayUrl;
	
	@Value("${bluefood.sbpay.token}")
	private String sbPayToken;
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = PagamentoException.class) // Se uma excecao do PagamentoException for lancada, tera um rollback.
	public Pedido criarEPagar(Carrinho carrinho, String numCartao) throws PagamentoException {
		
		Pedido pedido = new Pedido();
		pedido.setData(LocalDateTime.now());
		pedido.setCliente(SecurityUtils.loggedCliente());
		pedido.setRestaurante(carrinho.getRestaurante());
		pedido.setStatus(Status.Producao);
		pedido.setTaxaEntrega(carrinho.getRestaurante().getTaxaEntrega());
		pedido.setSubtotal(carrinho.getPrecoTotal(false));
		pedido.setTotal(carrinho.getPrecoTotal(true));
		
		pedido = pedidoRepository.save(pedido);
		
		int ordem = 1;
		
		for ( ItemPedido itemPedido : carrinho.getItens()) {
			// Gerando ID ItemPedido
			itemPedido.setId(new ItemPedidoPK(pedido, ordem++));
			itemPedidoRepository.save(itemPedido);	
		}
		
		DadosCartao dadosCartao = new DadosCartao();
		dadosCartao.setNumCartao(numCartao);
		
		// Prepara o cabecalho da requisicao
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Token", sbPayToken);
		
		// Prepara a requisicao para ser enviada
		// DadosCartao sao os dados que vao no corpo do protocolo
		HttpEntity<DadosCartao> requestEntity = new HttpEntity<DadosCartao>(dadosCartao, headers);
		
		// Objeto utilizado para fazer invocacao de WebService
		RestTemplate restTemplate = new RestTemplate();
		
		Map<String, String> response;
		try {
				response = restTemplate.postForObject(sbPayUrl, requestEntity, Map.class);
			} catch (Exception e ) {
				throw new PagamentoException("Erro no servidor de pagamento");
			}
		
			StatusPagamento statusPagamento = StatusPagamento.valueOf(response.get("status"));
			
			if (statusPagamento != StatusPagamento.Autorizado) {
				throw new PagamentoException(statusPagamento.getDescricao());
			}
		
		
		return pedido;
	}
}
