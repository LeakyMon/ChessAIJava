import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessAI {
    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;

    //public void makeMove(ChessBoard chessBoard) {

    //}

    private List<Move> findAllLegalMoves(ChessBoard chessBoard) {
        ArrayList<Move> allBlackMoves = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ChessPiece piece = chessBoard.getPiece(i, j);
                if (piece != null && piece.getColor().equals("Black")) {
                    addLegalMovesForPiece(piece, i, j, allBlackMoves, chessBoard);
                }
            }
        }

        return allBlackMoves;
    }
    private void addLegalMovesForPiece(ChessPiece piece, int row, int col, List<Move> allBlackMoves, ChessBoard chessBoard) {
        // Example for a Pawn
        if (piece instanceof Pawn) {
            // Consider moving forward one and two squares, and capturing moves
            for (int newRow = row - 1; newRow <= row + 2; newRow++) {
                for (int newCol = col - 1; newCol <= col + 1; newCol++) {
                    if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
                        if (piece.isValidMove(row, col, newRow, newCol, chessBoard.getBoard())) {
                            allBlackMoves.add(new Move(piece, row, col, newRow, newCol));
                        }
                    }
                }
            }
        }
        if (piece instanceof Rook) {
            // Loop through all squares in the same row and column
            for (int i = 0; i < SIZE; i++) {
                // Check column moves
                if (i != col && piece.isValidMove(row, col, row, i, chessBoard.getBoard())) {
                    allBlackMoves.add(new Move(piece, row, col, row, i));
                }
                // Check row moves
                if (i != row && piece.isValidMove(row, col, i, col, chessBoard.getBoard())) {
                    allBlackMoves.add(new Move(piece, row, col, i, col));
                }
            }
        }
        if (piece instanceof Knight){

        }

        // Add similar logic for other types of pieces (Rooks, Knights, etc.)
    }



    private Move selectBestMove(List<Move> moves) {
        // Logic to evaluate and select the best move
        return moves.get(new Random().nextInt(moves.size())); // For random selection
    }

    private void executeMove(Move move, ChessBoard chessBoard) {
        // Logic to execute the chosen move on the board
    }

    public void makeMove(ChessBoard chessBoard) {

        List<Move> possibleMoves = findAllLegalMoves(chessBoard);
        Move chosenMove = selectBestMove(possibleMoves);
        executeMove(chosenMove, chessBoard);

        // Basic logic to move the first black pawn found
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ChessPiece piece = chessBoard.getPiece(i, j);
                if (piece != null && piece.getColor().equals("Black") && piece.getType(piece).equals("Pawn")) {
                    // Assuming the pawn moves one square forward
                    int newRow = i + 1;
                    int newCol = j;
                    if (newRow < SIZE && piece.isValidMove(i, j, newRow, newCol, chessBoard.getBoard())) {
                        chessBoard.movePiece(i, j, newRow, newCol);
                        return; // Exit after making one move
                    }
                }
            }
        }
    }
    public void debugPrintAllLegalMoves(ChessBoard chessBoard) {
        List<Move> allLegalMoves = findAllLegalMoves(chessBoard);
        System.out.println("Legal moves for Black:");
        for (Move move : allLegalMoves) {
            System.out.println(move);
        }
    }
}


