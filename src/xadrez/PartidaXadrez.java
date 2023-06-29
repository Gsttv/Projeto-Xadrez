package xadrez;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        SetupInicial();
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

    private void SetupInicial(){
        tabuleiro.ColocarPeca(new Torre(tabuleiro,Cor.WHITE),new Posicao(7,1));
        tabuleiro.ColocarPeca(new Rei(tabuleiro,Cor.BLACK),new Posicao(4,1));
        tabuleiro.ColocarPeca(new Rei(tabuleiro,Cor.WHITE),new Posicao(7,2));
    }
}
