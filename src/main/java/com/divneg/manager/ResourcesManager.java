package com.divneg.manager;

import game.div.cookiecounter.GameActivity;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;

/**
 * 
 * @author Divyanshu Negi
 * @version 1.0
 * @title : Helicopter Game
 * 
 */

public class ResourcesManager {
	public ITextureRegion splash_region;
	public ITextureRegion menu_background_region;
	public ITextureRegion play_region;
	public ITextureRegion options_region;
	public ITextureRegion title_sprite, lamp_close, lamp_open, craneL, craneR,
			up, down, up2, down2, chamber, box, machine;
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	private BuildableBitmapTextureAtlas conveyorSprite;
	private BitmapTextureAtlas splashTextureAtlas, boxsetAtlas;
	public Music music, burger_sound, finish;

	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------

	private static final ResourcesManager INSTANCE = new ResourcesManager();

	public Engine engine;
	public GameActivity activity;
	public Camera camera;
	public ITiledTextureRegion player_region, conveyor;
	public VertexBufferObjectManager vbom;
	private BuildableBitmapTextureAtlas menuTextureAtlas2;
	public TextureRegion retry;
	public BuildableBitmapTextureAtlas menuItems;
	public TextureRegion burger_title;
	public TextureRegion bg_title;
	public TextureRegion play_title;
	public TextureRegion share_title;
	public TextureRegion rank_title;
	public TextureRegion rush_title;
	public Music blade;
	public Music menu;
	private BuildableBitmapTextureAtlas blastAtlas;
	public TiledTextureRegion blast_Sprite;
	public Font bigFont;
	public Font smallFont;
	public TextureRegion pauseButton;
	public TextureRegion pause;
	public TextureRegion tap;
	public TextureRegion gameBackground;
	public TextureRegion cookie;
	public TextureRegion cookie_rays;
	public TextureRegion score;
	public TextureRegion cloud1;
	public TextureRegion milk;
	public TextureRegion shopIcon;
	public TextureRegion rankIcon;
	public TextureRegion savedGameIcon;
	public TextureRegion cloud4;
	public TextureRegion cloudBackground;
	public TextureRegion title;
	public TextureRegion playButton;
	public TextureRegion highButton;
	public TextureRegion leaderButton;
	public TextureRegion tapPlay;
	public TextureRegion resume;
	public TextureRegion trans_bg;
	public TextureRegion gameOver;
	public TextureRegion retryButton;
	public TextureRegion menuButton;
	public TextureRegion questionMark;

	// ---------------------------------------------
	// TEXTURES & TEXTURE REGIONS
	// ---------------------------------------------

	// ---------------------------------------------
	// CLASS LOGIC
	// ---------------------------------------------

	public void loadMenuResources() {
		loadMenuGraphics();
		loadMenuAudio();
		loadGameFonts();
	}

	public void loadGameResources() {
		loadGameGraphics();
		loadGameFonts();
		loadGameAudio();
	}

