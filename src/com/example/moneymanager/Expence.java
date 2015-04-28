package com.example.moneymanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class Expence extends Fragment
{
	TextView textView1,textView2;
	String value;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view;
		view = inflater.inflate(R.layout.activity_expence, null);
		
		textView2 = (TextView)view.findViewById(R.id.expense_t2);
		textView1 = (TextView)view.findViewById(R.id.expense_t1);		
		SharedPreferences preferences = getActivity().getSharedPreferences("Income", 0);
		value = preferences.getString("expense", "0.00");
		if(value.equals("0.00"))
			textView2.setText(value);
		else
			textView2.setText(value+"0");
		
		preferences = getActivity().getSharedPreferences("wallet", 0);
		value = preferences.getString("symbol", "");		
		textView1.setText(value);
		
		
				
		return view;
	}
	public String getExpence() 
	{
		// TODO Auto-generated method stub
		SharedPreferences preferences = getActivity().getSharedPreferences("Income", 0);
		value = preferences.getString("expense", "0.00");
		preferences = getActivity().getSharedPreferences("wallet", 0);
		value = preferences.getString("symbol", "");
		return value;
	}
}
