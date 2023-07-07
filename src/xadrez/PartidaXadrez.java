package xadrez;

import jogo.Peca;
import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

import java.util.ArrayList;
import java.util.List;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;
    private int Turno;
    private Cor jogadorAtual;
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        Turno = 1;
        jogadorAtual = Cor.WHITE;
        SetupInicial();
    }

    public int getTurno() {
        return Turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
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

    public boolean[][] movimentosPossiveis(PosicaoNoXadrez posicaoInicial){
        Posicao posicao = posicaoInicial.ConvertToPosicao();
        validarPosicao(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }

    public PecaXadrez moverPecas(PosicaoNoXadrez posicaoInicial, PosicaoNoXadrez posicaoDestino){
        Posicao inicio = posicaoInicial.ConvertToPosicao();
        Posicao destino = posicaoDestino.ConvertToPosicao();
        validarPosicao(inicio);
        validarPosicaoDestino(inicio,destino);
        Peca pecaComida = fazerJogada(inicio,destino);
        proximoTurno();
        return (PecaXadrez) pecaComida;
    }

    private Peca fazerJogada(Posicao inicio, Posicao destino){
        Peca p = tabuleiro.RomeverPeca(inicio);
        Peca pecaComida = tabuleiro.RomeverPeca(destino);
        tabuleiro.ColocarPeca(p,destino);
        if (pecaComida != null){
            pecasNoTabuleiro.remove(pecaComida);
            pecasCapturadas.add(pecaComida);
        }

        return pecaComida;

    }

    private void validarPosicao(Posicao incio){
        if (!tabuleiro.existePeca(incio)){
            throw new XadrezException("Nao existe peca na posicao");
        }
        if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(incio)).getCor()){
            throw new XadrezException("Essa peca nao e sua ");
        }
        if (!tabuleiro.peca(incio).existeAlgumMovimentoPossivel()){
            throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
        }
    }

    private void validarPosicaoDestino(Posicao inicio, Posicao destino){
        if (!tabuleiro.peca(inicio).movimentoPossivel(destino)){
            throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino");
        }
    }

    private void ColocarNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez){
        tabuleiro.ColocarPeca(pecaXadrez,new PosicaoNoXadrez(coluna,linha).ConvertToPosicao());
        pecasNoTabuleiro.add(pecaXadrez);
    }

    public void proximoTurno(){
        Turno++;
        jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
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
