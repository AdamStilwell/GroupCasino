package com.github.zipcodewilmington.gameToolsTest;

import com.github.zipcodewilmington.utils.Card;
import org.junit.Assert;
import org.junit.Test;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;

public class CardDeckTest {
    //test to see if all 52 cards are created
    @Test
    public void createCardDeckTest(){
        //given
        CardDeck cardDeck = new CardDeck();
        //when
        int expectedNumber = 52;
        int actualNumber = cardDeck.createCardDeck().size();
//        //then
        Assert.assertEquals(expectedNumber, actualNumber);
    }

    //test to see if all cards do not repeat
    @Test
    public void createCardDeckTest2(){
        //given
        CardDeck cardDeck = new CardDeck();
        Card[] deckArray = cardDeck.createCardDeck().toArray(new Card[0]);
        //with the current loop, the counter will increase when the card equals itself, the first card is skipped
        //there are 52 cards, so the counter should be 51
        int expectedCount = 51;
        int actualCount = 0;
        //when
        for(int i = 0; i <deckArray.length; i++){
            for(int j = 1; j <deckArray.length; j++){
                if(deckArray[i].getRank().equals(deckArray[j].getRank()) && deckArray[i].getSuit().equals(deckArray[j].getSuit())){
                    actualCount++;
                }
            }
        }
        //then
        Assert.assertEquals(expectedCount, actualCount);
    }
}
