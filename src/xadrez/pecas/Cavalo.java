package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "C";
    }
    private boolean podeMover(Posicao posicao){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0,0);


        p.setPosicao(posicao.getLinha() -1 , posicao.getColuna()-2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        p.setPosicao(posicao.getLinha()-2, posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }



        p.setPosicao(posicao.getLinha()-2, posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        p.setPosicao(posicao.getLinha() -1 , posicao.getColuna()+2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }



        p.setPosicao(posicao.getLinha() +1 , posicao.getColuna() +2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // superior direito

        p.setPosicao(posicao.getLinha() +2 , posicao.getColuna() +1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // inferior esquerdo

        p.setPosicao(posicao.getLinha() +2 , posicao.getColuna() -1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //inferior direito

        p.setPosicao(posicao.getLinha() +1 , posicao.getColuna() -2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        return mat;

    }
}
