package usu.edeaghe.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usu.edeaghe.expensetracker.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
