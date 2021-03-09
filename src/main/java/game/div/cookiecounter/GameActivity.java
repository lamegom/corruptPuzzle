package game.div.cookiecounter;



import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.LayoutGameActivity;

import com.divneg.manager.ResourcesManager;
import com.divneg.manager.SceneManager;
import com.facebook.android.friendsmash.FriendSmashApplication;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import game.div.corruptpuzzle.R;


//import com.gameanalytics.android.GameAnalytics;import game.div.cookiecounter.MyApplication.TrackerName;

//import com.google.ads.Ad;

//import com.google.ads.AdListener;

//import com.google.ads.AdRequest.ErrorCode;

//import com.google.ads.InterstitialAd;

//import com.jirbo.adcolony.AdColony;

////import io.fabric.sdk.android.Fabric;

////import com.crashlytics.android.Crashlytics;





/**

 * @author Divyanshu Negi

 * @version 1.0

 * @title : cookie_counter

 * 

 */

public class GameActivity extends LayoutGameActivity {//implements AdColonyAdAvailabilityListener, AdColonyNativeAdListener, AdColonyNativeAdMutedListener, AdColonyAdListener {



	public static int CAMERA_WIDTH;

	public static int CAMERA_HEIGHT;

	public static Font mfont, lfont;



	private ResourcesManager resourcesManager;


//	private static final String AD_UNIT_ID = "ca-app-pub-9218943577020014/5206631050";

	private static final String AD_UNIT_ID = "ca-app-pub-9218943577020014/4451139851";

	private static final String PROPERTY_ID = null;

	private static final String GAME_KEY = "9f1d1088fb10e6c2b46950470e9f2eaf";

	private static final String SECRET_KEY = "f04eea1625a1c64e6436d8b85a166ca70002dd10";

	private static GameActivity _instance = null;




	protected static final String LEADERBOARD_ID = "CgkIzdSliMYeEAIQEw";

	private GoogleSignInClient mGoogleSignInClient;
	private static final int RC_SIGN_IN = 9001;



	@Override

	public Engine onCreateEngine(EngineOptions pEngineOptions) {

		_instance = this;


		return new LimitedFPSEngine(pEngineOptions, 60);

	}



	private Camera camera;

	public Scene mScene;

//	private Infinario infinario;



	public EngineOptions onCreateEngineOptions() {



		final Display defaultDisplay = getWindow().getWindowManager()

				.getDefaultDisplay();

		CAMERA_WIDTH = defaultDisplay.getWidth();

		CAMERA_HEIGHT = defaultDisplay.getHeight();

		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		EngineOptions engineOptions = new EngineOptions(true,

				ScreenOrientation.PORTRAIT_FIXED, new FillResolutionPolicy(), this.camera);

		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);

		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

		engineOptions.getTouchOptions().setNeedsMultiTouch(true);

		return engineOptions;

	}
	
	// Store the Application (as you can't always get to it when you can't access the Activity - e.g. during rotations)
//    private FriendSmashApplication application;
	// userImage ProfilePictureView to display the user's profile pic
//    private ProfilePictureView userImage;
//    
//    // profile pic of the user you smashed
//    private ProfilePictureView youSmashedUserImage;
	
	// TextView for the user's name
