package com.example.moneymanager;

import java.io.ByteArrayOutputStream;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class AddCategoryActivity extends Activity
{
	ImageButton i_btn1;
	EditText editText;
	int i=R.drawable.select;
	byte[] img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_category);		
		i_btn1 = (ImageButton)findViewById(R.id.add_category_image_btn)	;
		editText = (EditText)findViewById(R.id.add_category_e1);
	}
	public void Next(View view)
	{
		Intent intent = new Intent(this, SelectIconActivity.class);
		startActivityForResult(intent, 1);	
	}
	
	public void click(View v)
	{		
		String str = editText.getText().toString();
		if(str.equals(""))
		{
			Toast.makeText(this, "Can't LEAVE blank field", Toast.LENGTH_LONG).show();
		}
		else
		{
			MainActivity main = new MainActivity();
			main.finish();
			
			// enter image into database
			Bitmap b=BitmapFactory.decodeResource(getResources(), i);
	        ByteArrayOutputStream bos=new ByteArrayOutputStream();
	        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
	        img=bos.toByteArray();// to change into byte
			// Create category class Reference 
	        Category category = new Category(str, img);
			// DBHelper class
	        DBHelper helper = new DBHelper(this);
			helper.insertData(category);
			i_btn1.setImageResource(R.drawable.select);
			editText.setText("");
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			android.content.Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		i = data.getIntExtra("image",R.drawable.select);
		i_btn1.setImageResource(i);
		
	}
	
	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	

}
