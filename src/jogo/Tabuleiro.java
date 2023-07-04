package jogo;

public class Tabuleiro {

    private Integer linha;
    private Integer colunas;
    private Peca[][] pecas;  // pecas que estao no tabuleiro

    public Tabuleiro(Integer linha, Integer colunas) { //O construtor da classe recebe como parâmetros o número de linhas e colunas do
        if(linha<1 || colunas<1){
            throw new GameException("Erro ao criar o tabuleiro, é necessario ao menos uma linha e uma coluna");
        }
        this.linha = linha;                            // tabuleiro e inicializa os atributos correspondentes. Além disso, ele cria uma
        this.colunas = colunas;                        // matriz pecas com o tamanho determinado pelas linhas e colunas.
        pecas = new Peca[linha][colunas];

    }

    public Integer getLinha() {
        return linha;
    }

    public Integer getColunas() {
        return colunas;
    }

    public Peca peca(int linha, int colunas){
        if(!posicaoExiste(linha,colunas)){
            throw new GameException("A posição informada não existe no tabuleiro");
        }
        return pecas[linha][colunas];
    }

    public Peca peca(Posicao posicao){
        if(!posicaoExiste(posicao)){
            throw new GameException("A posição informada não existe no tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void ColocarPeca(Peca peca, Posicao posicao){
        if (existePeca((posicao))){
            throw new GameException("Já existe uma peça na posição informada: " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca; // Na posicao passada sera atribuido a peça
        peca.posicao = posicao;
    }

    public Peca RomeverPeca(Posicao posicao){
        if (!posicaoExiste(posicao)){
            throw new GameException("Posição infomrada não existe");
        }if(peca(posicao)==null){
            return null;
        }
        Peca temp = peca(posicao);
        temp.posicao = null;
        pecas[posicao.getLinha()][posicao.getColuna()] = null;
        return temp;
    }

    private boolean posicaoExiste(int coluna, int linhas){
        return  linhas>=0 && linhas< linha && coluna>=0 && coluna<colunas;  // metodo para retorna se uma posicao existe
    }
    public boolean posicaoExiste(Posicao posicao){
        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    public boolean existePeca(Posicao posicao){
        if(!posicaoExiste(posicao)){
            throw new GameException("A posição informada não existe no tabuleiro");
        }
        return peca(posicao)!=null;
    }


}
