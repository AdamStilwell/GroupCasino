package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

/**
 * ONLY JAMES, DON'T TOUCH
 */
public class BettingPayout {
    private  double maxBet = 0;
    private  double minBet = 0;
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private final IOConsole errorConsole = new IOConsole(AnsiColor.RED);

    public BettingPayout(int min, int max){
        minBet = min;
        maxBet = max;
    }
    public  boolean checkMax(double bet) {
        return bet <= maxBet;
    }
    public  boolean checkMin(double bet){
        return bet >= minBet;
    }
    public boolean checkBetAgainstBalance(double bet, double currentBalance){
        return bet <= currentBalance;
    }

    public double betPayout(double bet, double multiplier, CasinoAccount account, boolean isWon) {
        if (isWon) {
            Double newBalance = account.getBalance() + (bet * multiplier);
            account.setBalance(newBalance);
            return (bet * multiplier);
        }
        Double newBalance = account.getBalance() - bet;
        account.setBalance(newBalance);
        return bet;
    }

    public double betPayout(double bet, double multiplier){
        return (bet * multiplier);
    }
    public boolean checkBet(double bet){
        return (checkMin(bet) && checkMax(bet));
    }

    public Double takeBet(int minBet, int maxBet, double currentBalance) {
        Double bet = 0.0;
        boolean isTakingBet = true;
        while(isTakingBet){
            bet = console.getDoubleInput(String.format("Enter the amount of bet (%d$ - %d$) [0 to exit] : ",minBet,maxBet));
            if(bet == 0.0){
                return 0.0;
            } else if(!checkBetAgainstBalance(bet,currentBalance)){
                errorConsole.println("Bet exceeds balance! Please place lower bet or deposit more money!");
            } else if(!checkBet(bet)){
                errorConsole.println("Invalid bet amount!");
            } else {
                return bet;
            }
        }
        return bet;
    }
}
