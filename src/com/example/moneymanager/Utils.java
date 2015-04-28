package com.example.moneymanager;

import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

public class Utils 
{
	public static SortedMap<Currency,Locale> currencyLocaleMap;
	
	static
	{
		currencyLocaleMap = new TreeMap<Currency,Locale>(new Comparator<Currency>() {			
			@Override
			public int compare(Currency c1, Currency c2) {
				
				return c1.getCurrencyCode().compareTo(c2.getCurrencyCode());
			}
		});
		
		for(Locale locale: Locale.getAvailableLocales())
		{
			try
			{
				Currency currency = Currency.getInstance(locale);
				currencyLocaleMap.put(currency, locale);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			}
		}
	
	public static String getCurrencySymbol(String currencyCode)
	{
		Currency currency = Currency.getInstance(currencyCode);
		//System.out.println(currencyCode+" :- "+currency.getSymbol(currencyLocaleMap.get(currency)));
		return currency.getSymbol(currencyLocaleMap.get(currency));
	}
}

