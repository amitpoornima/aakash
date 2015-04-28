package com.example.moneymanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Income extends Fragment
{
	TextView textView1,textView2;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		View view;		
		String value;
		view = inflater.inflate(R.layout.activity_income, null);		
		textView2 = (TextView)view.findViewById(R.id.income_t2);
		textView1 = (TextView)view.findViewById(R.id.income_t1);
		SharedPreferences preferences = getActivity().getSharedPreferences("Income", 0);
		value = preferences.getString("old_income", "0.00");
		if(value.equals("0.00"))
			textView2.setText(value);
		else
			textView2.setText(value+"0");
		preferences = getActivity().getSharedPreferences("wallet", 0);
		value = preferences.getString("symbol", "");		
		textView1.setText(value);
		return view;
	}

}
