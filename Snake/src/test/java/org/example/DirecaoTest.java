package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirecaoTest {

    @Test
    public void testDirecaoValues() {
        assertEquals(Direcao.UP, Direcao.valueOf("UP"));
        assertEquals(Direcao.DOWN, Direcao.valueOf("DOWN"));
        assertEquals(Direcao.LEFT, Direcao.valueOf("LEFT"));
        assertEquals(Direcao.RIGHT, Direcao.valueOf("RIGHT"));
    }

    @Test
    public void testDirecaoCount() {
        assertEquals(4, Direcao.values().length);
    }
}