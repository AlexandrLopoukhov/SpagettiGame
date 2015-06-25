package game.model;

import com.badlogic.gdx.math.Rectangle;

public interface GameUnit {

	public Rectangle getBody();

	public boolean isAlive();

	public void kill();

	public boolean isOverlaps(Rectangle rectangle);

}
