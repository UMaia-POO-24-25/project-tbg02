package org.example;

import java.util.LinkedList;

/**
 * Representa o objeto Snake.
 */

public class Snake {
    private final static int SNAKE_INITIAL_SIZE = 4; // Tamanho inicial da Snake.
    private LinkedList<Posicao> body; // Posições do corpo da Snake
    private Direcao direcao; // Direção atual da Snake.
    private boolean alive; // Indica se a Snake se encontra viva.

    /**
     * Instancia um novo objeto Snake voltado para a direção dada.
     */

    public Snake(Direcao starting_direcao) {
        body = new LinkedList<>();

        // Inicia o corpo da Snake, que começa em uma posição predefinida.

        for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
            body.add(new Posicao(i + 3, 15));
        }
        direcao = starting_direcao;
        alive = true;
    }
    // Getters e setters
    public LinkedList<Posicao> getBody() { // Devolve o método do corpo da Snake
        return body;
    } 
    public Posicao getHead() {
        return body.getLast();// Devolve o método da cabeça da Snake
    } 
    public Posicao getTail() {
        return body.getFirst(); // Devolve o método da cauda da Snake
    }
    public void setDirection(Direcao direcao) {
        this.direcao = direcao; // Define a direção da Snake.
    }  
    public Direcao getDirection() {
        return direcao; // Verifica a direção atual da Snake.
    } 
    public boolean isAlive() {
        return alive; // Verifica se a Snake está viva.
    } 
    public void kill() {
        alive = false; // Marca a Snake como morta.
    }


    /**
     * Move a cobra na direção atual.
     * Remove a cauda e adicione uma nova cabeça com base na direção.
     */


    public void move() {
        Posicao head = getHead();
        body.removeFirst();
        // Determine a nova posição da cabeça.
        Posicao newHead;
        switch (direcao) {
            case UP:
                newHead = new Posicao(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                newHead = new Posicao(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                newHead = new Posicao(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                newHead = new Posicao(head.getX() + 1, head.getY());
                break;
            default:
                throw new IllegalArgumentException("Direção Inválida");
        }
        // Nova cabeça
        body.addLast(newHead);
    }
    /**
     * Verifica se uma determinada posição faz parte do corpo da cobra.
     * Isto é útil para verificar colisões com a própria cobra.
     */

    public boolean isBody(Posicao p) {
        return body.contains(p);
    }
    /**
     * Verifica se a cabeça da cobra está na mesma posição que uma fruta.
     * Se for o caso, remove a fruta da lista.
     */

    public boolean ateFruit(LinkedList<Posicao> fruits) {
        Posicao head = getHead();
        return fruits.removeIf(head::equals);
    }

    /**
     * Verifica se a cabeça da cobra está na mesma posição que uma dinamite.
     */
    
    public boolean steppedOverDynamite(LinkedList<Posicao> dynamites) {
        Posicao head = getHead();
        return dynamites.removeIf(head::equals);
    }
    /**
     * Aumenta o tamanho da cobra adicionando um novo segmento.
     */

    public void increaseSize() {
        Posicao tail = getTail();
        body.addFirst(new Posicao(tail.getX(), tail.getY()));
    }

}
