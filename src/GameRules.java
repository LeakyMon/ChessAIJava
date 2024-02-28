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
            if (move.getNewX() == kingPosition.getCol() && move.getNewY() == kingPosition.getRow()) {
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

                            //Now the king is moved, so we have to check if it can be reached
                            Position pos = chessBoard.getKingPosition("White");
                            for (Move blackMove : allBlackMoves) {
                                if (blackMove.getNewY() == pos.getRow() && blackMove.getNewX() == pos.getCol()) {
                                    // If any black move can capture the king, the move doesn't resolve the check
                                    System.out.println("Diffent piece can still reach the king");
                                    isSafe = false;
                                    break;
                                }
                            }
                        }
                        else {
                        }
                        chessBoard.undoSimulatedMove(i, j, move.getNewX(), move.getNewY(), movingPiece, targetPiece);
                        if (isSafe) {
                                // If we found a move that keeps the king safe from all of black's responses, it's not a checkmate
                                return false;
                            }
                        }
                    }
                }
            }


        return true;
    }

}


/*

 if (chessPiece.isMoveValidWithoutChangingState(r, c, move.getNewY(), move.getNewX(), chessBoard.getBoard())) {
                                //IF THE OPPONENT CAN STILL REACH THE KING
                                System.out.println("Opponent can still reach king");

                                if (chessPiece.isMoveValidWithoutChangingState(move.getNewY(),move.getNewX(),r,c,chessBoard.getBoard())){

                                }
                                else {
                                    isSafe = false;
                                    break;
                                }



                            } else {


                            }
 */