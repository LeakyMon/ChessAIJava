import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Objects;

public class ChessAI {
    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;
    private boolean inCheck = false;

    public void setInCheck(boolean c){
        this.inCheck = c;
    }

    public List<Move> findAllLegalMoves(ChessBoard chessBoard, String color) {
            ArrayList<Move> allMoves = new ArrayList<>();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    ChessPiece piece = chessBoard.getPiece(i, j);
                    if (piece != null && piece.getColor().equals(color)) {
                        addLegalMovesForPiece(piece, i, j, allMoves, chessBoard);
                    }
                }
            }
            return allMoves;
        }



    public List<Move> findAllLegalMovesForWhite(ChessBoard chessBoard) {
        ArrayList<Move> allWhiteMoves = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ChessPiece piece = chessBoard.getPiece(i, j);
                if (piece != null && piece.getColor().equals("White")) {

                    ArrayList<Move> potentialMoves = new ArrayList<>();
                    addLegalMovesForPiece(piece, i, j, potentialMoves, chessBoard);


                }
            }
        }
        return allWhiteMoves;
    }



    public void addLegalMovesForPiece(ChessPiece piece, int row, int col, List<Move> allMoves, ChessBoard chessBoard) {
        // Example for a Pawn
        if (piece instanceof Pawn) {
            // Consider moving forward one and two squares, and capturing moves
            for (int newRow = row - 1; newRow <= row + 2; newRow++) {
                for (int newCol = col - 1; newCol <= col + 1; newCol++) {
                    if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
                        boolean temp = false;
                        temp = piece.getHasMoved(piece);
                        if (piece.isValidMove(row, col, newRow, newCol, chessBoard.getBoard())) {
                            //System.out.println(piece.getHasMoved(piece));
                            piece.setHasMoved(temp);
                            //System.out.println(piece.getHasMoved(piece));
                            allMoves.add(new Move(piece, row, col, newRow, newCol));
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
                    allMoves.add(new Move(piece, row, col, row, i));
                }
                // Check row moves
                if (i != row && piece.isValidMove(row, col, i, col, chessBoard.getBoard())) {
                    allMoves.add(new Move(piece, row, col, i, col));
                }
            }
        }
        if (piece instanceof Knight){
            int[][] knightMoves = {
                    {-2, -1}, {-2, 1}, // Upwards L-moves
                    {-1, -2}, {-1, 2}, // Leftward L-moves
                    {1, -2}, {1, 2},   // Rightward L-moves
                    {2, -1}, {2, 1}    // Downward L-moves
            };

            for (int[] move : knightMoves) {
                int newRow = row + move[0];
                int newCol = col + move[1];

                if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
                    if (piece.isValidMove(row, col, newRow, newCol, chessBoard.getBoard())) {
                        allMoves.add(new Move(piece, row, col, newRow, newCol));
                    }
                }
            }
        }
        if (piece instanceof Bishop){
            int[][] directions = {
                    {-1, -1}, {-1, 1},
                    {1, -1}, {1, 1}
            };

            for (int[] direction : directions) {
                int currentRow = row;
                int currentCol = col;

                while (true) {
                    currentRow += direction[0];
                    currentCol += direction[1];

                    // Check if the new position is within bounds
                    if (currentRow < 0 || currentRow >= SIZE || currentCol < 0 || currentCol >= SIZE) {
                        break; // Break if out of bounds
                    }

                    // Use the isValidMove method to check if the move is legal
                    if (piece.isValidMove(row, col, currentRow, currentCol, chessBoard.getBoard())) {
                        allMoves.add(new Move(piece, row, col, currentRow, currentCol));

                        // If there is a piece at the destination, we must break, whether it's a capture or our own piece
                        if (chessBoard.getPiece(currentRow, currentCol) != null) {
                            break;
                        }
                    } else {
                        break; // If the move is not valid, break out of the loop
                    }
                }
            }
        }
        if (piece instanceof Queen){

            int[][] queenMoves = {
                    {-1, -1}, {-1, 0}, {-1, 1}, // Up-Left, Up, Up-Right
                    {0, -1},           {0, 1},   // Left, Right
                    {1, -1}, {1, 0}, {1, 1}      // Down-Left, Down, Down-Right
            };
            for (int[] move : queenMoves) {
                int currentRow = row;
                int currentCol = col;

                while (true) {
                    currentRow += move[0];
                    currentCol += move[1];

                    // Check if the new position is within bounds
                    if (currentRow < 0 || currentRow >= SIZE || currentCol < 0 || currentCol >= SIZE) {
                        break; // Break if out of bounds
                    }

                    // Use the isValidMove method to check if the move is legal
                    if (piece.isValidMove(row, col, currentRow, currentCol, chessBoard.getBoard())) {
                        allMoves.add(new Move(piece, row, col, currentRow, currentCol));

                        // If there is a piece at the destination, we must break, whether it's a capture or our own piece
                        if (chessBoard.getPiece(currentRow, currentCol) != null) {
                            break;
                        }
                    } else {
                        break; // If the move is not valid, break out of the loop
                    }
                }

            }
        }
        if (piece instanceof King){
            int[][] kingMoves = {
                    {-1, -1}, {-1, 0}, {-1, 1}, // Up-Left, Up, Up-Right
                    {0, -1},           {0, 1},   // Left, Right
                    {1, -1}, {1, 0}, {1, 1}      // Down-Left, Down, Down-Right
            };

            for (int[] move : kingMoves) {
                int newRow = row + move[0];
                int newCol = col + move[1];

                // Ensure the new position is on the board
                if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
                    if (piece.isValidMove(row, col, newRow, newCol, chessBoard.getBoard())) {
                        // If the destination is empty or contains an opponent's piece, add the move
                        if (chessBoard.getPiece(newRow, newCol) == null ||
                                !chessBoard.getPiece(newRow, newCol).getColor().equals(piece.getColor())) {
                            allMoves.add(new Move(piece, row, col, newRow, newCol));
                        }
                    }
                }
            }
        }

        // Add similar logic for other types of pieces (Rooks, Knights, etc.)
    }

    public void isValidForSinglePiece(ChessPiece piece, int r, int c,ChessBoard chessBoard){

    }

    public Move selectBestMove(List<Move> moves, ChessBoard chessBoard) {
        Move bestMove = null;
        int highestScore = 0;

        for (Move move : moves) {
            if (move.capturesOpponentPiece(move.getNewX(), move.getNewY(), chessBoard)) {
                ChessPiece targetPiece = chessBoard.getPiece(move.getNewY(), move.getNewX());
                ChessPiece movingPiece = chessBoard.getPiece(move.getInitY(), move.getInitX());

                if (targetPiece != null && !targetPiece.getColor().equals(movingPiece.getColor())) {
                    int score = targetPiece.getScore();

                    System.out.println("Evaluating move " + move + " with score " + score);

                    if (score > highestScore) {
                        highestScore = score;
                        bestMove = move;
                    }
                }
            }
        }

        if (bestMove != null) {
            return bestMove;
        } else {
            // If no capturing move is found or all are same color, select a random move
            return moves.get(new Random().nextInt(moves.size()));
        }
    }

    public void executeMove(Move move, ChessBoard chessBoard) {
        int initX = move.getInitX();
        int initY = move.getInitY();
        int newX = move.getNewX();
        int newY = move.getNewY();

        ChessPiece targetSquare = chessBoard.getPiece(newY, newX);
        ChessPiece currentSquare = chessBoard.getPiece(initY, initX);

        // Check if the target square has an opponent's piece

        if (targetSquare != null && !targetSquare.getColor().equals(currentSquare.getColor())) {
            System.out.println(currentSquare.getType(currentSquare) + " " + currentSquare.getColor() + " captures " + targetSquare.getType(targetSquare) + " at " + move.toBoardCoordinate(newX,newY));
        }

        // Update the board with the move
        chessBoard.movePiece(initY, initX, newY, newX);

        //String pos = squareToLetter(initX, initY);
        String posF = squareToLetter(newY, newX);

    }

    public Move makeMove(ChessBoard chessBoard) {
        List<Move> possibleMoves = findAllLegalMoves(chessBoard, "Black");

        if (!inCheck){

            if (!possibleMoves.isEmpty()) {
                return selectBestMove(possibleMoves, chessBoard);

            } else {
                System.out.println("No legal moves found");
                // No legal moves found - could be stalemate or checkmate
                // Add logic here to handle endgame conditions
                return null;
            }
        }
        else {
            System.out.println("Checking");
            List<Move> escapeMoves = new ArrayList<>();
            for (Move move : possibleMoves) {
                // Simulate each move
                ChessPiece targetPiece = chessBoard.getPiece(move.getNewY(), move.getNewX());
                ChessPiece movingPiece = chessBoard.getPiece(move.getInitY(), move.getInitX()); // Save the moving piece
                chessBoard.simulateMove(move.getInitY(),move.getInitX(), move.getNewY(),move.getNewX());
                if (!GameRules.isKingInCheck(chessBoard, "Black")) {
                    escapeMoves.add(move);
                }
                chessBoard.undoSimulatedMove(move.getInitY(),move.getInitX(), move.getNewY(),move.getNewX(),movingPiece, targetPiece);
                // Undo the move or reset the board to its original state
            }
            if (!escapeMoves.isEmpty()) {
                return selectBestMove(escapeMoves, chessBoard);
            } else {
                System.out.println("No moves to escape check - checkmate");
                // Handle checkmate
                System.out.println("checkmate");
                return null;
            }
        }

        }



    public List<Move> returnAllLegalMoves(ChessBoard chessBoard) {

        return findAllLegalMoves(chessBoard, "Black");
    }

    public String squareToLetter(int col, int row) {

        String columnLetter = String.valueOf((char)('A' + col));
        int chessRow = 8 - row; // Convert array row index to chess row number
        return columnLetter + chessRow;
    }
}


