import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;

//CONNECTIONS: ChessBoardPanel | Settings | Player | ChessAI

public class ChessGame {

    boolean PlayerTurn = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Set the layout to BorderLayout
        frame.add(new ChessBoardPanel(), BorderLayout.CENTER); // Add the chessboard to the center
        // Create and add the settings panel to the bottom (south)
        Settings settingsPanel = new Settings();
        frame.add(settingsPanel, BorderLayout.SOUTH);
        frame.pack(); // This will adjust the size of the frame to fit its contents
        frame.setSize(500,600);
        frame.setVisible(true);


        startGame();

    }
    public static void startGame(){
        Player newPlayer = new Player(true);




    }



}