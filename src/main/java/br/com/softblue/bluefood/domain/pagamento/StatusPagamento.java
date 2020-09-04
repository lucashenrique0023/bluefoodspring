package br.com.softblue.bluefood.domain.pagamento;

public enum StatusPagamento {

	Autorizado("Autorizado"),
	NaoAutorizado("Nao autorizado pela instituicao financeira"),
	CartaoInvalido("Cartao invalido ou bloqueado");
	
	String descricao;
	
	StatusPagamento(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
