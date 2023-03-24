package com.github.zipcodewilmington.casino.gameTools;

import java.util.Random;

/** James made dice. do we need more methods?
 *
 */
public class Dice {
    private int sumOfRoll = 0;
    private int numberDice = 0;
    private int numberTosses; // prob don't need
    Random toss = new Random();

    public Dice(int numberDice) {
        this.numberDice = numberDice;
    }
    public void setNumberDice(int numberDice) {
        this.numberDice = numberDice;
    }
    public int getNumberDice() {
        return numberDice;
    }
    public int rollDice(int numDice) {
        for (int i = 1; i <= numberDice; i++) {
            sumOfRoll += toss.nextInt(6) + 1;
        }
        return sumOfRoll;
    }
}
