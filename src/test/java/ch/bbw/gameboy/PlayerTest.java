package ch.bbw.gameboy;

import ch.bbw.gameboy.api.PixelGraphic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player playerToTest;
    @BeforeEach
    void setUp() {
        playerToTest = new Player(
                new Sprite("sprites\\sportlehrer2.png", (x, y, color) -> {
                    x = 20;
                    y = 30;
                    color = 1;
                }), 160
        );
    }

    @Test
    void draw() {
    }

    @Test
    void getXAndGetY() {
        assertEquals(10, playerToTest.getX());
        assertEquals(90, playerToTest.getY());
    }

    @Test
    void getHeightAndGetWidth() {
        assertEquals(46, playerToTest.getHeight());
        assertEquals(40, playerToTest.getWidth());
    }
}