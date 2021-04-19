package ch.bbw.gameboy;

import ch.bbw.gameboy.api.ButtonController;

public class Player implements Drawable {

    private final Sprite sprite;
    private int posX = 10;
    private final int posY = 70;

    public Player(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void draw(GameLogic logic) {
        sprite.draw(posX, posY);
    }

    public void moving(ButtonController.GameButton button) {

        if (posX > 9 && posX <= 155 - sprite.getWidth()) {
            if (button == ButtonController.GameButton.LEFT) {
                posX -= 10;
            } else if (button == ButtonController.GameButton.RIGHT) {
                posX += 10;
            }

        } else if (posX < 9) {
            posX = 10;
        } else if (posX > 155 - sprite.getWidth()) {
            posX = 155 - sprite.getWidth();
        }
        sprite.draw(posX, posY);
    }
}
