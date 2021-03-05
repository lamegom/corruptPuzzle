package com.divneg.storage;

import game.div.cookiecounter.GameActivity;

import com.facebook.android.friendsmash.FriendSmashApplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Config {

	static FriendSmashApplication application = (FriendSmashApplication) GameActivity.getInstance().getApplication();
	// Cookie making rate
	public static double COOKIERATE = application.getCoins();
	public static double COOKIES = application.getBombs();
//	public static double COOKIERATE = Double.MIN_VALUE;
//	public static double COOKIES = Double.MIN_VALUE;
	
	// The Item Names
	public static String 	ITEM1 = "CASA";
   public static String      ITEM2 = "VIA PÚBLICA";
   public static String      ITEM3 = "DROGARIA";
   public static String      ITEM4 = "ACADEMIA";
	public static String 	ITEM5 = "CINEMA";
    public static String     ITEM6 = "ELEVADOR";
    public static String     ITEM7 = "BANCO";
	public static String 	ITEM8 = "TRASPORTE PÚBLICO";
	public static String 	ITEM9 = "HOSPITAL";
//	public static String      ITEM10 = "2011.Escândalo no Esporte";
//	public static String      ITEM11 = "2011.Escândalo em Cidades";
//	public static String     ITEM12 = "2011.Escândalo no Turismo";
//	public static String ITEM13 = "Falsifying signatures";
//	public static String ITEM14 = "Create links with terrorists";
//	public static String ITEM15 = "Exit the bar without paying";
//	public static String ITEM16 = "Finance churches for financial use";
//	public static String ITEM17 = "Borrowing and not return";
//	public static String ITEM18 = "Take advantage of the weakest";
//	public static String ITEM19 = "Blaming someone else for what he did";
//	public static String ITEM20 = "Conspiracy";
//	public static String ITEM21 = "Simulate a disease";
//	public static String ITEM22 = "Overcoming red light";
//	public static String ITEM23 = "Install a money laundering scheme";
//	public static String ITEM24 = "Install a forgery scheme";
//	public static String ITEM25 = "Authorities of Blackmail";

	public static int ITEM1NUM = (int) Double.parseDouble(getData("Cursor"));
	public static int ITEM2NUM = (int) Double.parseDouble(getData("Grandma"));
	public static int ITEM3NUM = (int) Double.parseDouble(getData("Farm"));
	public static int ITEM4NUM = (int) Double.parseDouble(getData("Factory"));
	public static int ITEM5NUM = (int) Double.parseDouble(getData("Mine"));
	public static int ITEM6NUM = (int) Double.parseDouble(getData("ShipMent"));
	public static int ITEM7NUM = (int) Double.parseDouble(getData("Alchemy Lab"));
	public static int ITEM8NUM = (int) Double.parseDouble(getData("Portal"));
	public static int ITEM9NUM = (int) Double.parseDouble(getData("Time Machine"));
	public static int ITEM10NUM = (int) Double.parseDouble(getData("Antimatter Condenser"));
	public static int ITEM11NUM = (int) Double.parseDouble(getData("Prism"));


	public static final double ITEM1COSTINITIAL = 15.0;
	public static final double ITEM2COSTINITIAL = 100;
	public static final double ITEM3COSTINITIAL = 1100;
	public static final double ITEM4COSTINITIAL = 120000;
	public static final double ITEM5COSTINITIAL = 1300000;
	public static final double ITEM6COSTINITIAL = 140000000;
	public static final double ITEM7COSTINITIAL = 200000000000d;
	public static final double ITEM8COSTINITIAL = 3300000000000d;
	public static final double ITEM9COSTINITIAL = 51000000000000d;
	public static final double ITEM10COSTINITIAL = 75000000000000d;
	public static final double ITEM11COSTINITIAL = 1000000000000000d;

	public static double cumulativeCost(double baseCost, int qtd){

		double result = ( baseCost * Math.pow(1.15, qtd))/ 0.15;
		return result;

	}

	public static double  getCost(String item){

		double baseCost = 0;
		int qtd = 0;

		if(item.equals("ITEM1COST")){
			baseCost = ITEM1COSTINITIAL;
			qtd = ITEM1NUM;
		}

		if(item.equals("ITEM2COST")){
			baseCost = ITEM2COSTINITIAL;
			qtd = ITEM2NUM;
		}

		if(item.equals("ITEM3COST")){
			baseCost = ITEM3COSTINITIAL;
			qtd = ITEM3NUM;
		}

		if(item.equals("ITEM4COST")){
			baseCost = ITEM4COSTINITIAL;
			qtd = ITEM4NUM;
		}

		if(item.equals("ITEM5COST")){
			baseCost = ITEM5COSTINITIAL;
			qtd = ITEM5NUM;
		}

		if(item.equals("ITEM6COST")){
			baseCost = ITEM6COSTINITIAL;
			qtd = ITEM6NUM;
		}

		if(item.equals("ITEM7COST")){
			baseCost = ITEM7COSTINITIAL;
			qtd = ITEM7NUM;
		}

		if(item.equals("ITEM8COST")){
			baseCost = ITEM8COSTINITIAL;
			qtd = ITEM8NUM;
		}

		if(item.equals("ITEM9COST")){
			baseCost = ITEM9COSTINITIAL;
			qtd = ITEM9NUM;
		}

		if(item.equals("ITEM10COST")){
			baseCost = ITEM10COSTINITIAL;
			qtd = ITEM10NUM;
		}

		if(item.equals("ITEM11COST")){
			baseCost = ITEM11COSTINITIAL;
			qtd = ITEM11NUM;
		}

		double result = baseCost;

		if(qtd > 0) {
			result = cumulativeCost(baseCost, qtd);
		}

		BigDecimal finalCost = new BigDecimal(result);
		return finalCost.setScale(2, RoundingMode.DOWN).doubleValue();

	}

	public static void  updateQtd(){

		ITEM1NUM = (int) Double.parseDouble(getData("Cursor"));
		ITEM2NUM = (int) Double.parseDouble(getData("Grandma"));
		ITEM3NUM = (int) Double.parseDouble(getData("Farm"));
		ITEM4NUM = (int) Double.parseDouble(getData("Factory"));
		ITEM5NUM = (int) Double.parseDouble(getData("Mine"));
		ITEM6NUM = (int) Double.parseDouble(getData("ShipMent"));
		ITEM7NUM = (int) Double.parseDouble(getData("Alchemy Lab"));
		ITEM8NUM = (int) Double.parseDouble(getData("Portal"));
		ITEM9NUM = (int) Double.parseDouble(getData("Time Machine"));
		ITEM10NUM = (int) Double.parseDouble(getData("Antimatter Condenser"));
		ITEM11NUM = (int) Double.parseDouble(getData("Prism"));


	}


	public static void fillData(String id, String value) {

			SharedPreferences prefs = GameActivity.getInstance()
					.getSharedPreferences(
							GameActivity.getInstance().getApplicationContext()
									.getPackageName(), Context.MODE_PRIVATE);
			prefs.edit().putString(id, value).apply();



	}
	
	public final static boolean SAVED_GAME = false;
	

	public static String getData(String id) {

		
			SharedPreferences prefs = GameActivity.getInstance()
		
				.getSharedPreferences(
						GameActivity.getInstance().getApplicationContext()
								.getPackageName(), Context.MODE_PRIVATE);


				return prefs.getString(id, "0");


	}

	public static String getCookieCount() {


		SharedPreferences prefs = GameActivity.getInstance()

				.getSharedPreferences(
						GameActivity.getInstance().getApplicationContext()
								.getPackageName(), Context.MODE_PRIVATE);

//				SharedPreferences.Editor editor = prefs.edit();
//				editor.clear();
//				editor.commit(); // commit changes


		return prefs.getString("cookiecount", "0.0");


	}
	
	public static String getCookieRate() {


				SharedPreferences prefs = GameActivity.getInstance()
			
				.getSharedPreferences(
						GameActivity.getInstance().getApplicationContext()
								.getPackageName(), Context.MODE_PRIVATE);

		return prefs.getString("cookierate", "0.0");


	}


}
