package usu.edeaghe.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usu.edeaghe.expensetracker.exceptions.ResourceNotFoundException;
import usu.edeaghe.expensetracker.model.Expense;
import usu.edeaghe.expensetracker.repository.ExpenseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
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
    @GetMapping("/{id}")
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


    /**
     * Updating an expense
     */
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int id, @RequestBody Expense expenseDetails){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense with id: " + id + " does not exits"));

        expense.setTitle(expenseDetails.getTitle());
        expense.setCategory(expenseDetails.getCategory());
        expense.setAmount(expenseDetails.getAmount());
        expense.setDate(expenseDetails.getDate());

        Expense newExpense = expenseRepository.save(expense);

        return ResponseEntity.ok(newExpense);

    }

    /**
     * Deleting an expense
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteExpense(@PathVariable int id){

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense with id: " + id + " does not exits"));

        expenseRepository.delete(expense);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);


    }

}
