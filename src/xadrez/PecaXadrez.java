package xadrez;

import jogo.Peca;
import jogo.Posicao;
import jogo.Tabuleiro;

public  abstract class PecaXadrez extends Peca {

    private Cor cor;
    private int moveCount;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increasewMoveCount(){
        moveCount ++;
    }
    public void decreasewMoveCount(){
        moveCount --;
    }

    protected boolean temUmaPecaOponente(Posicao posicao){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }

    public PosicaoNoXadrez getPosicaoNoXarez(){
        return PosicaoNoXadrez.PosicaoToPosicaoNoXarez(posicao);
    }


}
