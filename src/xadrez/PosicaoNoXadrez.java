package xadrez;

import jogo.Posicao;

public class PosicaoNoXadrez {

    private char coluna;
    private int linha;

    public PosicaoNoXadrez(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8){
            throw new XadrezException("Posição invalida");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    protected Posicao ConvertToPosicao(){
        return new Posicao(8-linha,coluna-'a');
    }

    protected  static PosicaoNoXadrez PosicaoToPosicaoNoXarez(Posicao posicao){
        return new PosicaoNoXadrez((char)('a'+ posicao.getColuna()),8  - posicao.getLinha());
    }

    @Override
    public String toString() {
        return "" + coluna +linha;
    }
}