//    private TextView welcomeTextView;
    
    /*
	 * Called when and Activity returns. Checks for the following scenarios:
	 * 
	 * == Returning from a Facebook dialog asking for the user_friends permission after the user hit
	 *    the Play button.
	 *    
	 * == Returning from a Facebook dialog asking for the user_friends permission after the user hit 
	 *    the Leaderbaord button.
	 *    
	 * == Returning from a Facebook dialog asking for the publish_actions permission after the user hit 
	 *    the close button on the Game Over dialog.
	 *    
	 * == Returns from a finished game - test status with resultCode and if successfully ended, update
	 *    their score and complete the game over process, otherwise show an error if there is one
	 *    
	 */
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//
////		  ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
////		  callbackManager.onActivityResult(requestCode, resultCode, data);
//		
////		fbUiLifecycleHelper.onActivityResult(requestCode, resultCode, data);
//
////		if (resultCode == Activity.RESULT_OK && data != null) {
////        	// Finished a game
////        	// Get the parameters passed through including the score
////			Bundle bundle = data.getExtras();
////			application.setScore(bundle.getInt("score"));
////			
////			// Save coins and bombs data to parse
////			int coinsCollected = (bundle.getInt("coins_collected"));
////			application.setCoinsCollected(coinsCollected);
////			if (coinsCollected > 0) {
////				application.setCoins(application.getCoins()+coinsCollected);
////			}
////			int bombsUsed = (bundle.getInt("bombs_used"));
////			if (bombsUsed > 0) {
////				application.setBombs(application.getBombs()-bombsUsed);
////			}
////			
////			
//
//
//            // log GAME_PLAYED event
////            ((HomeActivity)getActivity()).getEventsLogger().logGamePlayedEvent(application.getScore());
//
////		}
//
//
//	            
//	         // An OK result means the pinned dataset changed or
//	    		// log in was successful
//	    		if (resultCode == RESULT_OK) {
////	    			if (requestCode == EDIT_ACTIVITY_CODE) {
////	    				// Coming back from the edit view, update the view
////	    				todoListAdapter.loadObjects();
////	    			} else if (requestCode == LOGIN_ACTIVITY_CODE) {
////	    				// If the user is new, sync data to Parse,
////	    				// else get the current list from Parse
////	    				if (ParseUser.getCurrentUser().isNew()) {
////	    					syncTodosToParse();
////	    				} else {
////	    					loadFromParse();
////	    				}
////	    			}
//	    			
//	    			
//
//
//	    		}
//
//	    		Log.d("DEBUG", "requestCode : " + resultCode);
//		
//	}
//
//	
//	// Personalize this HomeFragment (social-version only)
	void personalizeHomeFragment() {
//
//		
////		LinearLayout layout = (LinearLayout) findViewById(R.id.welcome);
//
//		
////		// Set the userImage ProfilePictureView
////		userImage = (ProfilePictureView) findViewById(R.id.userImage);
////		
////		// Set the welcomeTextView TextView
////		welcomeTextView = (TextView)findViewById(R.id.welcomeTextView);
////		
////		LoginButton loginButton = (LoginButton)findViewById(R.id.btnFacebookLogin2);
//		
//
//
		FriendSmashApplication application = (FriendSmashApplication) getApplication();
//		
		application.loadInventory();
//		
//		Log.d("DEBUG", AccessToken.getCurrentAccessToken()+"");
//		Log.d("DEBUG", com.facebook.Profile.getCurrentProfile()+"");
//		
////		if (application.getCurrentFBUser() != null){
////			// Personalize this HomeFragment if the currentFBUser has been fetched
////			
////			// Set the id for the userImage ProfilePictureView
////            // that in turn displays the profile picture
////            userImage.setProfileId(application.getCurrentFBUser().getId());
////            // and show the cropped (square) version ...
////            userImage.setCropped(true);
////            
////            // Set the welcomeTextView Textview's text to the user's name
////            welcomeTextView.setText(application.getCurrentFBUser().getName());
////            Toast.makeText(this,"Welcome, " + application.getCurrentFBUser().getName(),3000).show();
            Toast.makeText(this,"getBombs, " + application.getBombs(),Toast.LENGTH_LONG).show();
            Toast.makeText(this,"getCoins, " + application.getCoins(),Toast.LENGTH_LONG).show();
////
//
//
////            loginButton.setVisibility(View.INVISIBLE);
////            
////		}     
////            
////            layout.addView(welcomeTextView);
//
////		}else{
////
////			userImage.setProfileId(null);
////          // and show the cropped (square) version ...
////          userImage.setCropped(true);
////          
////          // Set the welcomeTextView Textview's text to the user's name
////          welcomeTextView.setText(null);
////
////		}
	}

//
//
//	protected void gotoLogin() {
//		Intent myIntent = new Intent(GameActivity.this, LoginActivity.class);
//////	myIntent.putExtra("key", value); //Optional parameters
//		
//		GameActivity.this.startActivity(myIntent);
//		
//		
//		
//		
//		
//	}



	// Tag used when logging messages
//    private static final String TAG = GameActivity.class.getSimpleName();
    
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Session session = Session.getActiveSession();
//        Session.saveSession(session, outState);
//        fbUiLifecycleHelper.onSaveInstanceState(outState);
//    }

    
	// Instantiate the fbUiLifecycleHelper and call onCreate() on it
//    Session.StatusCallback callback = new Session.StatusCallback() {
//        @Override
//        public void call(Session session, SessionState state, Exception exception) {
////            onSessionStateChange(session, state, exception);
//        }
//    };
    
