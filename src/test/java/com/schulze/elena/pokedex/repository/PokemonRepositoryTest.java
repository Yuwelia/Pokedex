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
	private TrainerRepository trainerRepository;
//	@Autowired
//	private PokemonService pokemonService;

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
		trainer.setName("N (Natural Harmonia Gropius)");
		trainer.setRegion("Einall");
		trainer.setTitle("König");

		Pokemon newPokemon = new Pokemon();
		newPokemon.setName("Felilou");
		newPokemon.setPokedexNumber(509);
		newPokemon.setTypes("Unlicht");
		newPokemon.setTrainer(trainer);

		int id = pokemonRepository.add(newPokemon);

		Pokemon addedPokemon = pokemonRepository.getPokemonById(id);

		assertThat(addedPokemon.getId()).isEqualTo(id);
		assertThat(addedPokemon.getName()).isEqualTo("Felilou");
		assertThat(addedPokemon.getPokedexNumber()).isEqualTo(509);
		assertThat(addedPokemon.getTypes()).isEqualTo("Unlicht");
		assertThat(addedPokemon.getTrainer().getId()).isEqualTo(trainer.getId());
		assertThat(addedPokemon.getTrainer().getName()).isEqualTo(trainer.getName());
		assertThat(addedPokemon.getTrainer().getTitle()).isEqualTo(trainer.getTitle());
		assertThat(addedPokemon.getTrainer().getRegion()).isEqualTo(trainer.getRegion());


	}
}