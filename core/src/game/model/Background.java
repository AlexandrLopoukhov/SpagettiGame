package game.model;

import game.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
	Texture _texture;

	public Background() {
		Gdx.app.log("Background", "create & load texture");
		_texture = new Texture(Gdx.files.internal("stars.png"));
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		// TODO Auto-generated method stub
		batch.draw(_texture, getX(), getY(), GameScreen.GAME_WEIGHT * 2,
				GameScreen.GAME_HEIGHT);
		// super.draw(batch, parentAlpha);
	}

	@Override
	public void act(final float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}

}
