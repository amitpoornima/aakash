package com.example.moneymanager;


import java.io.ByteArrayOutputStream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainActivity extends FragmentActivity implements TabListener,OnClickListener
{
	Intent intent;
	ViewPager pager;
	ActionBar actionBar;
	Button add_incm_btn,add_expnc_btn,add_cat_btn,view_expnc_btn,Add_Borrow,Send_msg;
	SQLiteDatabase db;
	boolean flag;
	static int i=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences preferences1 = getSharedPreferences("wallet", 0);
		flag = preferences1.getBoolean("flag", true);
		if(flag == true)
		{
			preferences1.edit().putBoolean("flag", false).commit();
			DBHelper helper = new DBHelper(this);
			preDefine(helper);
		}	
		//add income
		add_incm_btn = (Button)findViewById(R.id.H_income);
		add_incm_btn.setOnClickListener(this);
		
		//add expense
		add_expnc_btn = (Button)findViewById(R.id.H_expence);
		add_expnc_btn.setOnClickListener(this);
		
		//add expense
		add_cat_btn = (Button)findViewById(R.id.H_category);
		add_cat_btn.setOnClickListener(this);
				
		//add expense
		view_expnc_btn = (Button)findViewById(R.id.H_view_expence);
		view_expnc_btn.setOnClickListener(this);	
		
		//add borrow
		Add_Borrow = (Button)findViewById(R.id.H_view_expence1);
		Add_Borrow.setOnClickListener(this);
		
		//Send msg
		Send_msg = (Button)findViewById(R.id.H_view_expence12);
		Send_msg.setOnClickListener(this);
				
		
		SharedPreferences preferences = getSharedPreferences("wallet", 0);
		String name = preferences.getString("name", "not found");		
		if(name.equals("not found"))
		{
			intent = new Intent(this, CreateActivity.class);
			startActivity(intent);
			finish();
		}
		
		pager = (ViewPager)findViewById(R.id.Viewpager);
		pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		        | ActionBar.NAVIGATION_MODE_TABS);
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		Tab tab1 = actionBar.newTab();
		tab1.setText("Expence");
		tab1.setTabListener(this);
		
		Tab tab2 = actionBar.newTab();
		tab2.setText("Balance");
		tab2.setTabListener(this);
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);
				
			}			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub				
			}			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}		
	
	class MyAdapter extends FragmentPagerAdapter
	{

		public MyAdapter(FragmentManager fm) 
		{
			super(fm);			
		}

		@Override
		public Fragment getItem(int i) 
		{
			Fragment fragment=null;
			switch (i) {
			case 0:
				fragment = new Expence();
				break;
			case 1:
				fragment = new Income();
				break;

			default:
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return 2;
		}		
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1)
	{
		
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		pager.setCurrentItem(arg0.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View arg0)
	{
		Intent intent;
		switch (arg0.getId())
		{
			case R.id.H_income:
				intent = new Intent(this, AddIncomeActivity.class);
				startActivity(intent);
				finish();
			break;
			
			case R.id.H_expence:
				intent = new Intent(this, AddExpenseActivity.class);
				startActivity(intent);
				finish();
			break;
			
			case R.id.H_category:
				intent = new Intent(this, AddCategoryActivity.class);
				startActivity(intent);
				finish();
			break;
			
			case R.id.H_view_expence:
				intent = new Intent(this, ViewExpenseActivity.class);
				startActivity(intent);
				finish();
			break;
			
			case R.id.H_view_expence1:
				intent = new Intent(this,Add_Borrow.class);
				startActivity(intent);
				finish();
			break;
			case R.id.H_view_expence12:
				intent = new Intent(this,Send_Msg.class);
				startActivity(intent);
				finish();
				

		default:
			break;
		}		
	}
	

	//pre Enter values Function
	public void preDefine(DBHelper help)
	{
		Category cat;
		int i;
		byte[] img;
		DBHelper helper = help;
		//Food & Drink
		i = R.drawable.pic49;
		Bitmap b = BitmapFactory.decodeResource(getResources(), i);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Food & Drink", img);
        helper.insertData(cat);
        
        // Transport
        i = R.drawable.pic53;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();        
        cat = new Category("Transport", img);
        helper.insertData(cat);
        
        // Education
        i = R.drawable.pic55;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Education", img);
        helper.insertData(cat);
        
        // Entertainment
        i = R.drawable.pic48;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Entertainment", img);
        helper.insertData(cat);
        
        // Medical
        i = R.drawable.pic50;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Medical", img);
        helper.insertData(cat);
        
        // Shopping
        i = R.drawable.pic52;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Shopping", img);
        helper.insertData(cat);
        
        // Medical
        i = R.drawable.pic54;
        
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Travel", img);
        helper.insertData(cat);
        
        // Medical
        i = R.drawable.pic51;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Other", img);
        helper.insertData(cat);
	}	
	
	@Override
	public void onBackPressed() 
	{
		finish();	
		super.onBackPressed();
	}
	
}
