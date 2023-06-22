package jogo;

public class Tabuleiro {

    private Integer linha;
    private Integer colunas;
    private Peca[][] pecas;  // pecas que estao no tabuleiro

    public Tabuleiro(Integer linha, Integer colunas) { //O construtor da classe recebe como parâmetros o número de linhas e colunas do
        this.linha = linha;                            // tabuleiro e inicializa os atributos correspondentes. Além disso, ele cria uma
        this.colunas = colunas;                        // matriz pecas com o tamanho determinado pelas linhas e colunas.
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

    public void ColocarPeca(Peca peca, Posicao posicao){
        pecas[posicao.getLinha()][posicao.getColuna()] = peca; // Na posicao passada sera atribuido a peça
        peca.posicao = posicao;
    }

}
