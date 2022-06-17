package ir.ac.kntu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Square[][] board;
        int rows, columns;

        try {
            rows = scanner.nextInt();
            columns = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Wrong input entered!");
            scanner.close();
            return;
        }

        board = new Square[rows][columns];

        try (scanner) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    String ch = scanner.next();
                    if (ch.equals(".") || ch.equals("#") || ch.equals("*")) {
                        board[i][j] = new Square(i, j, ch);
                    } else {
                        throw new InputMismatchException();
                    }
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Wrong input entered!");
        }

        int turns = 0;
        ArrayList<Square> squaresHaveToFeelInThisTurn;
        while (true) {
            squaresHaveToFeelInThisTurn = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (board[i][j].getContent().equals("*")) {
                        for (Square square : getManhattanSquares(board, board[i][j])) {
                            if (square.getContent().equals(".")) {
                                squaresHaveToFeelInThisTurn.add(square);
                            }
                        }
                    }
                }
            }

            if (squaresHaveToFeelInThisTurn.size() == 0) {
                break;
            } else {
                turns++;
            }

            for (Square square : squaresHaveToFeelInThisTurn) {
                square.setContent("*");
            }
        }

        System.out.println(turns);
    }

    public static ArrayList<Square> getManhattanSquares(Square[][] board, Square square) {
        ArrayList<Square> manhattanSquares = new ArrayList<>();
        for (Square[] squares : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (square.getManhattanDistance(squares[j]) == 1) {
                    manhattanSquares.add(squares[j]);
                }
            }
        }

        return manhattanSquares;
    }

}
