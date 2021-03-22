package ch.bbw.gameboy.api;

public interface PixelGraphic {

	/**
	 * sets a specific pixel to a color index
	 *
	 * @param x     horizontal distance
	 * @param y     vertical distance
	 * @param color must be one of 0,1,2,3 where 0 is the lightest color
	 */
	void setPixel(int x, int y, int color);

	default void setPixel(int x, int y, PixelColor color) {
		setPixel(x, y, color.ordinal());
	}

	/**
	 * @return the screen width in pixels
	 */
	default int getPixelWidth() {
		return 160;
	}

	/**
	 * @return the screen height in pixels
	 */
	default int getPixelHeight() {
		return 144;
	}

	default void clear() {
		for (int y = 0; y < getPixelHeight(); y++) {
			for (int x = 0; x < getPixelHeight(); x++) {
				setPixel(x, y, PixelColor.WHITE);
			}
		}
	}

	enum PixelColor {
		WHITE,
		LOW,
		HIGH,
		BLACK
	}
}
