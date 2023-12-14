public class King extends ChessPiece{


    public King(String type, String color){
        super(type,color);
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board) {

        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        //if verticle
        if (rowDiff > 1 || colDiff > 1){
            System.out.println("King can move that far");
            return false;
        }
        // Check if the move is diagonal (rowDiff should equal colDiff)
        if (rowDiff != colDiff && (startCol != endCol && startRow != endRow)) {
            return false; // Not a diagonal move or verticle move
        }
        //VERTICLE MOVE
        if (startCol == endCol){
            int direction = (startRow < endRow) ? 1 : -1; // Determines direction based on the target column
            if (board[endRow][startCol] != null) {
                return !board[endRow][startCol].getColor().equals("White"); // There is a piece blocking the path
            }
            return true;
        }
        //HORIZONTAL MOVE
        if (startRow == endRow){
            int direction = (startCol < endCol) ? 1 : -1; // Determines direction based on the target column
            if (board[startRow][endCol] != null) {
                return !board[startRow][endCol].getColor().equals("White"); // There is a piece blocking the path
            }
        }



        //DIAGONAL MOVE
        int rowDirection = (endRow > startRow) ? 1 : -1;
        int colDirection = (endCol > startCol) ? 1 : -1;
        int intermediateRow = startRow * rowDirection;
        int intermediateCol = startCol * colDirection;

        if (board[startRow+rowDirection][startCol+colDirection] == null){
            return true;
        }
        else {
            if (board[startRow+rowDirection][startCol+colDirection].getColor().equals("White")){
                return false;
            }
            else {
                return true;
            }
        }


        //return false;

    }

}