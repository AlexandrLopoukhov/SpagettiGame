package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import game.model.GameUnit;

public class Meatball extends Actor implements GameUnit {
	Texture _texture;
	Rectangle _body;
	boolean _isAlive;
	int _speed;

	public Meatball(final float x, final float y) {
		Gdx.app.log("Meatball", "create");
		_texture = new Texture(Gdx.files.internal("meatball.png"));
		_isAlive = true;
		setWidth(GameScreen.GAME_WIDTH / 20);
		setHeight(GameScreen.GAME_HEIGHT / 20);
		setX(x);
		setY(y);
		_speed = 30;
		_body = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Gdx.app.log("Met", "" + _body.height + "x" + _body.width);
		Gdx.app.log("MetText",
				"" + _texture.getHeight() + "x" + _texture.getWidth());
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(_texture, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void act(final float delta) {
		setX(getX() + _speed * delta);
		_body.setPosition(getX(), getY());
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
		Gdx.input.vibrate(25);
		this.remove();
		_isAlive = false;
		_texture.dispose();
		// костыль
		_body.set(0, 0, 0, 0);
	}

	@Override
	public boolean isOverlaps(final Rectangle rectangle) {
		return _body.overlaps(rectangle);
	}

}
