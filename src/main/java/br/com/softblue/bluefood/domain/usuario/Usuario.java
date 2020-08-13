package br.com.softblue.bluefood.domain.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class Usuario implements Serializable {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O Nome nao pode ser vazio")
	@Size(max = 80, message = "O Nome e muito grande")
	private String nome;
	
	@NotBlank(message = "O Email nao pode ser vazio")
	@Size(max = 60, message = "O Email e muito grande")
	@Email(message = "O email e invalido")
	private String email;
	
	@NotBlank(message = "A senha nao pode ser vazio")
	@Size(max = 80, message = "A senha e muito grande")
	private String senha;
	
	@NotBlank(message = "O telefone nao pode ser vazio")
	@Pattern(regexp = "[0-9]{10,11}", message = "O telefone possui formato invalido")
	@Column(length = 11, nullable = false)
	private String telefone;
}
