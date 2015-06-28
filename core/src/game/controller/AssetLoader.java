package game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture _ufoTexture;
	public static Texture _meatballTexture;
	public static Texture _spagettiTexture;
	public static Texture _backgroundTexture;
	public static Texture _temp;
	public static TextureRegion _arrows;
	public static TextureRegion _khob;

	public static void loadAsset() {
		_ufoTexture = new Texture(Gdx.files.internal("ufo.png"));
		_meatballTexture = new Texture(Gdx.files.internal("meatball.png"));
		_spagettiTexture = new Texture(Gdx.files.internal("monstr.png"));
		_backgroundTexture = new Texture(Gdx.files.internal("stars.jpg"));
		_temp = new Texture(Gdx.files.internal("atlas.png"));
		TextureRegion tmpLeftRight[][] = TextureRegion.split(_temp,
				_temp.getWidth() / 2, _temp.getHeight() / 2);
		_arrows = tmpLeftRight[0][1];
		TextureRegion rightbot[][] = tmpLeftRight[1][1].split(
				tmpLeftRight[1][1].getRegionWidth() / 2,
				tmpLeftRight[1][1].getRegionHeight() / 2);
		_khob = rightbot[0][1];
	}
}
