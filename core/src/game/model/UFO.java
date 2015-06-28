package game.model;

import game.controller.AssetLoader;
import game.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class UFO extends Actor implements GameUnit {
	Rectangle _body;
	Vector2 _velocity;
	boolean _isAlive;
	Vector2 _position;

	public UFO(final float y, final Vector2 velocity) {
		_isAlive = true;
		setWidth(GameScreen.GAME_WIDTH / 10);
		setHeight(GameScreen.GAME_HEIGHT / 10);
		setX(GameScreen.GAME_WIDTH - getWidth());
		setY(y);
		_velocity = velocity;
		_body = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Gdx.app.log("UFO", "create on x: " + getX() + " y: " + getY()
				+ " with width: " + getWidth() + " and height " + getHeight());
	}

	@Override
	public Rectangle getBody() {
		return _body;
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(AssetLoader._ufoTexture, getX(), getY(), getWidth(),
				getHeight());
	}

	@Override
	public void act(final float delta) {
		setX(getX() + _velocity.x * delta);
		setY(getY() + _velocity.y * delta);
		_body.setPosition(getX(), getY());
		Gdx.app.log("UFO", "" + this + " move to x: " + getX() + "y: " + getY());
	}

	@Override
	public boolean isOverlaps(final Rectangle rectangle) {
		return _body.overlaps(rectangle);
	}

	@Override
	public boolean isAlive() {
		return _isAlive;
	}

	@Override
	public void kill() {
		this.remove();
		_isAlive = false;
		_body.set(0, 0, 0, 0);
	}
}
