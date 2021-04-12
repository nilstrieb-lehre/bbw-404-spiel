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

    private final PixelGraphic graphic;

    private final StarfieldExample starfield;

    private Player player;
    private List<Drawable> objects = new ArrayList<>();
    private List<Sprite> sprites = new ArrayList<>();

    public GameLogic(PixelGraphic graphic) {
        this.graphic = graphic;
        starfield = new StarfieldExample(graphic);

        player = new Player(new Sprite("sprites\\sportlehrer2.png", graphic));
        sprites.add(new Sprite("sprites\\enemy1.jpg", graphic));
        sprites.add(new Sprite("sprites\\enemy2.jpg", graphic));
        sprites.add(new Sprite("sprites\\enemy3.jpg", graphic));
        sprites.add(new Sprite("sprites\\enemy4.jpg", graphic));
        sprites.add(new Sprite("sprites\\Endboss.png", graphic));
        objects.add(new Projectile(new Sprite("projectile.png", graphic), 30, 100, 1));
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

        if (Math.random() > 0.98) {
            objects.add(new Enemy(sprites.get(new Random().nextInt(3)+1), new Random().nextInt(100)+15, 5, -1));
        }

        /**
         * Noch den Endboss hinzufügen
         */

        graphic.clear();
        starfield.draw();
        player.draw();

        var toRemove = new ArrayList<Drawable>();
        for (Drawable object : objects) {
            try {
                object.draw();
            } catch (DeleteObjectException e) {
                toRemove.add(object);
            }
        }
        for (Drawable drawable : toRemove) {
            objects.remove(drawable);
        }
    }

    @Override
    public void onButtonPress(GameButton button) {
        player.moving(button);
    }

    @Override
    public void onButtonRelease(GameButton button) {
        System.out.println("up: " + button);
    }
}
