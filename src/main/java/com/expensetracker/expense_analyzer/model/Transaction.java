package com.expensetracker.expense_analyzer.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String type;
	@Positive
	private double amount;
	private String category;
	
	@NotNull
	private LocalDate date;
	private String paymentMethod;
	private String description;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) { this.id = id; }
	
	public String getType() {
		return type;
	}
	
	public void setType(String Type) {
		this.type=Type;
	}
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double Amount) {
		this.amount=Amount;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String Category) {
		this.category=Category;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate Date) {
		this.date=Date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String Description) {
		this.description=Description;
	}
	public String getPaymentMethod() { 
	    return paymentMethod; 
	}

	public void setPaymentMethod(String paymentMethod) { 
	    this.paymentMethod = paymentMethod; 
	}

}
