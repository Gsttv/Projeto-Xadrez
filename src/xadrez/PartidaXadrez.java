package xadrez;

import jogo.Peca;
import jogo.Posicao;
import jogo.Tabuleiro;
import xadrez.pecas.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;
    private int Turno;
    private Cor jogadorAtual;
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();
    private boolean check;
    private boolean checkMate;
    private PecaXadrez promoted;


    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
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

    public boolean isCheck() {
        return check;
    }

    public PecaXadrez getPromoted() {
        return promoted;
    }

    public PecaXadrez[][] getPecas() {  // Metodo para retorna uma matriz com as peças
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinha(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j); // necessario fazer um downcast
            }
        }
        return matriz;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public boolean[][] movimentosPossiveis(PosicaoNoXadrez posicaoInicial) {
        Posicao posicao = posicaoInicial.ConvertToPosicao();
        validarPosicao(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }

    public PecaXadrez moverPecas(PosicaoNoXadrez posicaoInicial, PosicaoNoXadrez posicaoDestino) {
        Posicao inicio = posicaoInicial.ConvertToPosicao();
        Posicao destino = posicaoDestino.ConvertToPosicao();
        validarPosicao(inicio);
        validarPosicaoDestino(inicio, destino);
        Peca pecaComida = fazerJogada(inicio, destino);
        if (testCheck(jogadorAtual)) {
            desfazerJogada(inicio, destino, pecaComida);
            throw new XadrezException("Voce nao pode se colocar em check ");
        }

        PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino);

        // promoted
        promoted = null;
        if (pecaMovida instanceof Peao){
            if ((pecaMovida.getCor() == Cor.WHITE && destino.getColuna() == 0) || (pecaMovida.getCor() == Cor.BLACK && destino.getColuna() == 7)){
                promoted = (PecaXadrez) tabuleiro.peca(destino);
                promoted = replacePromotedPiece("Q");
            }
        }

        check = testCheck(corDoOponente(jogadorAtual)) ? true : false;
        if (testCheckMate(corDoOponente(jogadorAtual))){
            checkMate = true;
        }else {
            proximoTurno();
        }

        return (PecaXadrez) pecaComida;
    }

    public PecaXadrez replacePromotedPiece(String type){
        if (promoted == null){
            throw new IllegalStateException("Nao existe peca para ser promovida");
        }
        if (!type.equals("T") && !type.equals("C") && !type.equals("Q")&& !type.equals("B")){
            throw new InvalidParameterException("Parametro invalido");
        }
        Posicao pos = promoted.getPosicaoNoXarez().ConvertToPosicao();
        Peca p = tabuleiro.RomeverPeca(pos);
        pecasNoTabuleiro.remove(p);

        PecaXadrez novaPeca = novaPeca(type,promoted.getCor());
        tabuleiro.ColocarPeca(novaPeca,pos);
        pecasNoTabuleiro.add(novaPeca);
        return novaPeca;
    }

    private PecaXadrez novaPeca (String type, Cor cor){
        if (type.equals("T")) return new Torre(tabuleiro,cor);
        if (type.equals("C")) return new Cavalo(tabuleiro,cor);
        if (type.equals("Q")) return new Rainha(tabuleiro,cor);
        return new Bispo(tabuleiro,cor);
    }




    private Peca fazerJogada(Posicao inicio, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.RomeverPeca(inicio);
        p.increasewMoveCount();
        Peca pecaComida = tabuleiro.RomeverPeca(destino);
        tabuleiro.ColocarPeca(p, destino);
        if (pecaComida != null) {
            pecasNoTabuleiro.remove(pecaComida);
            pecasCapturadas.add(pecaComida);
        }

        // fazendo a jogada especial roque
        if (p instanceof Rei && destino.getColuna() == inicio.getColuna()+2){
            Posicao incialT = new Posicao(inicio.getLinha(),inicio.getColuna()+3);
            Posicao destinoT = new Posicao(inicio.getLinha(),inicio.getColuna()+1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.RomeverPeca(incialT);
            tabuleiro.ColocarPeca(torre,destinoT);
            torre.increasewMoveCount();

        }
        // fazendo a jogada especial roque grande
        if (p instanceof Rei && destino.getColuna() == inicio.getColuna()-2){
            Posicao incialT = new Posicao(inicio.getLinha(),inicio.getColuna()-4);
            Posicao destinoT = new Posicao(inicio.getLinha(),inicio.getColuna()-1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.RomeverPeca(incialT);
            tabuleiro.ColocarPeca(torre,destinoT);
            torre.increasewMoveCount();

        }

        return pecaComida;

    }

    private void desfazerJogada(Posicao incial, Posicao destino, Peca pecaCapturada) {
        PecaXadrez p = (PecaXadrez) tabuleiro.RomeverPeca(destino);
        p.decreasewMoveCount();
        tabuleiro.ColocarPeca(p, incial);

        if (pecaCapturada != null) {
            tabuleiro.ColocarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }

        // fazendo a jogada especial roque
        if (p instanceof Rei && destino.getColuna() == incial.getColuna()+2){
            Posicao incialT = new Posicao(incial.getLinha(),incial.getColuna()+3);
            Posicao destinoT = new Posicao(incial.getLinha(),incial.getColuna()+1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.RomeverPeca(destinoT);
            tabuleiro.ColocarPeca(torre,incialT);
            torre.decreasewMoveCount();

        }
        // fazendo a jogada especial roque grande
        if (p instanceof Rei && destino.getColuna() == incial.getColuna()-2){
            Posicao incialT = new Posicao(incial.getLinha(),incial.getColuna()-4);
            Posicao destinoT = new Posicao(incial.getLinha(),incial.getColuna()-1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.RomeverPeca(destinoT);
            tabuleiro.ColocarPeca(torre,incialT);
            torre.decreasewMoveCount();

        }
    }

    private void validarPosicao(Posicao incio) {
        if (!tabuleiro.existePeca(incio)) {
            throw new XadrezException("Nao existe peca na posicao");
        }
        if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(incio)).getCor()) {
            throw new XadrezException("Essa peca nao e sua ");
        }
        if (!tabuleiro.peca(incio).existeAlgumMovimentoPossivel()) {
            throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
        }
    }

    private void validarPosicaoDestino(Posicao inicio, Posicao destino) {
        if (!tabuleiro.peca(inicio).movimentoPossivel(destino)) {
            throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino");
        }
    }

    private void ColocarNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez) {
        tabuleiro.ColocarPeca(pecaXadrez, new PosicaoNoXadrez(coluna, linha).ConvertToPosicao());
        pecasNoTabuleiro.add(pecaXadrez);
    }

    private Cor corDoOponente(Cor cor) {
        return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
    }

    private PecaXadrez Rei(Cor cor) {
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Nao existe o rei da cor " + cor + " no tabuleiro");
    }

    private boolean testCheck(Cor cor) {
        Posicao posicaoDoRei = Rei(cor).getPosicaoNoXarez().ConvertToPosicao();
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == corDoOponente(cor)).collect(Collectors.toList());
        for (Peca p : list) {
            boolean[][] mat = p.movimentosPossiveis();
            if (mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Cor cor) {
        if (!testCheck(cor)) {
            return false;
        }
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
            boolean[][] mat = p.movimentosPossiveis();
            for (int i = 0; i < tabuleiro.getLinha(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (mat[i][j]) {
                        Posicao origem = ((PecaXadrez) p).getPosicaoNoXarez().ConvertToPosicao();
                        Posicao destino = new Posicao(i, j);
                        Peca capturada = fazerJogada(origem, destino);
                        boolean testecheck = testCheck(cor);
                        desfazerJogada(origem, destino, capturada);
                        if (!testecheck) {
                            return false;
                        }
                    }

                }
            }

        }
        return true;
    }

    public void proximoTurno() {
        Turno++;
        jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
    }

    private void SetupInicial() {
        ColocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.WHITE,this));
        ColocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.WHITE));


        ColocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.BLACK,this));
        ColocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.BLACK));


    }
}
