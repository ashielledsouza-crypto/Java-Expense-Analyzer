package com.expensetracker.expense_analyzer.service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.expensetracker.expense_analyzer.model.Transaction;
import com.expensetracker.expense_analyzer.model.Budget;
import com.expensetracker.expense_analyzer.repository.Transactionrepo;
import com.expensetracker.expense_analyzer.repository.Budgetrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class expenseservice {
	@Autowired
    private Transactionrepo transactionRepository;
    
    @Autowired
    private Budgetrepo budgetRepository;

    // 1. Adding transactions and income
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // 2. Cashflow, total balance, current savings/expense
    public Map<String, Double> calculateCashflow() {
        List<Transaction> incomes = transactionRepository.findByType("INCOME");
        List<Transaction> expenses = transactionRepository.findByType("EXPENSE");

        double totalIncome = incomes.stream().mapToDouble(Transaction::getAmount).sum();
        double totalExpense = expenses.stream().mapToDouble(Transaction::getAmount).sum();
        
        Map<String, Double> cashflow = new HashMap<>();
        cashflow.put("Total Income", totalIncome);
        cashflow.put("Total Expense", totalExpense);
        cashflow.put("Current Savings", totalIncome - totalExpense);
        
        return cashflow;
    }

    // 3. Time travel (daily, weekly, monthly, yearly wise)
    public List<Transaction> getTransactionsBetweenDates(LocalDate start, LocalDate end) {
        return transactionRepository.findByDateBetween(start, end);
    }

    // 4. Budget planner: setting a budget
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    // 5. Budget planner: checking category limits
    public String checkBudget(String category) {
        Budget budget = budgetRepository.findByCategory(category);
        if (budget == null) {
            return "No budget set for " + category;
        }

        List<Transaction> categoryExpenses = transactionRepository.findByCategoryAndType(category, "EXPENSE");
        double totalSpent = categoryExpenses.stream().mapToDouble(Transaction::getAmount).sum();

        if (totalSpent > budget.getMonthlyLimit()) {
            return "WARNING: You are over budget by ₹" + (totalSpent - budget.getMonthlyLimit());
        }
        return "Safe. Remaining balance: ₹" + (budget.getMonthlyLimit() - totalSpent);
    }



}
