package com.schulze.elena.pokedex.repository;

import com.schulze.elena.pokedex.model.Pokemon;
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
				"SET name = '" + pokemon.getName() + "', " +
				"trainer_fk = " + pokemon.getTrainer().getId() + ", " +
				"pokedex_number = '" + pokemon.getPokedexNumber() + "' " +
				"WHERE id = " + pokemon.getId());

		jdbcTemplate.update(
			"DELETE FROM pokemon_type " +
				"WHERE pokemon_fk = " + pokemon.getId()
		);

		String[] types = pokemon.getTypes().split(",");

		for (String type : types) {
			type = type.trim();

			jdbcTemplate.update(
				"INSERT INTO pokemon_type (pokemon_fk, type_fk )" +
				"VALUES (" + pokemon.getId() + ", (SELECT id FROM type WHERE name = '" + type + "'))"
			);
		}
	}

	public int add(Pokemon pokemon) {

		int id = jdbcTemplate.queryForObject("SELECT NEXTVAL('pokemon_seq')", Integer.class);
		jdbcTemplate.update(
			"INSERT INTO pokemon(id, pokedex_number, name, trainer_fk) " +
				"VALUES ( " + id + ", " + pokemon.getPokedexNumber() + ", '"  + pokemon.getName() + "', " + pokemon.getTrainer().getId() + ")"
		);

		String[] types = pokemon.getTypes().split(",");

		for (String type : types) {
			type = type.trim();

			jdbcTemplate.update(
				"INSERT INTO pokemon_type (pokemon_fk, type_fk )" +
					"VALUES (" + pokemon.getPokedexNumber() + ", (SELECT id FROM type WHERE name = '" + type + "'))"
			);
		}
		return id;
	}

	public List<Pokemon> listAll() {

		return jdbcTemplate.query(
			"SELECT " +
				"pokemon.id, " +
				"pokemon.pokedex_number, " +
				"pokemon.name, " +
				"GROUP_CONCAT(type.name SEPARATOR ', ') AS types " +
				"FROM pokemon " +
				"LEFT JOIN pokemon_type " +
				"ON pokemon.id = pokemon_type.pokemon_fk " +
				"LEFT JOIN type " +
				"ON pokemon_type.type_fk = type.id " +
				"GROUP BY pokemon.id " +
				"ORDER BY pokemon.id;",
			new PokemonListMapper()
		);
	}

	public Pokemon getPokemonById(int id) {
		return jdbcTemplate.queryForObject(
			"SELECT " +
				"pokemon.id, " +
				"pokemon.pokedex_number, " +
				"GROUP_CONCAT(type.name SEPARATOR ', ') AS types, " +
				"pokemon.name " +
				"FROM pokemon " +
				"LEFT JOIN pokemon_type " +
				"ON pokemon.id = pokemon_type.pokemon_fk " +
				"LEFT JOIN type " +
				"ON pokemon_type.type_fk = type.id " +
				"WHERE pokemon.id = " + id +
				"GROUP BY pokemon.id",
			new PokemonMapper()
		);
	}

	public void deletePokemon(int id) {
		jdbcTemplate.update("DELETE FROM pokemon WHERE id = " + id);
	}

	private class PokemonMapper implements RowMapper<Pokemon> {
		public Pokemon mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Pokemon(
				resultSet.getInt("id"),
				resultSet.getInt("pokedex_number"),
				resultSet.getString("name"),
				resultSet.getString("types")
			);
		}
	}
	private class PokemonListMapper implements RowMapper<Pokemon> {

		public Pokemon mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Pokemon(
				resultSet.getInt("id"),
				resultSet.getInt("pokedex_number"),
				resultSet.getString("name"),
				resultSet.getString("types")
			);
		}
	}
}