//    public void share(String message) {
//
//    	if (ShareDialog.canShow(ShareLinkContent.class)) {
//    	    ShareLinkContent linkContent = new ShareLinkContent.Builder()
//    	            .setContentTitle("#StopCorruption!")
//    	            .setContentDescription(
//    	            		message)
//    	            .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=game.div.corruptpuzzle"))
//    	            .build();
//
//    	    shareDialog.show(linkContent);
//    	}
//    	
//    	
//    }
//
//    CallbackManager callbackManager;
//    ShareDialog shareDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
//    	FacebookSdk.sdkInitialize(getApplication().getApplicationContext());
    	super.onCreate(savedInstanceState);
    	Log.d("DEBUG", "onCreate : " );


    	/* callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {

			@Override
			public void onSuccess(Result result) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(FacebookException error) {
				// TODO Auto-generated method stub
				
			} 
			
        });
        
        */
        
//        LoginManager.getInstance().logOut();
//        application = (FriendSmashApplication) GameActivity.getInstance().getApplication();
////        ParseFacebookUtils.initialize(getApplicationContext());
//	
//		
//		setContentView(R.layout.game_activity);
//		
//		if(application.getCurrentFBUser()==null){
//			Intent myIntent = new Intent(GameActivity.this, LoginActivity.class);
////			myIntent.putExtra("key", value); //Optional parameters
//			GameActivity.this.startActivity(myIntent);
//	    }

//		Log.d("DEBUG", "setting up login button: ");
//		final LoginButton loginButton = (LoginButton) findViewById(R.id.btnFacebookLogin2);
////    	loginButton.setReadPermissions(Arrays.asList("basic_info","email"));
//    	loginButton.setOnClickListener(new OnClickListener(){
////
//			@Override
//			public void onClick(View v) {
//				Log.d("DEBUG", "c: ");
////				// TODO Auto-generated method stub
////				//Check if user is currently logged in
////		        if (AccessToken.getCurrentAccessToken() != null && com.facebook.Profile.getCurrentProfile() != null){
////		            //Logged in so show the login button
////		        	loginButton.setVisibility(View.VISIBLE);
////		        	loginButton.setOnClickListener(new View.OnClickListener() {
////		                @Override
////		                public void onClick(View view) {
////		//log out
////				personalizeHomeFragment();
//				
////		                    LoginManager.getInstance().logOut();
//		                    FriendSmashApplication application = (FriendSmashApplication) getApplication();
//		                    application.saveInventory();
//		                    application.setCurrentFBUser(null);
//		                    
//		                    System.gc();
//		            		System.exit(0);
//		            		finish();
//		            		
//		            		gotoLogin();
//		                    
////		                }
////		            });
////		        }
//				
//			}
////    		
//    	});

//        }
//    	
//    	callbackManager = CallbackManager.Factory.create();
//
//        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            private ProfileTracker mProfileTracker;
//
//			@Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//            	mProfileTracker = new ProfileTracker() {
//            	    @Override
//            	    protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
//            	        // Fetch user details from New Profile
//            	    	
//            	    	FriendSmashApplication application = (FriendSmashApplication) getApplication();
//
//                   	 application.setCurrentFBUser(newProfile);
//
////                    	Log.d("DEBUG", "parseUser: " + ParseUser.getCurrentUser());
////                    	Log.d("DEBUG", "fbUser: " + application.getCurrentFBUser().getId());
//                   	ParseUser user = ParseUser.getCurrentUser();
//					AccessToken accessToken = AccessToken.getCurrentAccessToken();
//					ParseFacebookUtils.linkInBackground(user, accessToken, new SaveCallback() {
//						
//						@Override
//						public void done(ParseException arg0) {
//							Log.d("DEBUG", arg0.toString());
//							
//						}
//					});
//                    	personalizeHomeFragment();
//                   	
//
//            	    }
//            	};
//            	
//            	 
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            	mProfileTracker = new ProfileTracker() {
//            	    @Override
//            	    protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
//            	        // Fetch user details from New Profile
//            	    	
//            	    	FriendSmashApplication application = (FriendSmashApplication) getApplication();
//
//                   	 application.setCurrentFBUser(newProfile);
//
////                    	Log.d("DEBUG", "parseUser: " + ParseUser.getCurrentUser());
////                    	Log.d("DEBUG", "fbUser: " + application.getCurrentFBUser().getId());
//                   	ParseUser user = ParseUser.getCurrentUser();
//					AccessToken accessToken = AccessToken.getCurrentAccessToken();
//					ParseFacebookUtils.linkInBackground(user, accessToken);
//                    	personalizeHomeFragment();
//                   	
//
//            	    }
//            	};
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            	Toast.makeText(getApplicationContext(),"Error: , " + exception.toString(),3000).show();
//            }
//        });    
//		
//	
//    	SceneManager.getInstance().createMenuScene();
    	
//    	personalizeHomeFragment();
    }
 	
 	
