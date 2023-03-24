package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.utils.Card;

import java.util.*;

public class WarPlayer extends Player {
    Card currentCard;
    Queue<Card> cardDeck = new LinkedList<>();

    public WarPlayer(CasinoAccount playerAccount) {
        super(playerAccount);
    }


    public Card getCurrentCard() {
        return this.currentCard;

    }

    public void setCurrentCard(Card card){
        currentCard = card;
    }

    public Card flipCard(){
        setCurrentCard(cardDeck.remove());
        return getCurrentCard();
    }

    public void addCardsToDeck(Card card){
        this.cardDeck.add(card);
    }

    public void addWinnings(ArrayList<Card> winnings){
        this.cardDeck.addAll(winnings);
    }

    public Card warTime() {
            if(!cardDeck.isEmpty()){
                flipCard();
            }else{
                return getCurrentCard();
            }
        return getCurrentCard();
    }
    public Queue<Card> getCardDeck(){
        return cardDeck;
    }


}
