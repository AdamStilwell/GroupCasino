package com.github.zipcodewilmington.gameToolsTest;


import com.github.zipcodewilmington.casino.gameTools.Rank;
import com.github.zipcodewilmington.casino.gameTools.Suit;
import com.github.zipcodewilmington.utils.Card;
import org.junit.Assert;
import org.junit.Test;

public class CardTest {
    //test to see if a card gets a suit value
    @Test
    public void createCardSuitTest(){
        //given
        //when
        Suit expectedSuit = Suit.DIAMONDS;
        Rank expectedRank = Rank.ACE;
        Card card = new Card(expectedSuit, expectedRank);
        Suit actualSuit = card.getSuit();
        //then
        Assert.assertEquals(expectedSuit, actualSuit);
    }
    //test to see if a card gets a rank value
    @Test
    public void createCardRankTest(){
        //given
        //when
        Suit expectedSuit = Suit.DIAMONDS;
        Rank expectedRank = Rank.ACE;
        Card card = new Card(expectedSuit, expectedRank);
        Rank actualRank = card.getRank();
        //then
        Assert.assertEquals(expectedRank, actualRank);
    }
}
