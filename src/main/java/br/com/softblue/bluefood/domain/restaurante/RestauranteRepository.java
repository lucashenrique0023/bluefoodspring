package br.com.softblue.bluefood.domain.restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>{

	public Restaurante findByEmail(String email);
	
	public List<Restaurante> findByNomeIgnoreCaseContaining(String nome);
	
	// Pesquisando Restaurantes(Uma tabela) de acordo com a Categoria(Outra tabela)
	//Categorias = Atributo de restaurantes que contem uma lista de categorias
	//Id filtra as categorias da lista pelos Ids delas.
	public List<Restaurante> findByCategorias_Id(Integer categoriaId);
}
