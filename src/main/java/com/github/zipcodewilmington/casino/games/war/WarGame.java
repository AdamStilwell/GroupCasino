package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.Card;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.Collections;


public class WarGame extends Game {
    WarPlayer currentPlayer;
    WarPlayer dealerPlayer = new WarPlayer(null);
    StringBuilder sb = new StringBuilder();
    ArrayList<Card> potentialWinnings = new ArrayList<>();
    ArrayList<Card> newDeck = new ArrayList<>();

    IOConsole console = new IOConsole(AnsiColor.RED);


    @Override
    public void remove(Player player) {
        currentPlayer = null;
    }

    @Override
    public void run() throws InterruptedException {
        warMainMenu();
    }

    private void warMainMenu() {
        boolean isRunning = true;
        while(isRunning){
            sb.setLength(0);
            Integer choice = console.getIntegerInput(sb
                    .append("+-------------------------------+\n")
                    .append("|              WAR              |\n")
                    .append("+-------------------------------+\n")
                    .append("|  1. Start game                |\n")
                    .append("|  2. Check balance             |\n")
                    .append("|  3. Exit Game                 |\n")
                    .append("+-------------------------------+\n")
                    .append("SELECT A NUMBER: ")
                    .toString());

            switch(choice){
            case 1:
                startGame();
                break;
            case 2:
                console.println(currentPlayer.getPlayerAccount().getBalance().toString());
                break;
            case 3:
                remove(currentPlayer);
                isRunning = false;
                break;
            default:
                console.println("Pick a viable choice!");
                break;
            }
        }
    }

    @Override
    public Player add(Player player) {
        return this.currentPlayer = (WarPlayer) player;
    }


    @Override
    public void startGame() {
        boolean isPlaying = true;
        dealCards();
        while(isPlaying) {
            sb.setLength(0);
            Integer choice = console.getIntegerInput(sb
                    .append("+-------------------------------+\n")
                    .append("|              WAR              |\n")
                    .append("+-------------------------------+\n")
                    .append("|  1. Flip card                 |\n")
                    .append("|  2. Exit Game                 |\n")
                    .append("+-------------------------------+\n")
                    .append("SELECT A NUMBER: ")
                    .toString());

            switch(choice) {
                case 1:
                    potentialWinnings.add(currentPlayer.flipCard());
                    potentialWinnings.add(dealerPlayer.flipCard());
                    compareCards(currentPlayer.getCurrentCard(), dealerPlayer.getCurrentCard());
                    break;
                case 2:
                    remove(currentPlayer);
                    isPlaying = false;
                    break;
                default:
                    console.println("Pick a viable choice!");
                    break;
            }

        }
    }

    public void dealCards(){
        CardDeck cardDeck = new CardDeck();
        newDeck = cardDeck.createCardDeck();
        Collections.shuffle(newDeck);
        Card[] dealingDeck = newDeck.toArray(new Card[0]);
        for(int i = 0; i < dealingDeck.length; i++){
            if(i % 2 ==0){
                currentPlayer.addCardsToDeck(dealingDeck[i]);
            }else{
                dealerPlayer.addCardsToDeck(dealingDeck[i]);
            }
        }
    }
    public void compareCards(Card playerCard, Card dealerCard){
        if(playerCard.getRank().ordinal() > dealerCard.getRank().ordinal()){
            //player is winner, add the cards to their deck
            console.println(printWinnings(potentialWinnings, currentPlayer, currentPlayer.currentCard, dealerPlayer.currentCard));
            currentPlayer.addWinnings(potentialWinnings);
            console.println(printPlayerCardAmount());
            potentialWinnings.clear();
        }else if(playerCard.getRank().ordinal() < dealerCard.getRank().ordinal()){
            //dealer wins, add the cards to their deck
            console.println(printWinnings(potentialWinnings, dealerPlayer, currentPlayer.currentCard, dealerPlayer.currentCard));
            dealerPlayer.addWinnings(potentialWinnings);
            console.println(printPlayerCardAmount());
            potentialWinnings.clear();
        }else{
            //WarTime
            sb.append("\nIT'S WAR TIME!!!!!\n\nYou had a ")
                    .append(playerCard.getRank())
                    .append(" OF ")
                    .append(playerCard.getSuit())
                    .append("\nOpponent had a ")
                    .append(dealerCard.getRank())
                    .append(" OF ")
                    .append(dealerCard.getSuit())
                    .append("\n");
            console.println(sb.toString());
            warTime();
        }
    }

    public void warTime(){
        //deal as many cards as necessary
        for(int i = 0; i<3; i++) {
            potentialWinnings.add(currentPlayer.warTime());
            potentialWinnings.add(dealerPlayer.warTime());
        }
        //compare the last played card
        compareCards(currentPlayer.currentCard, dealerPlayer.currentCard);
    }

    public String printWinnings(ArrayList<Card> winnings, Player winner, Card card, Card card2){
        sb.setLength(0);
        if(currentPlayer.equals(winner)){
            sb.append("You won with a ")
                    .append(card.getRank())
                    .append(" OF ")
                    .append(card.getSuit());
        }else if(dealerPlayer.equals(winner)){
            sb.append("Opponent won with a ")
                    .append(card2.getRank())
                    .append(" OF ")
                    .append(card2.getSuit());
        }
        if(winnings.size()>2){
            sb.append(" and they won the ");
            for(Card c : winnings){
                  sb.append(c.getRank())
                  .append(" OF ")
                  .append(c.getSuit())
                  .append(", ");
            }
            sb.append("in the war.");
        }
        if(currentPlayer.equals(winner)){
            sb.append("\nOpponent lost with a " + card2.getRank() + " OF " + card2.getSuit());
        }else if(dealerPlayer.equals(winner)){
            sb.append("\nYou lost with a " + card.getRank() + " OF " + card.getSuit());
        }

        return sb.toString();
    }

    public String printPlayerCardAmount(){
        sb.setLength(0);
        sb.append("\n\nPlayer has ")
                .append(currentPlayer.getCardDeck().size())
                .append(" cards in their deck.\n");
        return sb.toString();
    }

    public WarPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(WarPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
