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

    private void ColocarNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez){
        tabuleiro.ColocarPeca(pecaXadrez,new PosicaoNoXadrez(coluna,linha).ConvertToPosicao());
    }

    private void SetupInicial(){
        ColocarNovaPeca('b',6,new Torre(tabuleiro,Cor.WHITE));
        ColocarNovaPeca('c',4,new Rei(tabuleiro,Cor.BLACK));
        ColocarNovaPeca('a',3,new Rei(tabuleiro,Cor.WHITE));
    }
}
