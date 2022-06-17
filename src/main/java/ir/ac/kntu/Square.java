package ir.ac.kntu;

public class Square {

    private int rowIndex;

    private int columnIndex;

    private String content;

    public Square(int rowIndex, int columnIndex, String content) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.content = content;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
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
