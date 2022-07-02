package ir.ac.kntu;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Hossein Yasbolaghi
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        int turn = 0;

        readDimensions(scanner, board);

        try (scanner) {
            readElements(scanner, board);
        } catch (InputMismatchException ex) {
            System.out.println("Wrong input entered!");
        }

        while (pointExistence(board)) {
            starManhattanSquares(board);
            turn++;
            printTheBoard(board);
        }

        System.out.println(turn);
    }

    private static void printTheBoard(Board board) {
        System.out.println();
        for (Square[] squares : board.getElements()) {
            for (Square square : squares) {
                System.out.print(square.getContent());
            }
            System.out.println();
        }
        System.out.println("---");
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
            scanner.nextLine();
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
     * Check the existence of a point
     *
     * @param board A board that we search on it
     * @return true if there is a point in board, otherwise returns false
     */
    public static boolean pointExistence(Board board) {
        for (Square[] elements : board.getElements()) {
            for (Square square : elements) {
                if (square.getContent().equals(".")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void starManhattanSquares(Board board) {
        Square[][] finalSquare = new Square[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (board.getElements()[i][j].getContent().equals(".") && checkManhattanSquares(board.getElements(), i, j)) {
                    finalSquare[i][j] = new Square(i, j, "*");
                } else {
                    finalSquare[i][j] = board.getElements()[i][j];
                }
            }
        }

        board.setElements(finalSquare);
    }

    /**
     * Checks all four manhattan squares of specified square with i and j index in the elements array
     * @param elements An 2D array that function search's on it
     * @param i row index of specified square in elements
     * @param j column index of specified square in elements
     * @return true if there is a manhattan square in the elements with content *(star).
     */
    public static boolean checkManhattanSquares(Square[][] elements, int i, int j) {
        if ((i - 1) >= 0 && elements[i - 1][j].getContent().equals("*")) {
            return true;
        }
        if ((i + 1) < elements.length && elements[i + 1][j].getContent().equals("*")) {
            return true;
        }
        if ((j - 1) < elements[0].length && elements[i][j - 1].getContent().equals("*")) {
            return true;
        }

        return (j + 1) < elements[0].length && elements[i][j + 1].getContent().equals("*");
    }
}