// 	public void addProfile(JSONObject fbUser){
//// 		Profile faceUser= new Profile(fbUser.optString("id"),null,null,null,fbUser.optString("name"), null);
////    	
////    	application.setCurrentFBUser(faceUser);
//
////    	Log.d("DEBUG", "parseUser: " + ParseUser.getCurrentUser());
////    	Log.d("DEBUG", "fbUser: " + application.getCurrentFBUser().getId());
//    	
//    	personalizeHomeFragment();
// 	}


	public void onCreateResources(

			OnCreateResourcesCallback pOnCreateResourcesCallback)

			throws IOException {

		ResourcesManager.prepareManager(mEngine, this, camera,

				getVertexBufferObjectManager());

		resourcesManager = ResourcesManager.getInstance();

		pOnCreateResourcesCallback.onCreateResourcesFinished();

		mfont = FontFactory.create(this.getFontManager(),

				this.getTextureManager(), 256, 256,

				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);

		mfont.load();




	}



	public void onPopulateScene(Scene pScene,

			OnPopulateSceneCallback pOnPopulateSceneCallback)

			throws IOException {

		mEngine.registerUpdateHandler(new TimerHandler(2f,

				new ITimerCallback() {

					public void onTimePassed(final TimerHandler pTimerHandler) {

						mEngine.unregisterUpdateHandler(pTimerHandler);

						/*

						 * SceneManager.getInstance().createMenuScene(GameActivity

						 * .this);

						 */

						SceneManager.getInstance().createMenuScene();


					}

				}));

		pOnPopulateSceneCallback.onPopulateSceneFinished();

	}



	@Override

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			SceneManager.getInstance().getCurrentScene().onBackKeyPressed();

		}



		return false;

	}



	public static GameActivity getInstance() {

		return _instance;

	}
	
	@Override

	protected int getLayoutID() {

		// TODO Auto-generated method stub

		return R.layout.game_activity;

	}



	@Override

	protected int getRenderSurfaceViewID() {

		// TODO Auto-generated method stub

		return R.id.SurfaceViewId;

	}
	

	ImageView image;
	
	public void addListenerOnButton() {

 
//		image = (ImageView) findViewById(R.id.imageView1);
		
		image = new ImageView(this);
//		image.setImageResource(R.drawable.btn_donate_pp);
		
//		LinearLayout layout = (LinearLayout) findViewById(R.id.adViewId);

//		layout.addView(image);
		
		
		image.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
//				Intent myIntent = new Intent(GameActivity.this, DonationsActivity.class);
//				myIntent.putExtra("key", value); //Optional parameters
//				GameActivity.this.startActivity(myIntent);
                
//      		  DonationsFragment donationsFragment;
//    	      donationsFragment = DonationsFragment.newInstance(BuildConfig.DEBUG, true, GOOGLE_PUBKEY, GOOGLE_CATALOG,
//    	              getResources().getStringArray(R.array.donation_google_catalog_values), false, null, null,
//    	              null, false, null, null, false, null);
			}
 
		});
 
	}
	




	@Override
	protected void onSetContentView() {



		super.onSetContentView();




//		 FriendSmashApplication application = (FriendSmashApplication) getApplication().getApplicationContext();
//		if(application.getCurrentFBUser()!=null){
//			
//			Log.d("DEBUG", "facebook profile loaded.");
//			setContentView(R.layout.game_activity);
//			  this.mRenderSurfaceView = new RenderSurfaceView(this);
//			    this.mRenderSurfaceView.setRenderer(this.mEngine, this);
//			    if(android.os.Build.VERSION.SDK_INT >= 11){
//			        this.mRenderSurfaceView.setPreserveEGLContextOnPause(true);
//			    }
////			    this.setContentView(this.mRenderSurfaceView, BaseGameFragmentActivity.createSurfaceViewLayoutParams());
//
//				
//		addListenerOnButton();
//		
//		if (getIntent().getBooleanExtra("EXIT", false)) 
//		{
//		        finish();
//		}
//		  }else{
////			  Intent myIntent = new Intent(GameActivity.this, LoginActivity.class);
////			myIntent.putExtra("key", value); //Optional parameters
////			GameActivity.this.startActivity(myIntent);
//		  }

	}


	@Override

	protected void onPause() {

		super.onPause();

		if (this.isGameLoaded() && null!=resourcesManager.music && resourcesManager.music.isPlaying())

		MainMenuScene.mms.paused();


		// Logs 'app deactivate' App Event.

//		  AppEventsLogger.deactivateApp(this);

	

	}



	@Override

	protected synchronized void onResume() {

		super.onResume();

//
//		AndroidLauncher launcher = AndroidLauncher.getInstance();
//		launcher.submitScore(1234);
		  // Logs 'install' and 'app activate' App Events.

//		  AppEventsLogger.activateApp(this);
//		  
//		  personalizeHomeFragment();
		  
		  
		  Log.d("DEBUG", "resuming.." +  (MainMenuScene.mms != null)+"");
		  
		  if (MainMenuScene.mms != null) {
             
              MainMenuScene.mms.createScene();;


		  }
		  

		  System.gc();

		

	}



	@Override

	protected void onDestroy() {

		super.onDestroy();
		

			if (this.isGameLoaded()) {

			System.exit(0);

		}

	}

	@Override

	protected void onStop() {

		// TODO Auto-generated method stub

		super.onStop();


		//System.exit(0);

	}

	

	@Override

	public void onGameCreated() {

		super.onGameCreated();

		mGoogleSignInClient = GoogleSignIn.getClient(this,
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());



	}



	@Override

	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {

		mScene = new Scene();

		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);



	}

	

	

