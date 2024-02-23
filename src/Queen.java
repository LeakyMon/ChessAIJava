public class Queen extends ChessPiece {
    public Queen(String type, String color){
        super(type,color, 10);
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {

        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

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
        if (board[endRow][endCol] != null && !board[endRow][endCol].getColor().equals(this.getColor())) {
            System.out.println("Queen: Enemy " + board[endRow][endCol].getType(board[endRow][endCol]) + " captured");
            return true;
        }
        else if (board[endRow][endCol] != null && board[endRow][endCol].getColor().equals(this.color)){
            return false;
        }

        return true; // Valid move
    }

}