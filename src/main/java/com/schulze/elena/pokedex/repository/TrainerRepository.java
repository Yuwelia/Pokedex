package com.schulze.elena.pokedex.repository;

import com.schulze.elena.pokedex.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TrainerRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Trainer getTrainer(int pokemonId) {

		return jdbcTemplate.queryForObject(
			"SELECT " +
			"trainer.name, " +
			"trainer.title, " +
			"trainer.region " +
			"FROM trainer, pokemon " +
			"WHERE trainer.id = pokemon.trainer_fk " +
			"AND pokemon.id = " + pokemonId
			,
			new TrainerRepository.TrainerMapper()
		);
	}

//	public List<String> getTrainerPokemonList() {
//
//	}

	private class TrainerMapper implements RowMapper<Trainer> {

		public Trainer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Trainer(
				resultSet.getString("name"),
				resultSet.getString("title"),
				resultSet.getString("region")
			);
		}
	}
}
