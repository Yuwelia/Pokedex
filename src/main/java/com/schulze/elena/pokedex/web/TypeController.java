package com.schulze.elena.pokedex.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/type")
public class TypeController {

	@GetMapping
	public String listType() {
		return "type/list.xhtml";
	}

	@GetMapping("/{id}")
	public String getType() {
		return "type/detail.xhtml";
	}
}
