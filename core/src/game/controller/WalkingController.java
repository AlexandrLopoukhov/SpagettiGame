package game.controller;

import game.model.GameWorld;
import game.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class WalkingController extends Actor {
	Texture _temp;
	TextureRegion _arrows;
	TextureRegion _khob;
	// размер джоя
	public static float SIZE = (GameScreen.GAME_HEIGHT + GameScreen.GAME_WIDTH) / 10;
	// размер движущейся части (khob)
	public static float CSIZE = (GameScreen.GAME_HEIGHT + GameScreen.GAME_WIDTH) / 20;

	public static float CIRCLERADIUS = 1.5f;
	public static float CONTRLRADIUS = 3F;
	// public static float Coefficient = 1F;

	// угол для определения направления
	float _angle;
	// public static int Opacity = 1;
	GameWorld _world;

	// координаты отклонения khob
	protected Vector2 _offsetPosition = new Vector2();

	protected Vector2 _position = new Vector2();
	protected Rectangle _bounds = new Rectangle();

	public WalkingController(final Vector2 pos, final GameWorld world) {
		Gdx.app.log("WalkingControl", "create");

		_temp = new Texture(Gdx.files.internal("atlas.png"));
		TextureRegion tmpLeftRight[][] = TextureRegion.split(_temp,
				_temp.getWidth() / 2, _temp.getHeight() / 2);
		TextureRegion left2[][] = tmpLeftRight[0][0].split(
				tmpLeftRight[0][0].getRegionWidth() / 2,
				tmpLeftRight[0][0].getRegionHeight());
		TextureRegion left[][] = left2[0][0].split(
				left2[0][0].getRegionWidth() / 4,
				left2[0][0].getRegionHeight() / 8);
		_arrows = tmpLeftRight[0][1];
		TextureRegion rightbot[][] = tmpLeftRight[1][1].split(
				tmpLeftRight[1][1].getRegionWidth() / 2,
				tmpLeftRight[1][1].getRegionHeight() / 2);
		_khob = rightbot[0][1];

		this._position = pos;
		this._bounds.width = SIZE;
		this._bounds.height = SIZE;
		this._world = world;

		// getOffsetPosition().x = 0;
		// getOffsetPosition().y = 0;

		setHeight(SIZE);
		setWidth(SIZE);
		setX(_position.x);
		setY(_position.y);

		this.addListener(new InputListener() {
			@Override
			public boolean touchDown(final InputEvent event, final float x,
					final float y, final int pointer, final int button) {
				return true;
			}

			@Override
			public void touchDragged(final InputEvent event, final float x,
					final float y, final int pointer) {
				withControl(x, y);
			}

			@Override
			public void touchUp(final InputEvent event, final float x,
					final float y, final int pointer, final int button) {
				_world.getMonstr().changeVelocity(0, 0);
				getOffsetPosition().x = 0;
				getOffsetPosition().y = 0;
			}
		});
	}

	@Override
	public void draw(final Batch batch, final float parentAlfa) {

		batch.draw(_arrows, getX(), getY(), getWidth(), getHeight());
		batch.draw(_khob,
				getX() + SIZE / 2 - CSIZE / 2 + getOffsetPosition().x, getY()
						+ SIZE / 2 - CSIZE / 2 + getOffsetPosition().y, CSIZE,
				CSIZE);
		// Gdx.app.log("WalkingController", "x " + getOffsetPosition().x + " y "
		// + getOffsetPosition().y);

	}

	public void setOffsetPosition(final float x, final float y) {
		_offsetPosition.x = x;
		_offsetPosition.y = y;
	}

	@Override
	public Actor hit(final float x, final float y, final boolean touchable) {
		// Процедура проверки. Если точка в прямоугольнике актёра, возвращаем
		// актёра.
		// Gdx.app.log("WalkingController", "hit");
		return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this
				: null;
	}

	public Vector2 getPosition() {
		return _position;
	}

	public Vector2 getOffsetPosition() {
		return _offsetPosition;
	}

	public Rectangle getBounds() {
		return _bounds;
	}

	public void withControl(final float x, final float y) {

		// точка касания относительно центра джойстика
		float calcX = x - SIZE / 2;
		float calcY = y - SIZE / 2;
		// setOffsetPosition(x, y);
		// Gdx.app.log("WalkingController", "x " + calcX + " y " + calcY);

		// определяем лежит ли точка касания в окружности джойстика
		if (((Math.abs(calcX * calcY)) <= SIZE * SIZE / 4)) {
			// Gdx.app.log("WalkingController", "x "
			// + ((calcX * calcY) <= SIZE * SIZE / 4));

			// пределяем угол касания
			double angle = Math.atan(calcX / calcY) * 180 / Math.PI;
			// Gdx.app.log("WalkingControl", "" + angle);

			// угол будет в диапозоне [-90;90]. Удобнее работать, если он в
			// диапозоне [0;360]
			// поэтому пошаманим немного
			if (angle < 0 && calcY < 0) {
				angle += 180;
			}
			if (angle > 0 && calcX < 0) {
				angle += 180;
			}
			if (angle < 0 && calcX < 0) {
				angle += 360;
			}
			// if (angle < 0) {
			// if (calcX < 0) {
			// angle = 180 + angle;
			// } else {
			// angle += 360;
			// }
			// }
			Gdx.app.log("WalkingControl", "" + angle);

			// в зависимости от угла указываем направление, куда двигать игрока
			if (angle < 45 || angle > 315) {
				_world.getMonstr().changeVelocity(0,
						_world.getMonstr().getSpeed());
				// ((Player) _world.selectedActor).upPressed();
			}

			if (angle > 135 && angle < 225) {
				_world.getMonstr().changeVelocity(0,
						-_world.getMonstr().getSpeed());
				// ((Player) _world.selectedActor).downPressed();
			}

			if (angle > 225 && angle < 315) {
				_world.getMonstr().changeVelocity(
						-_world.getMonstr().getSpeed(), 0);
				// ((Player) _world.selectedActor).leftPressed();
			}

			if (angle > 45 && angle < 135) {
				_world.getMonstr().changeVelocity(
						_world.getMonstr().getSpeed(), 0);
				// ((Player) _world.selectedActor).rightPressed();
			}

			// двигаем игрока
			// ((Player) _world.selectedActor).processInput();

			angle = (float) (angle * Math.PI / 180);
			// getOffsetPosition().x = (float) ((calcX * calcX + calcY * calcY >
			// 1F) ? Math
			// .cos(angle) * 0.75F : calcX);
			// getOffsetPosition().y = (float) ((calcX * calcX + calcY * calcY >
			// 1F) ? Math
			// .sin(angle) * 0.75F : calcY);
			getOffsetPosition().x = calcX / 2;
			getOffsetPosition().y = calcY / 2;

		} else {

			// _world.resetSelected();
			_world.getMonstr().changeVelocity(0, 0);
			getOffsetPosition().x = 0;
			getOffsetPosition().y = 0;
		}
		// Gdx.app.log("WalkingController", "x " + getOffsetPosition().x + " y "
		// + getOffsetPosition().y);

	}

}
