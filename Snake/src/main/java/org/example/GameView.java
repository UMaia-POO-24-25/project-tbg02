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
import java.io.InputStream;


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
    private static Terminal terminal;
    private static Screen screen;
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
                switch (k.getCharacter()) {
                    case 's':
                        return 1;
                    case 'q':
                        return 0;
                    case 'H':
                        return 2;
                }
            }
            sleep(100);
        }
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
        // Example: Draw a few walls inside the game area
        // You can add more walls or change their positions as needed
        drawString(10 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 10, BORDER_STRING, TextColor.ANSI.GREEN);
        drawString(11 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 10, BORDER_STRING, TextColor.ANSI.GREEN);
        drawString(12 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 10, BORDER_STRING, TextColor.ANSI.GREEN);
        drawString(20 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 15, BORDER_STRING, TextColor.ANSI.GREEN);
        drawString(21 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 15, BORDER_STRING, TextColor.ANSI.GREEN);
        drawString(22 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 15, BORDER_STRING, TextColor.ANSI.GREEN);
        drawString(30 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 20, BORDER_STRING, TextColor.ANSI.GREEN);
        drawString(31 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 20, BORDER_STRING, TextColor.ANSI.GREEN);
        drawString(32 + (fruitsCollected / MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) * 2, 20, BORDER_STRING, TextColor.ANSI.GREEN);
    }
    private void updateGame() {
        Posicao tail = state.getSnakeTail();
        clearStringAt(tail.getX(), tail.getY());
        state.moveSnake();
        drawSnake();
        Posicao head = state.getSnakeHead();
        if (checkCollision()) {
            highlightCrashPosition(head.getX(), head.getY());
            state.killSnake();
            Som.playSound("/sounds/gameover.wav");
            openMainMenu();
        } else if (state.snakeAteFruit()) {
            drawScore();
            generateNewFruit();
            fruitsCollected++;
            if (fruitsCollected <= MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE) {
                game_speed = Math.max(game_speed - SPEED_INCREMENT, MAX_GAME_SPEED); // Increase speed with limit
            }
            if (fruitsCollected % MAX_FRUITS_BEFORE_DIFFICULTY_INCREASE == 0) {
                increaseDifficulty();
            }
        } else if (state.snakeSteppedDynamite()) {
            highlightCrashPosition(head.getX(), head.getY());
            state.killSnake();
            Som.playSound("/sounds/explosion.wav");
            openMainMenu();
        }
        refreshScreen();
    }
    private boolean checkCollision() {
        Posicao head = state.getSnakeHead();
        if (head.getX() <= 0 || head.getX() >= gameplay_width ||
                head.getY() <= 0 || head.getY() >= gameplay_height) {
            return true;
        }
        if (state.getSnakeBody().stream().filter(p -> !p.equals(head)).anyMatch(head::equals)) {
            return true;
        }
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