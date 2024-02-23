public class Bishop extends ChessPiece{
    public Bishop(String type, String color) {
        super(type,color,3);
    }


    public boolean isMoveValidWithoutChangingState(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board){
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        // Ensure the move is strictly diagonal
        if (rowDiff != colDiff) {
            return false; // Not a diagonal move
        }

        // Determine the direction of movement
        int rowDirection = (endRow > startRow) ? 1 : -1;
        int colDirection = (endCol > startCol) ? 1 : -1;

        // Check for obstacles in the diagonal path
        for (int i = 1; i < rowDiff; i++) {
            int intermediateRow = startRow + i * rowDirection;
            int intermediateCol = startCol + i * colDirection;

            // Obstacle check
            if (board[intermediateRow][intermediateCol] != null) {
                return false; // Path is blocked by another piece
            }
        }

        // The destination square is either empty or occupied by an opponent's piece
        ChessPiece destinationPiece = board[endRow][endCol];
        if (destinationPiece != null) {
            if (!destinationPiece.getColor().equals(this.color)) {
                // Capturing an opponent's piece is a valid move
                return true;
            } else {
                // Cannot capture own piece
                return false;
            }
        }

        // If the destination square is empty, it's a valid move
        return true;
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {

        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        // Check if the move is diagonal (rowDiff should equal colDiff)
        if (rowDiff != colDiff) {
            return false; // Not a diagonal move
        }

        // Check for obstacles in the diagonal path
        int rowDirection = (endRow > startRow) ? 1 : -1;
        int colDirection = (endCol > startCol) ? 1 : -1;

        for (int i = 1; i < rowDiff; i++) {
            int intermediateRow = startRow + i * rowDirection;
            int intermediateCol = startCol + i * colDirection;

            if (board[intermediateRow][intermediateCol] != null) {
                return false; // There is an obstacle in the diagonal path
            }
        }

        // Check for capturing an opponent's piece
        if (board[endRow][endCol] != null && !board[endRow][endCol].getColor().equals(this.color)) {
            System.out.println("Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
            return true;
        }
        else if (board[endRow][endCol] != null && board[endRow][endCol].getColor().equals(this.color)){
            return false;
        }

        return true; // Valid diagonal move
        // Check for capture move

        //System.out.println("Conditions not met");

       // return false; // If none of the above conditions are met, it's an invalid move


        //return false;
    }
}
