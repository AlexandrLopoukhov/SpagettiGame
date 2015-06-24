package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameWorld extends Stage {

	SpagettiMonstr _monstr;
	UFO _ufo = new UFO();// /

	public GameWorld(final StretchViewport stretchViewport) {
		super(stretchViewport);
		Gdx.app.log("GameWorld", "create");
		addActor(new Background());
		_monstr = new SpagettiMonstr();
		addActor(_monstr);
		addActor(_ufo);// /

		this.addListener(new InputListener() {
			@Override
			public boolean keyDown(final InputEvent event, final int keycode) {
				switch (keycode) {
				case Keys.LEFT:
					_monstr.changeVelocity(-_monstr.getSpeed(), 0);
					break;
				case Keys.RIGHT:
					_monstr.changeVelocity(_monstr.getSpeed(), 0);
					break;
				case Keys.UP:
					_monstr.changeVelocity(0, _monstr.getSpeed());
					break;
				case Keys.DOWN:
					_monstr.changeVelocity(0, -_monstr.getSpeed());
					break;
				case Keys.SPACE:
					break;
				}
				return super.keyDown(event, keycode);
			}

			@Override
			public boolean keyUp(final InputEvent event, final int keycode) {
				Gdx.app.log("GameWorld", "reset speed");
				_monstr.changeVelocity(0, 0);
				return super.keyUp(event, keycode);
			}
		});
	}

	@Override
	public void act(final float delta) {
		if (_monstr.isOverlaps(_ufo.getBody())) {// /
			Gdx.app.log("GW", "intersect");// /
		}// /
		super.act(delta);
	}

}
