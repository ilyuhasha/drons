package com.example.demo;

import com.example.demo.models.Dron;
import com.example.demo.repository.DronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private DronRepository dronRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Проверить, есть ли уже дроны в базе данных
		List<Dron> drones = dronRepository.findAll();
		if (drones.size() < 3) {
			for (int i = drones.size(); i < 3; i++) {
				Dron dron = new Dron();
				dron.setStatus("свободен");
				dronRepository.save(dron);
			}
		}
	}
}
