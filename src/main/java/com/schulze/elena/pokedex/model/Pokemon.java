package com.schulze.elena.pokedex.model;

public class Pokemon {

	private int id;
	private String name;
	private String types;
	private String strongAgainst;
	private String vulnerableTo;

	public Pokemon(int id, String name, String types,  String strongAgainst, String vulnerableTo) {
		this.id = id;
		this.name = name;
		this.types = types;
		this.strongAgainst = strongAgainst;
		this.vulnerableTo = vulnerableTo;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTypes() {
		return types;
	}

	public String getStrongAgainst() {
		return strongAgainst;
	}

	public void setStrongAgainst(String strongAgainst) {
		this.strongAgainst = strongAgainst;
	}

	public void setVulnerableTo(String vulnerableTo) {
		this.vulnerableTo = vulnerableTo;
	}

	public String getVulnerableTo() {
		return vulnerableTo;
	}

	public void setType(String types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return name;
	}
}
