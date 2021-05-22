package flippingcoins.model;

import java.util.ArrayList;
import java.util.List;

public class FlippingCoinsState {

    public List<Coin> coinState = new ArrayList<>(10);

    public void initializeCoins(){
        for (int i = 0; i < 10; i++){
            coinState.add(Coin.HEAD);
        }
    }
    public  boolean isGameOver(List<Coin> coins) {
        for(int i = 0; i < coins.size();i++){
            if(coins.get(i) == Coin.HEAD){
                return false;
            }
        }
        return true;
    }

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
