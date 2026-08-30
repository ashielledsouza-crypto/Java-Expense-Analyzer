package com.expensetracker.expense_analyzer.repository;
import com.expensetracker.expense_analyzer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface Transactionrepo extends JpaRepository<Transaction,Long>{

	//the time travel feature
	List<Transaction> findByDateBetween(LocalDate startDaate,LocalDate endDate);
	//the return type is a list where the data type is of the table format 
	//for total income/expense 
	List<Transaction> findByType(String Type);
	
    List<Transaction> findByCategoryAndType(String Category, String Type);
    
    List<Transaction> findByTypeAndDateBetween(String type, LocalDate startDate, LocalDate endDate);
    
    List<Transaction> findTop5ByOrderByDateDesc();



}
