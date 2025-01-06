package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * A classe GameStateTest contém testes unitários para a classe GameState.
 * Ela verifica se a direção da cobra é definida corretamente e se a cobra se move corretamente.
 */

public class GameStateTest {
    private GameState gameState;
    private Snake snake;
    private LinkedList<Posicao> fruits;

    @BeforeEach
    public void setUp() {
        gameState = new GameState();
        snake = mock(Snake.class);
        fruits = mock(LinkedList.class);

        
        try {
            java.lang.reflect.Field snakeField = GameState.class.getDeclaredField("snake");
            snakeField.setAccessible(true);
            snakeField.set(gameState, snake);

            java.lang.reflect.Field fruitsField = GameState.class.getDeclaredField("fruits");
            fruitsField.setAccessible(true);
            fruitsField.set(gameState, fruits);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    

    @Test
    public void testSnakeAteFruit() {
        when(snake.ateFruit(fruits)).thenReturn(true);

        boolean result = gameState.snakeAteFruit();

        assertTrue(result);
        verify(snake).increaseSize();
    }

    @Test
    public void testSnakeDidNotEatFruit() {
        when(snake.ateFruit(fruits)).thenReturn(false);

        boolean result = gameState.snakeAteFruit();

        assertFalse(result);
        verify(snake, never()).increaseSize();
    }

     /**
     * O método testSetDirection verifica se a direção da cobra é definida corretamente.
     * Ele também verifica se a direção não é definida para a direção oposta.
     */

    @Test
    public void testSetDirection() {
        when(snake.getDirection()).thenReturn(Direcao.RIGHT);

        gameState.setDirection(Direcao.LEFT);
        verify(snake, never()).setDirection(Direcao.LEFT);

        gameState.setDirection(Direcao.UP);
        verify(snake).setDirection(Direcao.UP);
    }

    /**
     * O método testMoveSnake verifica se a cobra se move corretamente.
     */

    @Test
    public void testMoveSnake() {
        gameState.moveSnake();

        verify(snake).move();
    }
}