package ch.bbw.gameboy;

import ch.bbw.gameboy.impl.GameBbwoy;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;

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
            logic.reset();
            logic.setRunning(false);
            CompletableFuture.runAsync(() -> {
                JOptionPane.showMessageDialog(null, "Game over! Better Luck next Time");
                logic.setRunning(true);
            });
        }

        for (Drawable d : logic.getObjects()) {
            if (d instanceof Projectile) {
                if (this.x + this.getHeight() > d.getX() &&
                        this.x < d.getX() + d.getWidth() &&
                        this.y + this.getHeight() > d.getY() &&
                        this.y < d.getY() + d.getHeight()) {
                    logic.remove(d);
                    logic.remove(this);
                    logic.addScore();
                }
            }
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
