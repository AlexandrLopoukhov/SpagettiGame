package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
	private Stage _stage;
	final public int GAME_HEIGHT = Gdx.graphics.getHeight();
	final public int GAME_WEIGHT = Gdx.graphics.getWidth();

	public GameScreen() {
		_stage = new Stage(new StretchViewport(GAME_WEIGHT, GAME_HEIGHT));
		Gdx.input.setInputProcessor(_stage);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		_stage.dispose();
	}

}
