package br.com.softblue.bluefood.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.softblue.bluefood.domain.restaurante.CategoriaRestaurante;
import br.com.softblue.bluefood.domain.restaurante.CategoriaRestauranteRepository;

@DataJpaTest
@ActiveProfiles("test")
public class CategoriaRestauranteRepositoryTest {

	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Test
	public void testInsertAndDelete() throws Exception {
		
		assertThat(categoriaRestauranteRepository).isNotNull();
		
		CategoriaRestaurante cr = new CategoriaRestaurante();
		cr.setNome("Chines");
		cr.setImagem("chinesa.png");
		categoriaRestauranteRepository.saveAndFlush(cr);
		
		assertThat(cr.getId()).isNotNull();
		
		CategoriaRestaurante cr2 = categoriaRestauranteRepository.findById(cr.getId()).orElseThrow(NoSuchElementException::new);
		
		assertThat(cr.getNome()).isEqualTo(cr2.getNome());
		
		assertThat(categoriaRestauranteRepository.findAll()).hasSize(7); // data.sql insere 6.
		
		categoriaRestauranteRepository.delete(cr2);
		
		assertThat(categoriaRestauranteRepository.findAll()).hasSize(6);
		
		
	}
}
