package com.schulze.elena.pokedex.repository;

import com.schulze.elena.pokedex.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
				"trainer.region, " +
				"trainer.id " +
				"FROM trainer, pokemon " +
				"WHERE trainer.id = pokemon.trainer_fk " +
				"AND pokemon.id = " + pokemonId
			,
			new TrainerMapper()
		);
	}

	public Trainer getTrainerByTrainerId(int trainerId) {
		return jdbcTemplate.queryForObject(
			"SELECT " +
				"trainer.name, " +
				"trainer.title, " +
				"trainer.region, " +
				"trainer.id " +
				"FROM trainer " +
				"WHERE trainer.id = " + trainerId
			,
			new TrainerMapper()
		);
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
