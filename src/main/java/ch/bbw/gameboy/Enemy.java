package ch.bbw.gameboy;

public class Enemy implements Drawable{

    private final Sprite sprite;

    public Enemy (Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void draw() {
        sprite.draw(0,0);
    }
}
