import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//CONNECTIONS ChessGame, ChessPiece, Player

public class ChessBoardPanel extends JPanel implements MouseListener {
    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;
    private Image wRook, wKnight, wBishop, wQueen, wKing, wPawn;
    private Image Rook, Knight, Bishop, Queen, King, Pawn;
    private ChessBoard chessBoard;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private boolean pieceSelected = false;
    private ChessPiece selectedPiece;
    private boolean isWhiteTurn = true;
    private ChessAI chessAI;
    private Position position;
    private ThreatState threatState;

    public ChessBoardPanel(ChessAI chessAI) {
        chessBoard = new ChessBoard();
        threatState = new ThreatState();
        loadImages();
        addMouseListener(this);
        this.chessAI = chessAI;
    }

    private void loadImages() {
        try {
            String basePath = "C:/Users/hecto/IdeaProjects/Chess/ChessPieces/";
            wRook = ImageIO.read(new File(basePath + "wRook.png"));
            wKnight = ImageIO.read(new File(basePath + "wKnight.png"));
            wBishop = ImageIO.read(new File(basePath + "wBishop.png"));
            wQueen = ImageIO.read(new File(basePath + "wQueen.png"));
            wKing = ImageIO.read(new File(basePath + "wKing.png"));
            wPawn = ImageIO.read(new File(basePath + "wPawn.png"));
            Rook = ImageIO.read(new File(basePath + "rook.png"));
            Knight = ImageIO.read(new File(basePath + "knight.png"));
            Bishop = ImageIO.read(new File(basePath + "bishop.png"));
            Queen = ImageIO.read(new File(basePath + "queen.png"));
            King = ImageIO.read(new File(basePath + "king.png"));
            Pawn = ImageIO.read(new File(basePath + "pawn.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        initializeBoardPieces(g);
        if (pieceSelected) {
            highlightTile(g, selectedRow, selectedCol);
            drawSelectedPiece(g, selectedRow, selectedCol);
        }
    }
    private void highlightTile(Graphics g, int row, int col) {
        g.setColor(new Color(255, 255, 0, 128)); // Semi-transparent yellow, for example
        g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void drawSelectedPiece(Graphics g, int row, int col) {
        ChessPiece piece = chessBoard.getPiece(row,col);
        if (piece != null) {
            //Image pieceImage = // get the image for this piece
                    // Draw the piece larger or with a border
                    // Adjust the x, y, width, and height as needed for the larger size
              //g.drawImage(pieceImage, col * SQUARE_SIZE, row * SQUARE_SIZE, largerWidth, largerHeight, this);
        }
    }



    private void drawBoard(Graphics g) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void initializeBoardPieces(Graphics g) {
        // Drawing Pawns
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ChessPiece piece = chessBoard.getPiece(i, j);
                if (piece != null) {
                    // Draw the piece. You need to determine which image to use based on the piece type and color.
                    Image pieceImage = getPieceImage(piece);
                    g.drawImage(pieceImage, j * SQUARE_SIZE, i * SQUARE_SIZE, this);
                }
            }
        }
    }


    private Image getPieceImage(ChessPiece piece) {
        //White pawn
        if (piece.getColor().equals("White")){
            if (piece.getType(piece).equals("Pawn")){
                return wPawn;
            }
            else if (piece.getType(piece).equals("Rook")){
                return wRook;
            }
            else if (piece.getType(piece).equals("Knight")){
                return wKnight;
            }
            else if (piece.getType(piece).equals("Bishop")){
                return wBishop;
            }
            else if (piece.getType(piece).equals("King")){
                return wKing;
            }
            else if (piece.getType(piece).equals("Queen")){
                return wQueen;
            }
        }

        else {
            if (piece.getType(piece).equals("Pawn")){
                return Pawn;
            }
            else if (piece.getType(piece).equals("Rook")){
                return Rook;
            }
            else if (piece.getType(piece).equals("Knight")){
                return Knight;
            }
            else if (piece.getType(piece).equals("Bishop")){
                return Bishop;
            }
            else if (piece.getType(piece).equals("King")){
                return King;
            }
            else if (piece.getType(piece).equals("Queen")){
                return Queen;
            }
        }

       return null;
    }

    public void mouseClicked(MouseEvent e) {
        if (isWhiteTurn) {

            int x = e.getX(); // Get the x-coordinate of the mouse click
            int y = e.getY(); // Get the y-coordinate of the mouse click

            // Calculate which square is clicked
            int row = y / SQUARE_SIZE;
            int col = x / SQUARE_SIZE;

            String pos = squareToLetter(col, row);
            System.out.println("Square Selected " + pos);
            //ChessPiece tempPiece = chessBoard.getPiece(row, col);

            if (selectedPiece != null) {
                // VALID MOVE
                if (selectedPiece.isValidMove(selectedRow, selectedCol, row, col, chessBoard.getBoard())) {

                        chessBoard.movePiece(selectedRow, selectedCol, row, col);

                        //System.out.println("Checking for check/checkmate opponent color: " + " Black");
                        if (GameRules.isKingInCheck(chessBoard, "Black",  row,col)) {
                            JOptionPane.showMessageDialog(this, "Black" + " is in Check!");
                        }
                        pieceSelected = false;
                        selectedPiece = null;
                        isWhiteTurn = false;
                   // }

                }
                //NOT A VALID MOVE FOR PIECE
                else {
                    //System.out.println("Not a valid move");
                    pieceSelected = false;
                    selectedPiece = null;
                }
            } else {
                // Check if the clicked square contains a piece of the current player's color
                ChessPiece clickedPiece = chessBoard.getPiece(row, col);
                if (clickedPiece != null && clickedPiece.getColor().equals("White")) {
                    // Only select the piece if it's the same color as the current player's turn
                    selectedPiece = clickedPiece;
                    selectedRow = row;
                    selectedCol = col;
                    pieceSelected = true;
                } else {
                    //System.out.println("Empty space or opponents color");
                }
            }
            repaint();
            if (!isWhiteTurn){
                completePlayerMove();
            }
        }

    }

    public void completePlayerMove() {
        System.out.println();
        System.out.println();
        System.out.println("\n-------WHITE's TURN OVER-------\n");
        System.out.println();
        System.out.println();
        System.out.println("\n-------BLACK's TURN-------\n");
        if (!isWhiteTurn) {
            Move tempMove = chessAI.makeMove(chessBoard);
            int r,c;
            r = tempMove.getNewX();
            c = tempMove.getNewY();
            chessAI.executeMove(tempMove, chessBoard);

            chessAI.debugPrintAllLegalMoves(chessBoard);

            if (GameRules.isKingInCheck(chessBoard, "White",r ,c)) {
                JOptionPane.showMessageDialog(this, "White is in Check!" + chessBoard.getPiece(r,c));
            }
            isWhiteTurn = true; // Switch back to player's turn

            String opponentColor = isWhiteTurn ? "White" : "Black";

            repaint();
            System.out.println("\n-------BLACK's TURN OVER-------\n");
            System.out.println("\n-------WHITE's TURN-------\n");
        }
    }


    private void checkGameState(String opponentColor) {
        System.out.println("Checking for check/checkmate opponent color: " + opponentColor);

     //       JOptionPane.showMessageDialog(this, opponentColor + " is in Check!");
      //  }
    }

    public void resetGame() {
        // Reset the chess board to its initial state
        chessBoard.initializeBoard();


        // Reset any other state variables, like whose turn it is
        isWhiteTurn = true;
        selectedPiece = null;
        pieceSelected = false;
        repaint();
    }



    // Other required methods of MouseListener (empty implementations if not used)
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public String squareToLetter(int col, int row) {

        String loc = "";

// Inverting the row number
        int invertedRow = 7 - row;

        switch (col) {
            case 0:
                loc = "A";
                break;
            case 1:
                loc = "B";
                break;
            case 2:
                loc = "C";
                break;
            case 3:
                loc = "D";
                break;
            case 4:
                loc = "E";
                break;
            case 5:
                loc = "F";
                break;
            case 6:
                loc = "G";
                break;
            case 7:
                loc = "H";
                break;
            case 8:
                loc = "I";
                break;
            default:
                break;
        }

        loc += (invertedRow + 1);

        return loc;
    }

}
