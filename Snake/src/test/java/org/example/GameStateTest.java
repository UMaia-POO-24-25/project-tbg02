package org.example;

import org.example.GameState;
import org.example.Posicao;
import org.example.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class GameStateTest {
    private GameState gameState;
    private Snake snake;
    private LinkedList<Posicao> fruits;

    @BeforeEach
    public void setUp() {
        gameState = new GameState();
        snake = mock(Snake.class);
        fruits = mock(LinkedList.class);

        // Usando reflexão para definir os mocks nos campos privados
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
}