package app;

import jogo.Tabuleiro;
import xadrez.PartidaXadrez;

public class Main {
    public static void main(String[] args) {

        PartidaXadrez partidaXadrez = new PartidaXadrez();
        UI.printTabuleiro(partidaXadrez.getPecas());
    }
}