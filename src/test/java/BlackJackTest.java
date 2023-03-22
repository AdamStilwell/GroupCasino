import org.junit.Test;

public class BlackJackTest {

    @Test
    public void testBet(){
        //need to set up the game first
        // checks if the placeBet method of the Player class works
        //set up the player, start game
git
        Player player = new Player();
        Deck deck = new Deck();
        BlackJack game = new BlackJack(deck, player);
        game.addPlayer(Player);  //adding player to the game
        game.startGame(); //start the game by dealing the cards and initiating the first turn of the game.
        int InitialChip = player.getChip();     //исходный
        //Then checking that the player can make a valid bet of 10 chips
        // and that his chip count is reduced by the bet amount.
        int betAmount = 10;
        assertTrue(player.placeBet(betAmount));
        assertEquals(player.getBet(), betAmount);
        assertEquals(player.getChip(), initialChip - betAmount);
        //player can not make a bet higher than their chip count?
        assertFalse(player.placeBet(initialChip + 1));
        //player can not make a bet of 0 or a negative amount
        assertFalse(player.placeBet(0));
        assertFalse(player.placeBet(-1));
    }

    @Test
    public void testHit(){
        //set up the game again
        BlackJack game = new BlackJack();
        Player player = new Player();
        game.addPlayer(Player);
        game.startGame();
        player.placeBet(10);
        // Checks that the player can hit and receive a new card
        int initialHandSize = player.getHand().size();
        player.hit();
        assertEquals(player.getHand().size(), initialHandSize +1);
        //Checks that the player cannot hit if they have already stood
        player.stand();
        assertFalse(player.hit());
    }

    @Test
    public void testStay(){
        //set up again
        BlackJack game = new BlackJack();
        Player player = new Player();
        game.addPlayer(Player);
        game.startGame();
        player.placeBet(10);
        //Checks that the player can stand and end their turn
        player.stand();
        assertTrue(player.isStanding());
    }

    @Test
    public void testGameStart() {
        //create the game
        BlackJack game = new BlackJack(deck, player, dealer);
        Player player = new Player();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        //start the game
        game.start();
        //each player has two cards
        assertSomething
    }

}

public class DeckTest {
    @Test
    public void testDeck() {
        //making a new deck
        Deck deck = new Deck();
        //check that the deck has the right amount of cards(using one deck)
        assertEquals(52, deck.seze());
        //create a new deck, shuffle it
        Deck shuffleDeck = new Deck();
        shuffleDeck.shuffle();
        // and to check if it still the right amount of cards in deck
        assertEquals(52, shuffleDeck.size());
        // to check if decks are different from each other
        assertNotEquals(seck.toString(), shuffleDeck.toString());
        //creating a new deck with 52 card in it
        //creates one more deck and shuffled it
        //and checks again if it contains 52 cards as well
        //and at the end we check if they don't have the same order

    }
}

