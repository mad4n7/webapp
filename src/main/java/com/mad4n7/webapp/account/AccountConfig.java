package com.mad4n7.webapp.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository repository) {
        return args -> {
            Account mad4n7 = new Account(
                    "mad4n7",
                    "hey123456",
                    "testing@mad4n7.com",
                    LocalDate.of(1997, Month.MARCH, 5)
            );

            Account jonny = new Account(
                    "jonnybravo",
                    "hey123456",
                    "jonnybravo@mad4n7.com",
                    LocalDate.of(1994, Month.MARCH, 5)
            );

            repository.saveAll(
                    List.of(mad4n7, jonny)
            );
        };
    }
}
