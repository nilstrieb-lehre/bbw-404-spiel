package ch.bbw.gameboy;

import ch.bbw.gameboy.api.PixelGraphic;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private List<Sprite> sprites = new ArrayList<>(10);

    public Score(PixelGraphic graphic) {
        for (int i = 0; i < 10; i++) {
            Sprite s = new Sprite("sprites\\font\\" + i + ".png", graphic);
            sprites.add(s);
        }
    }

    public void draw(int score) {
        String numbers = Integer.toString(score);

        final int y = 5;
        int x = 5;
        for (char c : numbers.toCharArray()) {
            int index = ((int) c) - 48;
            sprites.get(index).draw(x, y);
            x += 5;
        }
    }
}
