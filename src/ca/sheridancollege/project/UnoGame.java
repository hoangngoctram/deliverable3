/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;



/**
 *
 * @author Hoang Nguyen
 * @author Jacob Cruz
 */
public class UnoGame extends Game {
        public static void main(String[] args) {
        UnoPlayer player = new UnoPlayer("Alice", 0);   // Create a player
        Hand hand = new Hand();                         // Create an empty hand
        hand.addCard(new UnoCard(Color.RED, Value.THREE));
        hand.addCard(new UnoCard(Color.BLUE, Value.FIVE));
    }
    private boolean calledUno;
    private String gameWinner;
    private String roundWinner;
    private int turnCount;

    public UnoGame(String name) {
        super(name);
    }

    public void changeTurnOrder() {
        // Logic to change turn order
    }

    public void skipTurn() {
        // Logic to skip turn
    }

    public void play(Card card) {
        System.out.println("Playing card: " + card);
    }

    public void compareCards(Card card1, Card card2) {
        // Logic to compare cards
    }

    public void checkForVictory() {
        // Logic to check for victory
    }

    public void discardToDeck() {
        // Logic to move discard pile to deck
    }

    public void startRound() {
        System.out.println("Starting a new round.");
    }

    public void endRound() {
        System.out.println("Ending the round.");
    }

    @Override
    public void declareWinner() {
        System.out.println("The winner is: " + gameWinner);
    }

    @Override
    public void play() {
        System.out.println("Starting Uno game...");
    }}


