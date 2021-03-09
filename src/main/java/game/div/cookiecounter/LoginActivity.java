package game.div.cookiecounter;

import com.divneg.manager.ResourcesManager;
import com.facebook.android.friendsmash.FriendSmashApplication;
//import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
//import com.facebook.Request;
//import com.facebook.Response;
//import com.facebook.Session;
//import com.facebook.SessionState;
//import com.facebook.UiLifecycleHelper;
//import com.facebook.android.friendsmash.FriendSmashApplication;
//import com.facebook.model.GraphUser;
//import com.facebook.widget.LoginButton;
import org.andengine.audio.music.Music;

import game.div.corruptpuzzle.R;

public class LoginActivity extends Activity {
//    CallbackManager callbackManager;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		
//		FacebookSdk.sdkInitialize(this.getApplicationContext());
//		Log.d("MyApp", "Loggin out...");
//		LoginManager.getInstance().logOut();
        FriendSmashApplication application = (FriendSmashApplication) getApplication();
//        application.saveInventory();
        application.setCurrentFBUser(null);
		
		setContentView(R.layout.activity_login);
//		
//		LoginButton loginButton = (LoginButton) findViewById(R.id.btnFacebookLogin);
//    	loginButton.setReadPermissions(Arrays.asList("basic_info","email"));
//    	loginButton.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
				linkFacebookUser() ;
//				LoginButton loginButton = (LoginButton) findViewById(R.id.btnFacebookLogin);
//	    		 loginButton.setVisibility(View.INVISIBLE);
//	    		 
//	    		 ImageView wait = (ImageView) findViewById(R.id.screen_wait);
//	    		 wait.setVisibility(View.VISIBLE);
//				
//			}
//    		
//    	});
    	
		

	}
	 // this method will link the current ParseUser to the used Facebook account if needed
	  private boolean linkFacebookUser() {
//	    final ParseUser parseuser = ParseUser.getCurrentUser();
	    
//	    ParseFacebookUtils.logInWithReadPermissionsInBackground(this, Arrays.asList("basic_info","email"), new LogInCallback() {
//	    	  @Override
//	    	  public void done(ParseUser user, ParseException err) {
//	    		  
//	    	    if (user == null) {
//	    	      Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
//	    	      addProfile(parseuser);
////	    	      loginButton.setVisibility(View.VISIBLE);
////	    	      
//	    	    } else if (user.isNew()) {
//	    	      Log.d("MyApp", "User signed up and logged in through Facebook!");
//	    	      
////	    	      getUserDetailsFromFB();
//	    	      addProfile(user);
//	    	      
//	    	      
//	    	    } else {
//	    	      Log.d("MyApp", "User logged in through Facebook!");
//	    	      
	    	      getUserDetailsFromFB();
//	    	      addProfile(user);
//	    	      
//
//	    	    }
//	    	  }
//	    	});

	    return true;
	  }
	  


        FriendSmashApplication application = (FriendSmashApplication) getApplication();
	    
	  
	  private void getUserDetailsFromFB() {
//		  GraphRequest request = new GraphRequest(
//	               AccessToken.getCurrentAccessToken(),
//	               "/me",
//	               null,
//	               HttpMethod.GET,
//	               new GraphRequest.Callback() {
//	                   public void onCompleted(GraphResponse response) {
//	           /* handle the result */
//	                       try {
//	                    	   Log.d("MyApp", "setting facebook user");
//	                    	   
//	                    	   FriendSmashApplication application = (FriendSmashApplication) getApplication();
//	                    	   Profile faceUser = null;
//	                           try{
//	                        	   faceUser= new Profile(response.getJSONObject().getString("id"),null,null,null,response.getJSONObject().getString("name"), null);
//	                           }catch(NullPointerException n){
////	                        	   faceUser= new Profile(ParseUser.getCurrentUser().getString(KEY_AUTH_DATA)),null,null,null,response.getJSONObject().getString("name"), null);
//	                        	   Log.d("MyApp", "fbUser: " + response);
//	                           }
//	                           
//	                           application.setCurrentFBUser(faceUser);
//	                           
	                           Intent myIntent = new Intent(LoginActivity.this, GameActivity.class);
//	       		  			myIntent.putExtra("key", value); //Optional parameters
	       		    	      LoginActivity.this.startActivity(myIntent);
//	       	    	      
//	                           
//	                           
//	         	    	      
//	                       } catch (JSONException e) {
//	                           e.printStackTrace();
//	                       }
//	                   }
//	               }
//	       );
//	       
//	       Bundle parameters = new Bundle();
//           parameters.putString("fields", "id,name,email,gender, birthday, friends,albums");
//           request.setParameters(parameters);
//           request.executeAsync();
//           
//           
//
	   }
	
