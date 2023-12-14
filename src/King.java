public class King extends ChessPiece{


    public King(String type, String color){
        super(type,color, 10);
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {

        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        // Check if the move is more than one square in any direction
        if (rowDiff > 1 || colDiff > 1) {
            return false; // King cannot move more than one square away
        }

        // Ensure the destination square is within the board bounds
        if (endRow < 0 || endRow >= board.length || endCol < 0 || endCol >= board[0].length) {
            return false; // Destination is outside the board
        }

        // Check if the destination square is occupied
        ChessPiece destinationPiece = board[endRow][endCol];
        if (destinationPiece != null) {
            // If the destination square is occupied by a piece of the same color, it's not a valid move
            if (destinationPiece.getColor().equals(this.getColor())) {
                return false;
            }
        }

        // The move is valid (either moving to an empty square or capturing an opponent's piece)
        return true;


        //return false;

    }

}