package com.expensetracker.expense_analyzer.controller;

import com.expensetracker.expense_analyzer.model.Transaction;
import com.expensetracker.expense_analyzer.model.Budget;
import com.expensetracker.expense_analyzer.service.expenseservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.expensetracker.expense_analyzer.repository.Budgetrepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private expenseservice expenseServ;
    
    @Autowired
    private Budgetrepo budgetRepo;

    // 1. Adding transactions and income
    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return expenseServ.saveTransaction(transaction);
    }

    // 2. Cashflow, total balance, current savings
    @GetMapping("/cashflow")
    public Map<String, Double> getCashflow() {
        return expenseServ.calculateCashflow();
    }

    // 3. Time travel (daily, weekly, monthly views)
    @GetMapping("/timetravel")
    public List<Transaction> timeTravel(
            @RequestParam String start, 
            @RequestParam String end) {
        // Converts the String dates from the URL into LocalDate objects
        return expenseServ.getTransactionsBetweenDates(LocalDate.parse(start), LocalDate.parse(end));
    }

    // 4. Budget planner: setting a budget
    @GetMapping("/budgets")
    public List<Budget> getAllBudgets() {
        return budgetRepo.findAll();
    }

    @PostMapping("/budgets")
    public Budget saveOrUpdateBudget(@RequestBody Budget budget) {
        Budget existing = budgetRepo.findByCategory(budget.getCategory());
        
        if (existing != null) {
            // Updated to use your exact variable name
            existing.setMonthlyLimit(budget.getMonthlyLimit());
            return budgetRepo.save(existing);
        }
        
        return budgetRepo.save(budget);
    }

    @DeleteMapping("/budgets/category/{category}")
    public void deleteBudget(@PathVariable String category) {
        // Find the budget by category name and delete it
        Budget existing = budgetRepo.findByCategory(category);
        if (existing != null) {
            budgetRepo.delete(existing);
        }
    }

    
    @GetMapping("/expenses/summary")
    public Map<String, Double> getExpenseSummary() {
        return expenseServ.getExpenseSummary();
    }
    
    @GetMapping("/transactions/recent")
    public List<Transaction> getRecentTransactions() {
        return expenseServ.getRecentTransactions();
    }
    
 // 6. Delete a transaction
    @DeleteMapping("/transactions/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        return expenseServ.deleteTransaction(id);
    }

    // 7. Update a transaction
    @PutMapping("/transactions/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return expenseServ.updateTransaction(id, transaction);
    }
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return expenseServ.getAllTransactions(); // Correctly calls the Service
    }
    
}