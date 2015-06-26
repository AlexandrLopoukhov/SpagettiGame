package game;

import game.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class SpagettiGame extends Game {
	public Screen _game;

	@Override
	public void create() {
		Gdx.app.log("SpagettiGame", "create game");
		_game = new GameScreen();
		setScreen(_game);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		_game.dispose();
	}
}
