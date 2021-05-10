package ch.bbw.gameboy;

import ch.bbw.gameboy.impl.GameBbwoy;

import javax.swing.*;

public class Enemy implements Drawable {

    private final Sprite sprite;
    private int x;
    private int y;
    private final int speed;

    public Enemy(Sprite sprite, int x, int y, int speed) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void draw(GameLogic logic) {
        y -= speed;
        sprite.draw(x, y);

        if (y + sprite.getHeight() > GameBbwoy.DISPLAY_HEIGHT) {
            //game over, Count at null
            logic.reset();
            logic.setRunning(false);
            JOptionPane.showMessageDialog(null, "Game over! Better Luck next Time");
            logic.setRunning(true);
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return sprite.getHeight();
    }

    @Override
    public int getWidth() {
        return sprite.getWidth();
    }
}
