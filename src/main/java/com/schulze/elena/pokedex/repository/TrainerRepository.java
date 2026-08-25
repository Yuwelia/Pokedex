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

	public Trainer getTrainerByPokemonId(int pokemonId) {

		return jdbcTemplate.queryForObject(
			"SELECT " +
				"trainer.name, " +
				"trainer.title, " +
				"trainer.region, " +
				"trainer.id " +
				"FROM trainer, pokemon " +
				"WHERE trainer.id = pokemon.trainer_fk " +
				"AND pokemon.id = ?",
			new TrainerMapper(),
			pokemonId
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
				"WHERE trainer.id = ?",
			new TrainerMapper(),
			trainerId
		);
	}

	public List<Trainer> listAll() {
		return jdbcTemplate.query(
			"SELECT " +
			"trainer.id, " +
			"trainer.name, " +
			"trainer. title, "	+
			"trainer.region " +
			"FROM trainer",
			new TrainerMapper()
		);
	}

	public List<String> getPokemonListForTrainer(Trainer trainer) {
		return null;
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
