package com.example.demo;

import com.example.demo.main.MainExecute;
import com.example.demo.repository.IArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlaylistApplication  implements CommandLineRunner {
	@Autowired
	private IArtistRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PlaylistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainExecute mainExecute = new MainExecute(repository);
		mainExecute.menu();
	}
}
