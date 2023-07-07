package xadrez;

import jogo.Peca;
import jogo.Posicao;
import jogo.Tabuleiro;

public  abstract class PecaXadrez extends Peca {

    private Cor cor;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    protected boolean temUmaPecaOponente(Posicao posicao){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }

    public PosicaoNoXadrez getPosicaoNoXarez(){
        return PosicaoNoXadrez.PosicaoToPosicaoNoXarez(posicao);
    }


}
