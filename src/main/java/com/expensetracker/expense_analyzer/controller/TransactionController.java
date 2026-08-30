package com.expensetracker.expense_analyzer.controller;

import com.expensetracker.expense_analyzer.model.Transaction;
import com.expensetracker.expense_analyzer.model.Budget;
import com.expensetracker.expense_analyzer.service.expenseservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private expenseservice expenseServ;

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
    @PostMapping("/budgets")
    public Budget setBudget(@RequestBody Budget budget) {
        return expenseServ.saveBudget(budget);
    }

    // 5. Budget planner: checking category limits
    @GetMapping("/budgets/status")
    public String checkBudgetStatus(@RequestParam String category) {
        return expenseServ.checkBudget(category);
    }
}