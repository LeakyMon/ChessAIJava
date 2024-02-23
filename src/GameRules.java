import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameRules {

    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;



    public static boolean isKingInCheck(ChessBoard chessBoard, String kingColor, int r, int c) {
        System.out.println("Game Rules - isKingInCheck");

        // Determine opponent's color
        //String opponentColor = kingColor.equals("Black") ? "White" : "Black";
        System.out.println("Is king in check: " + kingColor);

        Position kingPosition = chessBoard.getKingPosition(kingColor);
        int kingRow = kingPosition.getRow();
        int kingCol = kingPosition.getCol();
        ChessPiece tempPiece = chessBoard.getPiece(r,c);
        System.out.println("Row: "+ r + " Col: " + c + "KR: " + kingRow + " KingCol " + kingCol);
        if (tempPiece.isValidMove(r,c,kingRow,kingCol,chessBoard.getBoard())){
            System.out.println("King in check");
            boolean temp = isKingInCheckmate(tempPiece,chessBoard, r, c);
            return true;
        }
        else {
            System.out.println("Not in check");
            return false; // The king is not in check
        }



    }

    public static boolean isKingInCheckmate(ChessPiece chessPiece,ChessBoard chessBoard, int r, int c) {
        ChessAI chessAI = new ChessAI();
        ArrayList<Move> allWhiteMoves = new ArrayList<>();
        chessAI.addLegalMovesForPiece(chessPiece, r, c, allWhiteMoves, chessBoard);


        return false;
    }

}
