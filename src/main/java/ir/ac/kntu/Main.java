package ir.ac.kntu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Hossein Yasbolaghi
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        readDimensions(scanner, board);

        try (scanner) {
            readElements(scanner, board);
        } catch (InputMismatchException ex) {
            System.out.println("Wrong input entered!");
        }

        System.out.println(getTurns(board));
    }

    /**
     * Reads dimensions of board
     *
     * @param scanner Console input.
     * @param board   A Board that read dimensions will save on it.
     */
    public static void readDimensions(Scanner scanner, Board board) {
        try {
            board.setRows(scanner.nextInt());
            board.setColumns(scanner.nextInt());
        } catch (InputMismatchException ex) {
            System.out.println("Wrong input entered!");
            readDimensions(scanner, board);
        }
    }

    /**
     * Reads content of board's elements
     *
     * @param scanner Console input.
     * @param board   A Board that read elements will save on it.
     */
    private static void readElements(Scanner scanner, Board board) {
        Square[][] elements = new Square[board.getRows()][board.getColumns()];
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                String ch = scanner.next();
                if (isValidContent(ch)) {
                    elements[i][j] = new Square(i, j, ch);
                } else {
                    throw new InputMismatchException();
                }
            }
        }

        board.setElements(elements);
    }

    /**
     * Checks validation of a content
     *
     * @param str The validation of it's content will be checked.
     * @return true if the str has a valid content, otherwise returns false.
     */
    public static boolean isValidContent(String str) {
        return str.equals(".") || str.equals("#") || str.equals("*");
    }

    /**
     * Calculates the minimum number of turns to feel a specific Board and feel that Board
     *
     * @param board A Board that method works on it.
     * @return An Integer that represents the minimum number of turns to feel a specific Board.
     */
    private static int getTurns(Board board) {
        int turns = 0;
        ArrayList<Square> squaresCanFeelInThisTurn;
        while (true) {
            squaresCanFeelInThisTurn = getSquaresCanFeel(board);

            if (squaresCanFeelInThisTurn.size() == 0) {
                break;
            } else {
                turns++;
            }

            for (Square square : squaresCanFeelInThisTurn) {
                square.setContent("*");
            }
        }

        return turns;
    }

    /**
     * Search's a specific Board to find some squares of it
     *
     * @param board A Board that this method search on it for specific Squares.
     * @return An ArrayList of Squares that have .(dot) content and can feel in this turn.
     */
    private static ArrayList<Square> getSquaresCanFeel(Board board) {
        ArrayList<Square> squaresCanFeel = new ArrayList<>();
        for (Square[] squares : board.getElements()) {
            for (Square square : squares) {
                if (square.getContent().equals("*")) {
                    squaresCanFeel.addAll(getManhattanSquares(board.getElements(), square));
                }
            }
        }

        return squaresCanFeel;
    }

    /**
     * Search's a specific Board to find some squares of it
     *
     * @param board  A Board that this method search on it for specific Squares.
     * @param square A specific Square in the board.
     * @return An ArrayList of Squares that satisfy two conditions,
     * 1) The Manhattan distance of them with specific Square be 1,
     * 2) The content of Squares that satisfy first condition be .(dot).
     */
    public static ArrayList<Square> getManhattanSquares(Square[][] board, Square square) {
        ArrayList<Square> manhattanSquares = new ArrayList<>();
        for (Square[] squares : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (!squares[j].getContent().equals(".")) {
                    continue;
                }
                if (square.getManhattanDistance(squares[j]) == 1) {
                    manhattanSquares.add(squares[j]);
                }
            }
        }

        return manhattanSquares;
    }

}
