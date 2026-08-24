package com.schulze.elena.pokedex.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Pokemon {

	private int id;
	private int pokedexNumber;
	private String name;
	private String types;
	private String strongAgainst;
	private String vulnerableTo;
	private Trainer trainer;

	public Pokemon(int id,int pokedexNumber, String name, String types) {
		this.id = id;
		this.pokedexNumber = pokedexNumber;
		this.name = name;
		this.types = types;
	}

	public Pokemon(int id,int pokedexNumber, String name, String types, Trainer trainer) {
		this.id = id;
		this.pokedexNumber = pokedexNumber;
		this.name = name;
		this.types = types;
		this.trainer = trainer;
	}

	public Pokemon() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPokedexNumber() {
		return pokedexNumber;
	}
	public void setPokedexNumber(int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
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
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
