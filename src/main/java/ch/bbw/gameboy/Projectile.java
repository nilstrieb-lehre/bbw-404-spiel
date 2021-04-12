package ch.bbw.gameboy;

public class Projectile implements Drawable {
    private final Sprite sprite;
    private int x;
    private int y;
    private int speed;

    public Projectile(Sprite sprite, int x, int y, int speed) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void draw() {
        y -= speed;
        sprite.draw(x, y);
    }
}
