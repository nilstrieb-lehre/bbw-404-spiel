package ch.bbw.gameboy;

public interface Drawable {
    void draw(GameLogic logic);

    int getX();
    int getY();
    int getHeight();
    int getWidth();

    default boolean collidesWith(Drawable other) {
        return false;
    }
}
