package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    private Snake snake;

    @BeforeEach
    void setUp() {
        snake = new Snake(Direcao.RIGHT);
    }

    @Test
    void testInitialSetup() {
        assertEquals(4, snake.getBody().size(), "A cobra deve começar com 4 segmentos.");
        assertEquals(new Posicao(6, 15), snake.getHead(), "A cabeça inicial deve estar na posição correta.");
        assertEquals(Direcao.RIGHT, snake.getDirection(), "A direção inicial deve ser RIGHT.");
        assertTrue(snake.isAlive(), "A cobra deve começar viva.");
    }

    @Test
    void testMove() {
        snake.move();
        assertEquals(new Posicao(7, 15), snake.getHead(), "Após mover, a cabeça deve estar na posição correta.");
        assertEquals(4, snake.getBody().size(), "O comprimento da cobra não deve mudar ao se mover.");
    }

    @Test
    void testChangeDirection() {
        snake.setDirection(Direcao.UP);
        assertEquals(Direcao.UP, snake.getDirection(), "A direção deve ser alterada corretamente.");
    }

    @Test
    void testIsBody() {
        Posicao bodySegment = new Posicao(3, 15);
        assertTrue(snake.isBody(bodySegment), "A posição deve ser parte do corpo da cobra.");

        Posicao outsideSegment = new Posicao(10, 15);
        assertFalse(snake.isBody(outsideSegment), "A posição não deve ser parte do corpo da cobra.");
    }

    @Test
    void testEatFruit() {
        LinkedList<Posicao> fruits = new LinkedList<>();
        fruits.add(new Posicao(6, 15)); // Posição inicial da cabeça

        assertTrue(snake.ateFruit(fruits), "A cobra deve comer a fruta se a cabeça estiver na mesma posição.");
        assertTrue(fruits.isEmpty(), "A lista de frutas deve ser atualizada após comer.");
    }

    @Test
    void testSteppedOverDynamite() {
        LinkedList<Posicao> dynamites = new LinkedList<>();
        dynamites.add(new Posicao(6, 15)); // Posição inicial da cabeça

        assertTrue(snake.steppedOverDynamite(dynamites), "A cobra deve ativar a dinamite se a cabeça estiver na mesma posição.");
        assertTrue(dynamites.isEmpty(), "A lista de dinamites deve ser atualizada após ativar.");
    }

    @Test
    void testIncreaseSize() {
        snake.increaseSize();
        assertEquals(5, snake.getBody().size(), "O tamanho da cobra deve aumentar em 1.");
        assertEquals(new Posicao(3, 15), snake.getTail(), "A nova cauda deve ser adicionada corretamente.");
    }

    @Test
    void testKill() {
        snake.kill();
        assertFalse(snake.isAlive(), "A cobra deve ser marcada como morta.");
    }
}