// 	public void addProfile(final ParseUser parseuser){
// 		Log.d("MyApp", ParseFacebookUtils.isLinked(parseuser)+"");
//	    if (!ParseFacebookUtils.isLinked(parseuser)) {
//	    	  ParseFacebookUtils.linkWithReadPermissionsInBackground(parseuser, this, Arrays.asList("basic_info","email"), new SaveCallback() {
//	    	    @Override
//	    	    public void done(ParseException ex) {
//	    	      if (ParseFacebookUtils.isLinked(parseuser)) {
//	    	        Log.d("MyApp", "Woohoo, user logged in with Facebook!");
////	    	        Intent myIntent = new Intent(LoginActivity.this, GameActivity.class);
//////  		  			myIntent.putExtra("key", value); //Optional parameters
////  		    	      LoginActivity.this.startActivity(myIntent);
//	    	        
////	    	        getUserDetailsFromFB();
//	    	        
//	    	      }
//	    	    }
//	    	  });
//    	
//	    }else{
////	    	getUserDetailsFromFB();
//	    }

	    	 
    	
    	
//    	personalizeHomeFragment();
// 	}

 	
	// userImage ProfilePictureView to display the user's profile pic
//    private ProfilePictureView userImage;
	
	// TextView for the user's name
    private TextView welcomeTextView;
 	
	// Personalize this HomeFragment (social-version only)
	void personalizeHomeFragment() {
		FriendSmashApplication application = (FriendSmashApplication) this.getApplication();

		
		LinearLayout layout = (LinearLayout) findViewById(R.id.welcome);

		
		// Set the userImage ProfilePictureView
//		userImage = (ProfilePictureView) findViewById(R.id.userImage);
		
		// Set the welcomeTextView TextView
		welcomeTextView = (TextView)findViewById(R.id.welcomeTextView);

		
		
		application.loadInventory();
		
//		Log.d("DEBUG", application.getCurrentFBUser().getName());
		
		if (application.getCurrentFBUser() != null) {
//			// Personalize this HomeFragment if the currentFBUser has been fetched
//			
//			// Set the id for the userImage ProfilePictureView
//            // that in turn displays the profile picture
//            userImage.setProfileId(application.getCurrentFBUser().getId());
////            // and show the cropped (square) version ...
//            userImage.setCropped(true);
////            
////            // Set the welcomeTextView Textview's text to the user's name
//            welcomeTextView.setText(application.getCurrentFBUser().getName());
//            Toast.makeText(this,"Welcome, " + application.getCurrentFBUser().getName(),3000).show();
////            Toast.makeText(this,"getBombs, " + application.getBombs(),3000).show();
////            Toast.makeText(this,"getCoins, " + application.getCoins(),3000).show();
//            
//            loginButton.setVisibility(View.INVISIBLE);
//            
		}     
//            
////            layout.addView(welcomeTextView);
//		}else{
//
//			
//			loginButton.setVisibility(View.VISIBLE);
//		}
	}
	
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.id.login, menu);
		return true;
	}

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}

