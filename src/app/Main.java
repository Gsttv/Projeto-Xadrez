package app;

import jogo.Tabuleiro;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoNoXadrez;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc =  new Scanner(System.in);

        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true){
            UI.printTabuleiro(partidaXadrez.getPecas());
            System.out.println("");
            System.out.println("Posição de origem: ");
            PosicaoNoXadrez incial = UI.lerpPosicao(sc);

            System.out.println("Posição de destino: ");
            PosicaoNoXadrez destino = UI.lerpPosicao(sc);

            PecaXadrez pecaComida = partidaXadrez.moverPecas(incial,destino);
        }

    }
}