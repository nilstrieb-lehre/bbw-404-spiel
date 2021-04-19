package ch.bbw.gameboy;

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
    public void draw(GameLogic logic) throws DeleteObjectException {
        y -= speed;
        sprite.draw(x, y);

        if (y >= 144) {
            throw new DeleteObjectException();
            /**
             *game Over
             */
        }
    }
}
