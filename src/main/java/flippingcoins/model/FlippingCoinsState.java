package flippingcoins.model;

import java.util.Arrays;
import java.util.List;

public class FlippingCoinsState {

    public  List<Integer> coinState = Arrays.asList(0,0,0,0,0,0,0,0,0,0);

    public  boolean isGameOver(List<Integer> coins) {
        for(int i = 0; i < coins.size();i++){
            if(coins.get(i) == 0){
                return false;
            }
        }
        return true;
    }

}
