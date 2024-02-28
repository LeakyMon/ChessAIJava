import java.util.ArrayList;

public class ChessBoard {

    private final int SIZE = 8; // Standard ches
    private Position whiteKingPosition;
    private Position blackKingPosition;
    private ThreatState threatState = new ThreatState();

    private ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoard() {
        initializeBoard();
    }

    void initializeBoard() {
        //clear board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null; // Set each square to null
            }
        }


        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("Pawn", "Black");
            board[6][i] = new Pawn("Pawn", "White");
        }

        // Initialize Rooks
        board[0][0] = new Rook("Rook", "Black");
        board[0][7] = new Rook("Rook", "Black");
        board[7][0] = new Rook("Rook", "White");
        board[7][7] = new Rook("Rook", "White");

        // Initialize Knights
        board[0][1] = new Knight("Knight", "Black");
        board[0][6] = new Knight("Knight", "Black");
        board[7][1] = new Knight("Knight", "White");
        board[7][6] = new Knight("Knight", "White");

        // Initialize Bishops
        board[0][2] = new Bishop("Bishop", "Black");
        board[0][5] = new Bishop("Bishop", "Black");
        board[7][2] = new Bishop("Bishop", "White");
        board[7][5] = new Bishop("Bishop", "White");

        // Initialize Queens
        board[0][3] = new Queen("Queen", "Black");
        board[7][3] = new Queen("Queen", "White");

        // Initialize Kings
        board[0][4] = new King("King", "Black");
        board[7][4] = new King("King", "White");
        blackKingPosition = new Position(0, 4); // Store black king's position
        whiteKingPosition = new Position(7, 4); // Store white king's position
    }

    public void setPiece(int sR, int sC, ChessPiece piece){
        if (sR >= 0 && sR < SIZE && sC >= 0 && sC < SIZE) {
            board[sR][sC] = piece;
        } else {
            System.out.println("Position out of bounds");
        }
    }

    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece pieceToMove = board[startRow][startCol]; // Get the piece to move
        //Keep track of king positions
        if (pieceToMove.getType(pieceToMove).equals("King")){
            setKingPosition(pieceToMove.color, endRow,endCol);
        }
        board[startRow][startCol] = null; // Remove the piece from the starting position
        board[endRow][endCol] = pieceToMove; // Place the piece at the target position
    }

    public ChessPiece getPiece(int row, int col) {
        return board[row][col];
    }
    public ChessPiece[][] getBoard(){
        return board;
    }

    public void setKingPosition(String color, int row, int col) {
        System.out.println("setting king position " + color);
        if ("White".equals(color)) {
            whiteKingPosition = new Position(row, col);
        } else if ("Black".equals(color)) {
            blackKingPosition = new Position(row, col);
        }
        System.out.println(whiteKingPosition);
        System.out.println(blackKingPosition);
    }

    public Position getKingPosition(String color) {


        return "White".equals(color) ? whiteKingPosition : blackKingPosition;
    }

    public void updateThreatState(){

    }

    public String toBoardCoordinate(int row, int col) {
        char colLetter = (char) ('A' + col);
        int rowNumber = SIZE - row; // Assuming your board rows start from 0 at the bottom
        return "" + colLetter + rowNumber;
    }

    public ThreatState getThreatState() {
        return threatState;
    }

    /*
    public ThreatState checkThreatState(ArrayList<Move> legalMovesPiece, ChessBoard chessBoard){
        int Kingx = chessBoard.getKingPosition("White").getCol();
        int Kingy = chessBoard.getKingPosition("White").getRow();
        String kingPos = toBoardCoordinate(Kingy,Kingx);
        System.out.println(kingPos);
        System.out.println("King X: " + Kingx + " King Y: " + Kingy);
        for (Move potentialMove : legalMovesPiece) {
            System.out.println("Evaluating move " + potentialMove);
            //System.out.println(getThreatState().isUnderThreat(potentialMove.toThreatState(potentialMove)));
            if (getThreatState().isUnderThreat(potentialMove.toThreatState(potentialMove))){
                if (potentialMove.getNewY() == Kingy && potentialMove.getNewX() == Kingx) {
                    System.out.println("White King is in Check!");
                }
                System.out.println("Warning Piece in King Threat Zone!");
                getThreatState().setKingMoveable(potentialMove.toThreatState(potentialMove),false);
            }
        }

        return null;
    }

     */
    public void simulateMove(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece movingPiece = this.board[startRow][startCol];
        if (movingPiece.getType(movingPiece).equals("King")){
            setKingPosition(movingPiece.color, endRow,endCol);
        }
        this.board[startRow][startCol] = null; // Remove the piece from the starting square
        this.board[endRow][endCol] = movingPiece; // Place it on the destination square
    }

    // Method to undo a simulated move
    public void undoSimulatedMove(int startRow, int startCol, int endRow, int endCol, ChessPiece originalPiece, ChessPiece capturedPiece) {
        this.board[startRow][startCol] = originalPiece; // Restore the original piece to its starting square
        this.board[endRow][endCol] = capturedPiece; // Restore the captured piece, if any, to the destination square
    }
}
