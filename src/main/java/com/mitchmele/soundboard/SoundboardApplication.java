package com.mitchmele.soundboard;

import com.mitchmele.soundboard.show.ShowRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {ShowRepository.class})
public class SoundboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoundboardApplication.class, args);
	}

}
