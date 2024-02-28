public class Move {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;

    // ... existing attributes like startX, startY, etc.

    private ChessPiece piece; // Add a reference to the chess piece

    public Move(ChessPiece piece, int startY, int startX, int endY, int endX) {
        // ... initialization
        this.startY= startY;
        this.startX = startX;
        this.endY = endY;
        this.endX = endX;

        this.piece = piece;
    }
    public int getInitX(){
        return this.startX;
    }
    public int getInitY(){
        return this.startY;
    }
    public int getNewX(){
        return this.endX;
    }
    public int getNewY(){
        return this.endY;
    }

    @Override
    public String toString() {
        String startSquare = toBoardCoordinate(startX, startY);
        String endSquare = toBoardCoordinate(endX, endY);
        return piece.getType(piece) + " from " + startSquare + " to " + endSquare;
    }

    public String toBoardCoordinate(int row, int col) {
        char colLetter = (char) ('A' + col);
        int rowNumber = SIZE - row; // Assuming your board rows start from 0 at the bottom
        return "" + colLetter + rowNumber;
    }

    public String toThreatState(Move move){
        return toBoardCoordinate(move.endY, move.endX);
    }

    public boolean capturesOpponentPiece(int x, int y, ChessBoard chessBoard) {


        if (chessBoard.getPiece(x,y) != null){
            return true;
        }

        return false;


    }

    // ... other methods and constructors



}
