package usu.edeaghe.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usu.edeaghe.expensetracker.exceptions.ResourceNotFoundException;
import usu.edeaghe.expensetracker.model.Expense;
import usu.edeaghe.expensetracker.repository.ExpenseRepository;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;


    /**
     * Getting all expenses
     */
    @GetMapping()
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    /**
     * Getting single employee
     */
    public ResponseEntity<Expense> getExpenseById(@PathVariable int id){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with id: " + id + " does not exits"));

        return ResponseEntity.ok(expense);
    }

    /**
     * Creating a new expense
     */
    @PostMapping()
    public Expense createExpense(@RequestBody Expense expense){
        return expenseRepository.save(expense);
    }
}
