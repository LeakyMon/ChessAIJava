import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Settings extends JPanel{
    public Settings(ChessBoardPanel chessBoardPanel) {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton undoButton = new JButton("Undo");
        JButton redoButton = new JButton("Redo");
        JButton restartButton = new JButton("Restart");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Undo Clicked");


            }
        });
        // Similarly, add listeners for redo, restart, and exit
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redo
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessBoardPanel.resetGame();


            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(undoButton);
        add(redoButton);
        add(restartButton);
        add(exitButton);
    }
}
