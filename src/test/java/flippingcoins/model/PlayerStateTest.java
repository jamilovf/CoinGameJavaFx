package flippingcoins.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStateTest {
    PlayerState playerState = new PlayerState();

    @Test
    void modifySteps() {
        ResultState resultState = new ResultState();
        playerState.setPlayer1Steps(1);
        playerState.setPlayer2Steps(2);

        assertEquals(2,playerState.modifySteps(resultState));

        playerState.setPlayer1Turn(false);

        assertEquals(3,playerState.modifySteps(resultState));

        assertEquals(-1,playerState.modifySteps(resultState));
    }

    @Test
    void switchPlayer() {
        playerState.switchPlayer();

        assertEquals(false,playerState.isPlayer1Turn());
        assertEquals(0,playerState.getPlayer1Steps());

        playerState.switchPlayer();

        assertEquals(true,playerState.isPlayer1Turn());
        assertEquals(0,playerState.getPlayer2Steps());
    }
}