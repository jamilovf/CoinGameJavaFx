package flippingcoins.model;

import lombok.Data;

@Data
public class PlayerState {
    private int player1Steps;
    private int player2Steps;
    private boolean player1Turn;

    public PlayerState() {
        player1Steps = 0;
        player2Steps = 0;
        player1Turn = true;
    }

    public int modifySteps(ResultState resultState) {
        if (player1Turn && player1Steps < 3) {
            player1Steps++;
            resultState.setPlayer1Steps(resultState.getPlayer1Steps() + 1);
            return player1Steps;
        } else if (!player1Turn && player2Steps < 3) {
            player2Steps++;
            resultState.setPlayer2Steps(resultState.getPlayer2Steps() + 1);
            return player2Steps;
        }
        else {
            return -1;
        }
    }

    public boolean switchPlayer(){
        if (player1Turn) {
            player1Turn = false;
            player1Steps = 0;
        }
        else {
            player1Turn = true;
            player2Steps = 0;
        }
        return player1Turn;
    }
}