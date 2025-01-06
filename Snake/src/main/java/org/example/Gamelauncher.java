package org.example;

/**
 * A classe Gamelauncher é o ponto de entrada do jogo.
 * Ela define as dimensões da janela do jogo e inicializa a visualização do jogo.
 * Em seguida, abre o menu principal do jogo.
 */

public class Gamelauncher {
    public static void main(String[] args) {
        final int LARGE_WIDTH = 80;
        final int LARGE_HEIGHT = 23;
        System.out.println("Começa o jogo com a largura " + LARGE_WIDTH + " e altura: " + LARGE_HEIGHT);
        GameView game = new GameView(LARGE_WIDTH, LARGE_HEIGHT);
        game.openMainMenu();
    }
}
