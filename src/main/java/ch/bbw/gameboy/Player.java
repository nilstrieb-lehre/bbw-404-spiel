package ch.bbw.gameboy;

public class Player implements Drawable {

    private final Sprite sprite;

    public Player(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void draw() {
        sprite.draw(0, 0);
    }
}
