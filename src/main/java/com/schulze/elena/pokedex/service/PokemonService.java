package com.schulze.elena.pokedex.service;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.repository.PokemonRepository;
import com.schulze.elena.pokedex.repository.TrainerRepository;
import com.schulze.elena.pokedex.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {
	@Autowired
	private PokemonRepository pokemonRepository;
	@Autowired
	TypeRepository typeRepository;
	@Autowired
	private TrainerRepository trainerRepository;


	public List<Pokemon> listPokemons() {
		List<Pokemon> pokemons = pokemonRepository.listAll();

		for (Pokemon pokemon : pokemons) {
			setTypeAndReactions(pokemon);
		}

		return pokemons;
	}


	public Optional<Pokemon> getPokemonById(int id) {
		Optional<Pokemon> pokemon = pokemonRepository.getPokemonById(id);
		pokemon.ifPresent(this::setTypeAndReactions);
		return pokemon;
	}

	public void updatePokemon(Pokemon pokemon) {
		pokemonRepository.update(pokemon);
	}

	public int addPokemon(Pokemon pokemon) {
		return pokemonRepository.add(pokemon);
	}

	public void deletePokemon(int id) {
		pokemonRepository.deletePokemon(id);
	}

	private void setTypeAndReactions(Pokemon pokemon) {
		List<String> types = (Arrays.stream(pokemon.getTypes()
				.replaceAll("[\\[\\]]", "")
				.split(","))
			.map(String::trim)
			.toList()
		);


		String strongAgainst = String.join(", ", typeRepository.getStrongAgainstTypes(types));
		pokemon.setStrongAgainst(strongAgainst);

		String vulnerableTo = String.join(", ", typeRepository.getVulnerableToTypes(types));
		pokemon.setVulnerableTo(vulnerableTo);
	}
}


