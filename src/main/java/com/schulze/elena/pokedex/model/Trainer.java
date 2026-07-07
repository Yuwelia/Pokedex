package com.schulze.elena.pokedex.model;

import java.util.List;

public class Trainer {
	private String name;
	private String title;
	private String Region;
	private List<String> pokemonList;

	public Trainer(String name, String title, String region) {
		this.name = name;
		this.title = title;
		Region = region;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public List<String> getPokemonList() {
		return pokemonList;
	}
	public void setPokemonList(List<String> pokemonList) {
		this.pokemonList = pokemonList;
	}
}
