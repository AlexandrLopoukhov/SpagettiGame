package game.screens;

import game.SpagettiGame;
import game.model.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
	private GameWorld _stage;
	SpagettiGame _game;
	final static public int GAME_HEIGHT = Gdx.graphics.getHeight();
	final static public int GAME_WIDTH = Gdx.graphics.getWidth();

	public GameScreen(final SpagettiGame game) {
		_game = game;
		_stage = new GameWorld(new StretchViewport(GAME_WIDTH, GAME_HEIGHT),
				this);
		Gdx.input.setInputProcessor(_stage);
		Gdx.app.log("GameScreen", "create");
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show");
	}

	@Override
	public void render(final float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_stage.act(delta);
		_stage.draw();
	}

	@Override
	public void resize(final int width, final int height) {
		Gdx.app.log("GameScreen", "resize to " + width + " * " + height);
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide");
		// Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		Gdx.app.log("GameScreen", "dispose");
		_stage.dispose();
		this.dispose();
	}

	public void setFinall() {
		_game._finalScreen = new FinalScreen(_game);
		_game.setScreen(_game._finalScreen);

	}

	public GameWorld getStage() {
		return _stage;
	}

}
