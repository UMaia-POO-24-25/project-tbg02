package org.example;

/**
 * A classe Posicao representa uma posição com coordenadas x e y.
 * Ela fornece métodos para obter as coordenadas e sobrescreve os métodos equals e hashCode
 * para permitir a comparação de objetos Posicao e o uso em coleções baseadas em hash.
 */

public class Posicao {
    private int x;
    private int y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se é o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false; // Verifica se é o tipo correto
        Posicao posicao = (Posicao) o; // Converte o objeto para Posicao
        return x == posicao.x && y == posicao.y; // Verifica se as coordenadas são iguais
    }

    @Override
    public int hashCode() {
        return 31 * x + y; // Gera um código hash baseado nas coordenadas x e y
    }
}

