package com.expensetracker.expense_analyzer.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;
	private String Type;
	@Positive
	private double Amount;
	private String Category;
	
	@NotNull
	private LocalDate Date;
	
	private String Description;
	
	public Long getId() {
		return ID;
	}

	
	public String getType() {
		return Type;
	}
	
	public void setType(String Type) {
		this.Type=Type;
	}
	public double getAmount() {
		return Amount;
	}
	
	public void setAmount(double Amount) {
		this.Amount=Amount;
	}
	
	public String getCategory() {
		return Category;
	}
	public void setCategory(String Category) {
		this.Category=Category;
	}
	
	public LocalDate getDate() {
		return Date;
	}
	
	public void setDate(LocalDate Date) {
		this.Date=Date;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String Description) {
		this.Description=Description;
	}
}
