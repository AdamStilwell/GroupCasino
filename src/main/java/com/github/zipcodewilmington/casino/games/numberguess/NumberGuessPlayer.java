package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessPlayer extends Player implements GamblingPlayer {
    public NumberGuessPlayer(CasinoAccount playerAccount) {
        super(playerAccount);
    }

//    @Override
//    public Double checkBalance() {
//        return super.getPlayerAccount().getBalance();
//    }
//
//    public Double deposit(Double amount){
//        if(amount < super.getPlayerAccount().getBalance()){
//            super.getPlayerAccount().setBalance(amount);
//            return super.getPlayerAccount().getBalance();
//        }
//        return -1.0;
//    }

}