/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Random;

/**
 *
 * @author jacob
 */
public class UnoGame {
    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        game.startRound();
        GameView gv = new GameView();
        PlayerView pv = new PlayerView();

        UnoPlayer player = new UnoPlayer(pv.enterName());
        UnoPlayer ai = new UnoPlayer("AI");
        
        
        while(true){
//            Turn order where the player went first
            if(game.playerGoesFirst=true){
                gv.displayTurnCount(game.turnCount);
                pv.displayName(player.getName());
                pv.displayHand(game.playerHand);
                Card selected = game.playerHand.getCards().get(pv.selectCard());
                
                
                game.turnCount+=1;
            }
      
            
//            Turn orderwhere the player went second




// break loop if round win condition is fulfilled
        }
        
        
    }
    private boolean calledUno;
    private String gameWinner;
    private String roundWinner;
    private int turnCount =0;
    private Deck d = new Deck(108);
    private boolean playerGoesFirst;
    private Hand playerHand = new Hand();
        
    private Hand aiHand = new Hand();

    
 
//    public UnoGame(String name) {
//        super(name);
//    }

    public void changeTurnOrder() {
        // Logic to change turn order
    }

    public void skipTurn() {
        // Logic to skip turn
    }


    public void play() {
        d.getCards().clear();
        d.buildDeck();
        d.shuffle();
        

        
        
        for(int i =0;i<8;i++){
            draw(playerHand);
            draw(aiHand);
        }
        
    }

    public void compareCards(Card card1, Card card2) {
        // Logic to compare cards
    }

    public void checkForVictory(Hand hand) {
        if(hand.getCards().isEmpty()||calledUno==true){
            endRound();
        }
    }

    public void discardToDeck() {
        // Logic to move discard pile to deck
    }

    public void startRound() {
        play();
        Random rand = new Random();
        playerGoesFirst = rand.nextBoolean();
        
        
    }

    public void endRound() {
        System.out.println("Ending the round.");
    }
    
    public void draw(Hand hand){
        Card topCard = d.getCards().get(0);
        d.getCards().remove(topCard);
        hand.getCards().add(topCard);
    }

//    @Override
//    public void declareWinner() {
//        System.out.println("The winner is: " + gameWinner);
//    }
//
//    @Override
//    public void play() {
//        System.out.println("Starting Uno game...");
//    }
}
