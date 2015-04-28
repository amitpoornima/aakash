package com.example.moneymanager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AddIncomeActivity extends Activity
{
	EditText edit;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_income);
		edit = (EditText)findViewById(R.id.add_income_edit);
		button = (Button)findViewById(R.id.add_income_button);
		button.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				String str = (edit.getText().toString());
				String old_value;
				double value;
				if(str.equals(""))
				{
					Toast.makeText(AddIncomeActivity.this, "Can't Leave Blank Field", Toast.LENGTH_LONG).show();
				}
				else
				{
					SharedPreferences preferences = getSharedPreferences("Income", 0);
					old_value = preferences.getString("old_income", "0.00");
					value = Double.parseDouble(old_value)+Double.parseDouble(str);
					Editor editor = preferences.edit();
					editor.putString("new_income", str);
					editor.putString("old_income", Double.toString(value));
					editor.commit();
					Intent intent = new Intent(AddIncomeActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});	
	}
	
	@Override
	public void onBackPressed() 
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
}
