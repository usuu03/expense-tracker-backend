package usu.edeaghe.expensetracker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import usu.edeaghe.expensetracker.model.Expense;
import usu.edeaghe.expensetracker.repository.ExpenseRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class ExpenseConfig {

    @Bean
    CommandLineRunner commandLineRunner(ExpenseRepository expenseRepository){
        return args -> {
            Expense expense1 = new Expense(
                    "Bus",
                    "Transport",
                    -2.00,
                    Date.valueOf(LocalDate.of(2023, 8, 26))
            );
            Expense expense2 = new Expense(
                    "Petrol",
                    "Transport",
                    -20.20,
                    Date.valueOf(LocalDate.of(2023, 8, 3))
            );
            Expense expense3 = new Expense(
                    "Deposit",
                    "Other",
                    +99.00,
                    Date.valueOf(LocalDate.of(2023, 7, 29))


            );

            expenseRepository.saveAll(List.of(expense1, expense2, expense3));

        };
    }


}
