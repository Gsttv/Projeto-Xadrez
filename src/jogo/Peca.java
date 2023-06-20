package jogo;

public class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) { // Apenas tabuleiro é passado como argumento porque uma peça recem criar tem a posição nula
        this.tabuleiro = tabuleiro;    // dizendo q a peça nao foi colada no tabuleiro.
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

}
