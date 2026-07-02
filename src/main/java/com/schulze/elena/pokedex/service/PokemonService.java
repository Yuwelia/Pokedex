package com.schulze.elena.pokedex.service;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.repository.PokemonRepository;
import com.schulze.elena.pokedex.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {
	@Autowired
	private PokemonRepository pokemonRepository;
	@Autowired
	TypeRepository typeRepository;

	public List<Pokemon> getPokemons() {
		List<Pokemon> pokemons = pokemonRepository.listAll();
		for (Pokemon pokemon : pokemons) {
			pokemon.setType(pokemon.getTypes().replace("[", "").replace("]", ""));
		}

		setTypeAndReactions(pokemons);

		return pokemons;
	}

	private void setTypeAndReactions(List<Pokemon> pokemons) {
		String typ1;
		String typ2;
		for (Pokemon pokemon : pokemons) {
			String[] types = pokemon.getTypes()
				.replaceAll("[\\[\\]]", "")
				.split(",");

			typ1 = types[0].trim();
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


