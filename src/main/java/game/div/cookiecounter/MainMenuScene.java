package game.div.cookiecounter;

import java.util.ArrayList;
import java.util.Random;

/* ... */
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.modifier.ease.EaseBackIn;
import org.andengine.util.modifier.ease.EaseBackInOut;
import org.andengine.util.modifier.ease.EaseBackOut;
import org.andengine.util.modifier.ease.EaseBounceIn;
import org.andengine.util.modifier.ease.EaseBounceInOut;
import org.andengine.util.modifier.ease.EaseBounceOut;
import org.andengine.util.modifier.ease.EaseCircularIn;
import org.andengine.util.modifier.ease.EaseCircularInOut;
import org.andengine.util.modifier.ease.EaseCircularOut;
import org.andengine.util.modifier.ease.EaseCubicIn;
import org.andengine.util.modifier.ease.EaseCubicInOut;
import org.andengine.util.modifier.ease.EaseCubicOut;
import org.andengine.util.modifier.ease.EaseElasticIn;
import org.andengine.util.modifier.ease.EaseElasticInOut;
import org.andengine.util.modifier.ease.EaseElasticOut;
import org.andengine.util.modifier.ease.EaseExponentialIn;
import org.andengine.util.modifier.ease.EaseExponentialInOut;
import org.andengine.util.modifier.ease.EaseExponentialOut;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.EaseQuadIn;
import org.andengine.util.modifier.ease.EaseQuadInOut;
import org.andengine.util.modifier.ease.EaseQuadOut;
import org.andengine.util.modifier.ease.EaseQuartIn;
import org.andengine.util.modifier.ease.EaseQuartInOut;
import org.andengine.util.modifier.ease.EaseQuartOut;
import org.andengine.util.modifier.ease.EaseQuintIn;
import org.andengine.util.modifier.ease.EaseQuintInOut;
import org.andengine.util.modifier.ease.EaseQuintOut;
import org.andengine.util.modifier.ease.EaseSineIn;
import org.andengine.util.modifier.ease.EaseSineInOut;
import org.andengine.util.modifier.ease.EaseSineOut;
import org.andengine.util.modifier.ease.EaseStrongIn;
import org.andengine.util.modifier.ease.EaseStrongInOut;
import org.andengine.util.modifier.ease.EaseStrongOut;
import org.andengine.util.modifier.ease.IEaseFunction;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.divneg.base.BaseScene;
import com.divneg.manager.SceneManager;
import com.divneg.manager.SceneManager.SceneType;
import com.divneg.storage.Config;
import com.facebook.android.friendsmash.FriendSmashApplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import game.div.corruptpuzzle.R;
import game.div.foradilma.util.Common;
import game.div.foradilma.util.IabHelper;
import game.div.foradilma.util.IabResult;
import game.div.foradilma.util.Inventory;
import game.div.foradilma.util.Purchase;

//import com.infinario.android.infinariosdk.Infinario;

public class MainMenuScene extends BaseScene implements

