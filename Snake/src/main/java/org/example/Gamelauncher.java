package org.example;
public class Gamelauncher {
    public static void main(String[] args) {
        final int LARGE_WIDTH = 80;
        final int LARGE_HEIGHT = 23;
        System.out.println("Começa o jogo com a largura " + LARGE_WIDTH + " e altura: " + LARGE_HEIGHT);
        GameView game = new GameView(LARGE_WIDTH, LARGE_HEIGHT);
        game.openMainMenu();
    }
}
