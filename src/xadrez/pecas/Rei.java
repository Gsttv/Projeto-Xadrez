package xadrez.pecas;

import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoNoXadrez;

public class Rei extends PecaXadrez {

    private PartidaXadrez partidaXadrez;
    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    private boolean testeRoque(Posicao posicao){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getMoveCount() == 0;
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

        // ----- Jogada especial Roque ------
        if (getMoveCount() == 0 && !partidaXadrez.isCheck()){
            Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna()+3);
            if (testeRoque(posT1)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna()+1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna()+2);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null ){
                    mat[posicao.getLinha()][posicao.getColuna() + 2] =  true;
                }
            }
            Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna()-4);
            if (testeRoque(posT2)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna()-1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna()-2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna()-3);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null){
                    mat[posicao.getLinha()][posicao.getColuna() + 2] =  true;
                }
            }
        }


        return mat;
    }
}
