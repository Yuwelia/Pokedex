package com.schulze.elena.pokedex.service;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.repository.PokemonRepository;
import com.schulze.elena.pokedex.repository.TrainerRepository;
import com.schulze.elena.pokedex.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		return pokemons;
	}


	public Pokemon getPokemonById(int id) {
		return pokemonRepository.getPokemonById(id);
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

	private void setTypeAndReactions(List<Pokemon> pokemons) {
		for (Pokemon pokemon : pokemons) {

			// TODO Schönere typen machen
			String[] types = pokemon.getTypes()
				.replaceAll("[\\[\\]]", "")
				.split(",");

			String typ1 = types[0].trim();
			String typ2;
			if (types.length > 1) {
				typ2 = types[1].trim();
			} else {
				typ2 = null;
			}

			List<String> strongAgainstList = typeRepository.getStrongAgainstTypes(typ1, typ2);
			String strongAgainst = strongAgainstList.stream().collect(Collectors.joining(", "));
			pokemon.setStrongAgainst(strongAgainst);

			List<String> vulnerableToList = typeRepository.getVulnerableToTypes(typ1, typ2);
			String vulnerableTo = vulnerableToList.stream().collect(Collectors.joining(", "));
			pokemon.setVulnerableTo(vulnerableTo);
		}
	}
}


