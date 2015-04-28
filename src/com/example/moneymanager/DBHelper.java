package com.example.moneymanager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{	
	
	public static String DATABASE_NAME = "pocketmoney.db";
	public static String TABLE_1 = "expense";
	public static String TABLE_2 = "category";
	public static int DATABASE_VERSION = 1;
	//expense column
	public static String expense_id = "id";
	public static String expense_Select_Category = "Select_Category";	
	public static String expense_Expense = "Expense";
	public static String expense_With = "With";
	public static String expense_Note = "Note";
	public static String expense_Date = "Date";
	public static String expense_image = "Image";
	// category column
	public static String key_id = "id";
	public static String key_name = "Name";
	public static String key_image = "Image";
	
	
	private SQLiteDatabase db;
	public static String TAG = "MyDb";	
	
	public DBHelper(Context context) 
	{
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		Log.i(TAG, "database created");		
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_1 + "(" + expense_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + expense_Select_Category + " TEXT," +  expense_Expense + " INTEGER,"  + expense_Date +  " DATE" + ");";
		db.execSQL(CREATE_CONTACTS_TABLE);
		Log.i(TAG, "expense table created");		
	
		CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_2 + "(" + key_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + key_name + " TEXT," + key_image + " BLOB" + ");";         
		db.execSQL(CREATE_CONTACTS_TABLE);
		Log.i(TAG, "category table created");
		
		
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertData(Expense e)
	{		
		db = getWritableDatabase();
		ContentValues values = new ContentValues();		
		values.put(expense_Select_Category,e.getCategory());
		values.put(expense_Expense, e.getExpense());		
		values.put(expense_Date, e.getDate());
		db.insert(TABLE_1, null, values);
		db.close();
	}

	public void insertData(Category c)
	{		
		db = getWritableDatabase();
		ContentValues values = new ContentValues();		
		values.put(key_name, c.getCategory());
		values.put(key_image, c.getImage());
		db.insert(TABLE_2, null, values);
		db.close();
	}
	
	
	public List<Category> retriveData()
	{
		List<Category> list = new ArrayList<Category>();
		String select = "select * from "+TABLE_2+";";
		db = getWritableDatabase();
		Cursor cursor =db.rawQuery(select, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Category category = new Category();
				category.setCategory(cursor.getString(1));
				category.setImage(cursor.getBlob(2));
				list.add(category);
			}while(cursor.moveToNext());
		}
		db.close();
		return list;
	}
	
	public List<Expense> retriveData1()
	{
		List<Expense> list = new ArrayList<Expense>();
		String select = "select * from "+TABLE_1+";";
		db = getWritableDatabase();
		Cursor cursor =db.rawQuery(select, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Expense e = new Expense();
				e.setCategory(cursor.getString(1));				
		
				e.setExpense(cursor.getInt(3));
				
							
				e.setDate(cursor.getString(6));
				
				list.add(e);
			}while(cursor.moveToNext());
		}
		db.close();
		return list;
	}

	public Category getData(int value) 
	{	
		 db = this.getReadableDatabase();
		 Category cat = new Category();
		 Cursor res =  db.rawQuery( "select * from category where id="+value+"", null );       
		 res.moveToFirst();
		 cat.setCategory(res.getString(1));
		 //cat.setImage(res.getBlob(2));
		 db.close();
		return cat;
	}
	
	public Expense getData1(int value) 
	{	
		 db = this.getReadableDatabase();
		 Expense cat = new Expense();
		 Cursor res =  db.rawQuery( "select * from expense where id="+value+"", null );       
		 res.moveToFirst();
		 cat.setCategory(res.getString(1));
		 
		 cat.setExpense(res.getInt(3));
		 System.out.println(res.getInt(3)+"");
		 cat.setDate(res.getString(6));
		 db.close();
		return cat;
	}

	public Expense getData1()
	{
		
		 db = this.getReadableDatabase();
		 Expense cat = new Expense();
		 Cursor res =  db.rawQuery( "select * from expense", null );       
		 res.moveToFirst();
		 cat.setCategory(res.getString(1));
		 
		 cat.setExpense(res.getInt(3));
		 System.out.println(res.getInt(3)+"");
		 cat.setDate(res.getString(6));
		 db.close();
		return cat;
	}
	
	
	
	
	
	
	
	//retrive  category or expense from table

	public ArrayList<StringBuffer> getCategory() 
	{
		
		ArrayList<StringBuffer> list =new ArrayList<StringBuffer>();
		
		db=getWritableDatabase();
		
		Cursor cursor=db.rawQuery("select * from expenses", null);
		
		if(cursor!=null)
		{
			while(cursor.moveToNext())
			{
				String category=cursor.getString(1);
				String money=cursor.getString(3);
				
				
				StringBuffer sb=new StringBuffer();
				sb.append(category).append("                  ").append(money);
				list.add(sb);
			}
		}
		db.close();
		return list;
	}

	

	public List<StringBuffer> showexpense()
	{
		int max=17;
		ArrayList<StringBuffer> list =new ArrayList<StringBuffer>();
		db=getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from expense", null);
		if(cursor!=null)
		{
			while(cursor.moveToNext())
			{//String Date=cursor.getString(1);
				String category=cursor.getString(1);
				String money=cursor.getString(2);
			int len=category.length();
				int space=max-len;
				for(int i=0;i<space;i++)
				{
					
					category=category+" ";
				}
				StringBuffer sb=new StringBuffer(); 
				sb.append(category).append("                         ").append(money);
				list.add(sb);
			}
		}
		db.close();
		return list;
		
	}
	
	public List<StringBuffer> showexpense1()
	{
		int max=17;
		ArrayList<StringBuffer> list =new ArrayList<StringBuffer>();
		db=getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from expense", null);
		if(cursor!=null)
		{
			while(cursor.moveToNext())
			{//String Date=cursor.getString(1);
				String category=cursor.getString(1);
				String money=cursor.getString(2);
			int len=category.length();
				int space=max-len;
				for(int i=0;i<space;i++)
				{
					
					category=category+" ";
				}
				StringBuffer sb=new StringBuffer(); 
				sb.append(category).append(" ").append(money);
				list.add(sb);
			}
		}
		db.close();
		return list;
		
	}


	public double getMoney()
	{
		
		String str;
		double value=0.0;
		db=getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from expense", null);
		if(cursor!=null)
		{
			while(cursor.moveToNext())
			{//String Date=cursor.getString(1);
			    str=cursor.getString(2);
			    value=value+Double.parseDouble(str);
			    
			}
		}
		db.close();
		return value;
		
	}
	


	
}
