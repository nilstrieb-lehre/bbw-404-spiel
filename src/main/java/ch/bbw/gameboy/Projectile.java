package ch.bbw.gameboy;

public class Projectile implements Drawable {
    private final Sprite sprite;
    private final int x;
    private int y;
    private final int speed;

    public Projectile(Sprite sprite, int x, int y, int speed) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void draw(GameLogic logic) {
        y -= speed;
        if (y <= 0) {
            logic.remove(this);
            return;
        }
        sprite.draw(x, y);

    }
}
