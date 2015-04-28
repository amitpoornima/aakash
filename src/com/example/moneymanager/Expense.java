package com.example.moneymanager;

public class Expense
{
	private String category;
	private int expense;
	private String date;
	
	
	public Expense(String category, int expense, String date) 
	{
		this.category = category;
		this.expense = expense;
		this.date = date;
	}
	public Expense() {
		// TODO Auto-generated constructor stub
	}

	public String getCategory()
	{
		return category;
	}
	
	public int getExpense()
	{
		return expense;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public void setExpense(int expense)
	{
		this.expense = expense;
	}
	
}
