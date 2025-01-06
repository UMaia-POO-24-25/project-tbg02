package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A classe DirecaoTest contém testes unitários para a enumeração Direcao.
 * Ela verifica se os valores da enumeração estão corretos e se a contagem de valores está correta.
 */

public class DirecaoTest {

    /**
     * O método testDirecaoValues verifica se os valores da enumeração Direcao
     * correspondem aos valores esperados (UP, DOWN, LEFT, RIGHT).
     */
    @Test
    public void testDirecaoValues() {
        assertEquals(Direcao.UP, Direcao.valueOf("UP"));
        assertEquals(Direcao.DOWN, Direcao.valueOf("DOWN"));
        assertEquals(Direcao.LEFT, Direcao.valueOf("LEFT"));
        assertEquals(Direcao.RIGHT, Direcao.valueOf("RIGHT"));
    }

    /**
     * O método testDirecaoCount verifica se a enumeração Direcao contém exatamente quatro valores.
     */

    @Test
    public void testDirecaoCount() {
        assertEquals(4, Direcao.values().length);
    }
}