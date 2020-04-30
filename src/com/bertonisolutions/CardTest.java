package com.bertonisolutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void setCard() {
        Card card;
        card = new Card("AS");
        assertEquals(card.getValue(), 12);
        assertEquals(card.getSuit(), 'S');
        card.setCard("2D");
        assertEquals(card.getValue(), 0);
        assertEquals(card.getSuit(), 'D');
    }

    @Test
    void newCard() {
        Card card1 = new Card("1A");
        assertFalse(card1.isValid());

        Card card2 = new Card("A");
        assertFalse(card2.isValid());

        Card card3 = new Card("SSS");
        assertFalse(card3.isValid());

        Card card4 = new Card("S1");
        assertFalse(card4.isValid());

        String values = "23456789TJQKA";
        String suits = "SDHC";
        for (char value : values.toCharArray()) {
            for (char suit : suits.toCharArray()) {
                Card card = new Card("" + value + suit);
                assertTrue(card.isValid());
            }
        }
    }
}