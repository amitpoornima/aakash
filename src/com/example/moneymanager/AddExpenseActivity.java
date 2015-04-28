package com.example.moneymanager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AddExpenseActivity extends Activity implements OnClickListener 
{
	EditText select_category,amount;
    Button  add_expnc_btn;
    TextView Date,name,symbol;
    ImageView iv1;    	
	String date;
	private int year;
	private int month;
	private int day;
	int i=0;
	byte[] img;
	
	String category="";
	
	static final int DATE_DIALOG_ID =999;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		
		
		
		
		
		select_category = (EditText)findViewById(R.id.add_expense_select_category);
		amount = (EditText)findViewById(R.id.add_expense_amount);
		add_expnc_btn = (Button)findViewById(R.id.add_expense_btn);		// button for expense
		Date = (TextView)findViewById(R.id.add_expense_currentdate);
		name = (TextView)findViewById(R.id.add_expense_name);	    
		add_expnc_btn.setOnClickListener(this); // click listener on buttons
		select_category.setOnClickListener(this);
		// set current date on view
		setCurrentDateonView();
		
		Date.setOnClickListener(new OnClickListener()			
			 {			
				@Override
				public void onClick(View arg0) 
				{
					showDialog(DATE_DIALOG_ID);				
				}
			 });	
		
	}	

	// set cuurent set
			public void setCurrentDateonView() 
			{								
				final Calendar c = Calendar.getInstance();
				year = c.get(Calendar.YEAR);
				month = c.get(Calendar.MONTH);
				day = c.get(Calendar.DAY_OF_MONTH);
				
				Date.setText(new StringBuilder()
					.append(month+1).append("-")
					.append(day).append("-").append(year).append(""));	
				date = new StringBuilder().append(year).append("-")
				.append(month+1).append("-")
				.append(day).append("").toString();
		
			}

		
	
	
			@Override
			public void onClick(View v) 
			{
				if(v.getId()==R.id.add_expense_btn)
				{
					String amount1 = (amount.getText().toString());					
					String old_income,old_expense;
					int j = R.drawable.select; 
					double value;
					
					if(amount.getText().toString().equals(""))
					{
						Toast.makeText(this, "Plz Enter Amount", Toast.LENGTH_LONG).show();
					}
					else if(category.equals(""))
					{
						Toast.makeText(this, "Plz Select Category", Toast.LENGTH_LONG).show();
					}
					else
					{   
						//Preference
						SharedPreferences preferences1 = getSharedPreferences("Income", 0);
						old_income = preferences1.getString("old_income", "0.00");
						old_expense = preferences1.getString("expense", "0.00");
						value = Double.parseDouble(old_income)-Double.parseDouble(amount1);
						Editor editor = preferences1.edit();						
						editor.putString("old_income", Double.toString(value));
						value = Double.parseDouble(old_expense)+Double.parseDouble(amount1);
						editor.putString("expense", Double.toString(value));
						editor.commit();
						
						
						int expense = Integer.parseInt(amount1);
						// to add to the database;
						database(category,j,expense);
						
						Intent intent = new Intent(AddExpenseActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					}
				}
				if(v==select_category)
				{								
						Intent intent = new Intent(this,SelectCategoryActivity.class);
						startActivityForResult(intent, 1);					
				}
			}
			protected void onActivityResult(int requestCode, int resultCode, Intent data) 
			{
				super.onActivityResult(requestCode, resultCode, data);
				Category cat = new Category();
				DBHelper hepler = new DBHelper(this);  
			     int Value = data.getIntExtra("id", 0); 
			    	 if(Value>0)
			    	 {                  
			    		 cat = hepler.getData(Value);   
			    		// id_To_Update = Value;			    		  
			    	 }
			    category = cat.getCategory();	 
				select_category.setText(cat.getCategory());
				
				i=1;
			}					
			
			
				private void database(String category, int j, int expense)  
				{	
								
			        Expense epnse = new Expense(category, expense,date);
					// DBHelper class
			        DBHelper helper = new DBHelper(this);
					helper.insertData(epnse);
					select_category.setText(null);
					amount.setText(null);	
				}

		
}		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


