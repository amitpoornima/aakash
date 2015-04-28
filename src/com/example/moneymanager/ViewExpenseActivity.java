package com.example.moneymanager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ViewExpenseActivity extends Activity implements OnItemClickListener
{
	
	
	
	
	
	ListView li,li2;
	EditText e1,e2;
	ArrayAdapter<StringBuffer> adapter,adapter1;

	DBHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_expense);
		li=(ListView)findViewById(R.id.view_expense_list);
	
		
		db=new DBHelper(this);

		List<StringBuffer> list=db.showexpense();
		
	     adapter = new ArrayAdapter<StringBuffer>(this,android.R.layout.simple_list_item_1, list);
	      li.setAdapter(adapter);
	        
	
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}	

	//for return to main Activity
	@Override
	public void onBackPressed() 
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
	

}
