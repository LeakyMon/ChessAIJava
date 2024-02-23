public class Queen extends ChessPiece {
    public Queen(String type, String color){
        super(type,color, 10);
    }

    public boolean isMoveValidWithoutChangingState(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {
        // Assume this method is part of the Pawn class
        int rowDiff = endRow - startRow;
        int colDiff = endCol - startCol;

        // Queen moves either horizontally, vertically, or diagonally
        boolean isDiagonalMove = Math.abs(rowDiff) == Math.abs(colDiff);
        boolean isStraightMove = startRow == endRow || startCol == endCol;

        if (!isDiagonalMove && !isStraightMove) {
            return false; // Invalid move for a queen
        }

        // Determine step direction for iteration
        int rowStep = Integer.compare(rowDiff, 0);
        int colStep = Integer.compare(colDiff, 0);

        // Check each square along the path for obstacles
        int currentRow = startRow + rowStep;
        int currentCol = startCol + colStep;
        while (currentRow != endRow || currentCol != endCol) {
            if (board[currentRow][currentCol] != null) {
                return false; // Path is blocked
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        // Check the destination square
        ChessPiece destinationPiece = board[endRow][endCol];
        if (destinationPiece != null) {
            // If destination is occupied, it must be by an opponent's piece
            ChessPiece movingPiece = board[startRow][startCol];
            if (destinationPiece.getColor().equals(movingPiece.getColor())) {
                return false; // Cannot capture own piece
            }
        }

        return true; // The path is clear and the move is valid
    }
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {

        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        ChessPiece currPiece = board[startRow][startCol];
        // Check if the move is diagonal, vertical, or horizontal
        if (rowDiff != colDiff && startCol != endCol && startRow != endRow) {
            return false; // Not a diagonal, vertical, or horizontal move
        }

        int rowDirection = (endRow > startRow) ? 1 : (endRow < startRow) ? -1 : 0;
        int colDirection = (endCol > startCol) ? 1 : (endCol < startCol) ? -1 : 0;

        // Check for obstacles in the path
        for (int i = 1; i < Math.max(rowDiff, colDiff); i++) {
            int intermediateRow = startRow + i * rowDirection;
            int intermediateCol = startCol + i * colDirection;

            if (board[intermediateRow][intermediateCol] != null) {
                return false; // There is an obstacle in the path
            }
        }

        // Check for capturing an opponent's piece
        if (board[endRow][endCol] != null && !board[endRow][endCol].getColor().equals(currPiece.color)) {
            System.out.println("Queen: Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
            return true;
        }
        else if (board[endRow][endCol] != null && board[endRow][endCol].getColor().equals(currPiece.color)){
            return false;
        }

        return true; // Valid move
    }

}