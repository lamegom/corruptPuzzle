/**
 * Copyright 2012 Facebook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facebook.android.friendsmash;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.divneg.storage.Config;
import com.divneg.storage.GameScore;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.parse.FindCallback;
//import com.parse.Parse;
//import com.parse.ParseACL;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;
//import com.parse.SaveCallback;
//import com.facebook.Request;
//import com.facebook.Response;
//import com.facebook.Session;
//import com.facebook.SessionState;
//import com.facebook.UiLifecycleHelper;
//import com.facebook.model.GraphUser;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract.Profile;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import game.div.cookiecounter.AndroidLauncher;
import game.div.cookiecounter.GameActivity;
import game.div.corruptpuzzle.R;

/**
 *  Use a custom Application class to pass state data between Activities.
 */
public class FriendSmashApplication extends Application {

	/* Static Attributes */
	
	// Tag used when logging all messages with the same tag (e.g. for demoing purposes)
	static final String TAG = "FriendSmash";
	
	// Switch between the non-social and social Facebook versions of the game
	static final boolean IS_SOCIAL = true;

	
	/* Friend Smash application attributes */
	
	// Player inventory
	public static int NEW_USER_BOMBS = 0;
	public static int NEW_USER_COINS = 0;
	public static int NUM_BOMBS_ALLOWED_IN_GAME = 3;
	private int score = 0;
	private double bombs = 0;
	private double coins = 0;
	private int coinsCollected = 0;
	
	/* Facebook application attributes */

	// Logged in status of the user
	private boolean loggedIn = false;
	private static final String LOGGED_IN_KEY = "logged_in";
	
	private String fbAppID = null;
	
	// Current logged in FB user and key for saving/restoring during the Activity lifecycle
	private Profile currentFBUser;
	private static final String CURRENT_FB_USER_KEY = "current_fb_user";
	
	// List of the logged in user's friends and key for saving/restoring during the Activity lifecycle
//	private List<GraphUser> friends;
	
	// List of friends the user can invite (have not installed the app).
	private List<JSONObject> invitableFriends;
	
	private static final String FRIENDS_KEY = "friends";
		
	// ID of the last friend smashed (linked to the current score)
	private String lastFriendSmashedID = null;
	
	// Name of the last friend smashed
	private String lastFriendSmashedName = null;
	
	// Check to see whether user has said no when asked to play with friends.
	private boolean hasDeniedFriendPermission = false;
	
	// List of ordered ScoreboardEntry objects in order from highest to lowest score to
	// be shown in the ScoreboardFragment
	private ArrayList<ScoreboardEntry> scoreboardEntriesList = null;
	
	// FacebookRequestError to show when the GameFragment closes
//	private FacebookRequestError gameFragmentFBRequestError = null;
		

	/* Friend Smash application attribute getters & setters */
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getBombs() {

		bombs = Double.parseDouble(Config.getCookieCount());

		return bombs;
	}

	public void setBombs(double bombs) {

		this.bombs = bombs;
		Config.fillData("cookiecount", bombs + "");

	}

	public double getCoins() {

		coins = Double.parseDouble(Config.getCookieRate());
		return coins;
	}

	public void setCoins(double coins) {

		this.coins = coins;
		Config.fillData("cookierate", coins + "");
	}
	
	public int getCoinsCollected() {
		return coinsCollected;
	}

	public void setCoinsCollected(int coinsCollected) {
		this.coinsCollected = coinsCollected;		
	}
	
	/* Facebook attribute getters & setters */
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
		if (!loggedIn) {
			// If the user is logged out, reset the score and nullify all the logged-in user's values
			setScore(-1);
//			setCurrentFBUser(null);
//        	setFriends(null);
        	setLastFriendSmashedID(null);
        	setScoreboardEntriesList(null);
		}
	}

	public Profile getCurrentFBUser() {
		return currentFBUser;
	}
//
	public void setCurrentFBUser(Profile currentFBUser) {
		this.currentFBUser = currentFBUser;
	}
//
//	public List<GraphUser> getFriends() {
//		return friends;
//	}
	
	// Method to get the list of friends in an ArrayList<String> where each entry
	// is an inner JSON objects of each friend represented as a string - used for
	// saving/restoring each friend during the Activity lifecycle
	public ArrayList<String> getFriendsAsArrayListOfStrings() {
		ArrayList<String> friendsAsArrayListOfStrings = new ArrayList<String>();
		
//		Iterator<GraphUser> friendsIterator = friends.iterator();
//		while (friendsIterator.hasNext()) {
//			friendsAsArrayListOfStrings.add(friendsIterator.next().getInnerJSONObject().toString());
//		}
		
		return friendsAsArrayListOfStrings;
	}
	
