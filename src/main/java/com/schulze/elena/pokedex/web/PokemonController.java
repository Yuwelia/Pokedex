package com.schulze.elena.pokedex.web;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.service.PokemonService;
import com.schulze.elena.pokedex.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;
	@Autowired
	private TrainerService trainerService;


	@GetMapping({"", "/"})
	public String listPokemon(Model model) {
		List<Pokemon> pokemonList = pokemonService.getPokemons();

		model.addAttribute("pokemonList", pokemonList);
		return "pokemon/list.xhtml";
	}

	@GetMapping("/{id}")
	public String getPokemon(@PathVariable int id, Model model) {
		List<Pokemon> pokemonList = pokemonService.getPokemons();

		for (Pokemon pokemon : pokemonList) {
			if (pokemon.getId() == id) {
				model.addAttribute("pokemon", pokemon);
			}
		}

		return "pokemon/detail.xhtml";
	}

	@GetMapping("/{id}/update")
	public String updatePokemon(@PathVariable int id, Model model) {
		List<Pokemon> pokemonList = pokemonService.getPokemons();

		for (Pokemon pokemon : pokemonList) {
			if (pokemon.getId() == id) {
				model.addAttribute("pokemon", pokemon);
			}
		}
		model.addAttribute("trainerList", trainerService.getTrainerList());

		return "pokemon/update.xhtml";
	}

	@PutMapping("/{id}/update")
	public String updatePokemon(@PathVariable int id, @ModelAttribute("pokemon") Pokemon pokemon) {
		pokemonService.updatePokemon(pokemon);
		return "redirect:/pokemon/" + id;
	}

}
