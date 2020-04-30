package com.bertonisolutions;

import java.util.ArrayList;
import java.util.List;

public class PokerStat {
    private static final List<Hand> allHands = new ArrayList<>();
    private static final List<String> deck = new ArrayList<>();

    static {
        for (char value : "23456789TJQKA".toCharArray()) {
            for (char suit : "SDHC".toCharArray()) {
                deck.add("" + value + suit);
            }
        }
        fillAllHands(0, "");
        allHands.sort(Hand::compareTo);
    }

    private static void fillAllHands(int deckPos, String hand) {
        if (hand.length() >= 10) {
            allHands.add(new Hand(hand));
        } else if (deckPos < deck.size()) {
            for (int i = deckPos; i < deck.size(); i++) {
                fillAllHands(i + 1, hand + deck.get(i));
            }
        }
    }

    public double winProbability(Hand hand) {
        int l = 1, r = allHands.size();
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (allHands.get(m).compareTo(hand) >= 0) r = m;
            else l = m;
        }
        return 100.0 * r / allHands.size();
    }
}
