import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameRules {

    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;



    public static boolean isKingInCheck(ChessBoard chessBoard, String kingColor, int r, int c) {
        ChessAI chessAI = new ChessAI();
        Position kingPosition = chessBoard.getKingPosition(kingColor);
        // Assuming you have a method to get all opponent moves
        List<Move> opponentMoves = chessAI.findAllLegalMoves(chessBoard, kingColor.equals("White") ? "Black" : "White");
        for (Move move : opponentMoves) {
            if (move.getNewX() == kingPosition.getRow() && move.getNewY() == kingPosition.getCol()) {
                return true; // King is in check if any move can capture the king's position
            }
        }
        return false;
    }

    public static boolean isKingInCheckmate(ChessPiece chessPiece,ChessBoard chessBoard, int r, int c) {
        ArrayList<Move> allWhiteMoves = new ArrayList<>();

        ChessAI chessAI = new ChessAI();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ChessPiece piece = chessBoard.getPiece(i, j);
                if (piece != null && piece.getColor().equals("White")) {

                    ArrayList<Move> potentialMoves = new ArrayList<>();
                    chessAI.addLegalMovesForPiece(piece, i, j, potentialMoves, chessBoard);

                    for (Move move : potentialMoves) {
                        // Simulate the move
                        ChessPiece targetPiece = chessBoard.getPiece(move.getNewX(), move.getNewY());
                        ChessPiece movingPiece = chessBoard.getPiece(i, j); // Save the moving piece

                        // Check if the move resolves the check
                        if (!isKingInCheck(chessBoard, "White", r, c)) {
                            // If the move resolves the check, add it to the list of legal moves

                            //allWhiteMoves.add(movingPiece);
                            return false;
                        }

                        // Undo the move
                        chessBoard.setPiece(i, j, movingPiece); // Move the piece back
                        chessBoard.setPiece(move.getNewX(), move.getNewY(), targetPiece); // Res
                    }
                }
            }
        }


        System.out.println("GAME OVER CHECKMATE");
        return true;
    }

}
