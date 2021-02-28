package game.div.cookiecounter;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;

public class HelicopterStuff {

	Body heli_body;
	Sprite heli_sprite;
	public Body getHeli_body() {
		return heli_body;
	}
	public void setHeli_body(Body heli_body) {
		this.heli_body = heli_body;
	}
	public Sprite getHeli_sprite() {
		return heli_sprite;
	}
	public void setHeli_sprite(Sprite heli_sprite) {
		this.heli_sprite = heli_sprite;
	}
	public HelicopterStuff(Body heli_body, Sprite heli_sprite) {
		super();
		this.heli_body = heli_body;
		this.heli_sprite = heli_sprite;
	}
	
}
