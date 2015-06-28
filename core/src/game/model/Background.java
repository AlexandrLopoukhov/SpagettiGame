package game.model;

import game.controller.AssetLoader;
import game.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
	int _speed;

	public Background() {
		_speed = 100;
		setWidth(GameScreen.GAME_WIDTH * 2);
		setHeight(GameScreen.GAME_HEIGHT);
		Gdx.app.log("Background", "create & load texture");
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		batch.draw(AssetLoader._backgroundTexture, getX(), getY(), getWidth(),
				getHeight());
	}

	@Override
	public void act(final float delta) {
		if (getX() < -getWidth() / 2) {
			setX(0);
		}
		setX(getX() - _speed * delta);
	}

}
