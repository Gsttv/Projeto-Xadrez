package xadrez;

import jogo.Tabuleiro;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
    }

    public PecaXadrez[][] getPecas(){  // Metodo para retorna uma matriz com as peças
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColunas()];
        for (int i=0; i<tabuleiro.getLinha();i++){
            for (int j=0; j<tabuleiro.getColunas();j++){
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i,j); // necessario fazer um downcast
            }
        }
        return matriz;
    }
}
