public class Rook extends ChessPiece{
    public Rook(String type, String color) {
        super(type, color, 5);
    }

    public boolean isMoveValidWithoutChangingState(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board){

        boolean isVerticalMove = startCol == endCol;
        boolean isHorizontalMove = startRow == endRow;

        if (!isVerticalMove && !isHorizontalMove) {
            return false; // Move is neither vertical nor horizontal
        }

        // Determine the direction of the move and set up for iteration
        int rowDirection = Integer.compare(endRow, startRow); // 0 if no change, -1 for up, 1 for down
        int colDirection = Integer.compare(endCol, startCol); // 0 if no change, -1 for left, 1 for right

        // Check for obstacles along the path
        int currentRow = startRow + rowDirection;
        int currentCol = startCol + colDirection;
        while (currentRow != endRow || currentCol != endCol) {
            if (board[currentRow][currentCol] != null) {
                return false; // Path is blocked
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // At the destination square
        ChessPiece destinationPiece = board[endRow][endCol];
        if (destinationPiece != null) {
            ChessPiece movingPiece = board[startRow][startCol];
            if (destinationPiece.getColor().equals(movingPiece.getColor())) {
                return false; // Cannot capture your own piece
            } else {
                // Capturing an opponent's piece
                System.out.println("Rook: Enemy " + destinationPiece.getType(destinationPiece) + " captured");
                return true;
            }
        }

        return true; // The move is valid if it's clear or captures an opponent'


    }

    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {

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
            if (board[endRow][endCol] != null && !board[endRow][endCol].getColor().equals(rook.color)) {
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
                if (!board[endRow][endCol].getColor().equals(rook.color)) {
                    System.out.println("Rook: Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
                    return true;
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
        else if (board[endRow][endCol] != null && board[endRow][endCol].getColor().equals(rook.color)){
            return false;
        }

        // Check for capture move

        return true; // If none of the above conditions are met, it's an invalid move


        //return false;
    }

}

