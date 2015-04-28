package com.example.moneymanager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Custom_crrncy extends ArrayAdapter<String>
{	
		Activity activity;
		String[] data1,data2;
		int res;
		public Custom_crrncy(Activity context, int resource, String[] objects,String [] des) {
			super(context, resource, objects);
			activity = context;
			res = resource;
			data1 = objects;
			data2 = des; 
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			View v = convertView;
			if(v==null)
			{
			LayoutInflater inflater = activity.getLayoutInflater();
			holder name = new holder();
			v = inflater.inflate(res, null);
			//Toast.makeText(activity, "get View called...", Toast.LENGTH_SHORT).show();
			name.t1 = (TextView)v.findViewById(R.id.crrncy_lyout_t1);
			name.t2 = (TextView)v.findViewById(R.id.crrncy_lyout_t2);
			v.setTag(name);
			}
			holder name = (holder)v.getTag();
			name.t1.setText(data1[position]);
			name.t2.setText(data2[position]);			
			return v;
			
		}
		
		static class holder
		{
			TextView t1,t2;
		}
}
