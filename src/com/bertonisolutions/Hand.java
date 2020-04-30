package com.bertonisolutions;

import java.util.Arrays;
import java.util.Collections;

public class Hand implements Comparable<Hand> {
    private Card[] hand;

    public Hand(String handValue) {
        this.setHand(handValue);
    }

    public void setHand(String handValue) {
        if (handValue.length() == 10) {
            Card[] localHand = new Card[5];
            for (int i = 0; i < 10; i += 2) {
                localHand[i / 2] = new Card("" + handValue.charAt(i) + handValue.charAt(i + 1));
            }
            this.hand = localHand;
            Arrays.sort(this.hand);
        }
    }

    @Override
    public int compareTo(Hand other) {
        int[] a = this.handValue();
        int[] b = other.handValue();
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] > b[i]) return 1;
            if (a[i] < b[i]) return -1;
        }
        return 0;
    }

    public int[] handValue() {
        int[] value = new int[6];
        for (int i = 0; i < 5; i++) {
            value[i + 1] = this.hand[4 - i].getValue();
        }
        value[0] = 0;

        //ONE PAIR
        for (int i = 0; i < 4; i++) {
            if (this.hand[i].getValue() == this.hand[i + 1].getValue()) {
                Integer[] comp = new Integer[3];
                for (int j = 0; j < 3; j++) {
                    comp[j] = this.hand[(i + 2 + j) % 5].getValue();
                }
                Arrays.sort(comp, Collections.reverseOrder());
                value = new int[5];
                for (int j = 0; j < 3; j++) {
                    value[j + 2] = comp[j];
                }
                value[0] = 1;
                value[1] = this.hand[i].getValue();
            }
        }

        //TWO PAIRS
        if (this.hand[1].getValue() == this.hand[2].getValue() && this.hand[3] == this.hand[4]) {
            value = new int[]{2, this.hand[1].getValue(), this.hand[3].getValue(), this.hand[0].getValue()};
        }
        if (this.hand[0].getValue() == this.hand[1].getValue()) {
            if (this.hand[2].getValue() == this.hand[4].getValue()) {
                value = new int[]{2, this.hand[0].getValue(), this.hand[3].getValue(), this.hand[2].getValue()};
            }
            if (this.hand[2].getValue() == this.hand[4].getValue()) {
                value = new int[]{2, this.hand[0].getValue(), this.hand[2].getValue(), this.hand[3].getValue()};
            }
            if (this.hand[2].getValue() == this.hand[3].getValue()) {
                value = new int[]{2, this.hand[0].getValue(), this.hand[2].getValue(), this.hand[4].getValue()};
            }
        }

        //TREE OF A KIND
        for (int i = 0; i < 3; i++) {
            if (this.hand[i].getValue() == this.hand[i + 1].getValue() &&
                    this.hand[i].getValue() == this.hand[i + 2].getValue()) {
                Integer[] comp = {this.hand[(i + 4) % 5].getValue(), this.hand[(i + 3) % 5].getValue()};
                Arrays.sort(comp, Collections.reverseOrder());
                value = new int[]{3, this.hand[i].getValue(), comp[0], comp[1]};
            }
        }

        //STRAIGHT
        boolean consecutive = true;
        for (int i = 0; i < 4; i++) {
            if (this.hand[i].getValue() != this.hand[i + 1].getValue() - 1) {
                consecutive = false;
                break;
            }
        }
        if (consecutive) {
            value = new int[]{4, this.hand[4].getValue()};
        }

        //FLUSH
        boolean sameSuit = true;
        for (int i = 0; i < 4; i++) {
            if (this.hand[i].getSuit() != this.hand[i + 1].getSuit()) {
                sameSuit = false;
                break;
            }
        }
        if (sameSuit) {
            value = new int[6];
            for (int i = 0; i < 5; i++) {
                value[i + 1] = this.hand[4 - i].getValue();
            }
            value[0] = 5;
        }

        //FULL HOUSE
        if (this.hand[0].getValue() == this.hand[2].getValue() && this.hand[2].getValue() != this.hand[3].getValue() &&
                this.hand[3].getValue() == this.hand[4].getValue()) {
            value = new int[]{6, this.hand[0].getValue()};
        }
        if (this.hand[0].getValue() == this.hand[1].getValue() && this.hand[1].getValue() != this.hand[2].getValue() &&
                this.hand[2].getValue() == this.hand[4].getValue()) {
            value = new int[]{6, this.hand[4].getValue()};
        }

        //FOUR OF A KIND
        for (int i = 0; i < 2; i++) {
            if (this.hand[i].getValue() == this.hand[i + 3].getValue()) {
                value = new int[]{7, this.hand[i].getValue()};
            }
        }

        //STRAIGHT FLUSH
        boolean consecutiveSameSuit = true;
        for (int i = 0; i < 4; i++) {
            if (this.hand[i].getSuit() != this.hand[i + 1].getSuit() ||
                    this.hand[i].getValue() != this.hand[i + 1].getValue() - 1) {
                consecutiveSameSuit = false;
                break;
            }
        }
        if (consecutiveSameSuit) {
            value = new int[]{8, this.hand[4].getValue()};
        }

        //ROYAL FLUSH
        boolean isRoyal = sameSuit;
        for (int i = 0; i < 5; i++) {
            if (this.hand[i].getValue() != i + 8) {
                isRoyal = false;
                break;
            }
        }
        if (isRoyal) {
            value = new int[]{9};
        }

        return value;
    }
}
