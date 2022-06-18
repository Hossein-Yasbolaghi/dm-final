package ir.ac.kntu;

/**
 * @author Hossein Yasbolaghi
 * <p>
 * A class that represents a Square with specific row index and, column index and content in A Board
 * </p>
 */
public class Square {

    private int rowIndex;

    private int columnIndex;

    private String content;

    public Square(int rowIndex, int columnIndex, String content) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getManhattanDistance(Square other) {
        return Math.abs(this.rowIndex - other.rowIndex) + Math.abs(this.columnIndex - other.columnIndex);
    }
}
