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







}
