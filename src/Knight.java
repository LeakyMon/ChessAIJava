public class Knight extends ChessPiece{


    public Knight(String type, String color) {
        super(type, color, 3);
    }


    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {


        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        // Knights move in an L-shape: 2 squares in one direction and 1 square in the perpendicular direction
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Check for capturing an opponent's piece
            if (board[endRow][endCol] != null && !board[endRow][endCol].getColor().equals(this.color)) {
                System.out.println("Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                return true;
            }
            else if (board[endRow][endCol] != null && board[endRow][endCol].getColor().equals(this.color)){
                System.out.println("Cannot capture your own pieces");
                return false;
            }
            return true; // Valid knight move
        }

        return false; // Invalid move for a knight






    }

}
