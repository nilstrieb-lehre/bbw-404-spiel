package ch.bbw.gameboy.api;

public interface ButtonController {

	/**
	 * Fires when a button is pressed at least once.
	 * Depending on the keyboard, this even is re-triggered rapidly.
	 *
	 * @param button the button that is held down
	 */
	void onButtonPress(GameButton button);

	/**
	 * Fires once when a button is released
	 *
	 * @param button the button that is released
	 */
	void onButtonRelease(GameButton button);

	enum GameButton {
		LEFT,
		RIGHT,
		UP,
		DOWN,
		SPACE,
		CTRL
	}

}
