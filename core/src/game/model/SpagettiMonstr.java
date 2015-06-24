package game.model;

import game.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpagettiMonstr extends Actor {
	Texture _texture;

	public SpagettiMonstr() {
		Gdx.app.log("SpagettiMonstr", "create");
		_texture = new Texture(Gdx.files.internal("monstr.png"));
		setWidth(GameScreen.GAME_WIDTH / 10);
		setHeight(GameScreen.GAME_HEIGHT / 10);
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		// TODO Auto-generated method stub
		batch.draw(_texture, getX(), getY(), getWidth(), getHeight());
		// super.draw(batch, parentAlpha);
	}

	@Override
	public void act(final float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}

}
