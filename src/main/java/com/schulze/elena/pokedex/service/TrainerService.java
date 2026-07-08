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

	public Trainer getTrainer(int trainerId) {
		return trainerRepository.getTrainerByTrainerId(trainerId);
	}
}
