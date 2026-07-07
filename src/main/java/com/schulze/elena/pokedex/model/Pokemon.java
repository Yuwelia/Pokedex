package com.schulze.elena.pokedex.model;

public class Pokemon {

	private int id;
	private String name;
	private String types;
	private String strongAgainst;
	private String vulnerableTo;
	private Trainer trainer;

	public Pokemon(int id, String name, String types) {
		this.id = id;
		this.name = name;
		this.types = types;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getStrongAgainst() {
		return strongAgainst;
	}
	public void setStrongAgainst(String strongAgainst) {
		this.strongAgainst = strongAgainst;
	}
	public String getVulnerableTo() {
		return vulnerableTo;
	}
	public void setVulnerableTo(String vulnerableTo) {
		this.vulnerableTo = vulnerableTo;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public String toString() {
		return name;
	}
}
