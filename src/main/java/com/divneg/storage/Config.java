package com.divneg.storage;

import game.div.cookiecounter.GameActivity;
import game.div.cookiecounter.MainMenuScene;

import java.math.BigDecimal;

import com.facebook.android.friendsmash.FriendSmashApplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {

	static FriendSmashApplication application = (FriendSmashApplication) GameActivity.getInstance().getApplication();
	// Cookie making rate
	public static double COOKIERATE = application.getCoins();
	public static double COOKIES = application.getBombs();
//	public static double COOKIERATE = Double.MIN_VALUE;
//	public static double COOKIES = Double.MIN_VALUE;
	
	// The Item Names
	public static String 	ITEM1 = "2011.Palocci consultor";
   public static String      ITEM2 = "2011.Escândalo no Trabalho";
   public static String      ITEM3 = "2011.Escândalo nos Transportes";
   public static String      ITEM4 = "2011.Escândalo na Agricultura";
	public static String 	ITEM5 = "2012.Operação Porto Seguro";
    public static String     ITEM6 = "2012.Escândalo na Pesca";
    public static String     ITEM7 = "2012.Caso Cachoeira";
	public static String 	ITEM8 = "2013.Máfia do ISS";
	public static String 	ITEM9 = "2014.Operação Lava Jato";
	public static String      ITEM10 = "2011.Escândalo no Esporte";
	public static String      ITEM11 = "2011.Escândalo em Cidades";
	public static String     ITEM12 = "2011.Escândalo no Turismo";
	public static String ITEM13 = "Falsifying signatures";
	public static String ITEM14 = "Create links with terrorists";
	public static String ITEM15 = "Exit the bar without paying";
	public static String ITEM16 = "Finance churches for financial use";
	public static String ITEM17 = "Borrowing and not return";
	public static String ITEM18 = "Take advantage of the weakest";
	public static String ITEM19 = "Blaming someone else for what he did";
	public static String ITEM20 = "Conspiracy";
	public static String ITEM21 = "Simulate a disease";
	public static String ITEM22 = "Overcoming red light";
	public static String ITEM23 = "Install a money laundering scheme";
	public static String ITEM24 = "Install a forgery scheme";
	public static String ITEM25 = "Authorities of Blackmail";


	// The Item Numbers

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

	// The Item Cost
	static BigDecimal bd1 = new BigDecimal(Math.round((double) (15d * Math.pow(
			1.15, ITEM1NUM))));

	public static String ITEM1COST = String.valueOf(bd1);
	public static String ITEM2COST = String.valueOf(new BigDecimal(Math
			.round((double) (100d * Math.pow(1.15, ITEM2NUM)))));
	public static String ITEM3COST = String.valueOf(new BigDecimal(Math
			.round((double) (500d * Math.pow(1.15, ITEM3NUM)))));
	public static String ITEM4COST = String.valueOf(new BigDecimal(Math
			.round((double) (3000d * Math.pow(1.15, ITEM4NUM)))));
	public static String ITEM5COST = String.valueOf(new BigDecimal(Math
			.round((double) (10000d * Math.pow(1.15, ITEM5NUM)))));
	public static String ITEM6COST = String.valueOf(new BigDecimal(Math
			.round((double) (40000d * Math.pow(1.15, ITEM6NUM)))));
	public static String ITEM7COST = String.valueOf(new BigDecimal(Math
			.round((double) (200000d * Math.pow(1.15, ITEM7NUM)))));
	public static String ITEM8COST = String.valueOf(new BigDecimal(Math
			.round((double) (1666666d * Math.pow(1.15, ITEM8NUM)))));
	public static String ITEM9COST = String.valueOf(new BigDecimal(Math
			.round((double) (123456789d * Math.pow(1.15, ITEM9NUM)))));
	public static String ITEM10COST = String.valueOf(new BigDecimal(Math
			.round((double) (3999999999d * Math.pow(1.15, ITEM10NUM)))));
	public static String ITEM11COST = String.valueOf(new BigDecimal(Math
			.round((double) (75000000000000d * Math.pow(1.15, ITEM10NUM)))));

	public static void update() {

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

		bd1 = new BigDecimal(Math.round((double) (15d * Math
				.pow(1.15, ITEM1NUM))));

		ITEM1COST = String.valueOf(bd1);
		ITEM2COST = String.valueOf(new BigDecimal(Math
				.round((double) (100d * Math.pow(1.15, ITEM2NUM)))));
		ITEM3COST = String.valueOf(new BigDecimal(Math
				.round((double) (500d * Math.pow(1.15, ITEM3NUM)))));
		ITEM4COST = String.valueOf(new BigDecimal(Math
				.round((double) (3000d * Math.pow(1.15, ITEM4NUM)))));
		ITEM5COST = String.valueOf(new BigDecimal(Math
				.round((double) (10000d * Math.pow(1.15, ITEM5NUM)))));
		ITEM6COST = String.valueOf(new BigDecimal(Math
				.round((double) (40000d * Math.pow(1.15, ITEM6NUM)))));
		ITEM7COST = String.valueOf(new BigDecimal(Math
				.round((double) (200000d * Math.pow(1.15, ITEM7NUM)))));
		ITEM8COST = String.valueOf(new BigDecimal(Math
				.round((double) (1666666d * Math.pow(1.15, ITEM8NUM)))));
		ITEM9COST = String.valueOf(new BigDecimal(Math
				.round((double) (123456789d * Math.pow(1.15, ITEM9NUM)))));
		ITEM10COST = String.valueOf(new BigDecimal(Math
				.round((double) (3999999999d * Math.pow(1.15, ITEM10NUM)))));
		ITEM11COST = String.valueOf(new BigDecimal(Math
				.round((double) (74999999999999d * Math.pow(1.15, ITEM10NUM)))));

		COOKIERATE = application.getCoins();

	}

	public static void fillData(String id, String value) {

			SharedPreferences prefs = GameActivity.getInstance()
					.getSharedPreferences(
							GameActivity.getInstance().getApplicationContext()
									.getPackageName(), Context.MODE_PRIVATE);
			prefs.edit().putString(id, value).apply();

		update();

	}
	
	public final static boolean SAVED_GAME = false;
	

	public static String getData(String id) {

		
			SharedPreferences prefs = GameActivity.getInstance()
		
				.getSharedPreferences(
						GameActivity.getInstance().getApplicationContext()
								.getPackageName(), Context.MODE_PRIVATE);

				return prefs.getString(id, "0");


	}
	
	public static String getCookieRate(String id) {


				SharedPreferences prefs = GameActivity.getInstance()
			
				.getSharedPreferences(
						GameActivity.getInstance().getApplicationContext()
								.getPackageName(), Context.MODE_PRIVATE);

		return prefs.getString(id, "0");


	}

}
