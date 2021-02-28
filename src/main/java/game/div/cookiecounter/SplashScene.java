package game.div.cookiecounter;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.divneg.base.BaseScene;
import com.divneg.manager.SceneManager.SceneType;

/**
 * @author Mateusz Mysliwiec
 * @author www.matim-dev.com
 * @version 1.0
 */
public class SplashScene extends BaseScene
{
	private Sprite splash;
    @Override
    public void createScene()
    {
    	splash = new Sprite(0, 0, resourcesManager.splash_region, vbom)
    	{
    	    @Override
    	    protected void preDraw(GLState pGLState, Camera pCamera) 
    	    {
    	       super.preDraw(pGLState, pCamera);
    	       pGLState.enableDither();
    	    }
    	};
    	        
    	splash.setScale(1.2f);
    	splash.setPosition((GameActivity.CAMERA_WIDTH-splash.getWidth())/2, (GameActivity.CAMERA_HEIGHT-splash.getHeight())/2);
    	attachChild(splash);
    }

    @Override
    public void onBackKeyPressed()
    {

    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_SPLASH;
    }

    @Override
    public void disposeScene()
    {
        splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
    }

	}