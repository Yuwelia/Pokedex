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
