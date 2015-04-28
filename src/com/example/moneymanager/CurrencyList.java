package com.example.moneymanager;
import java.util.Currency;
import java.util.Locale;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class CurrencyList extends Activity implements OnItemClickListener
{
	ListView list;
	AutoCompleteTextView mt1;
	String data1[] = {"China","Hong Kong","India","Japan","Pakisthan","Rusia","Singapur","South africa","United Kindom","United State"};
	String data2[] = {"CNY","HKD","INR","JPY","PKR","RUR","SGD","ZAR","GBP","USD"};
	String data3[] = new String[10]; // = {"CNY","$","Rs","JPY","Rs","rub.","S$","R","&","$"};
    Custom_crrncy array;
    ArrayAdapter<String> adapter;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        list = (ListView)findViewById(R.id.currncy_list);
        getdata();
        array = new Custom_crrncy(this, R.layout.crrncy_layout, data1, data2);
        list.setAdapter(array);
        mt1 = (AutoCompleteTextView)findViewById(R.id.currncy_auto);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data1);
        mt1.setAdapter(adapter);
        mt1.setThreshold(2);
        list.setOnItemClickListener(this);
    }
	
	private void getdata()
	{
		data3[0] = Utils.getCurrencySymbol(Currency.getInstance("CNY").getCurrencyCode());
		data3[1] = Utils.getCurrencySymbol(Currency.getInstance("HKD").getCurrencyCode());
		data3[2] = Utils.getCurrencySymbol(Currency.getInstance("INR").getCurrencyCode());
		data3[3] = Utils.getCurrencySymbol(Currency.getInstance("JPY").getCurrencyCode());
		data3[4] = "Rs";
		data3[5] = Utils.getCurrencySymbol(Currency.getInstance("RUB").getCurrencyCode());
		data3[6] = Utils.getCurrencySymbol(Currency.getInstance("SGD").getCurrencyCode());
		data3[7] = Utils.getCurrencySymbol(Currency.getInstance("ZAR").getCurrencyCode());
		data3[8] = Utils.getCurrencySymbol(Currency.getInstance(Locale.UK).getCurrencyCode());
		data3[9] = Utils.getCurrencySymbol(Currency.getInstance(Locale.US).getCurrencyCode());
		
		 
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		Intent intent = new Intent();
		intent.putExtra("currency", data2[arg2]);
		intent.putExtra("symbol", data3[arg2]);
		setResult(RESULT_OK,intent);
		finish();
	}
	
	@Override
	public void onBackPressed() 
	{
		Intent intent = new Intent(this, CreateActivity.class);
		startActivity(intent);
		finish();
		super.onBackPressed();
	}
}
