public class Knight extends ChessPiece{


    public Knight(String type, String color) {
        super(type, color, 3);
    }

    public boolean isMoveValidWithoutChangingState(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board){

        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        // Get the current piece attempting the move
        ChessPiece currPiece = board[startRow][startCol];

        // Check if the move follows the knight's L-shaped pattern
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Destination square handling
            ChessPiece destinationPiece = board[endRow][endCol];
            if (destinationPiece == null) {
                // Empty destination square
                return true; // Valid move as knights can jump over other pieces
            } else if (!destinationPiece.getColor().equals(currPiece.getColor())) {
                // Destination square contains an opponent's piece
                System.out.println("Knight: Enemy " + destinationPiece.getType(destinationPiece) + " captured");
                return true; // Valid capture move
            }
            // Destination square contains a piece of the same color
            return false; // Cannot capture your own pieces
        }

        // If the move does not follow the L-shaped pattern, it's invalid for a knight
        return false;


    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {


        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        ChessPiece currPiece = board[startRow][startCol];
        //System.out.println(currPiece.color);
        // Knights move in an L-shape: 2 squares in one direction and 1 square in the perpendicular direction
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Check for capturing an opponent's piece
            //System.out.println(board[endRow][endCol].getColor());
            if (board[endRow][endCol] != null && !board[endRow][endCol].getColor().equals(currPiece.color)) {
                //System.out.println(board[endRow][endCol].getColor());
                System.out.println("Knight: Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                return true;
            }
            else if (board[endRow][endCol] != null && board[endRow][endCol].getColor().equals(currPiece.color)){
                //System.out.println(board[endRow][endCol].getColor());
                //System.out.println("Cannot capture your own pieces");
                return false;
            }
            return true; // Valid knight move
        }

        return false; // Invalid move for a knight






    }

}
