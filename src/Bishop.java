public class Bishop extends ChessPiece{
    public Bishop(String type, String color) {
        super(type,color);
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {return false;}
}
