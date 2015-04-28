package com.example.moneymanager;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import com.example.moneymanager.CustomSelect.holder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custom_view extends ArrayAdapter<Expense>
{
	Activity activity;
	int res;
	ArrayList<Expense> data = new ArrayList<Expense>();
	
	public Custom_view(Activity context, int resource, ArrayList<Expense> data)
	{
		super(context, resource, data);
		activity=context;
		res = resource;
		this.data = data;
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
			name.t1 = (TextView)v.findViewById(R.id.custom_view_text1);
			name.t2 = (TextView)v.findViewById(R.id.custom_view_text2);
			name.t3 = (TextView)v.findViewById(R.id.custom_view_text3);
			name.t4 = (TextView)v.findViewById(R.id.custom_view_text4);				
			name.i1 = (ImageView)v.findViewById(R.id.custom_view_image);
			v.setTag(name);
		}
		holder name = (holder)v.getTag();
		Expense pic = data.get(position);
		// category
		name.t1.setText(pic.getCategory());
		//convert byte to bitmap
		/*//byte[] outImage = pic.getImage();
		ByteArrayInputStream stream = new ByteArrayInputStream(outImage);
		Bitmap bitmap = BitmapFactory.decodeStream(stream);
		name.i1.setImageBitmap(bitmap);
		// set expense
		name.t3.setText(Integer.toString(pic.getExpense()));
		// set note and with		
		if(pic.getNote().equals(""))
		{
			name.t2.setText(pic.getWith());			
		}
		else
		{
			name.t2.setText(pic.getNote());
		}*/
		// set symbol
		SharedPreferences preferences = activity.getSharedPreferences("wallet", 0);
		name.t4.setText(preferences.getString("symbol", "syb"));		
		return v;
	}

	static class holder
	{
		TextView t1,t2,t3,t4;
		ImageView i1;
	}
	

}
