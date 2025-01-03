package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;


public class GameView {
    private final static String EMPTY_STRING = " ";
    private final static String BORDER_STRING = "▒";
    private final static String SNAKE_HEAD_STRING = "@";
    private final static String SNAKE_BODY_STRING = "O";
    private final static String FRUIT_STRING = "\u2022";
    private final static String DYNAMITE_STRING = "f";
    private final static int INITIAL_GAME_SPEED = 90;
    private final static int SPEED_INCREMENT = 5;
    private final static int NEW_OBJECT_TIME_RATE = 6000;
    private final static int X_COORDINATE_OFFSET = 1;
    private final static int Y_COORDINATE_OFFSET = 2;
    private int fruitsCollected = 0;
    private final static int MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE = 7;
    private final static int MAX_GAME_SPEED = 30; // Example maximum speed
    static Terminal terminal;
    static Screen screen;
    private GameState state;
    private final int gameplay_height;
    private final int gameplay_width;
    private int game_speed;



    public GameView(int width, int height) {
        try {
            System.out.println("Inicializa GameView com largura: " + width + " e altura:" + height);
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("A largura e a altura devem ser maiores que 0.");
            }
            // Load custom font
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Fonte.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Fonte não encontrada");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);
            SwingTerminalFontConfiguration fontConfig = SwingTerminalFontConfiguration.newInstance(customFont);
            // Create terminal with custom font configuration
            terminal = new DefaultTerminalFactory()
                    .setTerminalEmulatorFontConfiguration(fontConfig)
                    .createTerminal();
            System.out.println("Terminal inicio com a fonte nova");
            gameplay_width = width - X_COORDINATE_OFFSET;
            gameplay_height = height - Y_COORDINATE_OFFSET;
            System.out.println("Dimensões do jogo definidas: largura = " + gameplay_width + ", altura = " + gameplay_height);
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            System.out.println("Ecrã iniciado..");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o ecrã do jogo.", e);
        }
    }

    /**
 * Displays a centered message on the game screen.

 */
