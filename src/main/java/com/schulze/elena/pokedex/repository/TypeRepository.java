package com.schulze.elena.pokedex.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Repository
public class TypeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public List<String> getStrongAgainstTypes(List<String> typeList) {

		return jdbcTemplate.query(
			"SELECT " +
				"DISTINCT type.name AS types " +
				"FROM strong_against " +
				"JOIN type " +
				"ON strong_against.strong_against_fk = type.id " +
				"WHERE strong_against.type_fk IN( " +
				"SELECT id " +
				"FROM type " +
				"WHERE name IN(" + typeList.stream().map(x -> "?").collect(joining(", ")) + ")); ",
			new TypeMapper(),
			typeList.toArray()
		);
	}

	public List<String> getVulnerableToTypes(List<String> typeList) {
		StringBuilder s = new StringBuilder("?");

		Object[] types = new Object[typeList.size()];

		for (int i = 0; i < typeList.size(); i++) {

			types[i] = typeList.get(i);
			if (i < typeList.size() - 1) {
				s.append(", ?");
			}
		}

		return jdbcTemplate.query(
			"SELECT " +
				"DISTINCT type.name AS types " +
				"FROM vulnerable_to " +
				"JOIN type " +
				"ON vulnerable_to.vulnerable_to_fk = type.id " +
				"WHERE vulnerable_to.type_fk IN( " +
				"SELECT id " +
				"FROM type " +
				"WHERE name IN(" + s + ")); ",
			new TypeMapper(),
			types
		);
	}

	private class TypeMapper implements RowMapper<String> {

		@Override
		public String mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return resultSet.getString("types");
		}
	}
}


