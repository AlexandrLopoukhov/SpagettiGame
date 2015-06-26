package game.model;

import java.util.Random;
import game.controller.AssetLoader;
import game.controller.WalkingController;
import game.screens.GameScreen;
import game.screens.Meatball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameWorld extends Stage {
	SpagettiMonstr _monstr;
	WalkingController _control;
	float _gameTime = 0;

	public GameWorld(final StretchViewport stretchViewport) {
		super(stretchViewport);
		AssetLoader.loadAsset();
		new SheduleGenerateEnemy().start();
		Gdx.app.log("GameWorld", "create");
		addActor(new Background());
		_monstr = new SpagettiMonstr();
		_control = new WalkingController(new Vector2(GameScreen.GAME_WIDTH
				- GameScreen.GAME_WIDTH, GameScreen.GAME_HEIGHT
				- GameScreen.GAME_HEIGHT), this);
		addActor(_monstr);
		addActor(_control);

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
					addActor(new Meatball(_monstr.getX(), _monstr.getY()));
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

	public SpagettiMonstr getMonstr() {
		return _monstr;
	}

	public void setMonstr(final SpagettiMonstr monstr) {
		_monstr = monstr;
	}

	@Override
	public void act(final float delta) {
		_gameTime += delta;
		Gdx.app.log("GW", "" + _gameTime);
		collisionCheck();

		super.act(delta);

	}

	private void collisionCheck() {
		// Actor[] unitArray = this.getActors().items;
		// for (int i = 0; i < unitArray.length; i++) {
		//
		// for (int j = 0; j < unitArray.length; j++) {
		//
		// if ((((unitArray[i] instanceof Meatball) && (unitArray[j] instanceof
		// UFO)) || ((unitArray[i] instanceof SpagettiMonstr) && (unitArray[j]
		// instanceof UFO)))
		// && ((GameUnit) unitArray[i])
		// .isOverlaps(((GameUnit) unitArray[j]).getBody())) {
		// Gdx.app.log("Intersetct", unitArray[i].getClass()
		// + " with " + unitArray[j].getClass());
		// ((GameUnit) unitArray[i]).kill();
		// ((GameUnit) unitArray[j]).kill();
		// }
		// }
		// }
		new Thread(new Runnable() {
			Actor[] unitArray = getActors().items;

			@Override
			public void run() {
				for (int i = 0; i < unitArray.length; i++) {

					for (int j = 0; j < unitArray.length; j++) {

						if ((((unitArray[i] instanceof Meatball) && (unitArray[j] instanceof UFO)) || ((unitArray[i] instanceof SpagettiMonstr) && (unitArray[j] instanceof UFO)))
								&& ((GameUnit) unitArray[i])
										.isOverlaps(((GameUnit) unitArray[j])
												.getBody())) {
							Gdx.app.log("Intersetct", unitArray[i].getClass()
									+ " with " + unitArray[j].getClass());
							((GameUnit) unitArray[i]).kill();
							((GameUnit) unitArray[j]).kill();
						}
					}
				}
			}
		}).start();
	}

	class SheduleGenerateEnemy extends Thread {
		UFO _ufo;

		@Override
		public void run() {
			while (true) {
				try {
					Thread.currentThread();
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Random random = new Random();
				_ufo = new UFO(random.nextFloat() * GameScreen.GAME_HEIGHT,
						new Vector2(-random.nextFloat() * 50,
								random.nextFloat() * 50));
				addActor(_ufo);
			}
		}
	}
}
