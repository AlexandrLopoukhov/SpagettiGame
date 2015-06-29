package game.model;

import game.controller.AssetLoader;
import game.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpagettiMonstr extends Actor implements GameUnit {
	Vector2 _velocity;
	int _speedConstant;
	Rectangle _body;
	boolean _isAlive;

	public SpagettiMonstr() {
		_isAlive = true;
		setWidth(GameScreen.GAME_WIDTH / 10);
		setHeight(GameScreen.GAME_HEIGHT / 10);
		_velocity = new Vector2(0, 0);
		_speedConstant = 120;
		_body = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Gdx.app.log("SpagettiMonstr", "create on x: " + getX() + " y: "
				+ getY() + " with width: " + getWidth() + " and height "
				+ getHeight());
	}

	@Override
	public Rectangle getBody() {
		return _body;
	}

	public int getSpeed() {
		return _speedConstant;
	}

	public void setSpeed(final int speed) {
		_speedConstant = speed;
		Gdx.app.log("SpagettiMonstr", "set speed to " + speed);
	}

	public void changeVelocity(final int x, final int y) {
		_velocity.x = x;
		_velocity.y = y;
		Gdx.app.log("SpagettiMonstr", "change velocity to x: " + x + " y: " + y);
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(AssetLoader._spagettiTexture, getX(), getY(), getWidth(),
				getHeight());
	}

	@Override
	public void act(final float delta) {
		setX(getX() + _velocity.x * delta);
		setY(getY() + _velocity.y * delta);
		_body.setPosition(getX(), getY());
		Gdx.app.log("SpagettiMonstr", "" + this + " move to x: " + getX()
				+ "y: " + getY());
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
		_isAlive = false;
	}

}
