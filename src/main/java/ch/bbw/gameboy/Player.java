package ch.bbw.gameboy;

import ch.bbw.gameboy.api.ButtonController;

public class Player implements Drawable {

    private final Sprite sprite;
    private int posX = 10;
    private final int posY = 90;
    private int speed;

    public Player(Sprite sprite) {
        this.sprite = sprite;
        speed = 5;
    }

    @Override
    public void draw(GameLogic logic) {
        sprite.draw(posX, posY);
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
            if (posX > 0) posX -= speed;
            else posX = speed;
        } else if (button == ButtonController.GameButton.RIGHT) {
            if (posX + sprite.getWidth() < graphicX) posX += speed;
            else posX -= speed;
        }

        sprite.draw(posX, posY);
    }
}