private void displayMessage(String message) {
    TextGraphics tg = screen.newTextGraphics();
    tg.setForegroundColor(TextColor.ANSI.WHITE);
    
    // Calculate centered position
    int x = (gameplay_width - message.length()) / 2;
    int y = gameplay_height / 2;
    
    tg.putString(x, y, message);

    try{
    screen.refresh();
    } catch (IOException e) {
        e.printStackTrace();
    }

}
    private void clearScreen() {
        try {
            screen.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openMainMenu() {
        game_speed = INITIAL_GAME_SPEED;
        renderMainMenu();
        refreshScreen();
        int selected_option = handleMainMenu();
        if (selected_option == 1) {
            clearScreen();
            state = new GameState();
            Som.playSoundLoop("/sounds/start.wav");
            startGame();
        } else {
            exitGame();
        }
    }
    private void renderMainMenu() {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
        tg.putString(10, 2, "########   #######  ###   ###########   ###    ##    ########");
        tg.putString(10, 3, "##         ###  ##  ###   ###     ###   ###   ##     ###    ");
        tg.putString(10, 4, "##         ###  ##  ###   ###     ###   ###  ##      ###    ");
        tg.putString(10, 5, "##         ###  ##  ###   ###########   #######      ########");
        tg.putString(10, 6, "########   ###  ##  ###   ###     ###   ###  ##      ###    ");
        tg.putString(10, 7, "      ##   ###  ##  ###   ###     ###   ###   ##     ###    ");
        tg.putString(10, 8, "      ##   ###  ######    ###     ###   ###    ##    ###    ");
        tg.putString(10, 9, "########   ###  ######    ###     ###   ###     ##   ########");
        tg.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        String line1 = "###########################";
        String line2 = "#         Press           #";
        String line3 = "#  'S' to start           #";
        String line4 = "#  'Q' to quit the game.  #";
        String line5 = "#  'R' to Ranking.        #";
        String line6 = "###########################";
        int screenWidth = screen.getTerminalSize().getColumns();
        int x1 = (screenWidth - line1.length()) / 2;
        int x2 = (screenWidth - line2.length()) / 2;
        int x3 = (screenWidth - line3.length()) / 2;
        int x4 = (screenWidth - line4.length()) / 2;
        int x5 = (screenWidth - line5.length()) / 2;
        int x6 = (screenWidth - line6.length()) / 2;
        tg.putString(x1, 12, line1);
        tg.putString(x2, 13, line2);
        tg.putString(x3, 14, line3);
        tg.putString(x4, 15, line4);
        tg.putString(x5, 16, line5);
        tg.putString(x6, 17, line6);
    }
    private int handleMainMenu() {
        KeyStroke k;
        while (true) {
            k = readKeyStrokeInput();
            if (k != null) {
                Character character = k.getCharacter();

                if (character != null) {
                    switch (character) {
                        case 's':
                            return 1;
                        case 'q':
                            return 0;
                        case 'r':
                            showRanking();
                            break;
                        default:
                            // Opcional: Lida com outras entradas de caracteres ou ignora
                            break;
                    }
                } else {
                    // Lida com as entradas de não caracteres, se necessário
                    KeyType keyType = k.getKeyType();
                    switch (keyType) {
                        case ArrowUp:
                            // Implement action for Arrow Up if needed
                            break;
                        case ArrowDown:
                            // Implement action for Arrow Down if needed
                            break;
                        case Enter:
                            // Implement action for Enter key if needed
                            break;
                        // Add more cases as required
                        default:
                            // Optional: Handle other key types or ignore
                            break;
                    }
                }
            }
            sleep(100);
        }
    }

    private void showRanking() {
        clearScreen(); // Clear the screen before displaying ranking
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.ANSI.YELLOW);
        
        // Display the ranking header
        tg.putString(10, 2, "########## RANKING ##########");
        tg.putString(10, 3, "Name          Score");
        tg.putString(10, 4, "--------------------------");

        // Declare lineNumber outside the try block
        int lineNumber = 5; // Start displaying from line 5

        // Load and display the ranking from the file using ClassLoader
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Ranking/Ranking.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream == null) {
                tg.putString(10, lineNumber, "Erro: Ficheiro de ranking não encontrado.");
                refreshScreen();
                return; // Exit the method if the file is not found
            }
            
            String line;
            while ((line = reader.readLine()) != null) {
                tg.putString(10, lineNumber++, line); // Display each line of the ranking
            }
        } catch (IOException e) {
            tg.putString(10, lineNumber, "Erro ao carregar o ranking."); // Use lineNumber here
            e.printStackTrace();
        }

        // Prompt to return to the main menu
        tg.putString(10, lineNumber + 1, "Pressione qualquer tecla para voltar ao menu principal.");
        refreshScreen(); // Refresh the screen to show the ranking

        // Wait for a key press to return to the main menu
        try {
            screen.readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        openMainMenu(); // Return to the main menu
    }

    private void drawWalls() {
        TextGraphics tg = screen.newTextGraphics();
        for (Posicao p : state.getWalls()) {
            tg.setForegroundColor(TextColor.ANSI.GREEN);
            tg.putString(p.getX(), p.getY(), BORDER_STRING);
        }
    }
    private void startGame() {
        Som.stopSound();
        int counter = 0;
        drawWall();
        drawWalls(); // Add this line
        drawString(4, gameplay_height + 1, "SCORE: ", TextColor.ANSI.CYAN);
        drawScore();
        while (state.isSnakeAlive()) {
            if (counter % NEW_OBJECT_TIME_RATE == 0) {
                generateNewFruit();
                generateNewDynamite();
            }
            readKeyStrokeboard();
            if (counter % game_speed == 0) updateGame();
            sleep(1);
            counter++;
        }
    }
    private void increaseDifficulty() {
        // Calculate the increment based on fruits collected
        int increment = (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2;

        // Define new wall positions
        int[][] newWallPositions = {
                {10 + increment, 10},
                {11 + increment, 10},
                {12 + increment, 10},
                {20 + increment, 15},
                {21 + increment, 15},
                {22 + increment, 15},
                {30 + increment, 20},
                {31 + increment, 20},
                {32 + increment, 20}
        };

        // Iterate through each new wall position
        for (int[] pos : newWallPositions) {
            int x = pos[0];
            int y = pos[1];

            // Draw the wall on the screen
            drawString(x, y, BORDER_STRING, TextColor.ANSI.GREEN);

            // Add the wall position to the game state for collision detection
            Posicao wallPosicao = new Posicao(x, y);
            if (!state.getWalls().contains(wallPosicao)) { // Prevent adding duplicates
                state.getWalls().add(wallPosicao);
            }
        }
    }


    private void updateGame() {
        Posicao tail = state.getSnakeTail();
        clearStringAt(tail.getX(), tail.getY());
        state.moveSnake();
        drawSnake();
        Posicao head = state.getSnakeHead();
        if (checkCollision()) {
            highlightCrashPosition(head.getX(), head.getY());
            boolean isNewHighScore = state.killSnake(); // Check if new highscore
            Som.playSound("/sounds/gameover.wav");
            if (isNewHighScore) {
                String playerName = promptForHighScoreName();
                if (playerName.isEmpty()) {
                    playerName = "Anonymous";
                }
                state.setHighScoreName(playerName);
                displayMessage("Highscore saved! Press any key to continue.");
            } else {
                displayMessage("Game Over! Press any key to continue.");
            }
            try {
                screen.readInput(); // Wait for key press
            } catch (IOException e) {
                e.printStackTrace();
            } 

            openMainMenu();
        } else if (state.snakeAteFruit()) {
            drawScore();
            generateNewFruit();
            fruitsCollected++;
            if (fruitsCollected <= MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) {
                game_speed = Math.max(game_speed - SPEED_INCREMENT, MAX_GAME_SPEED); // Increase speed
            }
            if (fruitsCollected % MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE == 0) {
                increaseDifficulty();
            }
        } else if (state.snakeSteppedDynamite()) {
            highlightCrashPosition(head.getX(), head.getY());
            boolean isNewHighScore = state.killSnake(); // Check if new highscore
            Som.playSound("/sounds/explosion.wav");
            if (isNewHighScore) {
                String playerName = promptForHighScoreName();
                if (playerName.isEmpty()) {
                    playerName = "Anonymous";
                }
                state.setHighScoreName(playerName);
                displayMessage("Highscore saved! Press any key to continue.");
            } else {
                displayMessage("Game Over! Press any key to continue.");
            }
            try {
                screen.readInput(); // Wait for key press
            } catch (IOException e) {
                e.printStackTrace();
            } 
            
            openMainMenu();
        }
        refreshScreen();
    }

    /**
 * Prompts the player to enter their name for the highscore.
 *
 * @return The entered name.
 */
private String promptForHighScoreName() {
    TextGraphics tg = screen.newTextGraphics();
    tg.setForegroundColor(TextColor.ANSI.YELLOW);
    String prompt = "New Highscore! Enter your name: ";
    int promptX = (gameplay_width - prompt.length()) / 2;
    int promptY = gameplay_height / 2;
    tg.putString(promptX, promptY, prompt);
    
    try{
        screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    StringBuilder nameBuilder = new StringBuilder();
    while (true) {
        try {
            KeyStroke keyStroke = terminal.readInput();
            if (keyStroke == null) continue;
            if (keyStroke.getKeyType() == KeyType.Enter) {
                break;
            } else if (keyStroke.getKeyType() == KeyType.Backspace) {
                if (nameBuilder.length() > 0) {
                    nameBuilder.deleteCharAt(nameBuilder.length() - 1);
                    // Clear the last character on screen
                    int charX = promptX + prompt.length() + nameBuilder.length();
                    tg.putString(charX, promptY, " ");
                }
            } else {
                char c = keyStroke.getCharacter() != null ? keyStroke.getCharacter() : ' ';
                if (Character.isLetterOrDigit(c)) {
                    nameBuilder.append(c);
                    tg.setForegroundColor(TextColor.ANSI.GREEN);
                    tg.putString(promptX + prompt.length() + nameBuilder.length() - 1, promptY, String.valueOf(c));
                }
            }
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return nameBuilder.toString();
}


    private boolean checkCollision() {
        Posicao head = state.getSnakeHead();

        // Check collision with outer walls
        if (head.getX() <= 0 || head.getX() >= gameplay_width ||
                head.getY() <= 0 || head.getY() >= gameplay_height) {
            return true;
        }

        // Check collision with snake's body
        if (state.getSnakeBody().stream().filter(p -> !p.equals(head)).anyMatch(head::equals)) {
            return true;
        }

        // Check collision with internal walls
        if (state.getWalls().stream().anyMatch(head::equals)) {
            return true;
        }

        return false;
    }




    private void readKeyStrokeboard() {
        try {
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        state.setDirection(Direcao.UP);
                        break;
                    case ArrowDown:
                        state.setDirection(Direcao.DOWN);
                        break;
                    case ArrowLeft:
                        state.setDirection(Direcao.LEFT);
                        break;
                    case ArrowRight:
                        state.setDirection(Direcao.RIGHT);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void drawScore() {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.ANSI.CYAN);
        String scoreText = "SCORE: " + state.getScore();
        tg.putString(4, gameplay_height + 1, scoreText);
    
        // Display Highscore
        String highScoreText = "HIGH: " + state.getHighScoreName() + " - " + state.getHighScore();
        tg.setForegroundColor(TextColor.ANSI.MAGENTA);
        int highScoreX = gameplay_width - highScoreText.length() - 4;
        int highScoreY = gameplay_height + 1;
        tg.putString(highScoreX, highScoreY, highScoreText);
    }


    private void drawWall() {

        TextGraphics tg = screen.newTextGraphics();
        for (Posicao p : state.getWalls()) {
            tg.setForegroundColor(TextColor.ANSI.GREEN);
            tg.putString(p.getX(), p.getY(), BORDER_STRING);
        }
        for (int i = 0; i < gameplay_width; i++) {
            drawString(i, 0, BORDER_STRING, TextColor.ANSI.GREEN);
            drawString(i, gameplay_height, BORDER_STRING, TextColor.ANSI.GREEN);
        }
        for (int i = 0; i <= gameplay_height; i++) {
            drawString(0, i, BORDER_STRING, TextColor.ANSI.GREEN);
            drawString(gameplay_width, i, BORDER_STRING, TextColor.ANSI.GREEN);
        }
    }

    private void drawSnake() {
        TextGraphics tg = screen.newTextGraphics();
        for (Posicao p : state.getSnakeBody()) {
            if (p.equals(state.getSnakeHead())) {
                tg.setForegroundColor(TextColor.ANSI.GREEN);
                tg.putString(p.getX(), p.getY(), SNAKE_HEAD_STRING);
            } else {
                tg.setForegroundColor(TextColor.ANSI.GREEN);
                tg.putString(p.getX(), p.getY(), SNAKE_BODY_STRING);
            }
        }
    }
    private void highlightCrashPosition(int x, int y) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.ANSI.RED);
        tg.putString(x, y, "X");
    }
    private void drawString(int x, int y, String string, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        if (color != null) tg.setForegroundColor(color);
        tg.putString(x, y, string);
    }
    private void generateNewFruit() {
        Posicao newFruitPosicao = state.generateRandomObject(gameplay_width - 1, gameplay_height - 1);
        state.getFruits().add(newFruitPosicao);
        drawString(newFruitPosicao.getX(), newFruitPosicao.getY(), FRUIT_STRING, TextColor.ANSI.RED);
    }
    private void generateNewDynamite() {
        Posicao newDynamitePosicao = state.generateRandomObject(gameplay_width - 1, gameplay_height - 1);
        state.getDynamites().add(newDynamitePosicao);
        drawString(newDynamitePosicao.getX(), newDynamitePosicao.getY(), DYNAMITE_STRING, TextColor.ANSI.WHITE_BRIGHT);
    }
    private void clearStringAt(int x, int y) {
        drawString(x, y, EMPTY_STRING, null);
    }
    private void refreshScreen() {
        try {
            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void exitGame() {
        try {
            Som.stopSound(); //stops loops sound
            screen.stopScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private KeyStroke readKeyStrokeInput() {
        try {
            return terminal.readInput();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}