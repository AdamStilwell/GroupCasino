package com.github.zipcodewilmington.utils;

import java.util.Random;

/** James made dice. do we need more methods?
 *
 */
public class Dice {
    private int sumOfroll = 0;
    private int numberDice = 0;
    private int numberTosses;
    Random toss = new Random();
    public Dice(int numberDice){
        this.numberDice = numberDice;
    }

    public void setNumberDice(int x) {
        numberDice = x;
    }
    public int getNumberDice() {
        return numberDice;
    }
    public int rollDice(int numDice){
        for (int i = 1; i <= numberDice; i++) {
            sumOfroll += toss.nextInt(6) + 1;
        } return sumOfroll;
    }


}