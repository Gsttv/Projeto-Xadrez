package app;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class UI {

    public static void printTabuleiro(PecaXadrez[][] pecaXadrez) { // Metodo para mostrar o tabuleiro para o usuario
        for (int i = 0; i < pecaXadrez.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecaXadrez.length; j++) {
                printPeca(pecaXadrez[i][j]);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");

    }
    public static void printPeca(PecaXadrez pecaXadrez) {  // Metodo para mostrar cada peca
        if (pecaXadrez == null) {
            System.out.print("- ");
        } else {
            System.out.print(pecaXadrez);
        }
    }
}
