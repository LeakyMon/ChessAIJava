public class Rook extends ChessPiece{
    public Rook(String type, String color) {
        super(type, color);
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {return false;}

}

