package flippingcoins.model;


import java.time.Instant;

public class ResultState {

    private String player1Name;

    private String player2Name;

    private  int player1Steps = 0;

    private  int player2Steps = 0;

    private Instant time;

    private  String winner;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public  Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public void setPlayer1Steps(int player1Steps) {
        this.player1Steps = player1Steps;
    }

    public void setPlayer2Steps(int player2Steps) {
        this.player2Steps = player2Steps;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public int getPlayer1Steps() {
        return player1Steps;
    }

    public int getPlayer2Steps() {
        return player2Steps;
    }
}
