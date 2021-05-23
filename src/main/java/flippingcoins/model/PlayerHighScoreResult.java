package flippingcoins.model;

import lombok.Data;

/**
 * Class representing the final result for players to store in {@code tableView}.
 */
@Data
public class PlayerHighScoreResult {
    private String name;
    private int score;
}
