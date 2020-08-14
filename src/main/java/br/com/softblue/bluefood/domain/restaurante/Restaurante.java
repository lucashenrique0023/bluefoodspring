package br.com.softblue.bluefood.domain.restaurante;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.softblue.bluefood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "restaurante")
public class Restaurante extends Usuario {
	
	@NotBlank(message = "O CNPJ nao pode ser vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato invalido")
	@Column(length = 11, nullable = false)
	private String cnpj;
	
	@Size(max = 80)
	private String logotipo;
	
	@NotNull(message = "A taxa de entrega nao pode ser vazia")
	@Min(0)
	@Max(99)
	private BigDecimal taxaEntrega;
	
	@NotNull(message = "O tempo de entrega nao pode ser vazio")
	@Min(0)
	@Max(120)
	private Integer tempoEntregaBase;
	
	// Dono do relacionamento Many To Many
	// Restaurante consegue chegar em categorias
	@ManyToMany
	@JoinTable(
			name = "restaurante_has_categoria",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id")
	)
	private Set<CategoriaRestaurante> categorias = new HashSet<CategoriaRestaurante>(0);
	
}
