import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;

//CONNECTIONS: ChessBoardPanel | Settings | Player | ChessAI

public class ChessGame {
    private ChessAI chessAI;
    private ChessBoardPanel chessBoardPanel;
    private JFrame frame;
    private Settings settings;

    public ChessGame(){
        chessAI = new ChessAI();
        chessBoardPanel = new ChessBoardPanel(chessAI);

        chessAI = new ChessAI();

        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(chessBoardPanel, BorderLayout.CENTER); // Use the instance variable here
        // ... Other GUI components like settings panel

        settings = new Settings(chessBoardPanel);
        frame.add(settings, BorderLayout.SOUTH);

        frame.pack();
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    boolean PlayerTurn = true;

    public static void main(String[] args) {
        new ChessGame();

        System.out.println("Starting Game");

    }


}