package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FinalScreen implements Screen {

	float CAMERA_WIDTH = Gdx.graphics.getWidth();
	float CAMERA_HEIGHT = Gdx.graphics.getHeight();
	private SpriteBatch _batch;
	Texture _texture;
	private BitmapFont _fontRed1;
	GameScreen _gameScreen;

	public FinalScreen(final GameScreen gameScreen) {
		_texture = new Texture(Gdx.files.internal("main.jpg"));
		_gameScreen = gameScreen;
		_batch = new SpriteBatch();
		_fontRed1 = new BitmapFont();
		_fontRed1.setColor(Color.RED);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(final float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_batch.begin();
		_batch.draw(_texture, 0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		_fontRed1.draw(_batch, "" + "Game Over", 15, CAMERA_HEIGHT - 10);
		_fontRed1.draw(_batch, "" + "" + _gameScreen.getStage().getGameTime(),
				15, CAMERA_HEIGHT - 30);
		_batch.end();

	}

	@Override
	public void resize(final int width, final int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
