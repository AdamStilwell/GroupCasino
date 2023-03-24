package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.utils.Card;

public class BlackJackPlayer extends Player implements GamblingPlayer {

    public BlackJackPlayer(CasinoAccount playerAccount) {
        super(playerAccount);
    }


    public boolean hasBlackjack() {
        return false;
    }


    public boolean busted() {
        return false;
    }

    public void addCard(Card c) {

    }

    public int getHandValue() {
        return 0;
    }

    public int getValue() {
        return 1;
    }

}
