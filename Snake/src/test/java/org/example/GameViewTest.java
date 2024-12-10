import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.GameState;
import org.example.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GameViewTest {
    private GameView gameView;
    private Terminal mockTerminal;
    private Screen mockScreen;
    private GameState mockState;

    @BeforeEach
    void setUp() {
        // Mocks para as dependências
        mockTerminal = Mockito.mock(Terminal.class);
        mockScreen = Mockito.mock(Screen.class);
        mockState = Mockito.mock(GameState.class);

        // Instância de GameView com dimensões de exemplo
        gameView = new GameView(20, 20); // Configurar dimensões de exemplo
    }

    @Test
    void testOpenMainMenu() throws Exception {
        // Configurar comportamentos esperados do terminal
        when(mockTerminal.readInput()).thenReturn(null); // Simular nenhuma entrada

        // Executar o metedo
        gameView.openMainMenu();

        // Verificar se a tela foi limpa e atualizada
        verify(mockScreen, atLeastOnce()).clear();
        verify(mockScreen, atLeastOnce()).refresh();
    }

    @Test
    void testDrawWall() throws Exception {
        // Simular a criação de paredes no estado do jogo
        gameView.drawWall();

        // Verificar se a tela foi atualizada após o desenho das paredes
        verify(mockScreen, atLeastOnce()).refresh();
    }

    @Test
    void testIncreaseDifficulty() throws Exception {
        // Simular aumento de dificuldade e verificar se a tela foi atualizada
        gameView.increaseDifficulty();

        verify(mockScreen, atLeastOnce()).refresh();
    }

    @Test
    void testGameOverHandling() throws Exception {
        // Configurar o estado do jogo como "não vivo"
        when(mockState.isSnakeAlive()).thenReturn(false);

        // Simular a chamada para finalizar o jogo
        gameView.openMainMenu();

        // Verificar se os métodos de controle de som e tela foram chamados
        verify(mockScreen, atLeastOnce()).refresh();
    }

    @Test
    void testRenderMainMenu() throws Exception {
        // Verificar a chamada para desenhar o menu principal
        gameView.openMainMenu();

        // Verificar se os métodos de renderização foram invocados
        verify(mockScreen, atLeastOnce()).clear();
        verify(mockScreen, atLeastOnce()).refresh();
    }
}
