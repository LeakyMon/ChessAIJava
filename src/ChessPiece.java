//CONNECTIONS

public abstract class ChessPiece {


    private String type;
    protected String color;
    protected boolean hasMoved;

    public ChessPiece(String type,String color) {
        this.type = type;
        this.color = color;
        this.hasMoved = false;
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
    public void setColor(String color){
        this.color = color;
    }
    // Abstract method for validating moves, to be implemented in subclasses
    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessPiece[][] board);


}
