
import java.util.HashMap;
import java.util.Map;

public class ThreatState {
    private ChessBoard chessBoard;
    private Position position;


    private Map<String, Boolean> threatMatrix;


    public ThreatState() {
        this.threatMatrix = new HashMap<>();
        initializeThreatMatrix();

    }

    private void initializeThreatMatrix() {
        // Initialize the threat matrix with all squares set to false (no threat)
        // Assuming a standard chessboard layout from A1 to H8
        for (char col = 'A'; col <= 'H'; col++) {
            for (int row = 1; row <= 8; row++) {
                String key = "" + col + row; // Create the key, e.g., "A1"
                threatMatrix.put(key, false); // Initialize with no threat
            }
        }
        setThreat("E1", true);
        setThreat("E2", true);
        setThreat("D1", true);
        setThreat("D2", true);
        setThreat("F1", true);
        setThreat("F2", true);
    }

    // Method to update the threat status of a square
    public void setThreat(String square, boolean isUnderThreat) {
        threatMatrix.put(square, isUnderThreat);
    }

    // Method to check if a square is under threat
    public boolean isUnderThreat(String square) {
        return threatMatrix.getOrDefault(square, false);
    }
}
