import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameRules {

    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;

    public static boolean isKingInCheck(ChessBoard chessBoard, String kingColor, ChessAI chessAI) {

        if (Objects.equals(kingColor, "Black")){
            System.out.println("Is king in check " + kingColor + " ");
            int kingRow = -1, kingCol = -1;
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    ChessPiece piece = chessBoard.getPiece(row, col);
                    if (piece instanceof King && piece.getColor().equals("Black")) {
                        System.out.println("Instance of King " + row + " " + col);
                        kingRow = row;
                        kingCol = col;
                        break;
                    }
                }
            }
            List<Move> moves = chessAI.findAllLegalMovesForWhite(chessBoard);
            for (Move move : moves) {
                if (move.getNewX() == kingRow && move.getNewY() == kingCol) {
                    System.out.println("Black" + "'s King is in Check");
                    return true; // The opponent's king is in check
                }
            }
            System.out.println("Not in check");
            return false; // The opponent's king is not in check
        }
        else {
            String opponentColor = kingColor.equals("Black") ? "Black" : "White";
            System.out.println("Is king in check " + opponentColor + " ");
            // Find the opponent's king's position
            int kingRow = -1, kingCol = -1;
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    ChessPiece piece = chessBoard.getPiece(row, col);
                    if (piece instanceof King && piece.getColor().equals(opponentColor)) {
                        System.out.println("Instance of King " + row + " " + col);
                        kingRow = row;
                        kingCol = col;
                        break;
                    }
                }
            }

            // Check if the king is in check
            List<Move> moves = chessAI.findAllLegalMoves(chessBoard, opponentColor);
            for (Move move : moves) {
                if (move.getNewX() == kingRow && move.getNewY() == kingCol) {
                    System.out.println(opponentColor + "'s King is in Check");
                    return true; // The opponent's king is in check
                }
            }
            System.out.println("Not in check");
            return false; // The opponent's king is not in check
        }


    }

    public static boolean isKingInCheckmate(ChessBoard chessBoard, String kingColor, ChessAI chessAI) {
        // Implementation...

        return false;
    }

}
