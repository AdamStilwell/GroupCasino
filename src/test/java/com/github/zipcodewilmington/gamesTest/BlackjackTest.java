package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.casino.gameTools.Dealer;
import com.github.zipcodewilmington.casino.gameTools.Rank;
import com.github.zipcodewilmington.casino.gameTools.Suit;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackPlayer;
import com.github.zipcodewilmington.utils.Card;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class BlackjackTest {


//    @Test
//    public void testGetPlayerMove() {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream("hit\n".getBytes());
//        System.setOut(new PrintStream(out));
//        System.setIn(in);
//
//        String expected = "hit";
//        String actual = GetPlayerMove();
//
//        assertEquals(expected, actual);
//        assertEquals("Enter move (hit/stand): Please try again.\n", out.toString());
//    }
//
//    private String GetPlayerMove() {
//        return null;
//    }


    @Test
    public void testDealerTurn() {
        CardDeck deck = new CardDeck();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.CLUBS, Rank.KING));
        dealer.addCard(new Card(Suit.HEARTS, Rank.SEVEN));

        dealerTurn(dealer, deck);

        int expectedValue = dealer.getValue();
        assertTrue(expectedValue <= 17);

        if (expectedValue > 21) {
            assertTrue(dealer.busted());
        } else {
            assertFalse(dealer.busted());
        }
    }

    private void dealerTurn(Dealer dealer, CardDeck deck) {

    }

    @Test
    public void testPlayerTurn() {
        CardDeck deck = new CardDeck();
        BlackJackPlayer player = new BlackJackPlayer(new CasinoAccount());
        player.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        player.addCard(new Card(Suit.HEARTS, Rank.TEN));

        boolean busted = playerTurn(player, deck);

        int expectedValue = player.getHandValue();
        assertTrue(expectedValue <= 21);

        if (busted) {
            assertTrue(player.busted());
        } else {
            assertFalse(player.busted());
        }
    }

    private boolean playerTurn(BlackJackPlayer player, CardDeck deck) {
        return false;
    }


    @Test
    public void testPlayerWins() {
        // create a player and add two cards to their hand
        BlackJackPlayer player = new BlackJackPlayer(new CasinoAccount());
        player.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        player.addCard(new Card(Suit.HEARTS, Rank.FIVE));

        // create a dealer and add two cards to their hand
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.SPADES, Rank.ACE));
        dealer.addCard(new Card(Suit.CLUBS, Rank.SEVEN));

        // calculate the expected result based on the hands
        boolean expected = player.getHandValue() > dealer.getHandValue() && !player.busted() && !dealer.busted();

        // call the method and check the result
        boolean actual = playerWins(player, dealer);
        assertEquals(expected, actual);
    }

    private boolean playerWins(BlackJackPlayer player, Dealer dealer) {
        return false;
    }

//    @Test
//    public void testFindWinner() {
//        DealerPlayer dealer = new DealerPlayer();
//        dealer.addCard(new Card(Card.Rank.KING, Card.Suit.HEARTS));
//        dealer.addCard(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
//        dealer.addCard(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
//
//        DealerPlayer player = new DealerPlayer();
//        player.addCard(new Card(Card.Rank.QUEEN, Card.Suit.SPADES));
//        player.addCard(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
//
//        int bet = 10;
//
//        double expected = bet;
//        double actual = findWinner(dealer, player, bet);
//
//        assertEquals(expected, actual, 0.01);
//    }




}