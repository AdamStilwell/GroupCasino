package com.github.zipcodewilmington.utils;

import com.github.zipcodewilmington.casino.gameTools.Rank;
import com.github.zipcodewilmington.casino.gameTools.Suit;

public class Card {
    Suit suit;
    Rank rank;
    public Card(Suit s, Rank r){
        this.suit = s;
        this.rank = r;
    }

    public Suit getSuit(){
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }



}
