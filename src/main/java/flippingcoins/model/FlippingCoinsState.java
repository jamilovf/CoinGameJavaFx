package flippingcoins.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the state of the coins.
 */
public class FlippingCoinsState {

    public List<Coin> coinState = new ArrayList<>(10);

    /**
     * Initializes all coins to HEAD
     */
    public void initializeCoins(){
        for (int i = 0; i < 10; i++){
            coinState.add(Coin.HEAD);
        }
    }

    /**
     * Checking is game over or not.
     * Iterating over coins list.
     * Checking if coin is HEAD or TAIL.
     * @param coins instance of coins list
     * @return true or false based on coin's side
     */
    public  boolean isGameOver(List<Coin> coins) {
        for(int i = 0; i < coins.size();i++){
            if(coins.get(i) == Coin.HEAD){
                return false;
            }
        }
        return true;
    }

    /**
     * Modifying coin list based on column.
     * Getting corresponding coin to column and checking is HEAD or TAIL.
     * Setting coin to reverse state.
     * @param column
     * @return state of coin
     */
    public Coin modifyCoinState(int column){
        if(coinState.get(column) == Coin.HEAD){
            coinState.set(column, Coin.TAIL);
            return Coin.TAIL;
        }
        else{
            coinState.set(column, Coin.HEAD);
            return Coin.HEAD;
        }
    }

}
