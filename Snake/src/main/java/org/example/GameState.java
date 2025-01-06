package org.example;

import java.util.LinkedList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


/**
 * Representa o estado do jogo da cobra, incluindo a cobra, os frutos, os obstáculos e a pontuação.
 */

public class GameState {

    private final static int SCORE_PENALTY = 25;
    private Snake snake;
    private LinkedList<Posicao> fruits;
    private LinkedList<Posicao> dynamites;
    private LinkedList<Posicao> walls;
    private int score; // Current score
    private String highScoreName = "None"; // Highscore fields
    private int highScore = 0;
    private final String HIGH_SCORE_FILE = "src\\main\\resources\\Ranking\\Ranking.txt";
    private final Random rand; // Gerador de números aleatórios.

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

        loadHighScore(); // Carrega a melhor pontuação do arquivo

        
        generateWalls(); // Adiciona paredes no jogo
    }

    // Responsavel por verificar a pontuaçao mais alta do arquivo
    private void loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) { // Le a informaçao do txt
            String name = reader.readLine();
            String scoreStr = reader.readLine();
            //valida de o scoreStr contem apenas numeros antes de converter
            if (name != null && scoreStr != null) {
                highScoreName = name;
                highScore = Integer.parseInt(scoreStr);
            }
        } catch (IOException e) {
            //adicona uma mensagem para ser mais facil ver o problema
        }
    }
// Guarda a melhor pontuaçao
private void saveHighScore() {
    // Define the path to the high score file
    File highScoreFile = new File("src/main/resources/Ranking/Ranking.txt");
    
    //verifica se o diretorio existe
    highScoreFile.getParentFile().mkdirs(); // cria um novo diretorio caso nao exista

    //grava texto no arquivo
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(highScoreFile))) {
        writer.write(highScoreName);
        writer.newLine();
        writer.write(String.valueOf(highScore));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

  // Verifica e ve se existe alteraçao na tabela de pontuaçao
  public boolean checkHighScore() {
    if (score > highScore) {
        highScore = score;
      //highScoreName = getPlayerName();
        //saveHighScore();
        return true;
    }
    return false;
}

// Responsavel por armazenar a melhor pontuaçao
public void setHighScoreName(String name) {
    this.highScoreName = name;
    saveHighScore();
}


// Devolve a melhor pontuaçao

public int getHighScore() {
    return highScore;
}

public String getHighScoreName() {
    return highScoreName;
}


    // Responsaveis por colocar os frutos e os dinamites

    public LinkedList<Posicao> getFruits() {
        return fruits;
    }

    public LinkedList<Posicao> getDynamites() {
        return dynamites;
    }

    // Devolve a pontuaçao do jogador
    public int getScore() {
        return score;
    }


    //Verifica de a Snake se encontra viva
    public boolean isSnakeAlive() {
        return snake.isAlive();
    }

    //Mata a Snake, e verifica se a pontuação atual é um novo recorde e exibe informações.
    public boolean killSnake() {
        snake.kill();
        boolean isNewHighScore = checkHighScore();
        System.out.println("Current Score: " + getScore());
        System.out.println("Highscore: " + getHighScoreName() + " - " + getHighScore());
        return isNewHighScore;
    }

    // Responsavel por mover a cobra na sua direçao atual
    public void moveSnake() {
        snake.move();
    }

    // Devolve a lista de posições que representam o corpo da Snake.
    public LinkedList<Posicao> getSnakeBody() {
        return snake.getBody();
    }

    // Devolve a posicao da cabeça da Snake
    public Posicao getSnakeHead() {
        return (Posicao) snake.getHead();
    }

    // Devolve a posicao da cauda da Snake
    public Posicao getSnakeTail() {
        return (Posicao) snake.getTail();
    }

    /**
     * Verifica se a cobra comeu um fruto. Se sim, aumenta a pontuação e o tamanho da cobra
     *
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

    // Responsavel por criar paredes durante o jogo para aumentar a dificuldade.

    private void generateWalls() {

        walls.add(new Posicao(10, 10));
        walls.add(new Posicao(11, 10));
        walls.add(new Posicao(12, 10));

    }

    /**
     * Verifica se a cobra pisou numa dinamite. Se sim, diminui a pontuação
     *
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
     *
     * @return Um número inteiro aleatório no intervalo [min, max).
     */
    private int randomNumber(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    /**
     * Gera uma posição aleatória dentro da área do jogo.
     *
     * @return Uma nova posição aleatória.
     */
    public Posicao generateRandomPosition(int max_x, int max_y) {
        int x = randomNumber(1, max_x);
        int y = randomNumber(1, max_y);
        return new Posicao(x, y);
    }

    public LinkedList<Posicao> getWalls() {
        return walls;
    }

    /**
     * Verifica se a posição dada está vazia (não ocupada pela cobra, frutos ou dinamites).
     *
     * @return Verdadeiro se a posição estiver vazia, caso contrário, falso.
     */
    public boolean isEmptyPosition(Posicao p) {
        return !(snake.isBody((Posicao) p) || fruits.contains(p) || dynamites.contains(p));
    }

    /**
     * Gera uma posição aleatória para um novo objeto, garantindo que não está já ocupada.
     *
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
