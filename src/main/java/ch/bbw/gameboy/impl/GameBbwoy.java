package ch.bbw.gameboy.impl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

import ch.bbw.gameboy.GameLogic;
import ch.bbw.gameboy.api.ButtonController;
import ch.bbw.gameboy.api.ButtonController.GameButton;
import ch.bbw.gameboy.api.PixelGraphic;

public class GameBbwoy {

	public static final int FPS = 30;

	public static final int DISPLAY_WIDTH = 160;

	public static final int DISPLAY_HEIGHT = 144;

	public static void main(String[] args) throws Throwable {
		System.setProperty("sun.java2d.opengl", "true");
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		var display = new SwingDisplay(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		var logic = new GameLogic(display);
		SwingUtilities.invokeLater(() -> {
			var mainWindow = new JFrame("GameBbwoy");
			mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			mainWindow.setLocationRelativeTo(null);
			mainWindow.setContentPane(display);
			mainWindow.setResizable(false);
			mainWindow.setVisible(true);
			mainWindow.pack();
			mainWindow.addKeyListener(new SwingController(logic));
		});
		var future = Executors.newSingleThreadScheduledExecutor()
				.scheduleAtFixedRate(() -> {
					logic.tick();
					display.refresh();
				}, 0, 1000 / FPS, TimeUnit.MILLISECONDS);
		try {
			future.get();
		}
		catch (Throwable e) {
			if (e instanceof ExecutionException) {
				e = e.getCause();
			}
			// following two lines are the worst antipattern possible, however in this case
			// it's kinda convenient to print to stderr and terminate the swing thread
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static class SwingDisplay extends JPanel implements PixelGraphic {

		private static final int SCALE = 3;

		private static final int[] COLORS = new int[] {0xe6f8da, 0x99c886, 0x437969, 0x051f2a};

		private final int pixelWidth;

		private final int pixelHeight;

		private final transient BufferedImage img;

		private final int[] rgb;

		public SwingDisplay(int width, int height) {
			GraphicsConfiguration gfxConfig = GraphicsEnvironment
					.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice()
					.getDefaultConfiguration();
			this.pixelWidth = width;
			this.pixelHeight = height;
			rgb = new int[width * height];
			img = gfxConfig.createCompatibleImage(width, height);
			setPreferredSize(new Dimension(width * SCALE, height * SCALE));
			clear();
		}

		private static void assertPositiveAtMost(String name, int value, int max) {
			if (value < 0 || max <= value) {
				throw new IllegalArgumentException(name + "=" + value + " should be between 0 and " + max + " (exclusive)");
			}
		}

		@Override
		public int getPixelWidth() {
			return pixelWidth;
		}

		@Override
		public int getPixelHeight() {
			return pixelHeight;
		}

		public void setPixel(int x, int y, int color) {
			assertPositiveAtMost("x", x, pixelWidth);
			assertPositiveAtMost("y", y, pixelHeight);
			assertPositiveAtMost("color", color, COLORS.length);
			rgb[x + y * pixelWidth] = COLORS[color];
		}

		public void clear() {
			Arrays.fill(rgb, COLORS[0]);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawImage(img, 0, 0, pixelWidth * SCALE, pixelHeight * SCALE, null);
			g2d.dispose();
		}

		public void refresh() {
			img.setRGB(0, 0, pixelWidth, pixelHeight, rgb, 0, pixelWidth);
			validate();
			repaint();
		}
	}

	private static class SwingController implements KeyListener {

		private final ButtonController buttonController;

		public SwingController(ButtonController buttonController) {
			this.buttonController = buttonController;
		}

		private static Optional<GameButton> keyEventToButton(KeyEvent e) {
			return Optional.ofNullable(switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT -> GameButton.LEFT;
				case KeyEvent.VK_RIGHT -> GameButton.RIGHT;
				case KeyEvent.VK_DOWN -> GameButton.DOWN;
				case KeyEvent.VK_UP -> GameButton.UP;
				case KeyEvent.VK_SPACE -> GameButton.SPACE;
				case KeyEvent.VK_CONTROL -> GameButton.CTRL;
				default -> null;
			});
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// ignore
		}

		@Override
		public void keyPressed(KeyEvent e) {
			keyEventToButton(e).ifPresent(buttonController::onButtonPress);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			keyEventToButton(e).ifPresent(buttonController::onButtonRelease);
		}
	}
}
