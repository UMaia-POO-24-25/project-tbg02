package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PosicaoTest {

    @Test
    void testGetXAndGetY() {
        Posicao posicao = new Posicao(3, 5);
        assertEquals(3, posicao.getX());
        assertEquals(5, posicao.getY());
    }

    @Test
    void testEquals_SameObject() {
        Posicao posicao = new Posicao(3, 5);
        assertTrue(posicao.equals(posicao)); // Reflexividade
    }

    @Test
    void testEquals_DifferentObject_SameValues() {
        Posicao posicao1 = new Posicao(3, 5);
        Posicao posicao2 = new Posicao(3, 5);
        assertTrue(posicao1.equals(posicao2)); // Mesmo valor, deve retornar verdadeiro
        assertEquals(posicao1.hashCode(), posicao2.hashCode()); // Mesmo hashCode para objetos equivalentes
    }

    @Test
    void testEquals_DifferentObject_DifferentValues() {
        Posicao posicao1 = new Posicao(3, 5);
        Posicao posicao2 = new Posicao(4, 6);
        assertFalse(posicao1.equals(posicao2)); // Valores diferentes
    }

    @Test
    void testEquals_Null() {
        Posicao posicao = new Posicao(3, 5);
        assertFalse(posicao.equals(null)); // Comparação com null
    }

    @Test
    void testEquals_DifferentClass() {
        Posicao posicao = new Posicao(3, 5);
        String other = "Not a Posicao object";
        assertFalse(posicao.equals(other)); // Comparação com outra classe
    }

    @Test
    void testHashCode() {
        Posicao posicao = new Posicao(3, 5);
        int expectedHashCode = 31 * 3 + 5;
        assertEquals(expectedHashCode, posicao.hashCode());
    }
}