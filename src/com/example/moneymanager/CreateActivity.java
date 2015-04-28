package com.example.moneymanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Activity implements OnClickListener 
{
	private EditText et1,et2,et3;
	Button next;
	String str;
	String EMAIL_PATTERN;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		et1 = (EditText)findViewById(R.id.create_editText1);
		et2 = (EditText)findViewById(R.id.create_editText2);
		et3 = (EditText)findViewById(R.id.create_editText3);
		next = (Button)findViewById(R.id.create_button);		
		next.setOnClickListener(this);
		et3.setOnClickListener(this);
		EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	}
		
	@Override
	public void onClick(View v) 
	{
		
		// click for button
		if(v.getId()== R.id.create_button)
		{				
			if(et1.getText().toString().equals("")||et2.getText().toString().equals("")||et3.getText().toString().equals(""))
			{
				Toast.makeText(this, "Can't LEAVE blank fields", Toast.LENGTH_LONG).show();
			}
			else if(!et2.getText().toString().matches(EMAIL_PATTERN))				
			{
				Toast.makeText(this, "Email Address Is Not Properly", Toast.LENGTH_LONG).show();
			}
			else
			{				
				SharedPreferences preferences = getSharedPreferences("wallet", 0);
				Editor editor = preferences.edit();
				editor.putString("name", et1.getText().toString());
				editor.putString("email", et2.getText().toString());
				editor.putString("currency", et3.getText().toString());
				editor.putString("symbol", str);				
				editor.commit();
				Intent intent = new Intent(this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
		
		// click event for edit text 
		if(v.getId()==R.id.create_editText3)
		{			
			Intent intent = new Intent(this,CurrencyList.class);
			startActivityForResult(intent, 1);
		}
	}
	
	
	//for retrun from activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);		
		et3.setText(data.getStringExtra("currency"));
		str = data.getStringExtra("symbol");				
	}
}
