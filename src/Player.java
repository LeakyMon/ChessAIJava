public class Player {

    private boolean turn = true;

    Player(boolean turn){
        this.turn = turn;

    }
    public boolean getPlayerTurn(){
        return this.turn;
    }
    public void setPlayerTurn(boolean turn){
        this.turn = turn;
    }


}
