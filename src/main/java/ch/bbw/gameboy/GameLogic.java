package ch.bbw.gameboy;

import ch.bbw.gameboy.api.ButtonController;
import ch.bbw.gameboy.api.PixelGraphic;
import ch.bbw.gameboy.impl.GameBbwoy;

import java.io.IOException;

/**
 * Anchor point for the graphic {@link #tick()} and the user-interaction {@link #onButtonPress(GameButton)}.
 */
public class GameLogic implements ButtonController {

	private final PixelGraphic graphic;

	private final StarfieldExample starfield;

	private Player player;

	public GameLogic(PixelGraphic graphic) {
		this.graphic = graphic;
		starfield = new StarfieldExample(graphic);

        player = new Player(new Sprite("img_1.png", graphic));
    }

	public static void main(String[] args) throws Throwable {
		GameBbwoy.main(args); // just here for a convenient button
	}

	/**
	 * used to redraw the display. Executed with (more or less) constant fames per second.
	 * @see GameBbwoy#FPS
	 */
	public void tick() {
		graphic.clear();
		starfield.draw();
		player.draw();
	}

	@Override
	public void onButtonPress(GameButton button) {
		System.out.println("down: " + button);
	}

	@Override
	public void onButtonRelease(GameButton button) {
		System.out.println("up: " + button);
	}
}
