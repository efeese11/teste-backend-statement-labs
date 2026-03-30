package com.teste_backend_statement_labs.teste_backend_statement_labs;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingSpotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TesteBackendStatementLabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteBackendStatementLabsApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeParkingSpots(ParkingSpotRepository parkingSpotRepository) {
		return args -> {
			if (parkingSpotRepository.findAll().isEmpty()) { // só cria se não houver vagas
				for (int i = 1; i <= 50; i++) {
					ParkingSpot spot = new ParkingSpot(null,"V" + i, SpotStatus.FREE);
					parkingSpotRepository.save(spot);
				}
				System.out.println("50 vagas de estacionamento criadas com sucesso!");
			} else {
				System.out.println("Vagas já existem, pulando inicialização.");
			}
		};
	}
}
