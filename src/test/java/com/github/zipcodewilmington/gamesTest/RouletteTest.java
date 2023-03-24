package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.gameTools.WheelThing;

import java.util.Map;

import com.github.zipcodewilmington.casino.games.roulette.RouletteGame;
import com.github.zipcodewilmington.casino.games.roulette.RoulettePlayer;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouletteTest {

    @Test
    public void checkResult() {
        RouletteGame game = new RouletteGame();
        game.mapNumberToColor();
        Integer numberLandedOn = 23;
        Integer numberGuess = 23;
        Integer typeBet = 1;

        boolean isWon = game.checkResult(numberLandedOn, numberGuess, typeBet);
        assertTrue(isWon);
    }


    @Test
    public void testSpinWheelNumber() {
        WheelThing wheel = new WheelThing();
        RouletteGame game = new RouletteGame();

        Integer result = RouletteGame.spinWheelNumber();

        assertTrue(result >= 0 && result <= 36);
    }

    @Test
    public void testMapNumberToColor() {
        WheelThing wheel = new WheelThing();
        RouletteGame game = new RouletteGame();
        Map<Integer, String> result = game.mapNumberToColor();

        assertEquals("green", result.get(0));
        assertEquals("green", result.get(00));
        assertEquals("red", result.get(1));
        assertEquals("red", result.get(3));
        assertEquals("black", result.get(2));
        assertEquals("black", result.get(4));
        assertEquals("black", result.get(35));
        assertNull(result.get(37));
        assertNull(result.get(-1));
    }
    @Test
    public void add() {
        // Arrange

        RoulettePlayer player = new RoulettePlayer(new CasinoAccount());

        RouletteGame game = new RouletteGame();
        game.add(player);
        assertNotNull(game.getCurrentPlayer());

    }


    @Test
    public void remove() {
        RoulettePlayer player = new RoulettePlayer(new CasinoAccount());

        RouletteGame game = new RouletteGame();
        game.add(player);
        game.remove(player);
        assertNull(game.getCurrentPlayer());

    }
}
