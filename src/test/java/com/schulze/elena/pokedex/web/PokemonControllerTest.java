package com.schulze.elena.pokedex.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.schulze.elena.pokedex.model.Pokemon;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTest {

	@Autowired
	private MockMvcTester mockMvcTester; // Automatischer AssertJ-Wrapper für MockMvc

	@Test
	void listPokemon_ShouldMapUrlAndPopulateModel() {
		// Act: Führe den Request aus
		MvcTestResult result = mockMvcTester.get().uri("/pokemon").exchange();

		// Assert: Nutze die erweiterten AssertJ-Methoden von Spring
		assertThat(result).as("GET result")
			.hasStatusOk()
			.hasViewName("pokemon/list.xhtml")
			.model().as("GET result model")
				.containsKey("pokemonList")
				.extracting(model -> model.get("pokemonList"), InstanceOfAssertFactories.LIST)
				.extracting(pokemon -> ((Pokemon)pokemon).getName())
				.containsExactly("Mimigma", "Reshiram", "Feelinara", "Rayquaza", "Flurmel")
		;
	}
}