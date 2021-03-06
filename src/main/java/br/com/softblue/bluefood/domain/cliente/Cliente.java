package br.com.softblue.bluefood.domain.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
	
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {

	@NotBlank(message = "O CPF nao pode ser vazio")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF possui formato invalido")
	@Column(length = 11)
	private String cpf;
	
	@NotBlank(message = "O CEP nao pode ser vazio")
	@Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato invalido")
	@Column(length = 8)
	private String cep;
	
	public String getFormattedCep() {
		return cep.substring(0, 5) + "-" + cep.substring(5);
	}
}
