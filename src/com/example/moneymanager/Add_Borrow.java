package com.example.moneymanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Borrow extends Activity implements OnClickListener
{
	EditText name,amount;
	Button add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__borrow);
		
		name=(EditText)findViewById(R.id.editText1Borrow);
		amount=(EditText)findViewById(R.id.editText2Borrow);
		add=(Button)findViewById(R.id.buttonborrow);
		add.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View v)
	{
		if(v.getId()==R.id.buttonborrow)
		{
			String name1=name.getText().toString();
			String amount1=amount.getText().toString();
			String old_value;
			double value;
			if(name1.equals("") && amount1.equals(""))
			{
				Toast.makeText(this, "enter name and amount",Toast.LENGTH_LONG).show();
			}
			else
			{
				SharedPreferences preferences = getSharedPreferences("Income", 0);
				old_value = preferences.getString("old_income", "0.00");
				value = Double.parseDouble(old_value)+Double.parseDouble(amount1);
				Editor editor = preferences.edit();
				editor.putString("new_income", amount1);
				editor.putString("old_income", Double.toString(value));
				editor.commit();
				Intent intent = new Intent(Add_Borrow.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
		
	}

}
