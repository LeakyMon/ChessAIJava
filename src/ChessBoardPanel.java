import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ChessBoardPanel extends JPanel implements MouseListener {
    private static final int SIZE = 8;
    private static final int SQUARE_SIZE = 60;
    private Image wRook, wKnight, wBishop, wQueen, wKing, wPawn;
    private Image Rook, Knight, Bishop, Queen, King, Pawn;

    public ChessBoardPanel() {
        loadImages();
        addMouseListener(this);
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
        drawPieces(g);
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

    private void drawPieces(Graphics g) {
        // Drawing Pawns
        for (int i = 0; i < SIZE; i++) {
            g.drawImage(wPawn, i * SQUARE_SIZE, 6 * SQUARE_SIZE, this);
            g.drawImage(Pawn, i * SQUARE_SIZE, 1 * SQUARE_SIZE, this);
        }

        // Drawing Rooks
        g.drawImage(wRook, 0, 7 * SQUARE_SIZE, this);
        g.drawImage(wRook, 7 * SQUARE_SIZE, 7 * SQUARE_SIZE, this);
        g.drawImage(Rook, 0, 0, this);
        g.drawImage(Rook, 7 * SQUARE_SIZE, 0, this);

        g.drawImage(Knight, 1 * SQUARE_SIZE, 0, this); // Black Knight at 1,0
        g.drawImage(Knight, 6 * SQUARE_SIZE, 0, this); // Black Knight at 6,0
        g.drawImage(Bishop, 2 * SQUARE_SIZE, 0, this); // Black Bishop at 2,0
        g.drawImage(Bishop, 5 * SQUARE_SIZE, 0, this); // Black Bishop at 5,0
        g.drawImage(Queen, 3 * SQUARE_SIZE, 0, this);  // Black Queen at 3,0
        g.drawImage(King, 4 * SQUARE_SIZE, 0, this);   // Black King at 4,0

        // Drawing White Pieces
        g.drawImage(wKnight, 1 * SQUARE_SIZE, 7 * SQUARE_SIZE, this); // White Knight at 1,7
        g.drawImage(wKnight, 6 * SQUARE_SIZE, 7 * SQUARE_SIZE, this); // White Knight at 6,7
        g.drawImage(wBishop, 2 * SQUARE_SIZE, 7 * SQUARE_SIZE, this); // White Bishop at 2,7
        g.drawImage(wBishop, 5 * SQUARE_SIZE, 7 * SQUARE_SIZE, this); // White Bishop at 5,7
        g.drawImage(wQueen, 3 * SQUARE_SIZE, 7 * SQUARE_SIZE, this);  // White Queen at 3,7
        g.drawImage(wKing, 4 * SQUARE_SIZE, 7 * SQUARE_SIZE, this);   // White King at 4,7
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX(); // Get the x-coordinate of the mouse click
        int y = e.getY(); // Get the y-coordinate of the mouse click

        // Calculate which square is clicked
        int row = y / SQUARE_SIZE;
        int col = x / SQUARE_SIZE;

        String pos = squareToLetter(col,row);
        System.out.println("Square Selected " + pos);

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
