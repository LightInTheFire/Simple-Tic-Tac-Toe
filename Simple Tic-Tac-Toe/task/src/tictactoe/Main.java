package tictactoe;

import java.util.Scanner;

public class Main {

    private static char[][] gamePole = new char[5][9];
    private static String user;
    private static String winner;
    private static int turn = 1;
    private static int counter = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeGamePole();
        printGamePole();
        while (!isThereAWinner() ) {
            if (counter == 9) {
                winner = "Draw";
                break;
            }
            playerMove();
            counter++;
            printGamePole();
        }
        System.out.println(winner);
    }

    private static boolean isThereAWinner() {
       boolean value = false;

        for (int i = 1; i < 4; i++) {
            if (gamePole[i][2] == gamePole[i][4] && gamePole[i][4] == gamePole[i][6] && gamePole[i][2] != ' ') {
                winner = gamePole[i][2] + " wins";
                return true;
            }
        }
        for (int j = 2; j < 7; j += 2) {
            if (gamePole[1][j] == gamePole[2][j] && gamePole[2][j] == gamePole[3][j] && gamePole[1][j] != ' ') {
                winner = gamePole[1][j] + " wins";
                return true;
            }
        }
        if (gamePole[1][2] == gamePole[2][4] && gamePole[2][4] == gamePole[3][6] && gamePole[1][2] != ' ') {
            winner = gamePole[1][2] + " wins";
            return true;
        } else if (gamePole[1][6] == gamePole[2][4] && gamePole[2][4] == gamePole[3][2] && gamePole[1][6] != ' ') {
            winner = gamePole[1][6] + " wins";
            return true;
        }

        return value;
    }

    private static void playerMove() {
        boolean flag = true;
        do {
            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            } else {
          int xCoord = scanner.nextInt();
          int yCoord = scanner.nextInt();
                if (xCoord < 1 || xCoord > 3 || yCoord < 1 || yCoord > 3) {
                     System.out.println("Coordinates should be from 1 to 3!");
                } else if (gamePole[xCoord][convert(yCoord)] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else if (turn == 1) {
                    gamePole[xCoord][convert(yCoord)] = 'X';
                    flag = false;
                    turn = 2;
                } else if (turn == 2) {
                    gamePole[xCoord][convert(yCoord)] = 'O';
                    flag = false;
                    turn = 1;
                }
            }
        } while (flag);
    }

    private static int convert(int y){
        int correctCoords = 0;
        if (y == 1) {
            correctCoords = 2;
        } else if (y == 2) {
            correctCoords = 4;
        } else if (y == 3) {
            correctCoords = 6;
        }
        return correctCoords;
    }

    private static boolean isInsertCorrect() {
        char[] chars = user.toCharArray();
        int countX = 0;
        int countO = 0;
        for (char aChar : chars) {
            if (aChar == 'X') {
                countX++;
            } else if (aChar == 'O') {
                countO++;
            }
        }
        return Math.abs(countO - countX) < 2;
    }

    private static void printGamePole() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (j < 8) {
                    System.out.print(gamePole[i][j]);
                } else System.out.println(gamePole[i][j]);
            }
        }
    }

    private static char[] userInput() {
        String str = scanner.next();
        user = str;
        char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }
        return chars;
    }

    private static void modifyGamePole(char[] chars) {
        int k = 0;
        for (int i = 1; i < 4; i++) {;
            for (int j = 2; j < 7; j += 2) {
                gamePole[i][j] = chars[k];
                k++;
            }
        }

    }

    private static void initializeGamePole() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                gamePole[i][j] = ' ';
            }
        }
        for (int i = 0; i < 9; i++) {
            gamePole[0][i] = '-';
            gamePole[4][i] = '-';
        }
        for (int i = 1; i < 4; i++) {
            gamePole[i][0] = '|';
            gamePole[i][8] = '|';
        }
    }
}
