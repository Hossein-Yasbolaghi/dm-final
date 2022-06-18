package ir.ac.kntu;

/**
 * @author Hossein Yasbolaghi
 * <p>
 * A class that represents a board with specific number of rows, columns and Squares(elements)
 * </p>
 */
public class Board {

    private int rows;

    private int columns;

    private Square[][] elements;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Square[][] getElements() {
        return elements;
    }

    public void setElements(Square[][] elements) {
        this.elements = elements;
    }
}
