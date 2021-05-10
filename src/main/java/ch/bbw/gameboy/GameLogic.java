package ch.bbw.gameboy;

import ch.bbw.gameboy.api.ButtonController;
import ch.bbw.gameboy.api.PixelGraphic;
import ch.bbw.gameboy.impl.GameBbwoy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Anchor point for the graphic {@link #tick()} and the user-interaction {@link #onButtonPress(GameButton)}.
 */
public class GameLogic implements ButtonController {

    private static Random random = new Random();

    private final PixelGraphic graphic;

    private final StarfieldExample starfield;

    private boolean isRunning = true;

    private final Drawable splashScreen;

    private Player player;
    private List<Drawable> objects = new ArrayList<>();
    private List<Sprite> enemySprites = new ArrayList<>();

    private List<Drawable> toRemove = new ArrayList<>();


    public GameLogic(PixelGraphic graphic) {
        this.graphic = graphic;
        starfield = new StarfieldExample(graphic);

        player = new Player(new Sprite("sprites\\sportlehrer2.png", graphic));
        enemySprites.add(new Sprite("sprites\\enemy1.png", graphic));
        enemySprites.add(new Sprite("sprites\\enemy2.png", graphic));
        enemySprites.add(new Sprite("sprites\\enemy3.png", graphic));
        enemySprites.add(new Sprite("sprites\\enemy4.png", graphic));
        enemySprites.add(new Sprite("sprites\\Endboss.png", graphic));

        splashScreen = new SplashScreen(new Sprite("sprites\\logo.png", graphic), 5, 5);
    }

    public static void main(String[] args) throws Throwable {
        GameBbwoy.main(args); // just here for a convenient button
    }

    /**
     * used to redraw the display. Executed with (more or less) constant fames per second.
     *
     * @see GameBbwoy#FPS
     */
    public void tick() {

        if (!isRunning) {
            return;
        }

        if (Math.random() > 0.98) {
            if (Math.random() > 0.95) {
                objects.add(new Enemy(enemySprites.get(4), 100, 5, -1));
            } else {
                objects.add(new Enemy(enemySprites.get(random.nextInt(4)), random.nextInt(100) + 15, 5, -1));
            }
        }

        // Noch den Endboss hinzufügen


        graphic.clear();
        splashScreen.draw(this);
        starfield.draw();
        player.draw(this);

        toRemove.clear();

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(this);
        }

        objects.removeAll(toRemove);
    }

    public void remove(Drawable drawable) {
        toRemove.add(drawable);
    }

    public void add(Drawable object) {
        objects.add(object);
    }

    public void reset() {
        player = new Player(new Sprite("sprites\\sportlehrer2.png", graphic));
        objects.clear();
    }

    @Override
    public void onButtonPress(GameButton button) {
        player.moving(button, graphic.getPixelWidth());
    }

    @Override
    public void onButtonRelease(GameButton button) {
        if (button == GameButton.UP || button == GameButton.SPACE) {
            objects.add(new Projectile(new Sprite("projectile.png", graphic), player.getX() + player.getWidth() / 2, 100, 1));
            objects.get(objects.size() - 1).draw(this);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
