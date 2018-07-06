package game.div.cookiecounter;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

public class delSprites {

	Sprite s1;
	Text t1;

	public void delete() {
		s1.setVisible(false);
		s1.setIgnoreUpdate(true);
		MainMenuScene.mms.unregisterTouchArea(s1);
		GameActivity.getInstance().runOnUpdateThread(new Runnable() {

			@Override
			public void run() {

				MainMenuScene.mms.detachChild(s1);
			}
		});

	}
	
	public void deleteFont() {
		t1.setVisible(false);
		t1.setIgnoreUpdate(true);
		GameActivity.getInstance().runOnUpdateThread(new Runnable() {

			@Override
			public void run() {

				MainMenuScene.mms.detachChild(t1);
			}
		});

	}

	public delSprites(Sprite s1) {
		super();
		this.s1 = s1;
	}
	
	public delSprites(Text t1) {
		super();
		this.t1 = t1;
	}

}
