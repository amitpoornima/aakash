package com.example.moneymanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class SelectIconActivity extends Activity implements OnItemClickListener 
{
	GridView gridView;	
	Integer img[] = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pic10,R.drawable.pic11,R.drawable.pic12,R.drawable.pic13,R.drawable.pic14,R.drawable.pic15,R.drawable.pic16,R.drawable.pic17,R.drawable.pic18,R.drawable.pic19,R.drawable.pic20,R.drawable.pic21,R.drawable.pic22,R.drawable.pic23,R.drawable.pic24,R.drawable.pic25,R.drawable.pic26,R.drawable.pic27,R.drawable.pic28,R.drawable.pic29,R.drawable.pic30,R.drawable.pic31,R.drawable.pic32,R.drawable.pic33,R.drawable.pic34,R.drawable.pic35,R.drawable.pic36,R.drawable.pic37,R.drawable.pic38,R.drawable.pic39,R.drawable.pic40,R.drawable.pic41,R.drawable.pic42,R.drawable.pic43,R.drawable.pic44,R.drawable.pic45,R.drawable.pic46,R.drawable.pic47};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_icon);
		gridView = (GridView)findViewById(R.id.icon_grid);				
		gridView.setAdapter(new CustomImage(this));
		gridView.setOnItemClickListener(this);
	}	
	
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3)
		{
			Intent intent = new Intent();
			intent.putExtra("image",img[arg2]);
			setResult(RESULT_OK, intent);
			finish();			
		}
		
		@Override
		public void onBackPressed() 
		{
			Intent intent = new Intent(this, AddCategoryActivity.class);
			startActivity(intent);
			finish();
			super.onBackPressed();
		}
	

}
