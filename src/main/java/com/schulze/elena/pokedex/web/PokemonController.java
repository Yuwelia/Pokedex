package com.schulze.elena.pokedex.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

	@GetMapping
	public String listPokemon() {
		return "pokemon/list.xhtml";
	}

	@GetMapping("/{id}")
	public String getPokemon() {
		return "pokemon/detail.xhtml";
	}
}
