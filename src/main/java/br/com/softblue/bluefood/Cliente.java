package br.com.softblue.bluefood;

import lombok.Getter;

@Getter
public class Cliente {

	private String nome;
	
	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.getNome();
	}
}
