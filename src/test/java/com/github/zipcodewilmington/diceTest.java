package com.github.zipcodewilmington;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.zipcodewilmington.casino.gameTools.Dice;
import org.junit.Assert;
import org.junit.Test;

public class diceTest {
    @Test
    public void testDiceConstructor(){
        // Given
        int expected = 2;
        Dice dice = new Dice(expected);

        // When
        int actual = dice.getNumberDice();
        // Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testSetNumberDice(){
        // Given
        int numDice = 4;
        Dice dice = new Dice(numDice);
        // When
        int expected = 22;
        dice.setNumberDice(expected);
        int actual = dice.getNumberDice();
        //
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testRolldice(){
        // Given

        // When
        // Then
    }
}