//		public void text(final String message){
//
//			// Get a handler that can be used to post to the main thread
//
//						Handler mainHandler = new Handler(MyApplication.getAppContext().getMainLooper());
//
//
//
//						Runnable myRunnable = new Runnable(){
//
//
//
//							@Override
//
//							public void run() {
//
//								Toast.makeText(GameActivity.getInstance(),message , Toast.LENGTH_SHORT).show();
//
//										
//
//							}
//
//								
//
//						
//
//						}; // This is your code
//
//						mainHandler.post(myRunnable);
//
//						  
//
//						  
//
//
//
//
//
//			
//
//		}



	 public double round(double value, int places) {

	 if (places < 0) throw new IllegalArgumentException();



	 long factor = (long) Math.pow(10, places);

	 value = value * factor;

	 long tmp = Math.round(value);

	 return (double) tmp / factor;

	 }



	public void submitScore(int score){
		if(isSignedIn()) {
			mLeaderboardsClient.submitScore(getString(R.string.leaderboard_id), score);
		}
	}


	public boolean isSignedIn() {
		return GoogleSignIn.getLastSignedInAccount(this) != null;
	}

	private void signInSilently() {

		mGoogleSignInClient = GoogleSignIn.getClient(this,
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());

		mGoogleSignInClient.silentSignIn().addOnCompleteListener(GameActivity.getInstance(),
				new OnCompleteListener<GoogleSignInAccount>() {
					@Override
					public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
						if (task.isSuccessful()) {
							greetingMsg="Welcome back, ";
							onConnected(task.getResult());
						} else {
							onDisconnected();
						}
					}
				});
	}

	private void onConnected(GoogleSignInAccount googleSignInAccount) {

		mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
		mPlayersClient = Games.getPlayersClient(this, googleSignInAccount);

		mPlayersClient.getCurrentPlayer()
				.addOnCompleteListener(new OnCompleteListener<Player>() {
					@Override
					public void onComplete(@NonNull Task<Player> task) {
						String displayName;
						if (task.isSuccessful()) {
							displayName = task.getResult().getDisplayName();
						} else {
							Exception e = task.getException();
							//handleException(e, getString(R.string.players_exception));
							displayName = "???";
						}

						if(!greetingDisplayed)
							welcomeMessage(displayName);
					}
				});
	}

	private void welcomeMessage(String name){

		Toast toast = Toast.makeText(this, greetingMsg + name, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP, 0, 0);
		View view = toast.getView();
		TextView text = (TextView) view.findViewById(android.R.id.message);
		toast.show();
		greetingDisplayed=true;
	}



	public void showLeaderBoard() {
		if(isSignedIn()) {
			mLeaderboardsClient.getLeaderboardIntent(getString(R.string.leaderboard_id))
					.addOnSuccessListener(new OnSuccessListener<Intent>() {
						@Override
						public void onSuccess(Intent intent) {
							startActivityForResult(intent, RC_LEADERBOARD_UI);
						}
					});
		}
	}

	private void onDisconnected() {

		mLeaderboardsClient = null;
		mPlayersClient = null;
	}

	public void startSignInIntent() {

		mGoogleSignInClient = GoogleSignIn.getClient(this,
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());


		startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
	}

	private static final int RC_UNUSED = 5001;
	private static final int RC_LEADERBOARD_UI = 9004;

	private String greetingMsg="Welcome, ";
	private boolean greetingDisplayed;

	private LeaderboardsClient mLeaderboardsClient;
	private PlayersClient mPlayersClient;

}

