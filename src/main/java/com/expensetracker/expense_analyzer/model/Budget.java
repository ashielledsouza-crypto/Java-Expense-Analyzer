package com.expensetracker.expense_analyzer.model;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class Budget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique=true)
    private String category;
    @Positive
    private double monthlyLimit;

    public Budget() {}

    public Long getId() { 
    	return id; 
    	}
    public void setId(Long id) {
    	this.id = id;
    	}

    public String getCategory() { 
    	return category; 
    	}
    public void setCategory(String category) { 
    	this.category = category; 
    	}

    public double getMonthlyLimit() { 
    	return monthlyLimit; 
    	}
    public void setMonthlyLimit(double monthlyLimit) {
    	this.monthlyLimit = monthlyLimit;
    	}
}