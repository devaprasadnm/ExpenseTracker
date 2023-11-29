package com.web.bean;

public class Expense {
	private int id;
	private String userid;
	private String category;
	private String description;
	private double amount;
	private String day;
	private String month;
	private String year;
	public Expense(int id, String userid, String category, String description, double amount, String day, String month,
			String year) {
		super();
		this.id = id;
		this.userid = userid;
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
	
}
