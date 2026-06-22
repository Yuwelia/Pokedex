package com.schulze.elena.pokedex.web;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;

	@GetMapping({"", "/"})
	public String listPokemon(Model model) {
//		List<Pokemon> pokemonList = new ArrayList<>();
//		pokemonList.add(new Pokemon(700, "Feelinara"));
//		pokemonList.add(new Pokemon(778, "Mimigma"));


//		List<Map<String, String>> pokemonList = new ArrayList<>();
//		pokemonList.add(Map.of(
//			"pokedexNumber", "700",
//			"pokemonName", "Feelinara",
//			"types", "Fee",
//			"strongAgainst", "Drache, Kampf, Unlicht",
//			"vulnerableTo", "Stahl, Gift"
//		));
//		pokemonList.add(Map.of(
//			"pokedexNumber", "778",
//			"pokemonName", "Mimigma",
//			"types", "Geist, Fee",
//			"strongAgainst", "Geist, Psycho, Unlicht, Drache, Kampf",
//			"vulnerableTo", "Stahl, Geist"
//		));


		List<Pokemon> pokemonList = pokemonService.getPokemons();


		model.addAttribute("pokemonList", pokemonList);
		return "pokemon/list.xhtml";
	}

	@GetMapping("/{id}")
	public String getPokemon(Model model) {
		model.addAttribute("pokemonDetails", "Detail Seite");
		return "pokemon/detail.xhtml";
	}
}