	private void loadMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		menuTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 2048, 2048,
				BitmapTextureFormat.RGBA_8888,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);

		menuTextureAtlas2 = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 2048, 2048,
				BitmapTextureFormat.RGBA_8888,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);

		blastAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 2048, 2048,
				BitmapTextureFormat.RGBA_8888,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);
		menuItems = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 2048, 2048,
				BitmapTextureFormat.RGBA_8888,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);
		boxsetAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 128,
				128, BitmapTextureFormat.RGBA_8888,
				TextureOptions.REPEATING_BILINEAR_PREMULTIPLYALPHA);
		conveyorSprite = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 2048, 2048,
				BitmapTextureFormat.RGBA_8888,
				TextureOptions.NEAREST_PREMULTIPLYALPHA);

		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas,
		// activity, "blank.png");
		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas2,
		// activity, "blank.png");
		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(blastAtlas,
		// activity, "blank.png");
		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuItems,
		// activity, "blank.png");
		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(conveyorSprite,
		// activity, "blank.png");

		gameBackground = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity,
						"gameBackground.png");
		cookie_rays = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "cookie_rays.png");
		pause = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "pause.png");
		score = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "score.png");
		cloud1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "cloud1.png");
		// cloud2 =
		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas,
		// activity, "cloud1.png");
		shopIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "shopIcon.png");
		rankIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "rankIcon.png");
		savedGameIcon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "rankIcon.png");
		milk = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "milk.png");
		cookie = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "cookie.png");
		cloudBackground = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas2, activity, "cloudGFX.png");

		title = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "title.png");
		playButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "playButton.png");
		highButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "highButton.png");
		leaderButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "leaderboardButton.png");
		tapPlay = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "tapPlay.png");
		resume = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "resume.png");
		trans_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "trans_bg.png");
		gameOver = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "gameOver.png");
		menuButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "menuButton.png");
		retryButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "retryButton.png");
		questionMark = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas2, activity, "questionMark.png");
		blast_Sprite = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(menuItems, activity, "blast.png", 10, 1);
		try {
			this.menuTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.menuTextureAtlas.load();
			this.menuTextureAtlas2
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.menuTextureAtlas2.load();
			this.menuItems
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.menuItems.load();
			this.blastAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.blastAtlas.load();
			// this.boxsetAtlas.build(new
			// BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
			// BitmapTextureAtlas>(0, 1, 0));
			this.boxsetAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}

	private void loadMenuAudio() {
		try {

			music = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
					activity, "sound/looped.wav");
			music.setLooping(true);
			burger_sound = MusicFactory.createMusicFromAsset(
					engine.getMusicManager(), activity, "sound/burger.ogg");
			burger_sound.setLooping(false);
			finish = MusicFactory.createMusicFromAsset(
					engine.getMusicManager(), activity, "sound/finish.wav");
			finish.setLooping(false);
			blade = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
					activity, "sound/blades.ogg");
			blade.setLooping(false);
			menu = MusicFactory.createMusicFromAsset(engine.getMusicManager(),
					activity, "sound/menu.ogg");
			menu.setLooping(false);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadGameGraphics() {

	}

	private void loadGameFonts() {
		FontFactory.setAssetBasePath("font/");

		final ITexture fontTexture = new BitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		final ITexture fontTexture2 = new BitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		if(!isTablet(GameActivity.getInstance())){
		bigFont = FontFactory.createFromAsset(engine.getFontManager(),
				fontTexture, activity.getAssets(), "kavoon.otf", 100, true,
				Color.WHITE);

		smallFont = FontFactory.createFromAsset(engine.getFontManager(),
				fontTexture2, activity.getAssets(), "kavoon.otf", 70, true,
				Color.YELLOW);
		}else{
			bigFont = FontFactory.createFromAsset(engine.getFontManager(),
					fontTexture, activity.getAssets(), "kavoon.otf", 30, true,
					Color.WHITE);

			smallFont = FontFactory.createFromAsset(engine.getFontManager(),
					fontTexture2, activity.getAssets(), "kavoon.otf", 20, true,
					Color.YELLOW);
		}

		bigFont.load();
		smallFont.load();
	}
	
	private final int ORANGE = 0xFFFF3300;
	
	public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

	private void loadGameAudio() {

	}

	public void loadSplashScreen() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 300, 152, TextureOptions.BILINEAR);
		splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				splashTextureAtlas, activity, "splash.png", 0, 0);
		splashTextureAtlas.load();

	}

	public void unloadSplashScreen() {
		splashTextureAtlas.unload();
		splash_region = null;
	}

	/**
	 * @param engine
	 * @param activity
	 * @param camera
	 * @param vbom
	 * <br>
	 * <br>
	 *            We use this method at beginning of game loading, to prepare
	 *            Resources Manager properly, setting all needed parameters, so
	 *            we can latter access them from different classes (eg. scenes)
	 */
	public static void prepareManager(Engine engine, GameActivity activity,
			Camera camera, VertexBufferObjectManager vbom) {
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}

	// ---------------------------------------------
	// GETTERS AND SETTERS
	// ---------------------------------------------

	public static ResourcesManager getInstance() {
		return INSTANCE;
	}
}