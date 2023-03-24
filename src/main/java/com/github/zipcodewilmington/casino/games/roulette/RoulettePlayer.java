package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

public class RoulettePlayer extends Player implements GamblingPlayer {
    public RoulettePlayer(CasinoAccount playerAccount) {
        super(playerAccount);
    }
}
