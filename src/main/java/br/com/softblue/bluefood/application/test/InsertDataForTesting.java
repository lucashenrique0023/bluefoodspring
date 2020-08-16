package br.com.softblue.bluefood.application.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.util.StringUtils;

@Component
public class InsertDataForTesting {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		clientes();
		Restaurante[] restaurantes = restaurantes();
	}
	
	private Restaurante[] restaurantes() {
		List<Restaurante> restaurantes = new ArrayList<>();
		
		CategoriaRestaurante categoriaPizza = categoriaRestauranteRepository.findById(1).orElseThrow();
		CategoriaRestaurante categoriaSanduiche = categoriaRestauranteRepository.findById(2).orElseThrow();
		CategoriaRestaurante categoriaSobremesa = categoriaRestauranteRepository.findById(5).orElseThrow();
		CategoriaRestaurante categoriaJapones = categoriaRestauranteRepository.findById(6).orElseThrow();
		
		Restaurante r = new Restaurante();
		r.setNome("Bubger King");
		r.setEmail("r1@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("12121212121");
		r.setTaxaEntrega(BigDecimal.valueOf(3.2));
		r.setTelefone("30303030303");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0001-logo.png");
		r.setTempoEntrega(30);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Mc Naldo's");
		r.setEmail("r2@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("12121212121");
		r.setTaxaEntrega(BigDecimal.valueOf(4.2));
		r.setTelefone("30303030322");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0002-logo.png");
		r.setTempoEntrega(20);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Sbubby");
		r.setEmail("r3@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("12121212121");
		r.setTaxaEntrega(BigDecimal.valueOf(34.2));
		r.setTelefone("30303030333");
		r.getCategorias().add(categoriaSanduiche);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0003-logo.png");
		r.setTempoEntrega(23);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Pizza Brut");
		r.setEmail("r4@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("12121212121");
		r.setTaxaEntrega(BigDecimal.valueOf(44.42));
		r.setTelefone("30303030344");
		r.getCategorias().add(categoriaPizza);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0004-logo.png");
		r.setTempoEntrega(22);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Wiki Japa");
		r.setEmail("r5@bluefood.com.br");
		r.setSenha(StringUtils.encrypt("r"));
		r.setCnpj("12121212121");
		r.setTaxaEntrega(BigDecimal.valueOf(14.2));
		r.setTelefone("30303030355");
		r.getCategorias().add(categoriaJapones);
		r.getCategorias().add(categoriaSobremesa);
		r.setLogotipo("0005-logo.png");
		r.setTempoEntrega(22);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		Restaurante[] array = new Restaurante[restaurantes.size()];
		return restaurantes.toArray(array);
	}
	
	private Cliente[] clientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Cliente c = new Cliente();
		c.setNome("Joao Silva");
		c.setEmail("joao@bluefood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("52041210");
		c.setCpf("09807553440");
		c.setTelefone("81999999999");
		clienteRepository.save(c);
		clientes.add(c);
		
		c = new Cliente();
		c.setNome("Maria Torres");
		c.setEmail("maria@bluefood.com.br");
		c.setSenha(StringUtils.encrypt("c"));
		c.setCep("52041233");
		c.setCpf("09807553433");
		c.setTelefone("81999999933");
		clienteRepository.save(c);
		clientes.add(c);
		
		Cliente[] array = new Cliente[clientes.size()];
		return clientes.toArray(array);
	}
}
