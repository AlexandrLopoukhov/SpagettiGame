package game.controller;

import game.model.GameWorld;
import game.model.Meatball;
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
	// размер джостика
	public static float SIZE = (GameScreen.GAME_HEIGHT + GameScreen.GAME_WIDTH) / 10;
	// размер движущейся части (khob)
	public static float CSIZE = (GameScreen.GAME_HEIGHT + GameScreen.GAME_WIDTH) / 20;

	float _angle;
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

	}

	public void setOffsetPosition(final float x, final float y) {
		_offsetPosition.x = x;
		_offsetPosition.y = y;
	}

	@Override
	public Actor hit(final float x, final float y, final boolean touchable) {
		// Процедура проверки. Если точка в прямоугольнике актёра, возвращаем
		// актёра.
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
		if (Gdx.input.isTouched(2)) {
			_world.addActor(new Meatball(_world.getMonstr().getX(), _world
					.getMonstr().getY()));
		}
		// точка касания относительно центра джойстика
		float calcX = x - SIZE / 2;
		float calcY = y - SIZE / 2;

		// определяем лежит ли точка касания в окружности джойстика
		if (((Math.abs(calcX * calcY)) <= SIZE * SIZE / 4)) {

			// определяем угол
			double angle = Math.atan(calcX / calcY) * 180 / Math.PI;

			// меняем угол к формату от 0 до 360
			if (angle < 0 && calcY < 0) {
				angle += 180;
			}
			if (angle > 0 && calcX < 0) {
				angle += 180;
			}
			if (angle < 0 && calcX < 0) {
				angle += 360;
			}

			// в зависимости от угла указываем направление, куда двигать игрока
			if (angle < 45 || angle > 315) {
				_world.getMonstr().changeVelocity(0,
						_world.getMonstr().getSpeed());
			}

			if (angle > 135 && angle < 225) {
				_world.getMonstr().changeVelocity(0,
						-_world.getMonstr().getSpeed());
			}

			if (angle > 225 && angle < 315) {
				_world.getMonstr().changeVelocity(
						-_world.getMonstr().getSpeed(), 0);
			}

			if (angle > 45 && angle < 135) {
				_world.getMonstr().changeVelocity(
						_world.getMonstr().getSpeed(), 0);
			}

			setOffsetPosition(calcX / 2, calcY / 2);

		} else {
			_world.getMonstr().changeVelocity(0, 0);
			setOffsetPosition(0, 0);
		}

	}

}
