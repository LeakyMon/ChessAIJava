public class Rook extends ChessPiece{
    public Rook(String type, String color) {
        super(type, color);
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
            System.out.println(tempInt);

            for (int i = startRow + direction; i != endRow + direction; i+= direction){
                if (board[i][startCol] != null) {
                    return !board[i][startCol].getColor(board[i][startCol]).equals("White"); // There is a piece blocking the path
                }
            }
            if (board[endRow][endCol] != null && !board[endRow][endCol].getColor(board[endRow][endCol]).equals(this.color)) {
                System.out.println("Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                return true; // Capturing vertically
            }
            return true;
        }
        if (startRow == endRow) {
            int direction = (startCol < endCol) ? 1 : -1; // Determines direction based on the target column
            for (int i = startCol + direction; i != endCol; i += direction) {
                if (board[startRow][i] != null) {
                    return !board[startRow][i].getColor(board[startRow][i]).equals("White"); // There is a piece blocking the path
                }
            }
            if (board[endRow][endCol] != null && !board[endRow][endCol].getColor(board[endRow][endCol]).equals(this.color)) {
                System.out.println("Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                return true; // Capturing diagonally
            }
            return true; // Horizontal move is valid
        }

        // Check for capture move

        System.out.println("Conditions not met");

        return false; // If none of the above conditions are met, it's an invalid move


        //return false;
    }

}

