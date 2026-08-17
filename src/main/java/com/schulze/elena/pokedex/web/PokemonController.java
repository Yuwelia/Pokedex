package com.schulze.elena.pokedex.web;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.service.PokemonService;
import com.schulze.elena.pokedex.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

		Pokemon selectedPokemon = null;
		for (Pokemon pokemon : pokemonList) {
			if (pokemon.getId() == id) {
				selectedPokemon = pokemon;
				model.addAttribute("pokemon", pokemon);
			}
		}

		if (selectedPokemon == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon not found");
		}

		return "pokemon/detail.xhtml";
	}

	@GetMapping("/{id}/update")
	public String updatePokemon(@PathVariable int id, Model model) {
		List<Pokemon> pokemonList = pokemonService.getPokemons();

		Pokemon selectedPokemon = null;
		for (Pokemon pokemon : pokemonList) {
			if (pokemon.getId() == id) {
				selectedPokemon = pokemon;
				model.addAttribute("pokemon", pokemon);
			}
		}
		model.addAttribute("trainerList", trainerService.getTrainerList());

		if (selectedPokemon == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon not found");
		}

		return "pokemon/update.xhtml";
	}

	@PutMapping("/{id}/update")
	public String updatePokemon(@PathVariable int id, @ModelAttribute("pokemon") Pokemon pokemon) {
		pokemonService.updatePokemon(pokemon);
		return "redirect:/pokemon/" + id;
	}

	@GetMapping("/add")
	public String addPokemon(Model model) {
		model.addAttribute("pokemon", new Pokemon());
		model.addAttribute("trainerList", trainerService.getTrainerList());

		return "pokemon/add.xhtml";
	}

	@PostMapping("/add")
	public String addPokemon(@ModelAttribute("pokemon") Pokemon pokemon) {
		int id = pokemonService.addPokemon(pokemon);
		return "redirect:/pokemon/" + id;
	}

	@GetMapping("/{id}/delete")
	public String deletePokemon(@PathVariable int id, Model model) {
		pokemonService.deletePokemon(id);
		return "redirect:/pokemon/";
	}
}
