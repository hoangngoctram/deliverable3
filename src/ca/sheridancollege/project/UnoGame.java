/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Hoang Nguyen
 * @author Jacob Cruz
 */
public class UnoGame extends Game {
    private boolean calledUno;
    private String gameWinner;
    private String roundWinner;
    private Deck deck;
    private Hand hand;
    private ArrayList<UnoPlayer> players;

    public UnoGame(String name) {
        super(name);
        this.hand = new Hand(7); // Initial hand size
    }

    public void startRound() {
        // Logic for starting the round
    }

    public void play(Card card) {
        // Logic for playing a card
    }

    public void changeTurnOrder() {
        // Logic for changing turn order
    }

    public void checkForVictory() {
        // Logic for checking if someone won
    }

    @Override
    public void declareWinner() {
        System.out.println("Winner: " + gameWinner);
    }

    @Override
    public void play() {
        startRound();
    }
}
