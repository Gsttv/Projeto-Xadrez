package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;
import xadrez.PosicaoNoXadrez;

public class Rei extends PecaXadrez {
    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0,0);

        // acima
        p.setPosicao(posicao.getLinha() -1 , posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda
        p.setPosicao(posicao.getLinha(), posicao.getColuna()-1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //direita

        p.setPosicao(posicao.getLinha(), posicao.getColuna()+1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        // abaixo

        p.setPosicao(posicao.getLinha() +1 , posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // superior esquerdo

        p.setPosicao(posicao.getLinha() -1 , posicao.getColuna() -1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // superior direito

        p.setPosicao(posicao.getLinha() -1 , posicao.getColuna() +1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // inferior esquerdo

        p.setPosicao(posicao.getLinha() +1 , posicao.getColuna() -1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //inferior direito

        p.setPosicao(posicao.getLinha() +1 , posicao.getColuna() +1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        return mat;
    }
}
