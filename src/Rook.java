public class Rook extends ChessPiece{
    public Rook(String type, String color) {
        super(type, color, 5);
    }

    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {
        // Basic movement: Pawns move forward one square, but on their first move, they can move two squares
        // Capturing: Pawns capture diagonally
        String directionMove = "";
        ChessPiece rook = board[startRow][startCol];


        // Check for normal move
        if (startCol == endCol) {
            int direction = (startRow < endRow) ? 1 : -1; // Determines direction based on the target column
            int tempInt = startRow + direction;
            //System.out.println(tempInt);

            for (int i = startRow + direction; i != endRow; i += direction) {
                if (board[i][startCol] != null) {
                    return false; // There is a piece blocking the path
                }
            }
            if (board[endRow][endCol] != null && !board[endRow][endCol].getColor().equals(this.color)) {
                System.out.println("Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                return true; // Capturing vertically
            }
            //return false;
        }
        if (startRow == endRow) {
            int direction = (startCol < endCol) ? 1 : -1; // Determines direction based on the target column
            for (int i = startCol + direction; i != endCol; i += direction) {
                if (board[startRow][i] != null) {
                    return false; // There is a piece blocking the path
                }
            }
            if (board[endRow][endCol] != null){
                if (!board[endRow][endCol].getColor().equals(this.color)) {
                    System.out.println("Rook: Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                    return true; // Capturing diagonally
                }
                else {
                    //System.out.println("cant eat  your own color");
                    return false;
                }
            }

            //return false; // Horizontal move is valid
        }
        else if (startRow != endRow && startCol != endCol){
            return false;
        }
        else if (board[endRow][endCol] != null && board[endRow][endCol].getColor().equals(this.color)){
            return false;
        }

        // Check for capture move

        return true; // If none of the above conditions are met, it's an invalid move


        //return false;
    }

}

