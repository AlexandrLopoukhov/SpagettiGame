package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.controller.AssetLoader;
import game.screens.GameScreen;

public class Meatball extends Actor implements GameUnit {
	Rectangle _body;
	boolean _isAlive;
	int _speed;

	public Meatball(final float x, final float y) {
		_isAlive = true;
		setWidth(GameScreen.GAME_WIDTH / 20);
		setHeight(GameScreen.GAME_HEIGHT / 20);
		setX(x);
		setY(y);
		_speed = 120;
		_body = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Gdx.app.log("Meatball", "create on x: " + getX() + " y: " + getY()
				+ " with width: " + getWidth() + " and height " + getHeight());
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(AssetLoader._meatballTexture, getX(), getY(), getWidth(),
				getHeight());
	}

	@Override
	public void act(final float delta) {
		setX(getX() + _speed * delta);
		_body.setPosition(getX(), getY());
		Gdx.app.log("MeatBall", "" + this + " move to x: " + getX() + "y: "
				+ getY());
	}

	@Override
	public Rectangle getBody() {
		return _body;
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

	@Override
	public boolean isOverlaps(final Rectangle rectangle) {
		return _body.overlaps(rectangle);
	}

}
