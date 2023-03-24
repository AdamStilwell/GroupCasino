package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.casino.gameTools.Dealer;
import com.github.zipcodewilmington.utils.Card;

import java.util.Scanner;



public class BlackJackGame extends Game {
    Scanner scanner = new Scanner(System.in);

    public void initializeGame() {
        String names;
        System.out.println("Welcome to Blackjack!");
        System.out.println("");
        System.out.println("  BLACKJACK RULES: ");
        System.out.println("	-Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.");
        System.out.println("	-Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.");
        System.out.println("	-The players cards are added up for their total.");
        System.out.println("	-Players “Hit” to gain another card from the deck. Players “Stay” to keep their current card total.");
        System.out.println("	-Dealer “Hits” until they equal or exceed 17.");
        System.out.println("	-The goal is to have a higher card total than the dealer without going over 21.");
        System.out.println("	-If the player total equals the dealer total, it is a “Push” and the hand ends.");
        System.out.println("	-Players win their bet if they beat the dealer. Players win 1.5x their bet if they get “Blackjack” which is 21.");
        System.out.println("");
        System.out.println("");
    }

    private static final int Starting_bankroll = 100;

    private String getPlayerMove() {

        while (true) {
            System.out.println("Enter move (hit/stand): ");
            String move = scanner.nextLine();
            move = move.toLowerCase();

            if (move.equals("hit") || move.equals("stand")) {
                return move;
            }
            System.out.println("Please try again.");
        }
    }


    private void dealerTurn(Dealer dealer, CardDeck deck) {

        while (true) {
            System.out.println("Dealer's hand");
            System.out.println(dealer);

            int value = dealer.getValue();
            System.out.println("Dealer's hand has value " + value);
            System.out.println("Enter to continue...");
            scanner.nextLine();

            if (value < 17) {
                System.out.println("Dealer hits");
                Card c = deck.deal();
                dealer.addCard(c);

                System.out.println("Dealer card was " + c);

                if (dealer.busted()) {
                    System.out.println("Dealer busted!");
                    break;
                }
            } else {
                System.out.println("Dealer stands.");
                break;
            }

        }
    }

    private boolean playerTurn(BlackJackPlayer player, CardDeck deck) {
        while (true) {
            String move = getPlayerMove();

            if (move.equals("hit")) {
                Card c = deck.deal();
                System.out.println("Your card was: " + c);
                player.addCard(c);
                System.out.println("Player's hand");
                System.out.println(player);

                if (player.busted()) {
                    return true;
                }
            } else {
                // If he didn't hit, player chose to stand
                //and it means the turn is over
                return false;
            }

        }
    }




    //    private boolean push(BlackJackPlayer player, DealerPlayer dealer)
//    {
//        return player.getValue() == dealer.getValue();
//    }
    private boolean playerWins(BlackJackPlayer player, Dealer dealer) {
        if (player.busted()) {
            return false;
        }

        if (dealer.busted()) {
            return true;
        }

        return player.getValue() > dealer.getValue(); //checks the values of the hands
    }

    private double findWinner(Dealer dealer, BlackJackPlayer player, int bet) {
        if (playerWins(player, dealer)) {

            System.out.println("Player wins!");

            if (player.hasBlackjack()) {
                return 1.5 * bet;
            }

            return bet;
        } else if (push(player, dealer)) {

            System.out.println("You push");
            return 0;
        } else {
            System.out.println("Dealer wins");
            return -bet;
        }
    }

    private boolean push(BlackJackPlayer player, Dealer dealer) {
        return false;
    }

    public void run() {
        double bankroll = Starting_bankroll;
        System.out.println("Starting bankroll: " + bankroll);

        while (true) {
            bankroll = playRound((int) bankroll);
            System.out.println("Would you like to play again? (Y/N)");
            String playAgain = scanner.nextLine();
            if (playAgain.equalsIgnoreCase("N")) {
                break;
            }
        }

        System.out.println("Thanks for playing!");
    }

    private double playRound(int bankroll) {
        return 0;
    }


    @Override
    public void remove(Player player) {

    }

//        @Override
//        public void run () throws InterruptedException {
//
//        }

    @Override
    public Player add(Player player) {
        return null;
    }

//    @Override
//    public Player removePlayer(Player player) {
//        return null;
//    }

    @Override
    public void startGame() {


    }
}