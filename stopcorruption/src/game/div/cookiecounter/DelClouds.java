package game.div.cookiecounter;

import com.badlogic.gdx.physics.box2d.Body;

public class DelClouds {
	Body b1;
	
	public void delete()
	{
		GameActivity.getInstance().runOnUpdateThread(new Runnable() {
			
			@Override
			public void run() {
			    MainMenuScene.physicsWorld.destroyBody(b1);
				
			}
		});

	}

	public DelClouds(Body b1) {
		super();
		this.b1 = b1;
	}
}