//	public GraphUser getFriend(int index) {
//		if (friends != null && friends.size() > index) {
//			return friends.get(index);
//		} else {
//			return null;
//		}
//	}
//	
//	public void setFriends(List<GraphUser> friends) {
//		this.friends = friends;
//	}

	public String getLastFriendSmashedID() {
		return lastFriendSmashedID;
	}

	public void setLastFriendSmashedID(String lastFriendSmashedID) {
		this.lastFriendSmashedID = lastFriendSmashedID;
	}
	
	public String getLastFriendSmashedName() {
		return lastFriendSmashedName;
	}

	public void setLastFriendSmashedName(String lastFriendSmashedName) {
		this.lastFriendSmashedName = lastFriendSmashedName;
	}
	
	public boolean hasDeniedFriendPermission() {
		return hasDeniedFriendPermission;
	}

	public void setHasDeniedFriendPermission(boolean hasDeniedFriendPermission) {
		this.hasDeniedFriendPermission = hasDeniedFriendPermission;
	}

	public ArrayList<ScoreboardEntry> getScoreboardEntriesList() {
		return scoreboardEntriesList;
	}

	public void setScoreboardEntriesList(ArrayList<ScoreboardEntry> scoreboardEntriesList) {
		this.scoreboardEntriesList = scoreboardEntriesList;
	}

