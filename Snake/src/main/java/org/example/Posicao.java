package org.example;

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
        return 31 * x + y;
    }
}

