package game.div.cookiecounter;

import org.andengine.entity.text.Text;

public class MoveText {

		Text t1;
		float y;
		int alpha = 1;
		
	public void moveFont() {
		t1.setY(y);
	}
	
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void deleteFont(){
		t1.setVisible(false);
		t1.setIgnoreUpdate(true);
		GameActivity.getInstance().runOnUpdateThread(new Runnable() {

			@Override
			public void run() {

				MainMenuScene.mms.detachChild(t1);
			}
		});

	}

	public MoveText(Text t1,float y) {
		super();
		this.t1 = t1;
		this.y= y;
	}

}
