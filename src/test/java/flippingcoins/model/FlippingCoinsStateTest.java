package flippingcoins.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlippingCoinsStateTest {

    @Test
    void isGameOver() {
        boolean answer = true;
        boolean value;
        List<Integer> list = Arrays.asList(1,1,1,1,1,1,1,1,1,1);
        FlippingCoinsState coinsState = new FlippingCoinsState();

        value = coinsState.isGameOver(list);
        assertEquals(answer,value);
    }
}