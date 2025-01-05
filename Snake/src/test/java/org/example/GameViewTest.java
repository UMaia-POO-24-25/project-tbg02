package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.googlecode.lanterna.screen.Screen;


import static org.mockito.Mockito.*;

public class GameViewTest {
    private GameView gameView;
    private GameState mockGameState;
    private Screen mockScreen;

    @BeforeEach
    public void setUp() {
        mockGameState = Mockito.mock(GameState.class);
        mockScreen = Mockito.mock(Screen.class); // Initialize mockScreen
        gameView = new GameView(80, 23); // Initialize with width and height
    }

    
    @Test
    public void testOpenMainMenu() {
        // Mock behavior of GameState if needed
        when(mockGameState.isSnakeAlive()).thenReturn(true);

        gameView.openMainMenu();

       
    }
}