package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GameViewTest {
    private GameView gameView;
    private GameState mockGameState;

    @BeforeEach
    public void setUp() {
        mockGameState = Mockito.mock(GameState.class);
        gameView = new GameView(80, 23); // Initialize with width and height
        gameView.setState(mockGameState); // Assuming you have a method to set the state
    }

    @Test
    public void testOpenMainMenu() {
        // Mock behavior of GameState if needed
        when(mockGameState.isSnakeAlive()).thenReturn(true);

        gameView.openMainMenu();

        // Verify interactions or state changes
        verify(mockGameState, times(1)).isSnakeAlive();
    }
}
