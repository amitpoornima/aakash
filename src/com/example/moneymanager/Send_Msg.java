package com.example.moneymanager;

import java.util.ArrayList;
import java.util.List;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Send_Msg extends Activity implements OnClickListener
{
	EditText e1,e2;
	Button b1;
	String msg;
    DBHelper db;
    String old_value;
    double value;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send__msg);
		e1=(EditText)findViewById(R.id.msgtxt);
		
		//get income 
		String str="0.0";
		SharedPreferences preferences = getSharedPreferences("Income", 0);
		old_value = preferences.getString("old_income", "0.00");
		value = Double.parseDouble(old_value)+Double.parseDouble(str);
		db=new DBHelper(this);
		//get expense from total expense
		double exp=db.getMoney();
		String sms="Income is  "+value+"   and expense is   "+exp;
		Toast.makeText(this, sms, Toast.LENGTH_LONG).show();
		e1.setText(sms);
		e2=(EditText)findViewById(R.id.mobileno);
		b1=(Button)findViewById(R.id.sendmsg);
		db=new DBHelper(this);
		b1.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View v) 
	{
		
		if(v.getId()==R.id.sendmsg)
		{
			
			try 
			{
				String sms=e1.getText().toString();
				String pno=e2.getText().toString();
				if(pno.equals(""))
				{
					Toast.makeText(this, "please provide no.", Toast.LENGTH_LONG).show();
				}
				
				//Sms manager class call
				else
				{  
				SmsManager smsmanager=SmsManager.getDefault();
				smsmanager.sendTextMessage(pno, null, sms, null, null);
				Intent intent=new Intent(this, MainActivity.class);
				startActivity(intent);
				Toast.makeText(this, "msg sent", Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) 
			{
			
				e.printStackTrace();
			}
		}
		
		
	}


		
}

	

