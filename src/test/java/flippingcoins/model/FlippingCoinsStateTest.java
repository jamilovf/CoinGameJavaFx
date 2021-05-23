package flippingcoins.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlippingCoinsStateTest {
    FlippingCoinsState flippingCoinsState = new FlippingCoinsState();

    @Test
    void initializeCoins() {
        flippingCoinsState.initializeCoins();

        for (int i = 0; i < flippingCoinsState.coinState.size();i++){
            assertEquals(Coin.HEAD,flippingCoinsState.coinState.get(i));
        }
    }

    @Test
    void isGameOver() {
        List<Coin> coinsTrue =
                Arrays.asList(Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL);
        List<Coin> coinsFalse =
                Arrays.asList(Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.TAIL,Coin.HEAD);
        assertTrue(flippingCoinsState.isGameOver(coinsTrue));
        assertFalse(flippingCoinsState.isGameOver(coinsFalse));
    }

    @Test
    void modifyCoinState() {
        flippingCoinsState.initializeCoins();
        flippingCoinsState.coinState.set(1,Coin.TAIL);

        flippingCoinsState.modifyCoinState(0);
        flippingCoinsState.modifyCoinState(1);

        assertEquals(Coin.TAIL,flippingCoinsState.coinState.get(0));
        assertEquals(Coin.HEAD,flippingCoinsState.coinState.get(1));
    }
}