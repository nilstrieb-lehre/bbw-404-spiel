package ch.bbw.gameboy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.bbw.gameboy.api.PixelGraphic;

/**
 * Zeichnet ein Starfield-Effekt.
 * Dient zur Illustration und kann ansonsten gelöscht werden.
 */
public class StarfieldExample {

	private final PixelGraphic graphic;

	private final List<Star> stars;

	public StarfieldExample(PixelGraphic graphic) {
		this.graphic = graphic;
		stars = Stream.generate(Star::new).limit(100).collect(Collectors.toList());
	}

	public void draw() {
		for (var star : stars) {
			star.draw();
		}
	}

	class Star {

		private final double vx = (Math.random() - 0.5) * 4;

		private final double vy = (Math.random() - 0.5) * 3;

		private double x;

		private double y;

		public void draw() {
			var halfWidth = graphic.getPixelWidth() / 2;
			var halfHeight = graphic.getPixelHeight() / 2;
			x += vx;
			y += vy;
			if (Math.abs(x) >= halfWidth || Math.abs(y) >= halfHeight) {
				x = 0;
				y = 0;
			}
			var color = Math.min((Math.abs(x) + Math.abs(y)) / (halfWidth + halfHeight) * 4 + 0.5, 3);
			graphic.setPixel((int) x + halfWidth, (int) y + halfHeight, (int) color);
		}
	}
}
