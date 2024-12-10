package org.example;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GameViewTest {

    private GameView gameView;
    private Terminal mockTerminal;
    private Screen mockScreen;
    private TextGraphics mockTextGraphics;

    @BeforeEach
    void setUp() {
        // Mock objects
        mockTerminal = mock(Terminal.class);
        mockScreen = mock(Screen.class);
        mockTextGraphics = mock(TextGraphics.class);

        // Override dependencies using reflection or custom constructors
        gameView = Mockito.spy(new GameView(20, 20));
        GameView.terminal = mockTerminal;
        GameView.screen = mockScreen;

        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
    }

    private Terminal mock(Class<Terminal> terminalClass) {


        @Test
        void testOpenMainMenu () throws Exception {
            // Arrange
            doNothing().when(gameView).renderMainMenu();
            doNothing().when(gameView).refreshScreen();
            doReturn(1).when(gameView).handleMainMenu();


            gameView.openMainMenu();


            verify(gameView).renderMainMenu();
            verify(gameView).refreshScreen();
            verify(gameView).clearScreen();
        }

        @Test
        void testRenderMainMenu () {
            // Act
            gameView.renderMainMenu();

            // Assert
            verify(mockScreen).newTextGraphics();
            verify(mockTextGraphics, atLeastOnce()).putString(anyInt(), anyInt(), anyString());
        }

        @Test
        void testHandleMainMenu () {
            // Arrange
            KeyStroke mockKeyStroke = mock(KeyStroke.class);
            when(mockKeyStroke.getCharacter()).thenReturn('s');
            doReturn(mockKeyStroke).when(gameView).readKeyStrokeInput();

            // Act
            int result = gameView.handleMainMenu();

            // Assert
            assertEquals(1, result, "Pressionar 's' deve retornar 1 para iniciar o jogo.");
        }

        @Test
        void testDrawWalls () {
            // Arrange
            GameState mockState = mock(GameState.class);
            when(mockState.getWalls()).thenReturn(List.of(new Posicao(1, 1), new Posicao(2, 2)));
            gameView.state = mockState;

            // Act
            gameView.drawWalls();

            // Assert
            verify(mockScreen).newTextGraphics();
            verify(mockTextGraphics, times(2)).putString(anyInt(), anyInt(), eq("▒"));
        }

        @Test
        void testDrawScore () {
            // Arrange
            GameState mockState = mock(GameState.class);
            when(mockState.getScore()).thenReturn(100);
            gameView.state = mockState;

            // Act
            gameView.drawScore();

            // Assert
            verify(mockScreen).newTextGraphics();
            verify(mockTextGraphics).putString(anyInt(), anyInt(), contains("SCORE: 100"));
        }

        @Test
        void testCheckCollision_HeadOutsideBounds () {
            // Arrange
            GameState mockState = mock(GameState.class);
            Posicao mockHead = new Posicao(21, 21); // Fora dos limites
            when(mockState.getSnakeHead()).thenReturn(mockHead);
            gameView.state = mockState;

            // Act
            boolean result = gameView.checkCollision();

            // Assert
            assertTrue(result, "A colisão deve ser detectada quando a cabeça da cobra está fora dos limites.");
        }
    }
}
