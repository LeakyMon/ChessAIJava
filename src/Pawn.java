public class Pawn extends ChessPiece {


    public Pawn(String type, String color) {
        super(type, color, 1);
    }

    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {
        System.out.println("in pawn");

        // Basic movement: Pawns move forward one square, but on their first move, they can move two squares
        // Capturing: Pawns capture diagonally
        ChessPiece pawn = board[startRow][startCol];
        //System.out.println(pawn.getHasMoved(pawn));
        int direction = this.color.equals("White") ? -1 : 1; // Determines direction based on color
        // Check for normal move
        if (startCol == endCol) {
            if (!hasMoved && (endRow - startRow) == 2 * direction && board[endRow][endCol] == null) {
                board[startRow][startCol].setHasMoved(true);
                return true; // Double step on first move
            } else if ((endRow - startRow) == direction && board[endRow][endCol] == null) {
                board[startRow][startCol].setHasMoved(true);
                return true; // Single step forward
            }
        }

        // Check for capture move
        if (Math.abs(startCol - endCol) == 1 && (endRow - startRow) == direction) {
            System.out.println(this.color);
            System.out.println();
            if (board[endRow][endCol] != null && !board[endRow][endCol].getColor().equals(this.color)) {
                System.out.println("Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                return true; // Capturing diagonally
            }
        }

        //LOGIC FOR IF PAWN MAKES IT TO OTHER SIDE OF BOARD



        System.out.println("Conditions not met");

        return false; // If none of the above conditions are met, it's an invalid move


        //return false;
    }
}
