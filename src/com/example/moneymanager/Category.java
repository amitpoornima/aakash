package com.example.moneymanager;

public class Category
{
 
	private String category;
	private byte[] image;
 
	public Category() 
	{
		
	}
	public Category(String category, byte[] image) 
	{
		this.category = category;
		this.image = image;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public byte[] getImage()
	{
		return image;
	}
	
	public void setCategory(String category)
	{
		this.category =category;
	}
	
	public void setImage(byte[] image)
	{
		this.image = image;
	}	
}
