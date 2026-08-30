package com.expensetracker.expense_analyzer.repository;
import com.expensetracker.expense_analyzer.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Budgetrepo extends JpaRepository<Budget, Long> {
    
    // Allows us to check the limit for Food or Rent
    Budget findByCategory(String category);
}