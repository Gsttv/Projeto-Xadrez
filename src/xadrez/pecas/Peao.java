package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.WHITE) {
            p.setPosicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().existePeca(p2) && getMoveCount() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && temUmaPecaOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && temUmaPecaOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
        } else {
            p.setPosicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().existePeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().existePeca(p2) && getMoveCount() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExiste(p) && temUmaPecaOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            p.setPosicao(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExiste(p) && temUmaPecaOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }
        return mat;


    }
}
