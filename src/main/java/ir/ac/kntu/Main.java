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

        while (pointExistence(board)){
            starManhattanSquare(board);
            turn++;

//            Square[][] elements = board.getElements();
//            for (Square[] currents : elements){
//                for (Square current : currents){
//                    System.out.print(current.getContent());
//                }
//            }
//            System.out.println();
//            System.out.println("---");
        }

        System.out.println(turn);

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


    //check the existence of a point
    public static boolean pointExistence (Board board){
        for (Square[] elements : board.getElements()){
            for (Square current  : elements){
                if (current.getContent().equals(".")){
                    return true;
                }
            }
        }
        return false;
    }




    public static void starManhattanSquare (Board board){

        Square[][] elements1thCopy = board.getElements();
        Square[][] elements2endCopy = board.getElements();


        for(int i=0; i<elements1thCopy.length ; i++){
            for(int t=0 ; t<elements1thCopy[0].length ; t++){


                if(elements2endCopy[i][t].getContent().equals("*")){

                    if(i+1<elements1thCopy.length && !elements1thCopy[i+1][t].getContent().equals("#")){
                        elements1thCopy[i+1][t].setContent("*");
                    }
                    if(t+1<elements1thCopy[0].length && !elements1thCopy[i][t+1].getContent().equals("#")){
                        elements1thCopy[i][t+1].setContent("*");
                    }
                    if(0<=i-1 && !elements1thCopy[i-1][t].getContent().equals("#")){
                        elements1thCopy[i-1][t].setContent("*");
                    }
                    if(0<=t-1 && !elements1thCopy[i][t-1].getContent().equals("#")){
                        elements1thCopy[i][t-1].setContent("*");
                    }


                }
            }
        }

        board.setElements(elements1thCopy);

    }








}
