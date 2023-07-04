package xadrez;

import jogo.Peca;
import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        SetupInicial();
    }

    public PecaXadrez[][] getPecas(){  // Metodo para retorna uma matriz com as peças
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColunas()];
        for (int i=0; i<tabuleiro.getLinha();i++){
            for (int j=0; j<tabuleiro.getColunas();j++){
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i,j); // necessario fazer um downcast
            }
        }
        return matriz;
    }

    public PecaXadrez moverPecas(PosicaoNoXadrez posicaoInicial, PosicaoNoXadrez posicaoDestino){
        Posicao inicio = posicaoInicial.ConvertToPosicao();
        Posicao destino = posicaoDestino.ConvertToPosicao();
        validarPosicao(inicio);
        Peca pecaComida = fazerJogada(inicio,destino);
        return (PecaXadrez) pecaComida;
    }

    private Peca fazerJogada(Posicao inicio, Posicao destino){
        Peca p = tabuleiro.RomeverPeca(inicio);
        Peca pecaComida = tabuleiro.RomeverPeca(destino);
        tabuleiro.ColocarPeca(p,destino);
        return pecaComida;

    }

    private void validarPosicao(Posicao incio){
        if (!tabuleiro.existePeca(incio)){
            throw new XadrezException("Nao existe peca na posicao");
        }
    }

    private void ColocarNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez){
        tabuleiro.ColocarPeca(pecaXadrez,new PosicaoNoXadrez(coluna,linha).ConvertToPosicao());
    }

    private void SetupInicial(){
        ColocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));

        ColocarNovaPeca('c',7 , new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));


    }
}
