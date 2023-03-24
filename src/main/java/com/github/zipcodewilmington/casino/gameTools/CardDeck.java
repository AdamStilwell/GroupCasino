package com.github.zipcodewilmington.casino.gameTools;

import com.github.zipcodewilmington.utils.Card;

import java.util.ArrayList;

public class CardDeck {
    ArrayList<Card> cardDeck = new ArrayList<>();
    public ArrayList<Card> createCardDeck(){
        for(Suit s: Suit.values()){
            for(Rank r: Rank.values()){
                Card card = new Card(s,r);
                cardDeck.add(card);
            }
        }
        return cardDeck;
    }

    public Card deal() {
        return null;
    }
}
