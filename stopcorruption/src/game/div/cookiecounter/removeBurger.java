package game.div.cookiecounter;

import com.badlogic.gdx.physics.box2d.Body;

public class removeBurger {
Body body;

public void remove(){
	MainMenuScene.physicsWorld.destroyBody(body);
}

public removeBurger(Body body) {
	super();
	this.body = body;
}
}
