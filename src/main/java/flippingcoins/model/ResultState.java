package flippingcoins.model;

import lombok.Data;

import java.time.Instant;

/**
 * Class representing the result state of the players.
 */
@Data
public class ResultState {

    private String player1Name;

    private String player2Name;

    private  int player1Steps = 0;

    private  int player2Steps = 0;

    private Instant time;

    private  String winner;
}
