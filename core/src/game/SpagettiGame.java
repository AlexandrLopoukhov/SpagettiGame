package game;

import game.screens.GameScreen;
import game.screens.IntroScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class SpagettiGame extends Game {
	public Screen _gameScreen;
	public Screen _introScreen;

	@Override
	public void create() {
		Gdx.app.log("SpagettiGame", "create game");
		_introScreen = new IntroScreen(this);
		setScreen(_introScreen);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		// screen.dispose();
	}

}
