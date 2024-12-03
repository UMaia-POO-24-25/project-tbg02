package org.example;

import java.util.LinkedList;
import java.util.Random;

/**
 * Representa o estado do jogo da cobra, incluindo a cobra, os frutos, os obstáculos e a pontuação.
 */
public class GameState {

    // Penalização em pontuação quando a cobra pisa numa dinamite.
    private final static int SCORE_PENALTY = 25;

    private Snake snake;

    // Lista de frutos e dinamites no tabuleiro.
    private LinkedList<Posicao> fruits;
    private LinkedList<Posicao> dynamites;
    private LinkedList<Posicao> walls;

    // Current score
    private int score;

    // Gerador de números aleatórios.
    private final Random rand;

    /**
     * Instancia um novo objeto GameState.
     */
    public GameState() {
        rand = new Random();
        snake = new Snake(Direcao.RIGHT); // Snake starts facing right
        fruits = new LinkedList<>();
        dynamites = new LinkedList<>();
        walls = new LinkedList<>();
        score = 0;
        //hightscore();
        // Add walls to the game
        generateWalls();
    }

    // Getters
    public LinkedList<Posicao> getFruits() {
        return fruits;
    }

    public LinkedList<Posicao> getDynamites() {
        return dynamites;
    }

    public int getScore() {
        return score;
    }

    public boolean isSnakeAlive() {
        return snake.isAlive();
    }

    public void killSnake() {
        snake.kill();
    }

    public void moveSnake() {
        snake.move();
    }

    public LinkedList<Posicao> getSnakeBody() {
        return snake.getBody();
    }

    public Posicao getSnakeHead() {
        return (Posicao) snake.getHead();
    }

    public Posicao getSnakeTail() {
        return (Posicao) snake.getTail();
    }

    /**
     * Verifica se a cobra comeu um fruto. Se sim, aumenta a pontuação e o tamanho da cobra
     * @return Verdadeiro se a cobra comeu um fruto, caso contrário, falso.
     */
    public boolean snakeAteFruit() {
        if (snake.ateFruit(fruits)) {
            updateScore(true);
            snake.increaseSize();
            return true;
        }
        return false;
    }
    private void generateWalls() {
        // Example: Add walls at specific positions
        walls.add(new Posicao(10, 10));
        walls.add(new Posicao(11, 10));
        walls.add(new Posicao(12, 10));
        // Add more walls as needed
    }

    /**
     * Verifica se a cobra pisou numa dinamite. Se sim, diminui a pontuação
     * @return Verdadeiro se a cobra pisou numa dinamite, caso contrário, falso.
     */
    public boolean snakeSteppedDynamite() {
        if (snake.steppedOverDynamite(dynamites)) {
            updateScore(false);
            return true;
        }
        return false;
    }

    /**
     * Define a direção da cobra, impedindo-a de virar para a direção oposta.
     */
    public void setDirection(Direcao dir) {
        if (dir == null) return;

        switch (dir) {
            case UP:
                if (snake.getDirection() != Direcao.DOWN) snake.setDirection(Direcao.UP);
                break;
            case DOWN:
                if (snake.getDirection() != Direcao.UP) snake.setDirection(Direcao.DOWN);
                break;
            case LEFT:
                if (snake.getDirection() != Direcao.RIGHT) snake.setDirection(Direcao.LEFT);
                break;
            case RIGHT:
                if (snake.getDirection() != Direcao.LEFT) snake.setDirection(Direcao.RIGHT);
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    /**
     * Atualiza a pontuação com base nos eventos do jogo.
     */
    private void updateScore(boolean increase) {
        if (increase) {
            score += (snake.getBody().size() * 2 + dynamites.size());
        } else {
            score -= SCORE_PENALTY;
        }
    }

    /**
     * Gera um número inteiro aleatório entre min (inclusivo) e max (exclusivo).
     * @return Um número inteiro aleatório no intervalo [min, max).
     */
    private int randomNumber(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    /**
     * Gera uma posição aleatória dentro da área do jogo.
     * @return Uma nova posição aleatória.
     */
    private Posicao generateRandomPosition(int max_x, int max_y) {
        int x = randomNumber(1, max_x);
        int y = randomNumber(1, max_y);
        return new Posicao(x, y);
    }

    public LinkedList<Posicao> getWalls() {
        return walls;
    }
    /**
     * Verifica se a posição dada está vazia (não ocupada pela cobra, frutos ou dinamites).
     * @return Verdadeiro se a posição estiver vazia, caso contrário, falso.
     */
    private boolean isEmptyPosition(Posicao p) {
        return !(snake.isBody((Posicao) p) || fruits.contains(p) || dynamites.contains(p));
    }

    /**
     * Gera uma posição aleatória para um novo objeto, garantindo que não está já ocupada.
     * @return Uma posição válida para um novo objeto.
     */
    public Posicao generateRandomObject(int x_max, int y_max) {
        Posicao p;
        do {
            p = generateRandomPosition(x_max, y_max);
        } while (!isEmptyPosition(p));
        return p;
    }
}
