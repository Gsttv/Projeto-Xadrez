package jogo;

public abstract class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) { // Apenas tabuleiro é passado como argumento porque uma peça recem criar tem a posição nula
        this.tabuleiro = tabuleiro;    // dizendo q a peça nao foi colada no tabuleiro.
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public abstract boolean[][] movimentosPossiveis();

    public boolean movimentoPossivel(Posicao posicao){
        return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean existeAlgumMovimentoPossivel(){
        boolean[][] mat = movimentosPossiveis();
        for (int i=0; i< mat.length;i++){
            for (int j=0;j< mat.length;j++){
                if (mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

}
