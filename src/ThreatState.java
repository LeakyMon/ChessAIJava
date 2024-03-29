
import java.util.HashMap;
import java.util.Map;

public class ThreatState {
    private ChessBoard chessBoard;
    private Position position;


    private Map<String, Boolean> threatMatrix;
    private Map<String, Boolean> kingMoveable;


    public ThreatState() {
        this.threatMatrix = new HashMap<>();
        this.kingMoveable = new HashMap<>();
        initializeThreatMatrix();

    }

    private void initializeThreatMatrix() {
        // Initialize the threat matrix with all squares set to false (no threat)
        // Assuming a standard chessboard layout from A1 to H8
        for (char col = 'A'; col <= 'H'; col++) {
            for (int row = 1; row <= 8; row++) {
                String key = "" + col + row; // Create the key, e.g., "A1"
                threatMatrix.put(key, false); // Initialize with no threat
                kingMoveable.put(key,true);
            }
        }
    }

    // Method to update the threat status of a square
    public void setThreat(String square, boolean isUnderThreat) {
        threatMatrix.put(square, isUnderThreat);
    }

    // Method to check if a square is under threat
    public boolean isUnderThreat(String square) {
        return threatMatrix.getOrDefault(square, false);
    }
    public void setKingMoveable(String square, boolean moveable){
        kingMoveable.put(square,moveable);
    }
}
