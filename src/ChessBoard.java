

public class ChessBoard {
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
    }

    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece pieceToMove = board[startRow][startCol]; // Get the piece to move
        board[startRow][startCol] = null; // Remove the piece from the starting position
        board[endRow][endCol] = pieceToMove; // Place the piece at the target position
    }

    public ChessPiece getPiece(int row, int col) {
        return board[row][col];
    }
    public ChessPiece[][] getBoard(){
        return board;
    }





    // Additional methods like movePiece, isMoveValid, etc.
}
