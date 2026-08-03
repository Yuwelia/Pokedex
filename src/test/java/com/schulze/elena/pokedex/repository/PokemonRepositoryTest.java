package com.schulze.elena.pokedex.repository;

import static org.assertj.core.api.Assertions.*;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.model.Trainer;
import com.schulze.elena.pokedex.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PokemonRepositoryTest {

	@Autowired
	private PokemonRepository pokemonRepository;
	@Autowired
	private PokemonService pokemonService;

	@Test
	public void testListAll() {
		List<Pokemon> pokemons = pokemonRepository.listAll();

		assertThat(pokemons)
			.extracting(Pokemon::getName)
			.containsExactlyInAnyOrder("Mimigma", "Reshiram", "Feelinara", "Rayquaza", "Flurmel", "Altaria", "Metagross");
	}

	@Test
	public void testAddPokemon() {
		Trainer trainer = new Trainer();
		trainer.setId(2);

		Pokemon newPokemon = new Pokemon();
		newPokemon.setName("Felilou");
		newPokemon.setPokedexNumber(509);
		newPokemon.setTypes("Unlicht");
		newPokemon.setTrainer(trainer);

		int id = pokemonRepository.add(newPokemon);

		List<Pokemon> pokemons = pokemonService.getPokemons();

		Pokemon foundPokemon = pokemons.get(pokemons.size() - 1);

		assertThat(foundPokemon.getId()).isEqualTo(id);
		assertThat(foundPokemon.getName()).isEqualTo("Felilou");
		assertThat(foundPokemon.getPokedexNumber()).isEqualTo(509);
		assertThat(foundPokemon.getTypes()).isEqualTo("Unlicht");
		assertThat(foundPokemon.getTrainer().getId()).isEqualTo(trainer.getId());
		assertThat(foundPokemon.getStrongAgainst()).isEqualTo("Geist, Psycho");
		assertThat(foundPokemon.getVulnerableTo()).isEqualTo("Fee, Kampf, Käfer");
	}
}