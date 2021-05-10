package ch.bbw.gameboy;

public class SplashScreen implements Drawable {
    private final Sprite sprite;
    private int x;
    private int y;

    public SplashScreen(Sprite sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GameLogic logic) {
        sprite.draw(x, y);
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
