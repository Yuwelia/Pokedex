package com.schulze.elena.pokedex.repository;

import com.schulze.elena.pokedex.model.Pokemon;
import com.schulze.elena.pokedex.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PokemonRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void update(Pokemon pokemon) {

		jdbcTemplate.update(
			"UPDATE pokemon " +
				"SET name = ?, " +
				"trainer_fk = ?, " +
				"pokedex_number = ? " +
				"WHERE id = ?",
			pokemon.getName(),
			pokemon.getTrainer().getId(),
			pokemon.getPokedexNumber(),
			pokemon.getId()

		);

		jdbcTemplate.update(
			"DELETE FROM pokemon_type " +
				"WHERE pokemon_fk = ?",
			pokemon.getId()
		);

		String[] types = pokemon.getTypes().split(",");

		for (String type : types) {
			type = type.trim();

			jdbcTemplate.update(
				"INSERT INTO pokemon_type (pokemon_fk, type_fk )" +
				"VALUES (?, (SELECT id FROM type WHERE name = ?))",
				pokemon.getId(),
				type
			);
		}
	}

	public int add(Pokemon pokemon) {
		int id = jdbcTemplate.queryForObject(
			"SELECT NEXTVAL('pokemon_seq')",
			Integer.class);

		jdbcTemplate.update(
			"INSERT INTO pokemon(id, pokedex_number, name, trainer_fk) " +
				"VALUES (?, ?, ?, ?)",
			id,
			pokemon.getPokedexNumber(),
			pokemon.getName(),
			pokemon.getTrainer().getId()
		);

		String[] types = pokemon.getTypes().split(",");

		for (String type : types) {
			type = type.trim();

			jdbcTemplate.update(
				"INSERT INTO pokemon_type (pokemon_fk, type_fk )" +
					"VALUES (?, (SELECT id FROM type WHERE name = ?))",
				id,
				type
			);
		}

		return id;
	}

	public void deletePokemon(int id) {
		jdbcTemplate.update(
			"DELETE FROM pokemon_type WHERE pokemon_fk = ?",
			id);
		jdbcTemplate.update(
			"DELETE FROM pokemon WHERE id = ?",
			id);
	}

	public List<Pokemon> listAll() {

		return jdbcTemplate.query(
			selectStmt() +
				"GROUP BY pokemon.id " +
				"ORDER BY pokemon.id;",
			new PokemonMapper()
		);
	}

	public Pokemon getPokemonById(int id) {
		return jdbcTemplate.queryForObject(
			 selectStmt() +
				"WHERE pokemon.id = ? " +
				"GROUP BY pokemon.id",
			new PokemonMapper(),
			id
		);
	}

	private String selectStmt() {
		return "SELECT " +
			"pokemon.id, " +
			"pokemon.pokedex_number, " +
			"pokemon.name, " +
			"GROUP_CONCAT(type.name SEPARATOR ', ') AS types " +
			"FROM pokemon " +
			"LEFT JOIN pokemon_type " +
			"ON pokemon.id = pokemon_type.pokemon_fk " +
			"LEFT JOIN type " +
			"ON pokemon_type.type_fk = type.id ";
	}

	private Trainer setTrainer(int id) {
		return jdbcTemplate.queryForObject(
			"SELECT " +
				"trainer.name, " +
				"trainer.title, " +
				"trainer.region, " +
				"trainer.id " +
				"FROM trainer, pokemon " +
				"WHERE trainer.id = pokemon.trainer_fk " +
				"AND pokemon.id = " + id
			,
			new TrainerMapper()
		);
	}

	private class PokemonMapper implements RowMapper<Pokemon> {

		public Pokemon mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Pokemon(
				resultSet.getInt("id"),
				resultSet.getInt("pokedex_number"),
				resultSet.getString("name"),
				resultSet.getString("types"),
				setTrainer(resultSet.getInt("id"))
			);
		}
	}

	private class TrainerMapper implements RowMapper<Trainer> {

		public Trainer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Trainer(
				resultSet.getInt("id"),
				resultSet.getString("name"),
				resultSet.getString("title"),
				resultSet.getString("region")
			);
		}
	}
}
