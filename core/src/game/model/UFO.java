package game.model;

import game.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class UFO extends Actor implements GameUnit {
	Texture _texture;
	Rectangle _body;
	Vector2 _velocity = new Vector2(-10, 0);
	boolean _isAlive;

	public UFO() {
		Gdx.app.log("UFO", "create");
		_texture = new Texture(Gdx.files.internal("ufo.png"));
		_isAlive = true;
		setWidth(GameScreen.GAME_WIDTH / 10);
		setHeight(GameScreen.GAME_HEIGHT / 10);
		setX(GameScreen.GAME_WIDTH - getWidth());
		// setY(GameScreen.GAME_HEIGHT - 30);
		_body = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public Rectangle getBody() {
		return _body;
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		if (_isAlive) {
			batch.draw(_texture, getX(), getY(), getWidth(), getHeight());
		}
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
		this.remove();
		_isAlive = false;
		_texture.dispose();
		_body.set(0, 0, 0, 0);
	}
}
