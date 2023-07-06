package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0,0);

        // acima
        p.setPosicao(posicao.getLinha() -1 , posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() -1);
        }
        if (getTabuleiro().posicaoExiste(p) && temUmaPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda

        p.setPosicao(posicao.getLinha(), posicao.getColuna()-1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() -1);
        }
        if (getTabuleiro().posicaoExiste(p) && temUmaPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //direita

        p.setPosicao(posicao.getLinha(), posicao.getColuna()+1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() +1);
        }
        if (getTabuleiro().posicaoExiste(p) && temUmaPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // abaixo

        p.setPosicao(posicao.getLinha() +1 , posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() +1);
        }
        if (getTabuleiro().posicaoExiste(p) && temUmaPecaOponente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        return mat;
    }
}
