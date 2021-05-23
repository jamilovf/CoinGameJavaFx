package flippingcoins.model;

import lombok.Data;

/**
 * Class representing the dynamic state of the players inside game.
 */
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

    /**
     * Modifies steps of player.
     * Checking whose turn and steps less than 3 or not.
     * Increments steps of player.
     * Increments steps of player for result state.
     * @param resultState instance of result state.
     * @return steps of player.
     */
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

    /**
     * Switches player's turn.
     * Checking whose turn.
     * Switching player's turn.
     * Setting steps of player to 0.
     * @return player's turn.
     */
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