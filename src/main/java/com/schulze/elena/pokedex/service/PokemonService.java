package com.schulze.elena.pokedex.service;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.repository.PokemonRepository;
import com.schulze.elena.pokedex.repository.TrainerRepository;
import com.schulze.elena.pokedex.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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

		setTypeAndReactions(pokemons);

		// TODO Trainer gleich im PokemonRepository erzeugen
		setTrainer(pokemons);

		return pokemons;
	}


	// TODO getPokemonById implementieren und verwenden

	public void updatePokemon(Pokemon pokemon) {
		pokemonRepository.update(pokemon);
	}

	public int addPokemon(Pokemon pokemon) {
		return pokemonRepository.add(pokemon);
	}

	public void deletePokemon(int id) {
		pokemonRepository.deletePokemon(id);
	}

	private void setTypeAndReactions(List<Pokemon> pokemons) {
		for (Pokemon pokemon : pokemons) {

			// TODO Schönere typen machen
			List<String> types = Arrays.stream(pokemon.getTypes()
				.replaceAll("[\\[\\]]", "")
				.split(","))
				.toList()
				;

//			String typ1 = types[0].trim();
//			String typ2;
//			if (types.length > 1) {
//				typ2 = types[1].trim();
//			} else {
//				typ2 = null;
//			}

			List<String> strongAgainstList = typeRepository.getStrongAgainstTypes(types);
			String strongAgainst = strongAgainstList.stream().collect(Collectors.joining(", "));
			pokemon.setStrongAgainst(strongAgainst);

			List<String> vulnerableToList = typeRepository.getVulnerableToTypes(types);
			String vulnerableTo = vulnerableToList.stream().collect(Collectors.joining(", "));
			pokemon.setVulnerableTo(vulnerableTo);
		}
	}

	private void setTrainer(List<Pokemon> pokemons) {
		for (Pokemon pokemon : pokemons) {
			pokemon.setTrainer(trainerRepository.getTrainerByPokemonId(pokemon.getId()));
//			pokemon.getTrainerByPokemonId().setPokemonList(trainerRepository.getTrainerPokemonList());
		}
	}
}


