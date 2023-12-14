import javax.swing.JFrame;

public class ChessGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChessBoardPanel());

        frame.setSize(500, 520); // Set the window size slightly larger than the board
        frame.setVisible(true);
    }
}