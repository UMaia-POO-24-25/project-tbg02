package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.lang.reflect.Method;

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
    public void testDisplayMessage() throws Exception {
        Method displayMessageMethod = GameView.class.getDeclaredMethod("displayMessage", String.class);
        displayMessageMethod.setAccessible(true);
        displayMessageMethod.invoke(gameView, "Test Message");
        verify(mockScreen, times(1)).refresh();
    }

    @Test
    public void testOpenMainMenu() {
        // Mock behavior of GameState if needed
        when(mockGameState.isSnakeAlive()).thenReturn(true);

        gameView.openMainMenu();

        // Verify interactions or state changes
        //verify(mockGameState, times(1)).isSnakeAlive();
    }
}