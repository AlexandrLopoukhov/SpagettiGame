package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameWorld extends Stage {

	public GameWorld(final StretchViewport stretchViewport) {
		super(stretchViewport);
		Gdx.app.log("GameWorld", "create");
		addActor(new Background());
		this.addListener(new InputListener() {
			@Override
			public boolean keyDown(final InputEvent event, final int keycode) {
				switch (keycode) {
				case Keys.LEFT:
					Gdx.app.log("GameWorld", Keys.LEFT + "create" + keycode);
					break;
				case Keys.RIGHT:
					break;
				case Keys.UP:
					break;
				case Keys.DOWN:
					break;
				case Keys.SPACE:
					break;
				}
				return super.keyDown(event, keycode);
			}
		});
	}

	@Override
	public void act(final float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}

}
