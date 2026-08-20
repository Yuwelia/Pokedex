package com.schulze.elena.pokedex.service;

import com.schulze.elena.pokedex.model.Trainer;
import com.schulze.elena.pokedex.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;


	public List<Trainer> getTrainerList() {

		List<Trainer> trainerList = trainerRepository.listAll();

		setPokemonList(trainerList);

		return trainerList;
	}

	public Trainer getTrainer(int trainerId) {
		return trainerRepository.getTrainerByTrainerId(trainerId);
	}

	private void setPokemonList(List<Trainer> trainerList) {
		for (Trainer trainer : trainerList) {
			trainerRepository.getPokemonListForTrainer(trainer);
		}
	}
}
