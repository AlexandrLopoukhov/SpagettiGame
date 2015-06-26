package game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
	public static Texture _ufoTexture;
	public static Texture _meatballTexture;

	public static void loadAsset() {
		_ufoTexture = new Texture(Gdx.files.internal("ufo.png"));
		_meatballTexture = new Texture(Gdx.files.internal("meatball.png"));
	}

}
