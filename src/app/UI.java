package app;


import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoNoXadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static PosicaoNoXadrez lerpPosicao(Scanner sc){
        try {
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));
            return new PosicaoNoXadrez(coluna,linha);
        }catch (RuntimeException e ){
            throw new InputMismatchException("Erro na leitura dos valores, parametros valiodo são de a1 á h8");

        }
    }

    public static void printTabuleiro(PecaXadrez[][] pecaXadrez) { // Metodo para mostrar o tabuleiro para o usuario
        for (int i = 0; i < pecaXadrez.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecaXadrez.length; j++) {
                printPeca(pecaXadrez[i][j],false);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");

    }

    public static void printTabuleiro(PecaXadrez[][] pecaXadrez, boolean[][] movimentosPossiveis) { // Metodo para mostrar o tabuleiro para o usuario
        for (int i = 0; i < pecaXadrez.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecaXadrez.length; j++) {
                printPeca(pecaXadrez[i][j],movimentosPossiveis[i][j]);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");

    }

    public static void printPeca(PecaXadrez pecaXadrez, boolean background) {  // Metodo para mostrar cada peca
        if (background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (pecaXadrez == null) {
            System.out.print("-" + ANSI_RESET);
        }  else {
            if (pecaXadrez.getCor() == Cor.WHITE) {
                System.out.print(ANSI_WHITE + pecaXadrez + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + pecaXadrez + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    public static void printPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturadas){
        printTabuleiro(partidaXadrez.getPecas());
        System.out.println("");
        mostarPecasCapturadas(capturadas);
        System.out.println("Turno : " + partidaXadrez.getTurno());
        System.out.println("Jogador da vez: " + partidaXadrez.getJogadorAtual());
        if (partidaXadrez.isCheck()){
            System.out.println("CHECK!");
        }
    }

    public static void mostarPecasCapturadas(List<PecaXadrez> capturadas){
        List<PecaXadrez> brancas = capturadas.stream().filter(x -> x.getCor() == Cor.WHITE).collect(Collectors.toList());
        List<PecaXadrez> pretas = capturadas.stream().filter(x -> x.getCor() == Cor.BLACK).collect(Collectors.toList());
        System.out.println("Pecas Capturas: ");
        System.out.print("Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(brancas.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(pretas.toArray()));
        System.out.print(ANSI_RESET);

    }
}
