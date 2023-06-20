package jogo;

public class Tabuleiro {

    private Integer linha;
    private Integer colunas;
    private Peca[][] pecas;

    public Tabuleiro(Integer linha, Integer colunas) {
        this.linha = linha;
        this.colunas = colunas;
        pecas = new Peca[linha][colunas];
    }

    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public Integer getColunas() {
        return colunas;
    }

    public void setColunas(Integer colunas) {
        this.colunas = colunas;
    }

    public Peca peca(int linha, int colunas){
        return pecas[linha][colunas];
    }

    public Peca peca(Posicao posicao){
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

}
