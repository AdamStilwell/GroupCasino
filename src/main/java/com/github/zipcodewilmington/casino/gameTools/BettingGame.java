package com.github.zipcodewilmington.casino.gameTools;

import com.github.zipcodewilmington.casino.Player;


public interface BettingGame {
    int minBet = 0;
    int maxBet = 0;
}

//public interface BettingGame {
//    /**
//     * take a bet from a player
//     * @param player The player placing the bet
//     * @param amount The amount of the bet
//     */
//    void placeBet(Player player, double amount);
//
//    /**
//     * determines if the player can place a bet
//     * @param player The player to check
//     * @return true if the player can place a bet, if no 'false'
//     */
//     boolean canPlaceBet(PlayerInterface player);
//
//    /**
//     * determines if the game is over
//     * @return true if the game is over, if no 'false'
//     */
//     boolean isGameOver();
//
//    /**
//     * determines the winner of the game
//     * @return player that won a game
//     */
//     PlayerInterface getWinner();
//}

