package com.example.moneymanager;

import java.io.ByteArrayInputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewSingleExpenseActivity extends Activity 
{
	TextView t1,t2,t3;
	EditText e1,e2,e3,e4;
	ImageView iv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_single_expense);
		init();
		//set values
		SharedPreferences preferences = getSharedPreferences("wallet", 0);
		Expense cat = new Expense();
		Intent intent = getIntent();
		DBHelper hepler = new DBHelper(this);  
	                    
	    		 cat = hepler.getData1();   
	    		// id_To_Update = Value;			    		  
	    	 	    	 
		e1.setText(cat.getCategory());
		e2.setText(Integer.toString(cat.getExpense()));
			
		
	}

	private void init() 
	{
		
		e1 = (EditText)findViewById(R.id.view_expense_edit1);
		e2 = (EditText)findViewById(R.id.view_expense_edit2);
		
	}
	
	@Override
	public void onBackPressed() 
	{
		Intent intent = new Intent(this, ViewExpenseActivity.class);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}

	

}
