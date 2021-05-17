package ch.bbw.gameboy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void enemyTest() {
        assertDoesNotThrow(() -> new Enemy(null, 0, 0, 0));

        Enemy e = new Enemy(null, 56, 65, 56);
        assertEquals(56, e.getX());
        assertEquals(65, e.getY());

        assertThrows(NullPointerException.class, e::getHeight);
    }

}