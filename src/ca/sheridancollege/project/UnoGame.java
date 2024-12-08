/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Color.NONE;
import java.util.Random;

/**
 *
 * @author jacob
 */
public class UnoGame {
    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        game.startRound();



        
        

        
        
    }
    private boolean calledUno;
    private String gameWinner;
    private String roundWinner;
    private int turnCount =0;
    private Deck d = new Deck(108);
    private boolean playerGoesFirst;
    private Hand playerHand = new Hand();
    private Hand aiHand = new Hand();
    
    private GameView gv = new GameView();
    private PlayerView pv = new PlayerView();
    private UnoPlayer player = new UnoPlayer(pv.enterName());
    private UnoPlayer ai = new UnoPlayer("AI");

    private DiscardPile discard = new DiscardPile(0);
    
 
//    public UnoGame(String name) {
//        super(name);
//    }

  
//Having two different turn orders to account for which player goes first and if the turn order changes using skip/reverse/wild4
    public void handleTurns(){
                while(true){

//            Turn order where the player went first
            if(playerGoesFirst=true){
                gv.displayTurnCount(turnCount);
                pv.displayName(player.getName());
                pv.displayHand(playerHand);
                if(discard.getCards().isEmpty()==false){gv.displayTopOfDiscardPile(discard.getCards().get(0));}
                UnoCard selected = playerHand.getCards().get(pv.selectCard(playerHand));
                
                
                turnCount+=1;
            }
      
            
            
            
            
//            Turn orderwhere the player went second




// break loop if round win condition is fulfilled
        }
    }
    public void changeTurnOrder() {
        // Logic to change turn order
    }

    public void skipTurn() {
       if(playerGoesFirst==true){playerGoesFirst=false;}
       
       if(playerGoesFirst==false){playerGoesFirst=true;}
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

    public void compareAndPlayCards(UnoCard playedCard, Hand hand) {
        UnoCard topCard;
        
        
        topCard = discard.getCards().get(discard.getCards().size()-1);
        if(
            topCard.getColor().equals(playedCard.getColor())
            |topCard.getColor().equals(NONE)
            ||topCard.getValue().equals(playedCard.getValue())
            ){
                hand.getCards().remove(playedCard);
                discard.getCards().add(playedCard);
            }
          

            
            
            
            
            
  
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
        UnoCard topCard = d.getCards().get(0);
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
