package ch.bbw.gameboy;

import ch.bbw.gameboy.api.ButtonController;
import ch.bbw.gameboy.api.PixelGraphic;
import ch.bbw.gameboy.impl.GameBbwoy;

import java.util.ArrayList;
import java.util.List;

/**
 * Anchor point for the graphic {@link #tick()} and the user-interaction {@link #onButtonPress(GameButton)}.
 */
public class GameLogic implements ButtonController {

    private final PixelGraphic graphic;

    private final StarfieldExample starfield;

    private Player player;
    private List<Drawable> objects = new ArrayList<>();

    public GameLogic(PixelGraphic graphic) {
        this.graphic = graphic;
        starfield = new StarfieldExample(graphic);

        player = new Player(new Sprite("img_1.png", graphic));
        //objects.add(new Enemy(new Sprite("sprites\\enemy1.jpg", graphic), 30, 30, 0));
        //objects.add(new Enemy(new Sprite("sprites\\enemy2.jpg", graphic), 70, 30, 0));
        //objects.add(new Enemy(new Sprite("sprites\\enemy3.jpg", graphic), 120, 30, 0));
        //objects.add(new Enemy(new Sprite("sprites\\enemy4.jpg", graphic), 80, 30, 0));
        objects.add(new Enemy(new Sprite("sprites\\Endboss.jpg", graphic), 80, 40, 0));
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
    }
}
