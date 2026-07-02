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

	public List<Pokemon> listAll() {

		return jdbcTemplate.query(
			"SELECT " +
				"pokemon.id, " +
				"pokemon.name, " +
				"GROUP_CONCAT(type.name SEPARATOR ', ') AS types " +
				"FROM pokemon " +
				"LEFT JOIN pokemon_type " +
				"ON pokemon.id = pokemon_type.pokemon_fk " +
				"LEFT JOIN type " +
				"ON pokemon_type.type_fk = type.id " +
				"GROUP BY pokemon.id " +
				"ORDER BY pokemon.id;",
			new PokemonMapper()
		);
	}

	private class PokemonMapper implements RowMapper<Pokemon> {

		public Pokemon mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Pokemon(
				resultSet.getInt("id"),
				resultSet.getString("name"),
				resultSet.getString("types"),
				null,
				null
			);
		}
	}
}
