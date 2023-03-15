package com.blank04.universitycms;

import com.blank04.universitycms.database.DatabaseFiller;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityCmsApplication implements ApplicationRunner {

	private final DatabaseFiller databaseFiller;

	public UniversityCmsApplication(DatabaseFiller databaseFiller) {
		this.databaseFiller = databaseFiller;
	}

	public static void main(String[] args) {
		SpringApplication.run(UniversityCmsApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		databaseFiller.fillDatabase();
	}
}
