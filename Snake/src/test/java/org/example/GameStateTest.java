package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
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

    @Test
    public void testGenerateRandomObject() {
        Posicao mockPosicao = mock(Posicao.class);
        GameState spyGameState = spy(gameState);

        doReturn(mockPosicao).when(spyGameState).generateRandomPosition(anyInt(), anyInt());
        doReturn(true).when(spyGameState).isEmptyPosition(mockPosicao);

        Posicao result = spyGameState.generateRandomObject(80, 23);

        assertEquals(mockPosicao, result);
        verify(spyGameState, atLeastOnce()).generateRandomPosition(anyInt(), anyInt());
        verify(spyGameState, atLeastOnce()).isEmptyPosition(mockPosicao);
    }

    @Test
    public void testSetDirection() {
        when(snake.getDirection()).thenReturn(Direcao.RIGHT);

        gameState.setDirection(Direcao.LEFT);
        verify(snake, never()).setDirection(Direcao.LEFT);

        gameState.setDirection(Direcao.UP);
        verify(snake).setDirection(Direcao.UP);
    }


    @Test
    public void testMoveSnake() {
        gameState.moveSnake();

        verify(snake).move();
    }
}