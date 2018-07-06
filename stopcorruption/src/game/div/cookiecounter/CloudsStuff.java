package game.div.cookiecounter;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;

public class CloudsStuff {

	Body cloud_b;
	Sprite cloud_s;
	public CloudsStuff(Body cloud_b, Sprite cloud_s) {
		super();
		this.cloud_b = cloud_b;
		this.cloud_s = cloud_s;
	}
	public Body getCloud_b() {
		return cloud_b;
	}
	public void setCloud_b(Body cloud_b) {
		this.cloud_b = cloud_b;
	}
	public Sprite getCloud_s() {
		return cloud_s;
	}
	public void setCloud_s(Sprite cloud_s) {
		this.cloud_s = cloud_s;
	}
}
