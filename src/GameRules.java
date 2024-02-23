import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameRules {

    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;



    public static boolean isKingInCheck(ChessBoard chessBoard, String kingColor, int r, int c) {
        System.out.println("is King in Check " + kingColor);
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

    public static boolean isKingInCheckmate(ChessPiece chessPiece,ChessBoard chessBoard, int r, int c, List<Move> allBlackMoves) {
        ArrayList<Move> allWhiteMoves = new ArrayList<>();
        ArrayList<Move> allAttackerMoves = new ArrayList<>();
        ChessAI chessAI = new ChessAI();
        Position position = chessBoard.getKingPosition("White");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ChessPiece piece = chessBoard.getPiece(i, j);
                if (piece != null && piece.getColor().equals("White")) {

                    ArrayList<Move> potentialMoves = new ArrayList<>();
                    chessAI.addLegalMovesForPiece(piece, i, j, potentialMoves, chessBoard);
                    boolean isSafe = true;
                    for (Move move : potentialMoves) {
                        // Simulate the move
                        ChessPiece targetPiece = chessBoard.getPiece(move.getNewX(), move.getNewY());
                        ChessPiece movingPiece = chessBoard.getPiece(i, j); // Save the moving piece

                        if (movingPiece.isMoveValidWithoutChangingState(i, j, move.getNewX(), move.getNewY(), chessBoard.getBoard())) {
                            // If the move is valid, simulate it to see if it resolves the check
                            chessBoard.simulateMove(i, j, move.getNewX(), move.getNewY());
                            if (chessPiece.isMoveValidWithoutChangingState(r, c, move.getNewX(), move.getNewX(), chessBoard.getBoard())) {
                                //IF THE OPPONENT CAN STILL REACH THE KING

                                isSafe = false;

                            } else {

                                Position pos = chessBoard.getKingPosition("White");
                                for (Move blackMove : allBlackMoves){
                                    if (blackMove.getNewX() == pos.getRow() && blackMove.getNewY() == pos.getCol()) {
                                        // If any black move can capture the king, the move doesn't resolve the check
                                        isSafe = false;
                                        break;
                                    }
                                }
                            }
                            chessBoard.undoSimulatedMove(i, j, move.getNewX(), move.getNewY(), movingPiece, targetPiece);
                            if (isSafe) {
                                // If we found a move that keeps the king safe from all of black's responses, it's not a checkmate
                                return false;
                            }


                        }
                        else {
                            isSafe = false;
                        }
                    }
                }
            }
        }



        System.out.println("GAME OVER CHECKMATE");
        return true;
    }

}
