package Games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    static String[] board;
    static String turn;

    public static void startTicTacToe(PrintWriter output, BufferedReader input, HashMap<String, String> gameState) {
        Scanner in = new Scanner(input);
        board = new String[9];
        turn = "X";
        String winner = null;

        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }

        output.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard(output);

        output.println("X will play first. Enter a slot number to place X in:");

        while (winner == null) {
            int numInput;

            try {
                numInput = in.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    output.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                output.println("Invalid input; re-enter slot number:");
                in.next();
                continue;
            }

            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;

                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }

                printBoard(output);
                winner = checkWinner();
            } else {
                output.println("Slot already taken; re-enter slot number:");
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            output.println("It's a draw! Thanks for playing.");
        } else {
            output.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
    }

    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }

        return null;
    }

    static void printBoard(PrintWriter output) {
        output.println("|---|---|---|");
        output.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        output.println("|-----------|");
        output.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        output.println("|-----------|");
        output.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        output.println("|---|---|---|");
    }
}
