package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.CasinoAccount;

import com.github.zipcodewilmington.casino.games.war.WarGame;
import com.github.zipcodewilmington.casino.games.war.WarPlayer;

import com.github.zipcodewilmington.casino.games.wheelof6.WheelOfSixPlayer;
import org.junit.Test;


import static org.junit.Assert.*;

public class WarTest {
    @Test
    public void testAdd() {

        WarPlayer warPlayer = new WarPlayer(new CasinoAccount());
        WarGame game = new WarGame();
        game.add(warPlayer);
        assertNotNull(game.getCurrentPlayer());
    }

    @Test
    public void remove() {
        WarPlayer warplayer = new WarPlayer(new CasinoAccount());

        WarGame game = new WarGame();
        game.add(warplayer);
        game.remove(warplayer);
        assertNull(game.getCurrentPlayer());

    }
    @Test
    public void testConstructor() {
        CasinoAccount account = new CasinoAccount();
        WheelOfSixPlayer player = new WheelOfSixPlayer(account);
        assertEquals(account, player.getPlayerAccount());
    }


}
