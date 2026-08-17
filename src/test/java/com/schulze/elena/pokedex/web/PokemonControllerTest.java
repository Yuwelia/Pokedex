package com.schulze.elena.pokedex.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.repository.PokemonRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // TODO really needed, or default behavior anyway?
class PokemonControllerTest {

	@Autowired
	private MockMvcTester mockMvcTester; // Automatischer AssertJ-Wrapper für MockMvc
	@Autowired
	private PokemonRepository pokemonRepository;

	@Test
	void listPokemon_ShouldMapUrlAndPopulateModel() {
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
	void getPokemon_ShouldMapUrlAndPopulateModel() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/1").exchange();

		assertThat(result).as("GET result")
			.hasStatusOk()
			.hasViewName("pokemon/detail.xhtml")
			.model().as("GET result model")
			.containsKey("pokemon")
			.extracting(model -> model.get("pokemon"))
			.extracting(pokemon -> ((Pokemon)pokemon).getName())
			.isEqualTo("Mimigma")
		;
	}
	@Test
	void getPokemonFailed_ShouldMapUrlAndPopulateModel() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/123456789").exchange();

		assertThat(result).as("GET result")
			.hasStatus(HttpStatus.NOT_FOUND)
		;
	}

	@Test
	void updateGetPokemon_ShouldMapUrlAndPopulateModel() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/1/update").exchange();

		assertThat(result).as("GET result")
			.hasStatusOk()
			.hasViewName("pokemon/update.xhtml")
		;
	}

	@Test
	void updateGetPokemonFailed_ShouldMapUrlAndPopulateModel() {
		MvcTestResult result = mockMvcTester.get().uri("/pokemon/123456789/update").exchange();

		assertThat(result).as("GET result")
			.hasStatus(HttpStatus.NOT_FOUND)
		;
	}

	@Test
	void updatePutPokemon_ShouldMapUrlAndPopulateModel() {
		int id = 1;
		MvcTestResult result = mockMvcTester.put().uri("/pokemon/" + id + "/update")
			.formField("pokedexNumber", "567")
			.formField("name", "newName")
			.formField("types", "Feuer")
			.formField("trainer.id", "1")
			.exchange();

		Pokemon updatedPokemon = pokemonRepository.getPokemonById(id);

		Assertions.assertThat(updatedPokemon.getName()).isEqualTo("newName");
		Assertions.assertThat(updatedPokemon.getTypes()).isEqualTo("Feuer");
		Assertions.assertThat(updatedPokemon.getPokedexNumber()).isEqualTo(567);

		assertThat(result).as("GET result")
			.hasStatus3xxRedirection()
		;
	}
}