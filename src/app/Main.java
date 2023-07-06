package app;

import jogo.Tabuleiro;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoNoXadrez;
import xadrez.XadrezException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc =  new Scanner(System.in);

        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true){
            try {
                UI.limparTela();
                UI.printPartida(partidaXadrez);
                System.out.println("");
                System.out.println("Posicao de origem: ");
                PosicaoNoXadrez incial = UI.lerpPosicao(sc);

                boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(incial);
                UI.limparTela();
                UI.printTabuleiro(partidaXadrez.getPecas(),movimentosPossiveis);
                System.out.println("");
                System.out.println("Posicao de destino: ");
                PosicaoNoXadrez destino = UI.lerpPosicao(sc);

                PecaXadrez pecaComida = partidaXadrez.moverPecas(incial, destino);
            }catch (XadrezException x){
                System.out.println(x.getMessage());
                sc.nextLine();

            }catch (InputMismatchException i){
                System.out.println(i.getMessage());
                sc.nextLine();

            }
        }

    }
}