package com.schulze.elena.pokedex.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.repository.PokemonRepository;
import com.schulze.elena.pokedex.service.PokemonService;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PokemonControllerTest {

	@Autowired
	private MockMvcTester mockMvcTester; // Automatischer AssertJ-Wrapper für MockMvc
	@Autowired
	private PokemonRepository pokemonRepository;
	@Autowired
	PokemonService pokemonService;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// TODO einheitliches Namensschema für Tests (ohne "_test" hinten dran, mit "test" vorne dran)

	@Test
	void testListPokemon() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon").exchange();

		assertThat(result).as("GET result")
			.hasStatusOk()
			.hasViewName("pokemon/list.xhtml")
			.model().as("GET result model")
			.containsKey("pokemonList")
			.extracting(model -> model.get("pokemonList"), InstanceOfAssertFactories.LIST)
			.extracting(pokemon -> ((Pokemon)pokemon).getName())
			.containsExactly("Mimigma", "Reshiram", "Feelinara", "Rayquaza", "Flurmel", "Altaria", "Metagross")
		;
	}

	@Test
	void getPokemon() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/1").exchange();

		assertThat(result).as("GET result")
			.hasStatusOk()
			.hasViewName("pokemon/detail.xhtml")
			.model().as("GET result model")
			.containsKey("pokemon")
			.extracting(model -> model.get("pokemon"))
			.isInstanceOf(Pokemon.class)
			.satisfies(pokemonObject -> {
				// TODO ganzes Pokemon asserten
				Pokemon pokemon = (Pokemon)pokemonObject;
				assertThat(pokemon.getName()).isEqualTo("Mimigma");
			})
		;
	}

	@Test
	void testGetPokemon_NotExistingId() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/123456789").exchange();

		assertThat(result).as("GET result")
			.hasStatus(HttpStatus.NOT_FOUND)
		;
	}

	@Test
	void testUpdatePokemon_get() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/1/update").exchange();

		assertThat(result).as("GET result")
			.hasStatusOk()
			.hasViewName("pokemon/update.xhtml")
		;
	}

	@Test
	void testUpdatePokemon_get_Failed() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/123456789/update").exchange();

		assertThat(result).as("GET result")
			.hasStatus(HttpStatus.NOT_FOUND)
		;
	}

	@Test
	void updatePokemon_put_test() {
		int id = 1;
		MvcTestResult result = mockMvcTester.put().uri("/pokemon/" + id + "/update")
			.formField("pokedexNumber", "567")
			.formField("name", "newName")
			.formField("types", "Feuer")
			.formField("trainer.id", "1")
			.exchange();

		Pokemon updatedPokemon = pokemonRepository.getPokemonById(id);

		assertThat(updatedPokemon.getName()).isEqualTo("newName");
		assertThat(updatedPokemon.getTypes()).isEqualTo("Feuer");
		assertThat(updatedPokemon.getPokedexNumber()).isEqualTo(567);
		// TODO assert trainer

		assertThat(result).as("GET result")
			.hasStatus3xxRedirection()
		;
	}

	// TODO negativtest für update

	@Test
	void deletePokemon_test() {
		int beforeDelete = pokemonService.listPokemons().size();
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/1/delete").exchange();
		int afterDelete = pokemonService.listPokemons().size();

		assertThat(beforeDelete - afterDelete).isEqualTo(1);
		assertThatPokemonIsDeleted(1);

		assertThat(result).as("GET result")
			.hasStatus3xxRedirection()
			.hasViewName("redirect:/pokemon/")
		;
	}

	// TODO helper runter
	private void assertThatPokemonIsDeleted(int id) {

		int foundObjects = jdbcTemplate.queryForObject(
			"SELECT COUNT(*) FROM pokemon WHERE id = " + id,
			Integer.class
		);
		assertThat(foundObjects).isZero();
	}


	@Test
	void deletePokemonFailed_test() {
		int beforeDelete = pokemonService.listPokemons().size();
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/123456789/delete").exchange();
		int afterDelete = pokemonService.listPokemons().size();

		assertThat(afterDelete).isEqualTo(beforeDelete);
		//assertThatPokemonIsDeleted(123456789);
		assertThat(result).as("GET result")
			.hasStatus3xxRedirection()
			.hasViewName("redirect:/pokemon/")
		;
	}

	@Test
	void createPokemon_post_test() {
		MvcTestResult result = mockMvcTester.post().uri("/pokemon/add")
			.formField("pokedexNumber", "509")
			.formField("name", "Felilou")
			.formField("types", "Unlicht")
			.formField("trainer.id", "2")
			.exchange();

		assertThat(result).as("GET result")
			.hasStatus3xxRedirection()
			.hasViewName("redirect:/pokemon/8")
		;
		// TODO asserten, dass das Pokemon persistiert ist
	}

	@Test
	void createPokemonFailed_post_test() {
		MvcTestResult result = mockMvcTester.post().uri("/pokemon/add")
			.formField("pokedexNumber", "abc")
			.formField("name", "Felilou")
			.formField("types", "abc")
			.formField("trainer.id", "123456789")
			.exchange();

		assertThat(result).as("GET result")
			.hasStatus4xxClientError()
		;
		// TODO asserten, dass das kein neues Pokemon persistiert wurde
	}
}