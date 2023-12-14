import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessAI {
    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;
    public void makeMove(ChessBoard chessBoard) {
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
}


