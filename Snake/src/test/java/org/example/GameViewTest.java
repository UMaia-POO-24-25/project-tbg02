package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class GameViewTest {
    private GameView gameView;
    private Terminal mockTerminal;
    private Screen mockScreen;
    private GameState mockGameState;

    @BeforeEach
    public void setUp() {
        // Criar mocks para as dependências
        mockTerminal = mock(Terminal.class);
        mockScreen = mock(Screen.class);
        mockGameState = mock(GameState.class);

        // Inicializar a classe com os mocks
        gameView = new GameView(30, 20);
        gameView.setState(mockGameState);

        // Usar reflexão para definir os mocks privados
        try {
            var screenField = GameView.class.getDeclaredField("screen");
            screenField.setAccessible(true);
            screenField.set(gameView, mockScreen);

            var terminalField = GameView.class.getDeclaredField("terminal");
            terminalField.setAccessible(true);
            terminalField.set(gameView, mockTerminal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Test
public void testDisplayMessage() throws Exception {
    // Configurar o mock do Screen para retornar um TextGraphics válido
    TextGraphics mockTextGraphics = mock(TextGraphics.class);
    when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

    // Acessar o método privado displayMessage via reflexão
    Method displayMessageMethod = GameView.class.getDeclaredMethod("displayMessage", String.class);
    displayMessageMethod.setAccessible(true); // Torna o método acessível

    // Chamar o método que exibe a mensagem
    displayMessageMethod.invoke(gameView, "Test Message");

    // Verificar se o ecra foi atualizado
    verify(mockScreen, times(1)).refresh();
}

@Test
public void testDrawWalls() throws Exception {
    // Configurar o mock do Screen para retornar um TextGraphics válido
    TextGraphics mockTextGraphics = mock(TextGraphics.class);
    when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

    // Criar uma lista de paredes mockada
    Posicao mockPosicao = mock(Posicao.class);
    when(mockPosicao.getX()).thenReturn(1);
    when(mockPosicao.getY()).thenReturn(1);
    when(mockGameState.getWalls()).thenReturn(new LinkedList<>(List.of(mockPosicao)));

    // Acessar o método privado drawWalls via reflexão
    Method drawWallsMethod = GameView.class.getDeclaredMethod("drawWalls");
    drawWallsMethod.setAccessible(true); // Torna o método acessível

    // Chamar o método para desenhar as paredes
    drawWallsMethod.invoke(gameView);

    // Verificar se a parede foi desenhada
    verify(mockScreen, times(1)).newTextGraphics();
}

    @Test
    public void testExitGame() throws IOException {
        // Executar o método
        gameView.exitGame();

        // Verificar se o método stopScreen foi chamado
        verify(mockScreen, times(1)).stopScreen();
    }

    @Test
    public void testSleep() throws InterruptedException, IOException {
        // Simular o sleep
        gameView.sleep(100);

        // Não há verificação direta, mas podemos garantir que não lança exceção
        verify(mockTerminal, times(0)).readInput();
    }
    @Test
    public void testReadKeyStrokeboard() throws IOException {
        // Mock da entrada do teclado
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        when(mockTerminal.pollInput()).thenReturn(keyStroke);

        // Chamar o método para ler a entrada do teclado
        gameView.readKeyStrokeboard();

        // Verificar se a direção da cobra foi atualizada
        verify(mockGameState, times(1)).setDirection(Direcao.UP);
    }

    @Test
    public void testGenerateNewFruit() {
    // Configurar o mock do Screen para retornar um TextGraphics válido
    TextGraphics mockTextGraphics = mock(TextGraphics.class);
    when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

    // Criar uma posição mockada para a nova fruta
    Posicao mockPosicao = mock(Posicao.class);
    when(mockPosicao.getX()).thenReturn(1);
    when(mockPosicao.getY()).thenReturn(1);

    // Configurar o mock do GameState para retornar a posição mockada
    when(mockGameState.generateRandomObject(anyInt(), anyInt())).thenReturn(mockPosicao);

    // Chamar o método para gerar uma nova fruta
    gameView.generateNewFruit();

    // Verificar se a fruta foi adicionada ao estado do jogo
    verify(mockGameState, times(1)).generateRandomObject(anyInt(), anyInt());
}

@Test
public void testGenerateNewDynamite() {
    // Configurar o mock do Screen para retornar um TextGraphics válido
    TextGraphics mockTextGraphics = mock(TextGraphics.class);
    when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

    // Criar uma posição mockada para a nova dinamite
    Posicao mockPosicao = mock(Posicao.class);
    when(mockPosicao.getX()).thenReturn(1);
    when(mockPosicao.getY()).thenReturn(1);

    // Configurar o mock do GameState para retornar a posição mockada
    when(mockGameState.generateRandomObject(anyInt(), anyInt())).thenReturn(mockPosicao);

    // Chamar o método para gerar uma nova dinamite
    gameView.generateNewDynamite();

    // Verificar se a dinamite foi adicionada ao estado do jogo
    verify(mockGameState, times(1)).generateRandomObject(anyInt(), anyInt());
}

@Test
public void testCheckCollision() {
    // Mock da posição da cabeça da cobra
    Posicao mockHead = mock(Posicao.class);
    when(mockHead.getX()).thenReturn(5);
    when(mockHead.getY()).thenReturn(5);
    when(mockGameState.getSnakeHead()).thenReturn(mockHead);

    // Mock da posição de uma parede
    Posicao mockWall = mock(Posicao.class);
    when(mockWall.getX()).thenReturn(10);
    when(mockWall.getY()).thenReturn(10);
    when(mockGameState.getWalls()).thenReturn(new LinkedList<>(List.of(mockWall)));

    // Chamar o método para verificar colisão
    boolean result = gameView.checkCollision();

    // Verificar se o método retornou o resultado esperado
    assertFalse(result);
}
    
}