package game.screens;

import game.SpagettiGame;

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
	private SpagettiGame _game;

	public FinalScreen(final SpagettiGame game) {
		_texture = new Texture(Gdx.files.internal("main.jpg"));
		_game = game;
		_batch = new SpriteBatch();
		_fontRed1 = new BitmapFont();
		_fontRed1.setColor(Color.RED);
		Gdx.app.log("FinalScreen", "create");
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
		_fontRed1.getData().setScale(2, 3);
		_fontRed1.draw(_batch, "" + "Game Over", CAMERA_WIDTH / 3,
				CAMERA_HEIGHT * 0.2f);
		_fontRed1.draw(_batch, "" + "Score: "
				+ _game._gameScreen.getStage().getGameTime(), CAMERA_WIDTH / 3,
				CAMERA_HEIGHT * 0.1f);
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
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		this.dispose();
		_batch.dispose();
	}

}
