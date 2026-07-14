package com.schulze.elena.pokedex.web;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.model.Trainer;
import com.schulze.elena.pokedex.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
	@Autowired
	private TrainerService trainerService;


	@GetMapping({"", "/"})
	public String listPokemon(Model model) {
		List<Trainer> trainerList = trainerService.getTrainerList();

		model.addAttribute("trainerList", trainerList);
		return "trainer/list.xhtml";
	}

	@GetMapping("/{id}")
	public String getTrainer(@PathVariable int id, Model model) {

		Trainer trainer = trainerService.getTrainer(id);

		model.addAttribute("trainer", trainer);

		return "trainer/detail.xhtml";
	}
}
