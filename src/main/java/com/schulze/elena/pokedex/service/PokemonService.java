package com.schulze.elena.pokedex.service;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
	@Autowired
	private PokemonRepository pokemonRepository;

	public List<Pokemon> getPokemons() {
		return pokemonRepository.listAll();
	}

}
