package com.schulze.elena.pokedex.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

	@GetMapping({"", "/"})
	public String listTrainer() {
		return "trainer/list.xhtml";
	}

	@GetMapping("/{name}")
	public String getTrainer() {
		return "trainer/detail.xhtml";
	}
}
