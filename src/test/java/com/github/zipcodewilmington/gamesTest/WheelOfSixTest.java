package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.games.wheelof6.WheelOfSixGame;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WheelOfSixTest {

    @Test
    public void testCalculateWinnings1(){
        int bet = 0;
        int expectedWinnings = 0;
        int actualWinnings = WheelOfSixGame.calculateWinnings(bet);
        assertEquals(expectedWinnings, actualWinnings);

    }

    @Test
    public void testCalculateWinnings2(){
        int bet = 100;
        int expectedWinnings = 100;
        int actualWinnings = WheelOfSixGame.calculateWinnings(bet);
        assertEquals(expectedWinnings, actualWinnings);
    }
    @Test
    public void testSpinWheel() {
        WheelOfSixGame wheelOfSix = new WheelOfSixGame();
        String[] wheel = {"1", "2", "3", "4", "5", "6"};
        Object[] result = wheelOfSix.spinWheel(wheel);
        Assert.assertNotNull(result);
    }



    @Test
    public void testGetPayout() {
        WheelOfSixGame wheelOfSix = new WheelOfSixGame();
        int payout = wheelOfSix.getPayout("3", 2);
        assertEquals(2, payout);
    }
}