//	public FacebookRequestError getGameFragmentFBRequestError() {
//		return gameFragmentFBRequestError;
//	}
//
//	public void setGameFragmentFBRequestError(FacebookRequestError gameFragmentFBRequestError) {
//		this.gameFragmentFBRequestError = gameFragmentFBRequestError;
//	}

	public static String getLoggedInKey() {
		return LOGGED_IN_KEY;
	}

	public String getFBAppID() {
		return fbAppID;
	}

	public static String getCurrentFbUserKey() {
		return CURRENT_FB_USER_KEY;
	}

	public static String getFriendsKey() {
		return FRIENDS_KEY;
	}
	
	public List<JSONObject> getInvitableFriends() {
		return invitableFriends;
	}

	public void setInvitableFriends(List<JSONObject> invitableFriends) {
		this.invitableFriends = invitableFriends;
	}
	
	private GameScore gameScore;

	public void saveInventory() {
//		SharedPreferences prefs = getApplicationContext().getSharedPreferences(getApplicationContext()
//
//				.getPackageName(), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putFloat("cookie", (float) getBombs());
//        editor.putFloat("cookieRate", (float) getCoins());
//        editor.putLong("lastSavedTime", System.currentTimeMillis());
//        editor.commit();	
		
		
		
//		ParseUser user = ParseUser.getCurrentUser();
//
//        // Store data to Parse too.
//        if (user != null) {
//        	Log.d("DEBUG", "saveInventory.." + user.getObjectId());
//
//
//        	gameScore = new GameScore();
//
//
//        	gameScore.setCookie(""+getBombs());
//
//        	gameScore.setCookieRate(""+getCoins());
//
//        	gameScore.setITEM1(""+Config.ITEM1NUM);
//
//
//        	gameScore.setITEM2(""+Config.ITEM2NUM);
//
//
//        	gameScore.setITEM3(""+Config.ITEM3NUM);
//
//
//        	gameScore.setITEM4(""+Config.ITEM4NUM);
//
//
//        	gameScore.setITEM5(""+Config.ITEM5NUM);
//
//
//        	gameScore.setITEM6(""+Config.ITEM6NUM);
//
//
//        	gameScore.setITEM7(""+Config.ITEM7NUM);
//
//
//        	gameScore.setITEM8(""+Config.ITEM8NUM);
//
//
//        	gameScore.setITEM9(""+Config.ITEM9NUM);
//
//
//        	gameScore.setITEM10(""+Config.ITEM10NUM);
//
//
//        	gameScore.setITEM11(""+Config.ITEM11NUM);
//
//        	gameScore.setUser(user);
//
//        	gameScore.saveInBackground(
//					new SaveCallback() {
//
//						@Override
//						public void done(ParseException e) {
//
//							if (e == null) {
//								Toast.makeText(getApplicationContext(),
//										"getCookie: " +  gameScore.getCookie(),
//										Toast.LENGTH_SHORT).show();
//								Toast.makeText(getApplicationContext(),
//										"getCookieRate: " +  gameScore.getCookieRate(),
//										Toast.LENGTH_SHORT).show();
//								Toast.makeText(getApplicationContext(),
//										"getITEM1: " +  gameScore.getITEM1(),
//										Toast.LENGTH_SHORT).show();
//							} else {
//								Toast.makeText(getApplicationContext(),
//										"Error saving: " + e.getMessage(),
//										Toast.LENGTH_LONG).show();
//
//								e.printStackTrace();
//							}
//						}
//
//					});
//
//
//        }
	}
	
	
	/*
	 * The logic here is to check if we're connected to Parse. If we are, accept the data
	 * there as the authoritative source of data. If we are not connected, then look for
	 * data that is stored locally. If that doesn't exist, then use some default values.
	 * 
	 * In your own project, you may want to have more sophisticated conflict resolution. 
	 * For example, you may want to use lastSavedTime as a timestamp that could be 
	 * compared to the timestamp of data pulled from Parse and then use whichever data
	 * was the most recent. You may want to do this if you want support offline gaming. 
	 * 
	 */
	public void loadInventory() {
		
		
//		ParseUser user = ParseUser.getCurrentUser();
////
////        // Store data to Parse too.
//        if (user != null) {
//
//        	Log.d("DEBUG", "loadInventory.." + user.getObjectId());
//
//
//
//
//			ParseQuery<GameScore> query = GameScore.getQuery();
//			query.whereEqualTo("user", user);
//			query.orderByDescending("createdAt");
//			query.findInBackground(new FindCallback<GameScore>() {
//			     public void done(List<GameScore> objects, ParseException e) {
//			         if (e == null) {
//			        	if(objects.size()>0){
//			        	 // The query was successful.
//		    		    	Log.d("DEBUG", "gameScore return.." + objects.get(0));
//		        	    	fillUpGameScore(objects.get(0));
//			        	}
//			         } else {
//			        	 Log.d("DEBUG", "gameScore error.." + e);
//			         }
//			     }
//			 }
//
//
//			);
//
//
//
//
//
//        }

	}
	
	public void fillUpGameScore(GameScore gameScore){
//		Log.d("DEBUG", "cookie --> " + gameScore.getCookie());
//		Log.d("DEBUG", "getCookieRate --> " + gameScore.getCookieRate());
//		setBombs(Double.valueOf(gameScore.getCookie()));
//		setCoins(Double.valueOf(gameScore.getCookieRate()));
//
//		Config.fillData("Cursor", gameScore.getITEM1());
////		Log.d("DEBUG", "Cursor1 --> " + gameScore.getITEM1());
//		Log.d("DEBUG", "Cursor --> " + Config.getData("Cursor"));
//
//		Config.fillData("Grandma", gameScore.getITEM2());
//
//
//		Config.fillData("Farm", gameScore.getITEM3());
//
//
//		Config.fillData("Factory", gameScore.getITEM4());
//
//
//		Config.fillData("Mine", gameScore.getITEM5());
//
//
//		Config.fillData("ShipMent", gameScore.getITEM6());
//
//
//		Config.fillData("Alchemy Lab", gameScore.getITEM7());
//
//
//		Config.fillData("Portal", gameScore.getITEM8());
//
//
//		Config.fillData("Time Machine", gameScore.getITEM9());
//
//
//		Config.fillData("Antimatter Condenser", gameScore.getITEM10());
//
//
//		Config.fillData("Prism", gameScore.getITEM11());
//
//		Config.update();
	}
	

	
	public void onCreate() {
		super.onCreate();
		// Enable Crash Reporting
//		ParseCrashReporting.enable(this);


//		fbAppID = getString(R.string.facebook_app_id);
//		Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
//
//		// add Todo subclass
//		ParseObject.registerSubclass(GameScore.class);
//
//        ParseUser.enableAutomaticUser();
//        ParseACL defaultACL = new ParseACL();
//        ParseACL.setDefaultACL(defaultACL, true);
//        ParseFacebookUtils.initialize(getApplicationContext());
	}




	public static Context getAppContext() {
	      return FriendSmashApplication.context;
	  }
	  
	  private static Context context;
	  
	  public static boolean isLoggedIn2() {
	      return FriendSmashApplication.loggedIn2;
	  }
	  
	  private static boolean loggedIn2;




}
