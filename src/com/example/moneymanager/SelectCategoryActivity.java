package com.example.moneymanager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SelectCategoryActivity extends Activity implements OnItemClickListener
{
	ListView lv1;
	String[] data={"Food&Drink","Transport","Education","Entertainment","Medical","Shopping","Travel","Other"};
	
	ArrayList<Category> array = new ArrayList<Category>();
	CustomSelect adapter;
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_select_category);	
			DBHelper helper = new DBHelper(this);
			List<Category> list = helper.retriveData();
			for(Category ct : list)
			{
				array.add(ct);
			}
					
			adapter = new CustomSelect (this,R.layout.custom_select,array);
			lv1=(ListView)findViewById(R.id.select_category_list);
			lv1.setAdapter(adapter);
			lv1.setOnItemClickListener(this);
		}	


				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) 
				{
					 int id_To_Search = arg2 + 1;					          
					 Intent intent = new Intent();        
					 intent.putExtra("id", id_To_Search);					 
					 setResult(RESULT_OK, intent);
					  finish();
				}
				
			@Override
			public void onBackPressed() 
			{
				Intent intent = new Intent(this, AddExpenseActivity.class);
				startActivity(intent);
				finish();
				super.onBackPressed();
			}	
}
