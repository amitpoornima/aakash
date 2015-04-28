package com.example.moneymanager;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomSelect extends ArrayAdapter<Category>
{
	Activity activity;
	int res;
	ArrayList<Category> data = new ArrayList<Category>();

public CustomSelect(Activity context, int resource,
		ArrayList<Category> data)
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
			name.t1 = (TextView)v.findViewById(R.id.custom_select_text);
			name.i1 = (ImageView)v.findViewById(R.id.custom_select_image);
			v.setTag(name);
		}
		holder name = (holder)v.getTag();
		Category pic = data.get(position);
		name.t1.setText(pic.getCategory());
		//convert byte to bitmap
		byte[] outImage = pic.getImage();
		ByteArrayInputStream stream = new ByteArrayInputStream(outImage);
		Bitmap bitmap = BitmapFactory.decodeStream(stream);
		name.i1.setImageBitmap(bitmap);			
		return v;
	}

	static class holder
	{
		TextView t1;
		ImageView i1;
	}	
}
