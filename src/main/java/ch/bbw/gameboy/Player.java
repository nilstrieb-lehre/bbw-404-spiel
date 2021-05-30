package ch.bbw.gameboy;

import ch.bbw.gameboy.api.ButtonController;

public class Player implements Drawable {

    private final Sprite sprite;
    private int posX = 10;
    private final int posY = 90;
    private static final int speed = 2;

    private int velocity = 0;
    private double graphicX;

    private boolean leftPressed;
    private boolean rightPressed;

    public Player(Sprite sprite, double graphicX) {
        this.sprite = sprite;
        this.graphicX = graphicX;
    }

    @Override
    public void draw(GameLogic logic) {
        sprite.draw(posX, posY);

        if (velocity < 0) {
            if (posX > 0) posX += velocity;
            else posX = speed;
        } else if (velocity > 0) {
            if (posX + sprite.getWidth() < graphicX) posX += velocity;
            else posX -= speed;
        }
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public int getHeight() {
        return sprite.getHeight();
    }

    @Override
    public int getWidth() {
        return sprite.getWidth();
    }

    public void moving(ButtonController.GameButton button, double graphicX) {

        if (button == ButtonController.GameButton.LEFT) {
            velocity = -speed;
            leftPressed = true;
        } else if (button == ButtonController.GameButton.RIGHT) {
            velocity = speed;
            rightPressed = true;
        }

        sprite.draw(posX, posY);
    }

    public void stopMoving(ButtonController.GameButton button, int pixelWidth) {
        if (button == ButtonController.GameButton.LEFT) {
            leftPressed = false;
            if (rightPressed) {
                velocity = speed;
            } else {
                velocity = 0;
            }
        } else if (button == ButtonController.GameButton.RIGHT) {
            rightPressed = false;
            if (leftPressed) {
                velocity = -speed;
            } else {
                velocity = 0;
            }
        }

        sprite.draw(posX, posY);
    }
}