IOnMenuItemClickListener, IOnSceneTouchListener {

	static FixedStepPhysicsWorld physicsWorld;

	public static MainMenuScene mms;

	private Sprite cookie;

	private Rectangle floor;

	private Body heli_body;

	private boolean fly = false;

	private Sprite cloud;

	private Body cloud_body;

	private float speed = 5.0f;

	private boolean startGame = false;

	public double cookiCount = 0;

	private Sprite leaderButton;

	private Sprite highButton;

	private Sprite playButton;

	private Sprite title;

	private Sprite resume;

	private AnimatedSprite blast;

	private Sprite menuButton;

	private Sprite gameOver;

	private Sprite trans_bg;

	private Sprite retryButton;

	int loop = 0;

	private int heliDis = 0;

	private Text cookieRate;

	private boolean gameFinished = false;

	private ArrayList<CloudsStuff> cloudList = new ArrayList<CloudsStuff>();

	private ArrayList<DelClouds> deleteBody = new ArrayList<DelClouds>();

	private ArrayList<delSprites> delSprites;

	private ArrayList<delSprites> delSpritesGame;

	private ArrayList<delSprites> delSpritesFont;

	private ArrayList<MoveText> delMoveText;

	private ArrayList<delSprites> delSpritesRestart;

	private boolean gameOverCheck = false;

	private Text cookieCount;

	private Sprite milk;

	private Sprite raysSprite;

	private Sprite tapPlay;

	private Sprite howTo;

	private boolean deleteMenu = false;

	private boolean deleteGame = false;

	private boolean deleteRestart = false;

	private Sprite pauseButton;

	private boolean write = false;

	private int exit = 0;

	private boolean itsMenu = false;

	private boolean itsGame = false;

	private boolean itsRestart = false;

	private boolean backpress = false;

	private boolean pause = false;

	private boolean helicopterReady = false;

	private Sprite shopIcon;

	private Sprite rankIcon;

	private Sprite savedGamesIcon;

	private Sprite cookieDashboard;

	private Text text;

	public static MainMenuScene getInstance() {

		return mms;

	}

	private void createPhysics() {

		physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0,

		SensorManager.GRAVITY_EARTH), false, 3, 2);

		physicsWorld.setContactListener(createContactListener());

		registerUpdateHandler(physicsWorld);

	}

	// All text part here

	public void writeCookieCount() {

		TextOptions autowrap = new TextOptions(AutoWrap.WORDS,
				(GameActivity.getInstance().CAMERA_WIDTH),
				HorizontalAlign.CENTER);

		cookieCount = new Text(

		0,

		0,

		resourcesManager.bigFont,

		"Score : 999999999999999999999999999999999999999999999999999999999 ",

		autowrap,

		vbom);

		attachChild(cookieCount);

		write = true;

	}

	public void writeCookieRate() {

		TextOptions autowrap = new TextOptions(AutoWrap.WORDS,
				(GameActivity.getInstance().CAMERA_WIDTH - 50 - 70),
				HorizontalAlign.CENTER);

		cookieRate = new Text(0, 0, resourcesManager.smallFont,

		"Distance : 999999999999999999999999999999999999999999999999",

		autowrap,

		vbom);

		cookieRate.setY(cookieCount.getHeight() + GameActivity.CAMERA_HEIGHT

		* 2 / 100);

		attachChild(cookieRate);
		write = true;

	}

	FriendSmashApplication application = (FriendSmashApplication) GameActivity
			.getInstance().getApplication();

	@Override
	public void createScene() {

		mms = this;

		delSprites = new ArrayList<delSprites>();

		delSpritesGame = new ArrayList<delSprites>();

		delSpritesFont = new ArrayList<delSprites>();

		delMoveText = new ArrayList<MoveText>();

		delSpritesRestart = new ArrayList<delSprites>();

		cookiCount = Config.COOKIES;

		setOnSceneTouchListener(this);

		engine.registerUpdateHandler(new FPSLogger());

		engine.registerUpdateHandler(new IUpdateHandler() {

			@Override
			public void onUpdate(float pSecondsElapsed) {

				loop++;

				if (raysSprite != null) {

					raysSprite.setRotation((float) raysSprite.getRotation() + 0.2f);

				}

				if (!delMoveText.isEmpty()) {

					for (final MoveText m : delMoveText) {

						if (m.y > 0) {

							m.t1.setY(m.y -= 8.0f);

						} else {

							m.t1.setVisible(false);

							m.t1.setIgnoreUpdate(true);

							GameActivity.getInstance().runOnUpdateThread(

							new Runnable() {

								@Override
								public void run() {

									MainMenuScene.mms.detachChild(m.t1);

								}

							});

						}

					}

				}

				if (Config.COOKIERATE != 0 && loop % 30 == 0) {

					try {

						cookiCount = application.getBombs();

					} catch (Exception e) {
						cookiCount = Double.MIN_VALUE;
					}

					cookiCount += application.getCoins();

					application.setBombs(cookiCount);

				}

				if (gameOverCheck) {

					deleteBody();

				}

				deleteMenu();

				if (gameFinished && !cloudList.isEmpty()

				&& !physicsWorld.isLocked()) {

					GameActivity.getInstance().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							for (CloudsStuff c : cloudList) {

								c.cloud_s.setIgnoreUpdate(true);

								c.cloud_s.setVisible(false);

								detachChild(c.cloud_s);

								physicsWorld.destroyBody(c.getCloud_b());

								System.out.println("Cloud deleted");

							}

							cloudList.clear();

						}

					});

				}

				// mGoogleApiClient = GameActivity.getInstance().getApiClient();

				if (!startGame && loop % 10 == 0) {

					// cookiCount += 1;

					// speed += 0.05;

					// try {
					//
					//
					// cookieCount.setText(Common.milTrilConverter(Config.getData("cookie"),true)
					// + " ");
					//
					//
					//
					// } catch (Exception e) {
					// //
					// cookieCount.setText(Common.milTrilConverter(round(Double.MIN_VALUE,1),
					// true) + " ");
					// }

					cookieCount.setX(GameActivity.CAMERA_WIDTH / 2

					- cookieCount.getWidth() / 2);

					cookieCount.setY(GameActivity.CAMERA_HEIGHT * 5 / 100);

					cookieRate.setX(GameActivity.CAMERA_WIDTH / 2

					- cookieRate.getWidth() / 2);

					cookieRate.setY(GameActivity.CAMERA_HEIGHT * 5 / 100

					+ cookieCount.getHeight());

				} else if (cookieCount != null) {

					// Toast.makeText(GameActivity.getInstance(),"Ad Interstitial calling...."
					// , Toast.LENGTH_LONG).show();

					// Games.Leaderboards.submitScore(getmGoogleApi(),
					// LEADERBOARD_ID, (long) round(cookiCount,0));

					// if(round(cookiCount,0)==0D){

					// savedGamesSelect();

					//
					// cookiCount = Double.MIN_VALUE;
					//
					//
					//
					// cookiCount += 1;
					//
					//
					//
					// Config.fillData("cookie", String.valueOf(cookiCount));

					// }

					// initAchievements();
					//
					// initUserAnalytics();

					// try {
					//
					//
					//
					// cookiCount =
					// Double.parseDouble(Config.getData("cookie"));
					//
					// } catch (Exception e) {
					// // TODO: handle exception
					//
					// // cookiCount = Double.MIN_VALUE;
					// }

					try {

//						if(application.getCurrentFBUser()!=null){
						
						cookieCount.setText(Common.milTrilConverter(
								application.getBombs(), true)
								+ " ");

						cookieRate.setText(Common.milTrilConverter(
								application.getCoins(), false)
								+ " corrupts/sec");

						String milkHeight = application.getBombs() + "";
						milk.setHeight(GameActivity.CAMERA_HEIGHT
								* (Float.valueOf((String) milkHeight
										.subSequence(0, 1)) * 10) / 100);
						milk.setY(GameActivity.CAMERA_HEIGHT - milk.getHeight());
//						}

					} catch (Exception e) {
						// TODO: handle exception
						//
						//
						// cookieCount.setText(Common.milTrilConverter(Config.getData("cookie"),
						// true) + " ");
						//
						// cookieRate.setText(Common.milTrilConverter(Config.getCookieRate("cookieRate"),
						// false)+" corrupts/sec");
						//
						// milk.setHeight(GameActivity.CAMERA_HEIGHT * 10 /
						// 100);
						// milk.setY(GameActivity.CAMERA_HEIGHT -
						// milk.getHeight());
					}

				}

				if (startGame && loop % 5 == 0) {

					heliDis += 1;

					cookieRate.setText("Distance : " + heliDis + "m");

				}

				if (fly && heli_body != null && startGame) {

					heli_body.setLinearVelocity(new Vector2(0, -10));

				}

				if (startGame

						&& cloud_body != null

						&& cloud_body.getPosition().x < (GameActivity.CAMERA_WIDTH) * 1 / 100) {

					createClouds();

				}

			}

			private void initUserAnalytics() {
				// if(getmGoogleApi().isConnected()){
				// Player player =
				// Games.Players.getCurrentPlayer(getmGoogleApi());
				//
				// Infinario infinario = infinario =
				// Infinario.getInstance(GameActivity.getInstance(),
				// "9dc60216-fefd-11e4-9e41-b083fedeed2e");
				//
				// Map<String, Object> attributes = new HashMap<>();
				// attributes.put("country", "USA");
				// attributes.put("gender", "male");
				// attributes.put("googlePlayPlayer", player);
				// attributes.put("playerId", player.getPlayerId());
				// attributes.put("displayName", player.getDisplayName());
				// // attributes.put("campaign ID", "101");
				// attributes.put("source", "Google Games");
				// // attributes.put("birthday", "654868800");
				// attributes.put("acquisition cost", 1.2);
				// infinario.track("registration", attributes);
				// }

			}

			private void deleteBody() {

				if (!deleteBody.isEmpty()) {

					for (int i = 0; i < deleteBody.size(); i++) {

						deleteBody.get(i).delete();

					}

					deleteBody.clear();

				}

			}

			@Override
			public void reset() {

				// TODO Auto-generated method stub

			}

		});

		createHowToPlay();

		createPhysics();

		createBackground();

		createFloor();

		createRoof();

		// Game Cookie Clicker

		createRays();

		createCookie();

		createMilk();

		createShop();

		createRanking();

		createSavedGames();

		createCookieCounter();

		writeCookieCount();

		writeCookieRate();

		// ends cookie clcker

	}

	// SKUs for our products: the premium upgrade (non-consumable) and gas
	// (consumable)

	static final String SKU_PREMIUM = "premium";

	static final String SKU_GAS = "gas";

	// SKU for our subscription (infinite gas)

	// static final String SKU_INFINITE_GAS = "infinite_gas";;

	static final String SKU_INFINITE_GAS = "android.test.purchased";;

	// Debug tag, for logging

	static final String TAG = "Puchese button";

	// The helper object

	IabHelper mHelper;

	// (arbitrary) request code for the purchase flow

	static final int RC_REQUEST = 10001;

	// Does the user have the premium upgrade?

	boolean mIsPremium = false;

	// Does the user have an active subscription to the infinite gas plan?

	boolean mSubscribedToInfiniteGas = false;

	// Graphics for the gas gauge

	// static int[] TANK_RES_IDS = { R.drawable.gas0, R.drawable.gas1,
	// R.drawable.gas2,

	// R.drawable.gas3, R.drawable.gas4 };

	// How many units (1/4 tank is our unit) fill in the tank.

	static final int TANK_MAX = 4;

	// Current amount of gas in tank, in units

	int mTank;

	// "Subscribe to infinite gas" button clicked. Explain to user, then start
	// purchase

	// flow for subscription.

	public void onInfiniteGasButtonClicked() {

		// Get a handler that can be used to post to the main thread

		Handler mainHandler = new Handler(MyApplication.getAppContext()
				.getMainLooper());

		Runnable myRunnable = new Runnable() {

			@Override
			public void run() {

				// ...

				String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnxNvf9F1KsVDjDvzUKMs23V6GVeDtRrsC4E3o8MKwaTK2x3t+tkGDm3gKpobPooS19hLLpERQvGesdDvG0kxhnZGcmeW3nxzskGBTX6In3CkioACabRL/c5Nf5EHvlBauUSeA2BEKFuCvVhGAOf+bVFCx226HpW2B0WSCzpTxDy8wp0jSdhBPtPIDr06nfkoYaTCQU87TDBxVNsB1OSHJamrDdpookUI95l4eukJXEkTtBG31tPD6XHeVKHow5TfTvUUBoA/ZcjvI9A4dYZl8yo2AWgrh/w3u0RDxHJCnZ/IDpeaZ5WmdOjbBEdKCW3uIoq8Kca71aXl//UeRVaZhQIDAQAB";

				//

				// Create the helper, passing it our context and the public key
				// to verify signatures with

				Log.d(TAG, "Creating IAB helper.");

				mHelper = new IabHelper(MyApplication.getAppContext(),
						base64EncodedPublicKey);

				//

				// // enable debug logging (for a production application, you
				// should set this to false).

				mHelper.enableDebugLogging(true);

				//

				// Start setup. This is asynchronous and the specified listener

				// will be called once setup completes.

				Log.d(TAG, "Starting setup.");

				mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {

					public void onIabSetupFinished(IabResult result) {

						Log.d(TAG, "Setup finished.");

						//

						if (!result.isSuccess()) {

							// Oh noes, there was a problem.

							System.out
									.println("Problem setting up in-app billing: "
											+ result);

							return;

						}

						//

						// // Have we been disposed of in the meantime? If so,
						// quit.

						if (mHelper == null)
							return;

						//

					}

				});

				if (!mHelper.subscriptionsSupported()) {

					complain("Subscriptions not supported on your device yet. Sorry!");

					return;

				}

				/*
				 * TODO: for security, generate your payload here for
				 * verification. See the comments on
				 * 
				 * verifyDeveloperPayload() for more info. Since this is a
				 * SAMPLE, we just use
				 * 
				 * an empty string, but on a production app you should carefully
				 * generate this.
				 */

				String payload = "";

				setWaitScreen(true);

				Log.d(TAG,
						"Launching purchase flow for infinite gas subscription.");

				mHelper.launchPurchaseFlow(GameActivity.getInstance(),

				SKU_INFINITE_GAS, IabHelper.ITEM_TYPE_SUBS,

				RC_REQUEST, mPurchaseFinishedListener, payload);

			}

		}; // This is your code

		mainHandler.post(myRunnable);

	}

	// Callback for when a purchase is finished

	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {

		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {

			Log.d(TAG, "Purchase finished: " + result + ", purchase: "
					+ purchase);

			// if we were disposed of in the meantime, quit.

			if (mHelper == null)
				return;

			if (result.isFailure()) {

				complain("Error purchasing: " + result);

				setWaitScreen(false);

				return;

			}

			if (!verifyDeveloperPayload(purchase)) {

				complain("Error purchasing. Authenticity verification failed.");

				setWaitScreen(false);

				return;

			}

			Log.d(TAG, "Purchase successful.");

			// if (purchase.getSku().equals(SKU_GAS)) {

			// // bought 1/4 tank of gas. So consume it.

			// Log.d(TAG, "Purchase is gas. Starting gas consumption.");

			// mHelper.consumeAsync(purchase, mConsumeFinishedListener);

			// }

			// // else

			// if (purchase.getSku().equals(SKU_PREMIUM)) {

			// // bought the premium upgrade!

			// Log.d(TAG, "Purchase is premium upgrade. Congratulating user.");

			// alert("Thank you for upgrading to premium!");

			// mIsPremium = true;

			// updateUi();

			// setWaitScreen(false);

			// }

			// else

			if (purchase.getSku().equals(SKU_INFINITE_GAS)) {

				// bought the infinite gas subscription

				Log.d(TAG, "Infinite gas subscription purchased.");

				alert("Thank you for subscribing to infinite gas!");

				mSubscribedToInfiniteGas = true;

				mTank = TANK_MAX;

				// updateUi();

				setWaitScreen(false);

			}

		}

	};

	/** Verifies the developer payload of a purchase. */

	boolean verifyDeveloperPayload(Purchase p) {

		String payload = p.getDeveloperPayload();

		/*
		 * 
		 * TODO: verify that the developer payload of the purchase is correct.
		 * It will be
		 * 
		 * the same one that you sent when initiating the purchase.
		 * 
		 * 
		 * 
		 * WARNING: Locally generating a random string when starting a purchase
		 * and
		 * 
		 * verifying it here might seem like a good approach, but this will fail
		 * in the
		 * 
		 * case where the user purchases an item on one device and then uses
		 * your app on
		 * 
		 * a different device, because on the other device you will not have
		 * access to the
		 * 
		 * random string you originally generated.
		 * 
		 * 
		 * 
		 * So a good developer payload has these characteristics:
		 * 
		 * 
		 * 
		 * 1. If two different users purchase an item, the payload is different
		 * between them,
		 * 
		 * so that one user's purchase can't be replayed to another user.
		 * 
		 * 
		 * 
		 * 2. The payload must be such that you can verify it even when the app
		 * wasn't the
		 * 
		 * one who initiated the purchase flow (so that items purchased by the
		 * user on
		 * 
		 * one device work on other devices owned by the user).
		 * 
		 * 
		 * 
		 * Using your own server to store and verify developer payloads across
		 * app
		 * 
		 * installations is recommended.
		 */

		return true;

	}

	void complain(String message) {

		Log.e(TAG, "**** Purchase Error: " + message);

		alert("Error: " + message);

	}

	// Listener that's called when we finish querying the items and
	// subscriptions we own

	IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {

		public void onQueryInventoryFinished(IabResult result,
				Inventory inventory) {

			Log.d(TAG, "Query inventory finished.");

			// Have we been disposed of in the meantime? If so, quit.

			if (mHelper == null)
				return;

			// Is it a failure?

			if (result.isFailure()) {

				complain("Failed to query inventory: " + result);

				return;

			}

			Log.d(TAG, "Query inventory was successful.");

			/*
			 * 
			 * Check for items we own. Notice that for each purchase, we check
			 * 
			 * the developer payload to see if it's correct! See
			 * 
			 * verifyDeveloperPayload().
			 */

			// Do we have the premium upgrade?

			Purchase premiumPurchase = inventory.getPurchase(SKU_PREMIUM);

			mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));

			Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));

			// Do we have the infinite gas plan?

			Purchase infiniteGasPurchase = inventory
					.getPurchase(SKU_INFINITE_GAS);

			mSubscribedToInfiniteGas = (infiniteGasPurchase != null &&

			verifyDeveloperPayload(infiniteGasPurchase));

			Log.d(TAG, "User "
					+ (mSubscribedToInfiniteGas ? "HAS" : "DOES NOT HAVE")

					+ " infinite gas subscription.");

			if (mSubscribedToInfiniteGas)
				mTank = TANK_MAX;

			// Check for gas delivery -- if we own gas, we should fill up the
			// tank immediately

			Purchase gasPurchase = inventory.getPurchase(SKU_GAS);

			if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {

				Log.d(TAG, "We have gas. Consuming it.");

				mHelper.consumeAsync(inventory.getPurchase(SKU_GAS),
						mConsumeFinishedListener);

				return;

			}

			// updateUi();

			setWaitScreen(false);

			Log.d(TAG, "Initial inventory query finished; enabling main UI.");

		}

	};

	// Called when consumption is complete

	IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {

		public void onConsumeFinished(Purchase purchase, IabResult result) {

			Log.d(TAG, "Consumption finished. Purchase: " + purchase
					+ ", result: " + result);

			// if we were disposed of in the meantime, quit.

			if (mHelper == null)
				return;

			// We know this is the "gas" sku because it's the only one we
			// consume,

			// so we don't check which sku was consumed. If you have more than
			// one

			// sku, you probably should check...

			if (result.isSuccess()) {

				// successfully consumed, so we apply the effects of the item in
				// our

				// game world's logic, which in our case means filling the gas
				// tank a bit

				Log.d(TAG, "Consumption successful. Provisioning.");

				mTank = mTank == TANK_MAX ? TANK_MAX : mTank + 1;

				saveData();

				alert("You filled 1/4 tank. Your tank is now "
						+ String.valueOf(mTank) + "/4 full!");

			}

			else {

				complain("Error while consuming: " + result);

			}

			// updateUi();

			setWaitScreen(false);

			Log.d(TAG, "End consumption flow.");

		}

	};

	// The AppState slot we are editing. For simplicity this sample only
	// manipulates a single
	// Cloud Save slot and a corresponding Snapshot entry, This could be changed
	// to any integer
	// 0-3 without changing functionality (Cloud Save has four slots, numbered
	// 0-3).
	private static final int APP_STATE_KEY = 0;
	private static final int APP_STATE_KEY_RATIO = 1;

	void saveData() {

		/*
		 * 
		 * WARNING: on a real application, we recommend you save data in a
		 * secure way to
		 * 
		 * prevent tampering. For simplicity in this sample, we simply store the
		 * data using a
		 * 
		 * SharedPreferences.
		 */

		SharedPreferences.Editor spe = ((Activity) GameActivity.getInstance())
				.getPreferences(Context.MODE_PRIVATE).edit();

		spe.putInt("tank", mTank);

		spe.commit();

		Log.d(TAG, "Saved data: tank = " + String.valueOf(mTank));

	}

	// Enables or disables the "please wait" screen.

	void setWaitScreen(boolean set) {

		// findViewById(R.id.screen_main).setVisibility(set ? View.GONE :
		// View.VISIBLE);

		GameActivity.getInstance().findViewById(R.id.screen_wait)
				.setVisibility(set ? View.VISIBLE : View.GONE);

	}

	void alert(final String message) {

		// Get a handler that can be used to post to the main thread

		Handler mainHandler = new Handler(MyApplication.getAppContext()
				.getMainLooper());

		Runnable myRunnable = new Runnable() {

			@Override
			public void run() {

				AlertDialog.Builder bld = new AlertDialog.Builder(
						(Activity) GameActivity.getInstance());

				bld.setMessage(message);

				bld.setNeutralButton("OK", null);

				Log.d(TAG, "Showing alert dialog: " + message);

				bld.create().show();

			}

		}; // This is your code

		mainHandler.post(myRunnable);

	}

	// Main screen in the gameplay

	public void createMenu() {

		itsMenu = true;

		itsGame = false;

		itsRestart = false;

		createHighButton();

		createLeaderButton();

		createTitle();

		createPlayButton();

		createHowToPlay();

	}

	private void deleteMenu() {

		if (!delSprites.isEmpty() && deleteMenu) {

			int i = 0;

			for (delSprites d : delSprites) {

				d.delete();

				System.out.println("menu number " + i);

				i += 1;

			}

			delSprites.clear();

			deleteMenu = false;

		}

		if (!delSpritesGame.isEmpty() && deleteGame || backpress) {

			int i = 0;

			for (delSprites d : delSpritesGame) {

				d.delete();

				System.out.println("game number " + i);

				i += 1;

			}

			for (delSprites t : delSpritesFont) {

				t.deleteFont();

				System.out.println("font number ");

			}

			delSpritesFont.clear();

			delSpritesGame.clear();

			deleteGame = false;

		}

		if (!delSpritesRestart.isEmpty() && deleteRestart || backpress) {

			int i = 0;

			for (delSprites d : delSpritesRestart) {

				d.delete();

				System.out.println("restart number " + i);

				i += 1;

			}

			delSpritesRestart.clear();

			deleteRestart = false;

		}

	}

	private void createHowToPlay() {

		howTo = new Sprite(GameActivity.CAMERA_WIDTH * 75 / 100,

		GameActivity.CAMERA_HEIGHT * 50 / 100,

		resourcesManager.questionMark, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				switch (pSceneTouchEvent.getAction()) {

				case TouchEvent.ACTION_DOWN:

					this.setScale(1.25f);

					break;

				case TouchEvent.ACTION_UP:

					this.setScale(1.0f);

					break;

				}

				return true;

			}

		};

		registerTouchArea(howTo);

		howTo.setHeight(GameActivity.CAMERA_HEIGHT * 14 / 100);

		howTo.setWidth(GameActivity.CAMERA_WIDTH * 12 / 100);

		attachChild(howTo);

		delSprites.add(new delSprites(howTo));

	}

	public void createTitle() {

		title = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 60 / 100,

		GameActivity.CAMERA_HEIGHT * 38 / 100, resourcesManager.title,

		vbom);

		title.setPosition(GameActivity.CAMERA_WIDTH / 2 - title.getWidth() / 2,

		GameActivity.CAMERA_HEIGHT * 15 / 100);

		delSprites.add(new delSprites(title));

		attachChild(title);

	}

	public void createPlayButton() {

		// deleteRestart = false;

		// deleteGame = false;

		final IEaseFunction[] currentEaseFunctionsSet = EASEFUNCTIONS[3];

		playButton = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 24 / 100,

		GameActivity.CAMERA_HEIGHT * 33 / 100,

		resourcesManager.playButton, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				switch (pSceneTouchEvent.getAction()) {

				case TouchEvent.ACTION_DOWN:

					// movecraneupL();

					this.setScale(1.25f);

					break;

				case TouchEvent.ACTION_UP:

					// uped = false;

					this.setScale(1.0f);

					// onInfiniteGasButtonClicked();

					createGameScene();

					backpress = false;

					heliDis = 0;

					loop = 0;

					speed = 5.0f;

					// createTapPlay();

					unregisterTouchArea(playButton);

					unregisterTouchArea(highButton);

					unregisterTouchArea(leaderButton);

					unregisterTouchArea(howTo);

					playButton.setVisible(false);

					highButton.setVisible(false);

					leaderButton.setVisible(false);

					howTo.setVisible(false);

					title.setVisible(false);

					deleteMenu = true;

					break;

				}

				return true;

			}

		};

		playButton.setPosition(

		GameActivity.CAMERA_WIDTH / 2 - playButton.getWidth() / 2,

		GameActivity.CAMERA_HEIGHT * 70 / 100);

		registerTouchArea(playButton);

		attachChild(playButton);

		delSprites.add(new delSprites(playButton));

	}

	public void createHighButton() {

		highButton = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 35 / 100,

		GameActivity.CAMERA_HEIGHT * 19.5f / 100,

		resourcesManager.highButton, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				switch (pSceneTouchEvent.getAction()) {

				case TouchEvent.ACTION_DOWN:

					// movecraneupL();

					this.setScale(1.25f);

					break;

				case TouchEvent.ACTION_UP:

					// uped = false;

					this.setScale(1.0f);

					break;

				}

				return true;

			}

		};

		highButton.setPosition(

		GameActivity.CAMERA_WIDTH / 2 - highButton.getWidth() / 2

		+ GameActivity.CAMERA_WIDTH * 20 / 100,

		GameActivity.CAMERA_HEIGHT * 67 / 100);

		registerTouchArea(highButton);

		delSprites.add(new delSprites(highButton));

		attachChild(highButton);

	}

	public void createLeaderButton() {

		leaderButton = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 35 / 100,

		GameActivity.CAMERA_HEIGHT * 19.5f / 100,

		resourcesManager.leaderButton, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				switch (pSceneTouchEvent.getAction()) {

				case TouchEvent.ACTION_DOWN:

					// movecraneupL();

					this.setScale(1.25f);

					break;

				case TouchEvent.ACTION_UP:

					// uped = false;

					this.setScale(1.0f);

					break;

				}

				return true;

			}

		};

		leaderButton.setPosition(

		GameActivity.CAMERA_WIDTH / 2 - leaderButton.getWidth() / 2

		- GameActivity.CAMERA_WIDTH * 20 / 100,

		GameActivity.CAMERA_HEIGHT * 67 / 100);

		registerTouchArea(leaderButton);

		attachChild(leaderButton);

		delSprites.add(new delSprites(leaderButton));

	}

	public void createGameScene() {

		gameOverCheck = false;

		itsGame = true;

		itsMenu = false;

		itsRestart = false;

		createRays();

		createPauseModule();

		// writeScore();

		// writeDistance();

	}

	// modules are here -->

	public void createBackground() {

		Sprite background = new Sprite(0, 0, GameActivity.CAMERA_WIDTH,

		GameActivity.CAMERA_HEIGHT, resourcesManager.gameBackground,

		vbom);

		attachChild(background);

		Sprite cloudBackground = new Sprite(0, 0,

		resourcesManager.cloudBackground, vbom);

		// AutoParallaxBackground parallaxBackground = new

		// AutoParallaxBackground(

		// 0.517f, 0.835f, 1.0f, 20);

		// parallaxBackground.setColor(new Color(132, 213, 255));

		// parallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f,

		// new Sprite(0, 0, resourcesManager.gameBackground, vbom)));

		// parallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new

		// Sprite(0, 80, this.mParallaxLayerMid, vertexBufferObjectManager)));

		// this.setBackground(parallaxBackground);

		// this.setBackgroundEnabled(true);

		// attachChild(cloudBackground);

	}

	public void createMilk() {

		milk = new Sprite(0, 0, GameActivity.CAMERA_WIDTH,

		GameActivity.CAMERA_HEIGHT * 20 / 100, resourcesManager.milk,

		vbom);

		milk.setY(GameActivity.CAMERA_HEIGHT - milk.getHeight());

		attachChild(milk);

		delSpritesGame.add(new delSprites(milk));

	}

	public void createShop() {

		shopIcon = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 20 / 100,

		GameActivity.CAMERA_WIDTH * 16 / 100,

		resourcesManager.shopIcon, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				// TODO Auto-generated method stub

				if (pSceneTouchEvent.isActionDown()) {

					this.setScale(1.25f);

				} else if (pSceneTouchEvent.isActionUp()) {

					//

					// if(mSubscribedToInfiniteGas){
					Intent intent = new Intent(GameActivity.getInstance(),

					ShopList.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtra("EXIT", true);

					GameActivity.getInstance().startActivity(intent);

					// }else{

					// onInfiniteGasButtonClicked();

					// }

					this.setScale(1.0f);

				}

				return true;

			}

		};

		if (!isTablet(GameActivity.getInstance())) {
			shopIcon.setY((GameActivity.CAMERA_HEIGHT * 80 / 100) - 80);
		} else {
			shopIcon.setY((GameActivity.CAMERA_HEIGHT * 80 / 100) - 20);
		}

		shopIcon.setX(GameActivity.CAMERA_WIDTH / 5 * 2 - shopIcon.getWidth());

		registerTouchArea(shopIcon);

		attachChild(shopIcon);

		delSpritesGame.add(new delSprites(shopIcon));

	}

	private void postMessageInThread(final String msg) {
		Thread t = new Thread() {
			public void run() {
//				try {
////					GameActivity.getInstance().share(msg);
//
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
			}
		};
		t.start();
	}

    


	public void createRanking() {

		rankIcon = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 20 / 100,

		GameActivity.CAMERA_WIDTH * 16 / 100,

		resourcesManager.rankIcon, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				// TODO Auto-generated method stub

				if (pSceneTouchEvent.isActionDown()) {

					this.setScale(1.25f);

				} else if (pSceneTouchEvent.isActionUp()) {

					
					postMessageInThread("This is my score in Corrupt Puzzle : '" + cookieCount.getText() + "'. Do you think you can beat me!");
					
					//
					// if(getmGoogleApi().isConnected())
					// GameActivity.getInstance().startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getmGoogleApi(),
					//
					// LEADERBOARD_ID), REQUEST_LEADERBOARD );

					// if(mSubscribedToInfiniteGas){

					// GameActivity.getInstance().startActivity(
					//
					// new Intent(GameActivity.getInstance(),
					//
					// ShopList.class));

					// }else{

					// onInfiniteGasButtonClicked();

					// }

					this.setScale(1.0f);

				}

				return true;

			}

		};

		if (!isTablet(GameActivity.getInstance())) {

			rankIcon.setY((GameActivity.CAMERA_HEIGHT * 80 / 100) - 80);
		} else {
			rankIcon.setY((GameActivity.CAMERA_HEIGHT * 80 / 100) - 20);
		}
		rankIcon.setX(GameActivity.CAMERA_WIDTH
				- (GameActivity.CAMERA_WIDTH / 5 * 2));

		registerTouchArea(rankIcon);

		attachChild(rankIcon);

		delSpritesGame.add(new delSprites(rankIcon));

	}

	public boolean isTablet(Context context) {
		boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
		boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
		return (xlarge || large);
	}

	public void createSavedGames() {

		savedGamesIcon = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 20 / 100,

		GameActivity.CAMERA_WIDTH * 16 / 100,

		resourcesManager.savedGameIcon, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				// TODO Auto-generated method stub

				if (pSceneTouchEvent.isActionDown()) {

					this.setScale(1.25f);

				} else if (pSceneTouchEvent.isActionUp()) {

					//
					// if(getmGoogleApi().isConnected())
					// GameActivity.getInstance().startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getmGoogleApi(),
					//
					// LEADERBOARD_ID), REQUEST_LEADERBOARD );

					// if(mSubscribedToInfiniteGas){

					// GameActivity.getInstance().startActivity(
					//
					// new Intent(GameActivity.getInstance(),
					//
					// ShopList.class));

					// }else{

					// onInfiniteGasButtonClicked();

					// }

					this.setScale(1.0f);

				}

				return true;

			}

		};

		savedGamesIcon.setY((GameActivity.CAMERA_HEIGHT * 80 / 100) - 80);

		savedGamesIcon.setX(GameActivity.CAMERA_WIDTH / 5 * 3
				- (GameActivity.CAMERA_WIDTH));

		registerTouchArea(savedGamesIcon);

		attachChild(savedGamesIcon);

		delSpritesGame.add(new delSprites(savedGamesIcon));

	}

	public void createRays() {

		raysSprite = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 150 / 100,

		GameActivity.CAMERA_WIDTH * 150 / 100,

		resourcesManager.cookie_rays, vbom);

		raysSprite.setY(GameActivity.CAMERA_HEIGHT / 2 - raysSprite.getHeight()

		/ 2);

		raysSprite.setX(GameActivity.CAMERA_WIDTH / 2 - raysSprite.getWidth()

		/ 2);

		attachChild(raysSprite);

		delSpritesGame.add(new delSprites(raysSprite));

	}

	public void createPauseModule() {

		pauseButton = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 10 / 100,

		GameActivity.CAMERA_HEIGHT * 13 / 100, resourcesManager.pause,

		vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (startGame) {

					switch (pSceneTouchEvent.getAction()) {

					case TouchEvent.ACTION_DOWN:

						this.setScale(1.25f);

						createResume();

						startGame = false;

						paused();

						break;

					case TouchEvent.ACTION_UP:

						this.setScale(1.0f);

						break;

					}

				}

				return true;

			}

		};

		registerTouchArea(pauseButton);

		pauseButton.setY(GameActivity.CAMERA_HEIGHT - pauseButton.getHeight());

		pauseButton.setX(GameActivity.CAMERA_WIDTH - pauseButton.getWidth());

		attachChild(pauseButton);

		delSpritesGame.add(new delSprites(pauseButton));

	}

	public void createTapPlay() {

		tapPlay = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 40 / 100,

		GameActivity.CAMERA_HEIGHT * 15 / 100,

		resourcesManager.tapPlay, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {

//					heli_body.setGravityScale(4);

					startGame = true;

					// SceneManager.getInstance().menuScene.setIgnoreUpdate(false);

					this.setVisible(false);

					this.setIgnoreUpdate(true);

					GameActivity.getInstance().runOnUpdateThread(

					new Runnable() {

						@Override
						public void run() {

							detachChild(tapPlay);

							unregisterTouchArea(tapPlay);

						}

					});

				}

				return true;

			}

		};

		registerTouchArea(tapPlay);

		tapPlay.setY(GameActivity.CAMERA_HEIGHT / 2 - tapPlay.getHeight() / 2);

		tapPlay.setX(GameActivity.CAMERA_WIDTH / 2 - tapPlay.getWidth() / 2);

		attachChild(tapPlay);

	}

	public void createClouds() {

		int[] h = { 22, 32, 50, 60 };

		int rnd = new Random().nextInt(h.length);

		int y = h[rnd];

		cloud = new Sprite(GameActivity.CAMERA_WIDTH,

		GameActivity.CAMERA_HEIGHT * y / 100,

		(GameActivity.CAMERA_WIDTH) * 18 / 100,

		(GameActivity.CAMERA_HEIGHT) * 14 / 100,

		resourcesManager.cloud1, vbom);

		// rect.setAlpha(0.8f);

		float height = cloud.getHeightScaled() / 32;

		float width = cloud.getWidthScaled() / 32;

		final Vector2[] vertices = {

		new Vector2(-0.49689f * width, -0.03499f * height),

		new Vector2(-0.02484f * width, -0.48397f * height),

		new Vector2(+0.12836f * width, -0.42566f * height),

		new Vector2(+0.34369f * width, -0.23324f * height),

		new Vector2(+0.48033f * width, -0.03499f * height),

		new Vector2(+0.27329f * width, +0.25656f * height),

		new Vector2(+0.07867f * width, +0.37318f * height),

		new Vector2(-0.25259f * width, +0.31487f * height) };

		cloud_body = PhysicsFactory.createPolygonBody(physicsWorld, cloud,

		vertices, BodyType.KinematicBody,

		PhysicsFactory.createFixtureDef(0, 0, 0));

		cloud_body.setFixedRotation(false);

		physicsWorld.registerPhysicsConnector(new PhysicsConnector(cloud,

		cloud_body, true, true));

		cloud_body.setUserData("cloud1");

		cloud_body.setLinearVelocity(new Vector2(-(speed + 2), 0));

		cloud_body.setUserData("cloud");

		attachChild(cloud);

		delSpritesGame.add(new delSprites(cloud));

		deleteBody.add(new DelClouds(cloud_body));

	}

	public void createCookieCounter() {

		cookieDashboard = new Sprite(0, 0, GameActivity.CAMERA_WIDTH,

		GameActivity.CAMERA_HEIGHT * 20 / 100,

		resourcesManager.trans_bg, vbom);

		attachChild(cookieDashboard);

	}

	public void createGameover() {

		gameFinished = true;

		helicopterReady = false;

		itsGame = false;

		itsMenu = false;

		itsRestart = true;

		trans_bg = new Sprite(0, 0, GameActivity.CAMERA_WIDTH,

		GameActivity.CAMERA_HEIGHT, resourcesManager.trans_bg, vbom);

		delSpritesRestart.add(new delSprites(trans_bg));

		attachChild(trans_bg);

		gameOver = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 50 / 100,

		GameActivity.CAMERA_HEIGHT * 20 / 100,

		resourcesManager.gameOver, vbom);

		gameOver.setY(GameActivity.CAMERA_HEIGHT / 2 - gameOver.getHeight());

		gameOver.setX(GameActivity.CAMERA_WIDTH / 2 - gameOver.getWidth() / 2);

		delSpritesRestart.add(new delSprites(gameOver));

		attachChild(gameOver);

		menuButton = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 20 / 100,

		GameActivity.CAMERA_HEIGHT * 10 / 100,

		resourcesManager.menuButton, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {

					this.setScale(1.25f);

					this.unregisterUpdateHandler(menuButton);

					unregisterTouchArea(menuButton);

					this.setIgnoreUpdate(true);

					retryButton.setIgnoreUpdate(true);

					trans_bg.setIgnoreUpdate(true);

					gameOver.setIgnoreUpdate(true);

					this.setVisible(false);

					retryButton.setVisible(false);

					trans_bg.setVisible(false);

					gameOver.setVisible(false);

					GameActivity.getInstance().runOnUiThread(new Runnable() {

						@Override
						public void run() {

							detachChild(menuButton);

							detachChild(trans_bg);

							detachChild(retryButton);

							detachChild(gameOver);

						}

					});

					gameFinished = false;

					createMenu();

					SceneManager.getInstance().menuScene.setIgnoreUpdate(false);

					pause = false;

				} else if (pSceneTouchEvent.isActionUp()) {

					this.setScale(1.0f);

				}

				return true;

			}

		};

		// attachChild(menuButton);

		menuButton

		.setY(gameOver.getY() + GameActivity.CAMERA_HEIGHT * 30 / 100);

		menuButton.setX(gameOver.getX());

		retryButton = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 20 / 100,

		GameActivity.CAMERA_HEIGHT * 10 / 100,

		resourcesManager.retryButton, vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {

					gameFinished = false;

					heliDis = 0;

					loop = 0;

					speed = 5.0f;

					this.unregisterUpdateHandler(retryButton);

					unregisterTouchArea(retryButton);

					deleteRestart = true;

					createGameScene();

					// createTapPlay();

					SceneManager.getInstance().menuScene.setIgnoreUpdate(false);

					pause = false;

					this.setScale(1.25f);

				} else if (pSceneTouchEvent.isActionUp()) {

					this.setScale(1.0f);

				}

				return true;

			}

		};

		delSpritesRestart.add(new delSprites(retryButton));

		attachChild(retryButton);

		retryButton.setY(gameOver.getY() + GameActivity.CAMERA_HEIGHT * 30

		/ 100);

		retryButton.setX(GameActivity.CAMERA_WIDTH / 2 - retryButton.getWidth()

		/ 2);

		// registerTouchArea(menuButton);

		registerTouchArea(retryButton);

	}

	public void createBlast(float x, float y) {

		deleteGame = true;

		startGame = false;

		gameOverCheck = true;

		blast = new AnimatedSprite(0, 0, GameActivity.CAMERA_WIDTH * 20 / 100,

		GameActivity.CAMERA_HEIGHT * 20 / 100,

		resourcesManager.blast_Sprite, vbom) {

			@Override
			protected void onManagedUpdate(float pSecondsElapsed) {

				// TODO Auto-generated method stub

				super.onManagedUpdate(pSecondsElapsed);

				if (this.getCurrentTileIndex() == 9) {

					final Sprite sp = this;

					this.setVisible(false);

					this.setIgnoreUpdate(true);

					GameActivity.getInstance().runOnUiThread(new Runnable() {

						public void run() {

							detachChild(sp);

						}

					});

					createGameover();

				}

			}

		};

		long[] Long = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };

		blast.setPosition(cookie.getX() + cookie.getWidth() / 2, cookie.getY()

		+ cookie.getHeight() / 2);

		blast.animate(Long, 0, 9, false);

		attachChild(blast);

	}

	public void moveText(float x, final float y) {

		text = new Text(x, y, resourcesManager.bigFont, "+1", vbom);

		attachChild(text);

		delMoveText.add(new MoveText(text, y));

	}

	public void createCookie() {

		final IEaseFunction[] currentEaseFunctionsSet = EASEFUNCTIONS[3];

		final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(1,

		0.5f, 0.5f);

		cookie = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 80 / 100,

		GameActivity.CAMERA_WIDTH * 80 / 100, resourcesManager.cookie,

		vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				// TODO Auto-generated method stub

				if (pSceneTouchEvent.isActionDown()) {

					this.setScale(0.95f);

					cookiCount = application.getBombs();
					;

					// if(application.isLoggedIn()){

					cookiCount += 1;
					// }

					application.setBombs(cookiCount);

					moveText(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());

				} else if (pSceneTouchEvent.isActionUp()) {

					this.setScale(1.0f);

				}

				return true;

			}

		};

		// registerTouchArea(helicopter);

		cookie.setY((float) (GameActivity.CAMERA_HEIGHT / 2.2 - cookie
				.getHeight() / 2));

		cookie.setX(GameActivity.CAMERA_WIDTH / 2 - cookie.getWidth() / 2);

		registerTouchArea(cookie);

		attachChild(cookie);

		delSpritesGame.add(new delSprites(cookie));

		// attachChild(new Box2dDebugRenderer(physicsWorld, vbom));

	}

	public void createFloor() {

		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,

		0.5f, 0.5f);

		final Body floor_body;

		final Rectangle floor = new Rectangle(0, GameActivity.CAMERA_HEIGHT,

		GameActivity.CAMERA_WIDTH, 1, this.vbom);

		floor_body = PhysicsFactory.createBoxBody(physicsWorld, floor,

		BodyType.StaticBody, wallFixtureDef);

		floor_body.setUserData("floor");

		floor.setUserData("floor");

		attachChild(floor);

	}

	public void createResume() {

		resume = new Sprite(0, 0, GameActivity.CAMERA_WIDTH * 40 / 100,

		GameActivity.CAMERA_HEIGHT * 45 / 100, resourcesManager.resume,

		vbom) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,

			float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionUp()) {

					this.setVisible(false);

					this.setIgnoreUpdate(true);

					GameActivity.getInstance().runOnUpdateThread(

					new Runnable() {

						@Override
						public void run() {

							detachChild(resume);

							startGame = true;

						}

					});

					SceneManager.getInstance().menuScene.setIgnoreUpdate(false);

					pause = false;

					unregisterTouchArea(resume);

				}

				return true;

			}

		};

		resume.setY(GameActivity.CAMERA_HEIGHT / 2 - resume.getHeight() / 2);

		resume.setX(GameActivity.CAMERA_WIDTH / 2 - resume.getWidth() / 2);

		registerTouchArea(resume);

		attachChild(resume);

		delSpritesGame.add(new delSprites(resume));

	}

	public void createRoof() {

		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,

		0.5f, 0.5f);

		final Body roof_body;

		final Rectangle roof = new Rectangle(0, 0, GameActivity.CAMERA_WIDTH,

		0.5f, this.vbom);

		roof_body = PhysicsFactory.createBoxBody(physicsWorld, roof,

		BodyType.StaticBody, wallFixtureDef);

		roof_body.setUserData("roof");

		attachChild(roof);

	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,

	float pMenuItemLocalX, float pMenuItemLocalY) {

		// TODO Auto-generated method stub

		return false;

	}

	@Override
	public void onBackKeyPressed() {

		if (!pause) {

			if (itsMenu) {

				System.exit(0);

			} else if (itsGame || itsRestart) {

				gameOverCheck = true;

				backpress = true;

				// deleteRestart = true;

				// deleteGame = true;

				startGame = false;

				// onInfiniteGasButtonClicked();

				createMenu();

			} else if (!itsGame && !itsMenu && !itsRestart) {

				System.exit(0);

			}

			//

		} else {

			GameActivity.getInstance().runOnUiThread(new Runnable() {

				@Override
				public void run() {

					Toast.makeText(GameActivity.getInstance(),

					"Resume Game First", Toast.LENGTH_SHORT).show();

				}

			});

		}

	}

	@Override
	public SceneType getSceneType() {

		// TODO Auto-generated method stub

		return null;

	}

	@Override
	public void disposeScene() {

		// TODO Auto-generated method stub

	}

	public void paused() {

		pause = true;

		resourcesManager.music.pause();

		SceneManager.getInstance().menuScene.setIgnoreUpdate(true);

	}
	public void resumed() {

		pause = false;

//		resourcesManager.music.pause();

		SceneManager.getInstance().menuScene.setIgnoreUpdate(false);

	}
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

		if (pSceneTouchEvent.isActionDown() && !pause && helicopterReady) {

			// fly();

			if (!startGame && heli_body != null) {

				// tapPlay.setVisible(false);

				// tapPlay.setIgnoreUpdate(true);

				GameActivity.getInstance().runOnUpdateThread(new Runnable() {

					@Override
					public void run() {

						// detachChild(tapPlay);

						// unregisterTouchArea(tapPlay);

					}

				});

				// unregisterTouchArea(this);

//				heli_body.setGravityScale(4);

				createClouds();

				startGame = true;

			}

			if (startGame) {

				fly = true;

			}

			// heli_body.setTransform(heli_body.getWorldCenter(), 0.10f);

		}

		if (pSceneTouchEvent.isActionUp()) {

			// fly();

			// heli_body.setTransform(heli_body.getWorldCenter(), -0.10f);

			fly = false;

		}

		return true;

	}

	public void fly() {

		heli_body.setLinearVelocity(new Vector2(0, -15));

	}

	private static final IEaseFunction[][] EASEFUNCTIONS = new IEaseFunction[][] {

	new IEaseFunction[] { EaseLinear.getInstance(),

	EaseLinear.getInstance(), EaseLinear.getInstance() },

	new IEaseFunction[] { EaseBackIn.getInstance(),

	EaseBackOut.getInstance(), EaseBackInOut.getInstance() },

	new IEaseFunction[] { EaseBounceIn.getInstance(),

	EaseBounceOut.getInstance(), EaseBounceInOut.getInstance() },

	new IEaseFunction[] { EaseCircularIn.getInstance(),

	EaseCircularOut.getInstance(),

	EaseCircularInOut.getInstance() },

	new IEaseFunction[] { EaseCubicIn.getInstance(),

	EaseCubicOut.getInstance(), EaseCubicInOut.getInstance() },

	new IEaseFunction[] { EaseElasticIn.getInstance(),

	EaseElasticOut.getInstance(),

	EaseElasticInOut.getInstance() },

	new IEaseFunction[] { EaseExponentialIn.getInstance(),

	EaseExponentialOut.getInstance(),

	EaseExponentialInOut.getInstance() },

	new IEaseFunction[] { EaseQuadIn.getInstance(),

	EaseQuadOut.getInstance(), EaseQuadInOut.getInstance() },

	new IEaseFunction[] { EaseQuartIn.getInstance(),

	EaseQuartOut.getInstance(), EaseQuartInOut.getInstance() },

	new IEaseFunction[] { EaseQuintIn.getInstance(),

	EaseQuintOut.getInstance(), EaseQuintInOut.getInstance() },

	new IEaseFunction[] { EaseSineIn.getInstance(),

	EaseSineOut.getInstance(), EaseSineInOut.getInstance() },

	new IEaseFunction[] { EaseStrongIn.getInstance(),

	EaseStrongOut.getInstance(), EaseStrongInOut.getInstance() } };

	private ContactListener createContactListener() {

		ContactListener contactListener = new ContactListener() {

			@Override
			public void beginContact(Contact contact) {

				final Fixture x1 = contact.getFixtureA();

				final Fixture x2 = contact.getFixtureB();

				if (x2.getBody().getUserData().equals("helicopter")

				&& x1.getBody().getUserData().equals("floor")

				&& startGame) {

					createBlast(x2.getBody().getTransform().getPosition().x, x2

					.getBody().getTransform().getPosition().y);

					if (!delSpritesGame.isEmpty()) {

						cookie.setVisible(false);

					}

					System.out.println("1 collision");

				}

				else if (x2.getBody().getUserData().equals("helicopter")

				&& x1.getBody().getUserData().equals("roof")

				&& startGame) {

					createBlast(x2.getBody().getTransform().getPosition().x, x2

					.getBody().getTransform().getPosition().y);

					if (!delSpritesGame.isEmpty()) {

						cookie.setVisible(false);

					}

					System.out.println("3 collision");

				}

				else if (x1.getBody().getUserData().equals("helicopter")

				&& x2.getBody().getUserData().equals("cloud")

				&& startGame) {

					createBlast(0, 0);

					System.out.println("2 collision");

					if (!delSpritesGame.isEmpty()) {

						cookie.setVisible(false);

					}

				}

				else if (x2.getBody().getUserData().equals("helicopter")

				&& x1.getBody().getUserData().equals("cloud")

				&& startGame) {

					createBlast(0, 0);

					System.out.println("2n collision");

					if (!delSpritesGame.isEmpty()) {

						cookie.setVisible(false);

					}

				}

			}

			@Override
			public void endContact(Contact contact) {

				// TODO Auto-generated method stub

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {

				// TODO Auto-generated method stub

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

				// TODO Auto-generated method stub

			}

		};

		return contactListener;

	}

	// public final boolean SAVED_GAME = false;
	//
	//
	// public void fillData(String id, String value) {
	//
	//
	// SharedPreferences prefs = GameActivity.getInstance()
	//
	// .getSharedPreferences(
	//
	// GameActivity.getInstance().getApplicationContext()
	//
	// .getPackageName(), Context.MODE_PRIVATE);
	//
	// prefs.edit().putString(id, value).apply();
	//
	//
	//
	//
	// }

	// public String getData(String id) {
	//
	//
	//
	// SharedPreferences prefs = GameActivity.getInstance()
	//
	// .getSharedPreferences(
	//
	// GameActivity.getInstance().getApplicationContext()
	//
	// .getPackageName(), Context.MODE_PRIVATE);
	//
	//
	//
	// return prefs.getString(id, "0");
	//
	//
	//
	//
	//
	//
	//
	// }

	// public double round(double value, int places) {
	//
	// if (places < 0) throw new IllegalArgumentException();
	//
	//
	//
	// long factor = (long) Math.pow(10, places);
	//
	// value = value * factor;
	//
	// long tmp = Math.round(value);
	//
	// return (double) tmp / factor;
	//
	// }

	public void initAchievements() {

		// if(getmGoogleApi().isConnected()){
		// Gra�a Foxter CgkIzdSliMYeEAIQAg

		// Games.Achievements.unlock(mGoogleApiClient, "CgkIzdSliMYeEAIQAg");

		// The F*cking Channel (Rede Globo) CgkIzdSliMYeEAIQAw

		// Games.Achievements.unlock(mGoogleApiClient, "CgkIzdSliMYeEAIQAw");

		// AJUSTES AP�S POSSE DIA 1/1/2015

		// Games.Achievements.increment(getmGoogleApi(), "CgkIzdSliMYeEAIQCA",
		// 1);

		// Maio e ainda n�o = CgkIzdSliMYeEAIQDg

		// Games.Achievements.unlock(mGoogleApiClient, "CgkIzdSliMYeEAIQDg");

		// E AGORA QUE T� TUDO IGUAL !!! - CgkIzdSliMYeEAIQDw

		// Games.Achievements.unlock(mGoogleApiClient, "CgkIzdSliMYeEAIQDw");

		// AA-AND NOW !!! - CgkIzdSliMYeEAIQEA

		// Games.Achievements.unlock(mGoogleApiClient, "CgkIzdSliMYeEAIQEA");

		// TRUCO !!! - CgkIzdSliMYeEAIQEQ

		// Games.Achievements.unlock(mGoogleApiClient, "CgkIzdSliMYeEAIQEQ");

		// IXI - CgkIzdSliMYeEAIQEg

		// Games.Achievements.unlock(mGoogleApiClient, "CgkIzdSliMYeEAIQEg");

		//
		// Field[] everything = R.string.class.getFields();
		//
		//
		// for (int j = 0; j < everything.length; j++) {
		// Field field = everything[j];
		//
		// if(field.getName().indexOf("achievement_")>0){
		// try {
		// Games.Achievements.unlock(getmGoogleApi(), (String)
		// field.get(field.getName()));
		// } catch (IllegalAccessException | IllegalArgumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// }
		//
		//
		// }

	}

	private static int RC_SIGN_IN = 9001;

	public void submitEvent(String eventId) {
		// eventId is taken from the developer console
		String myEventId = eventId;

		// increment the event counter
		// Games.Events.increment(getmGoogleApi(), myEventId, 1);
	}

	public boolean oddNumber(double num) {

		int primo = 0;
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				primo = 1;
			}
		}
		if (primo == 0 && num != 1) {
			// System.out.println("primo");
			return true;
		} else {
			// System.out.println("Nao primo");
			return false;
		}
	}

	/**
	 * Async migrate the data in Cloud Save (stateKey APP_STATE_KEY) to a
	 * Snapshot in the Saved Games service with unique snap
	 * 'Snapshot-{APP_STATE_KEY}'. If no such Snapshot exists, create a Snapshot
	 * and populate all fields. If the Snapshot already exists, update the
	 * appropriate data and metadata. After migrate, the UI will be cleared and
	 * the data will be available to load from Snapshots.
	 */
	private void cloudSaveMigrate() {
		final boolean createIfMissing = true;

		// Note: when migrating your users from Cloud Save to Saved Games, you
		// will need to perform
		// the migration process at most once per device. You should keep track
		// of the migration
		// status locally for each AppState data slot (using SharedPreferences
		// or similar)
		// to avoid repeating network calls or migrating the same AppState data
		// multiple times.

		// Compute SnapshotMetadata fields based on the information available
		// from AppState. In
		// this case there is no data available to auto-generate a description,
		// cover image, or
		// playedTime. It is strongly recommended that you generate unique and
		// meaningful
		// values for these fields based on the data in your app.
		final String snapshotName = makeSnapshotName(APP_STATE_KEY);
		final String description = "Saved game #" + APP_STATE_KEY;
		final long playedTimeMillis = 60 * 60 * 1000;
		final Bitmap bitmap = BitmapFactory.decodeResource(GameActivity
				.getInstance().getResources(), R.drawable.ic_launcher);

		// AsyncTask<Void, Void, Boolean> migrateTask = new AsyncTask<Void,
		// Void, Boolean>() {
		// @Override
		// protected void onPreExecute() {
		// // showProgressDialog("Migrating");
		//
		// }
		//
		// // @Override
		// // protected Boolean doInBackground(Void... params) {
		// // // Get AppState Data
		// // AppStateManager.StateResult load = AppStateManager.load(
		// // getmGoogleApi(), APP_STATE_KEY).await();
		// //
		// // if (!load.getStatus().isSuccess()) {
		// // Log.w(TAG, "Could not load App State for migration.");
		// // return false;
		// // }
		// //
		// // // Get Data from AppState
		// // byte[] data = load.getLoadedResult().getLocalData();
		// //
		// // // Open the snapshot, creating if necessary
		// // OpenSnapshotResult open = Games.Snapshots.open(
		// // getmGoogleApi(), snapshotName, createIfMissing).await();
		// //
		// // if (!open.getStatus().isSuccess()) {
		// // Log.w(TAG, "Could not open Snapshot for migration.");
		// // // TODO: Handle Snapshot conflicts
		// // // Note: one reason for failure to open a Snapshot is conflicting
		// saved games.
		// // // This is outside the scope of this sample, however you should
		// resolve such
		// // // conflicts in your own app by following the steps outlined here:
		// // //
		// https://developers.google.com/games/services/android/savedgames#handling_saved_game_conflicts
		// // return false;
		// // }
		// //
		// // // Write the new data to the snapshot
		// // com.google.android.gms.games.snapshot.Snapshot snapshot =
		// open.getSnapshot();
		// // snapshot.getSnapshotContents().writeBytes(data);
		// //
		// // // Change metadata
		// // SnapshotMetadataChange metadataChange = new
		// SnapshotMetadataChange.Builder()
		// // .fromMetadata(snapshot.getMetadata())
		// // .setCoverImage(bitmap)
		// // .setDescription(description)
		// // .setPlayedTimeMillis(playedTimeMillis)
		// // .build();
		// //
		// // CommitSnapshotResult commit = Games.Snapshots.commitAndClose(
		// // getmGoogleApi(), snapshot, metadataChange).await();
		// //
		// // if (!commit.getStatus().isSuccess()) {
		// // Log.w(TAG, "Failed to commit Snapshot.");
		// // return false;
		// // }
		// //
		// // // No failures
		// // return true;
		// // }
		//
		// @Override
		// protected void onPostExecute(Boolean result) {
		// if (result) {
		// // displayMessage(getString(R.string.cloud_save_migrate_success),
		// false);
		// } else {
		// // displayMessage(getString(R.string.cloud_save_migrate_failure),
		// true);
		// }
		//
		// // dismissProgressDialog();
		// // clearDataUI();
		// }
		// };
		// migrateTask.execute();
	}

	String value = null;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	// Request code used to invoke Snapshot selection UI.
	private static final int RC_SELECT_SNAPSHOT = 9002;

	/**
	 * Generate a unique Snapshot name from an AppState stateKey.
	 * 
	 * @param appStateKey
	 *            the stateKey for the Cloud Save data.
	 * @return a unique Snapshot name that maps to the stateKey.
	 */
	private String makeSnapshotName(int appStateKey) {
		return "Snapshot-" + String.valueOf(appStateKey);
	}

	// public void submitEvent(String eventId) {

	// // eventId is taken from the developer console

	// String myEventId = eventId;

	//

	// // increment the event counter

	// Games.Events.increment(mGoogleApiClient, myEventId, 1);

	// }

	//

	// public void getEvent(){

	//

	// // EventCallback is a subclass of ResultCallback; use this to handle the

	// // query results

	// EventCallback ec = new EventCallback();

	//

	// // Load all events tracked for your game

	// com.google.android.gms.common.api.PendingResult<Events.LoadEventsResult>

	// pr = Games.Events.load(mGoogleApiClient, true);

	// pr.setResultCallback(ec);

	//

	//

	// class EventCallback implements ResultCallback {

	// // Handle the results from the events load call

	// public void onResult(com.google.android.gms.common.api.Result result) {

	// Events.LoadEventsResult r = (Events.LoadEventsResult)result;

	// com.google.android.gms.games.event.EventBuffer eb = r.getEvents();

	//

	// for (int i=0; i < eb.getCount(); i++) {

	// // do something with the events retrieved

	// }

	// eb.close();

	// }

	// }

	// }

}
