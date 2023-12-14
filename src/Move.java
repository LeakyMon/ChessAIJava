public class Move {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;

    // ... existing attributes like startX, startY, etc.

    private ChessPiece piece; // Add a reference to the chess piece

    public Move(ChessPiece piece, int startX, int startY, int endX, int endY) {
        // ... initialization
        this.startX = startX;
        this.startY= startY;
        this.endX = endX;
        this.endY = endY;
        this.piece = piece;
    }

    @Override
    public String toString() {
        String startSquare = toBoardCoordinate(startX, startY);
        String endSquare = toBoardCoordinate(endX, endY);
        return piece.getType(piece) + " from " + startSquare + " to " + endSquare;
    }

    private String toBoardCoordinate(int row, int col) {
        char colLetter = (char) ('A' + col);
        int rowNumber = SIZE - row; // Assuming your board rows start from 0 at the bottom
        return "" + colLetter + rowNumber;
    }

    // ... other methods and constructors



}
