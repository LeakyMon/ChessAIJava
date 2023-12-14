public class Pawn extends ChessPiece {


    public Pawn(String type, String color) {
        super(type, color);
    }

    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {
        // Basic movement: Pawns move forward one square, but on their first move, they can move two squares
        // Capturing: Pawns capture diagonally
        ChessPiece pawn = board[startRow][startCol];
        int direction = this.color.equals("White") ? -1 : 1; // Determines direction based on color
        // Check for normal move
        if (startCol == endCol) {
            if (!hasMoved && (endRow - startRow) == 2 * direction && board[endRow][endCol] == null) {
                return true; // Double step on first move
            } else if ((endRow - startRow) == direction && board[endRow][endCol] == null) {
                return true; // Single step forward
            }
        }

        // Check for capture move
        if (Math.abs(startCol - endCol) == 1 && (endRow - startRow) == direction) {
            System.out.println(this.color);
            System.out.println();
            if (board[endRow][endCol] != null && !board[endRow][endCol].getColor(board[endRow][endCol]).equals(this.color)) {
                System.out.println("Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                return true; // Capturing diagonally
            }
      }
        System.out.println("Conditions not met");

        return false; // If none of the above conditions are met, it's an invalid move


        //return false;
    }
}
