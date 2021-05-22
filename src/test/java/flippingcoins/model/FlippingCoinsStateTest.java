package flippingcoins.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlippingCoinsStateTest {

    @Test
    void isGameOver() {
        List<Integer> listTrue = Arrays.asList(1,1,1,1,1,1,1,1,1,1);
        List<Integer> listFalse = Arrays.asList(1,1,1,1,1,1,1,1,1,0);

        FlippingCoinsState coinsState = new FlippingCoinsState();

        assertTrue(coinsState.isGameOver(listTrue));
        assertFalse(coinsState.isGameOver(listFalse));
    }
}