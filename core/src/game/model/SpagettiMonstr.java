package game.model;

import game.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpagettiMonstr extends Actor implements GameUnit {
	Texture _texture;
	Vector2 _velocity;
	int _speedConstant;
	Rectangle _body;
	boolean _isAlive;

	public SpagettiMonstr() {
		Gdx.app.log("SpagettiMonstr", "create");
		_texture = new Texture(Gdx.files.internal("monstr.png"));
		_isAlive = true;
		setWidth(GameScreen.GAME_WIDTH / 10);
		setHeight(GameScreen.GAME_HEIGHT / 10);
		_velocity = new Vector2(0, 0);
		_speedConstant = 50;
		_body = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Gdx.app.log("SpBody", "" + _body.height + "x" + _body.width);
	}

	@Override
	public Rectangle getBody() {
		return _body;
	}

	public int getSpeed() {
		return _speedConstant;
	}

	public void setSpeed(final int speed) {
		Gdx.app.log("SpagettiMonstr", "set speed to " + speed);
		_speedConstant = speed;
	}

	public void changeVelocity(final int x, final int y) {
		Gdx.app.log("SpagettiMonstr", "change velocity to " + x + " " + y);
		_velocity.x = x;
		_velocity.y = y;
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		// TODO Auto-generated method stub
		batch.draw(_texture, getX(), getY(), getWidth(), getHeight());
		// super.draw(batch, parentAlpha);
	}

	@Override
	public void act(final float delta) {
		setX(getX() + _velocity.x * delta);
		setY(getY() + _velocity.y * delta);
		_body.setPosition(getX(), getY());
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
