package com.bertonisolutions;

import java.util.HashMap;
import java.util.Map;

public class Card implements Comparable<Card> {
    private int value;
    private char suit;
    public static final Map<Character, Integer> VALUES = new HashMap<>() {{
        put('2', 0);
        put('3', 1);
        put('4', 2);
        put('5', 3);
        put('6', 4);
        put('7', 5);
        put('8', 6);
        put('9', 7);
        put('T', 8);
        put('J', 9);
        put('Q', 10);
        put('K', 11);
        put('A', 12);
    }};
    public static final Map<Character, Character> SUITS = new HashMap<>() {{
        put('S', 'S');
        put('D', 'D');
        put('H', 'H');
        put('C', 'C');
    }};

    public Card(String card) {
        setCard(card);
    }

    public void setCard(String card) {
        if (card.length() == 2) {
            this.value = VALUES.getOrDefault(card.charAt(0), -1);
            this.suit = SUITS.getOrDefault(card.charAt(1), '-');
        } else {
            this.value = -1;
            this.suit = '-';
        }
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.getValue(), other.getValue());
    }

    public int getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }

    public boolean isValid() {
        return this.value != -1 && this.suit != '-';
    }
}
