//CONNECTIONS

public abstract class ChessPiece {


    private String type;
    protected String color;
    protected boolean hasMoved;
    protected int score;

    public ChessPiece(String type,String color,int score) {
        this.type = type;
        this.color = color;
        this.hasMoved = false;
        this.score = score;
    }

    public String getType(ChessPiece piece){
        return piece.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getColor(){
        return this.color;
    }
    public void setHasMoved(boolean didMove){
        this.hasMoved=didMove;
    }
    public boolean getHasMoved(ChessPiece piece){
        return piece.hasMoved;
    }
    public void setColor(String color){
        this.color = color;
    }
    public int getScore() {
        return score;
    }
    // Abstract method for validating moves, to be implemented in subclasses
    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board);
    public abstract boolean isMoveValidWithoutChangingState(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board);

}
