package com.schulze.elena.pokedex.web;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;

	@GetMapping({"", "/"})
	public String listPokemon(Model model) {
		List<Pokemon> pokemonList = pokemonService.getPokemons();

		model.addAttribute("pokemonList", pokemonList);
		return "pokemon/list.xhtml";
	}

	@GetMapping("/{id}")
	public String getPokemon(@PathVariable int id, Model model) {
		model.addAttribute("pokemonDetails", "Detail Seite");

		List<Pokemon> pokemonList = pokemonService.getPokemons();

		for (Pokemon pokemon : pokemonList) {
			if (pokemon.getId() == id) {
				model.addAttribute("pokemon", pokemon);
			}
		}

		return "pokemon/detail.xhtml";
	}
}
