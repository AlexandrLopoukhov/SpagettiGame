package game.screens;

import java.util.HashMap;

import game.SpagettiGame;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IntroScreen implements Screen, InputProcessor {
	SpagettiGame _game;
	private int _width, _height;
	private Texture _bgTexture;
	private SpriteBatch _spriteBatch;
	float CAMERA_WIDTH = Gdx.graphics.getWidth();
	float CAMERA_HEIGHT = Gdx.graphics.getHeight();
	public HashMap<String, Texture> _textures;
	boolean _downBtn;

	@Override
	public void show() {
		_textures = new HashMap<String, Texture>();
		_downBtn = false;
		_spriteBatch = new SpriteBatch();
		loadTextures();
	}

	private void loadTextures() {

		_bgTexture = new Texture(Gdx.files.internal("main.jpg"));
		_textures.put("cover_button_start_up",
				new Texture(Gdx.files.internal("cover_button_start_up.png")));
		_textures.put("cover_button_start_down",
				new Texture(Gdx.files.internal("cover_button_start_down.png")));
	}

	public void showMenu() {
		if (_downBtn) {
			_spriteBatch.draw(_textures.get("cover_button_start_down"),
					CAMERA_WIDTH / 2.5f, CAMERA_HEIGHT - CAMERA_HEIGHT * 0.98f,
					CAMERA_WIDTH * 0.4f, CAMERA_HEIGHT * 0.2f);
		} else {
			_spriteBatch.draw(_textures.get("cover_button_start_up"),
					CAMERA_WIDTH / 2.5f, CAMERA_HEIGHT - CAMERA_HEIGHT * 0.98f,
					CAMERA_WIDTH * 0.4f, CAMERA_HEIGHT * 0.2f);
		}

	}

	public void showBG() {
		_spriteBatch.draw(_bgTexture, 0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

	}

	public IntroScreen(final SpagettiGame game) {
		this._game = game;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyUp(final int keycode) {
		return true;
	}

	@Override
	public void hide() {
	}

	@Override
	public void render(final float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_spriteBatch.begin();
		showBG();
		showMenu();
		_spriteBatch.end();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyTyped(final char character) {
		return false;
	}

	@Override
	public void dispose() {
		try {
			_spriteBatch.dispose();
			_bgTexture.dispose();
			_textures.clear();
		} catch (Exception e) {
		}
	}

	@Override
	public boolean touchDragged(final int x, final int y, final int pointer) {
		return false;
	}

	// @Override
	public boolean touchMoved(final int x, final int y) {
		return false;
	}

	@Override
	public boolean mouseMoved(final int x, final int y) {
		return false;
	}

	@Override
	public boolean scrolled(final int amount) {
		return false;
	}

	@Override
	public boolean touchDown(final int x, final int y, final int pointer,
			final int button) {
		if (x > CAMERA_WIDTH / 2.5f
				&& y > CAMERA_HEIGHT - CAMERA_HEIGHT * 0.98f) {
			_downBtn = true;
		}
		return true;
	}

	@Override
	public boolean keyDown(final int keycode) {
		return true;
	}

	@Override
	public boolean touchUp(final int x, final int y, final int pointer,
			final int button) {
		_game._gameScreen = new GameScreen(this._game);
		_game.setScreen(_game._gameScreen);
		_downBtn = false;
		Gdx.app.log("main4", "" + Gdx.input.getInputProcessor());
		return true;
	}

	@Override
	public void resize(final int width, final int height) {
	}
}
