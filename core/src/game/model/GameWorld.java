package game.model;

import java.util.Random;

import game.controller.AssetLoader;
import game.controller.WalkingController;
import game.screens.GameScreen;

import com.badlogic.gdx.Application;
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

	public float getGameTime() {
		return _gameTime;
	}

	public void setGameTime(final float gameTime) {
		_gameTime = gameTime;
	}

	SheduleGenerateEnemy _ufoGenerator;
	GameScreen _gameScreen;

	public GameWorld(final StretchViewport stretchViewport,
			final GameScreen gameScreen) {
		super(stretchViewport);
		Gdx.app.log("GameWorld", "create on " + Gdx.app.getType());
		AssetLoader.loadAsset();
		_gameScreen = gameScreen;
		_ufoGenerator = new SheduleGenerateEnemy();
		_ufoGenerator.start();
		addActor(new Background());
		if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
			_control = new WalkingController(new Vector2(GameScreen.GAME_WIDTH
					- GameScreen.GAME_WIDTH, GameScreen.GAME_HEIGHT
					- GameScreen.GAME_HEIGHT), this);
			addActor(_control);
		}
		_monstr = new SpagettiMonstr();
		addActor(_monstr);

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
		Gdx.app.log("GameWorld", "game time " + _gameTime);
		collisionCheck();
		freeSpace();

		super.act(delta);

		if (!_monstr.isAlive()) {
			_ufoGenerator.interrupt();
			_gameScreen.setFinall();
			this.dispose();
			Gdx.app.log("GameWorld", "STOP at" + _gameTime);
		}

	}

	private void freeSpace() {
		new Thread(new Runnable() {
			Actor[] _unitArray = getActors().items;

			@Override
			public void run() {
				try {
					for (int i = 0; i < _unitArray.length; i++) {
						if (((_unitArray[i] instanceof Meatball) || (_unitArray[i] instanceof UFO))
								&& (_unitArray[i].getX() > GameScreen.GAME_WIDTH
										|| _unitArray[i].getX() < 0
										|| _unitArray[i].getY() < 0 || _unitArray[i]
										.getY() > GameScreen.GAME_HEIGHT)) {
							Gdx.app.log("GameWorld", "leave screen "
									+ _unitArray[i].getClass().getSimpleName()
									+ " so, kill it");
							((GameUnit) _unitArray[i]).kill();
						}
					}
				} catch (NullPointerException e) {
					Gdx.app.log("GameWorld",
							"freeSpace error-cause check in other Thread for speed up ");
				}

			}

		}).start();
	}

	private void collisionCheck() {
		new Thread(new Runnable() {
			Actor[] _unitArray = getActors().items;

			@Override
			public void run() {
				try {
					for (int i = 0; i < _unitArray.length; i++) {
						for (int j = 0; j < _unitArray.length; j++) {
							if ((((_unitArray[i] instanceof Meatball) && (_unitArray[j] instanceof UFO)) || ((_unitArray[i] instanceof SpagettiMonstr) && (_unitArray[j] instanceof UFO)))
									&& ((GameUnit) _unitArray[i])
											.isOverlaps(((GameUnit) _unitArray[j])
													.getBody())) {
								Gdx.app.log("GameWorld", "intersect "
										+ _unitArray[i].getClass()
												.getSimpleName()
										+ " with "
										+ _unitArray[j].getClass()
												.getSimpleName());
								((GameUnit) _unitArray[i]).kill();
								((GameUnit) _unitArray[j]).kill();
							}
						}
					}
				} catch (NullPointerException e) {
					Gdx.app.log("GameWorld",
							"collisionCheck error-cause check in other Thread for speed up ");
				}

			}

		}).start();
	}

	class SheduleGenerateEnemy extends Thread {
		UFO _ufo;

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// здесь т.к. слип сбрасывает значение флага выбрасывая
					// исключение
					Thread.currentThread().interrupt();
				}
				Random random = new Random();
				_ufo = new UFO(random.nextFloat() * GameScreen.GAME_HEIGHT,
						new Vector2(-random.nextFloat() * 120,
								random.nextFloat() * 20));
				addActor(_ufo);
			}
		}
	}
}
