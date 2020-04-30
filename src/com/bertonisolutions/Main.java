package com.bertonisolutions;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //THIS TAKES SOME TIME TO PREPROCESS
        PokerStat pokerStat = new PokerStat();

        System.out.println("Preloaded data!");
        int n = Integer.parseInt(scan.nextLine());
        int countPlayer1 = 0;
        int countPlayer2 = 0;
        int countTie = 0;
        System.out.println("---PLAYER 1---|---PLAYER 2---");
        while (n-- > 0) {
            Hand p1 = new Hand(String.join("", scan.nextLine().split("\\s+")));
            Hand p2 = new Hand(String.join("", scan.nextLine().split("\\s+")));
            String probPlayer1 = String.format("%13.9f%%", pokerStat.winProbability(p1));
            String probPlayer2 = String.format("%13.9f%%", pokerStat.winProbability(p2));
            System.out.println(probPlayer1 + "|" + probPlayer2);

            if (p1.compareTo(p2) > 0) countPlayer1++;
            else if (p2.compareTo(p1) > 0) countPlayer2++;
            else countTie++;
        }
        System.out.println("Player 1 Wins: " + countPlayer1);
        System.out.println("Player 2 Wins: " + countPlayer2);
        System.out.println("Neither Wins: " + countTie);
    }
}
