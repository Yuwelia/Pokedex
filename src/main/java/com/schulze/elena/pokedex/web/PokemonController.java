package com.schulze.elena.pokedex.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

	@GetMapping({"", "/"})
	public String listPokemon(Model model) {
		model.addAttribute("pokedex", "700");
		model.addAttribute("pokemon", "Feelinara");
		model.addAttribute("type", "Fee");
		model.addAttribute("strongAgainst", "Drache, Kampf, Unlicht");
		model.addAttribute("vulnerableTo", "Stahl, Gift");
		return "pokemon/list.xhtml";
	}

	@GetMapping("/{id}")
	public String getPokemon() {
		return "pokemon/detail.xhtml";
	}
}
