package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.casino.gameTools.Rank;
import com.github.zipcodewilmington.casino.gameTools.Suit;
import com.github.zipcodewilmington.casino.games.war.WarPlayer;
import com.github.zipcodewilmington.utils.Card;
import org.junit.Test;

import static org.junit.Assert.*;

public class WarPlayerTest {
    @Test
    public void testWarPlayerConstructor() {
        // Create a new casino account
        CasinoAccount account = new CasinoAccount();

        // Create a new War player using the casino account
        WarPlayer player = new WarPlayer(account);

        // Assert that the player account is the same as the one passed to the constructor
        assertEquals(account, player.getPlayerAccount());

        // Assert that the player's hand is empty
        assertEquals(null, player.getCurrentCard());
    }


//    @Test
//    public void storeDeck(){
//
//    }
    @Test
    public void testGetCurrentCard() {

        WarPlayer player = new WarPlayer(new CasinoAccount());
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        player.setCurrentCard(card);

        Card currentCard = player.getCurrentCard();

        assertNotNull(currentCard);

        assertEquals(card, currentCard);
    }
}
