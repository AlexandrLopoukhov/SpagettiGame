package game;

import game.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class SpagettiGame extends Game {
	public Screen _game;

	@Override
	public void create() {
		_game = new GameScreen();
		setScreen(_game);
	}
}
