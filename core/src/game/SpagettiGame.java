package game;

import game.screens.GameScreen;
import game.screens.IntroScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SpagettiGame extends Game {
	public GameScreen _gameScreen;
	public IntroScreen _introScreen;
	Music _music;

	@Override
	public void create() {
		Gdx.app.log("SpagettiGame", "create game");
		_introScreen = new IntroScreen(this);
		setScreen(_introScreen);
		_music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		_music.setLooping(true);
		_music.play();
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
