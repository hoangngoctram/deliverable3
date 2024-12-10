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
    private boolean playerCalledUno;
    private boolean AICalledUno;
    private String gameWinner;
    private String roundWinner;
    private int turnCount =1;
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
                
//            If turncount is even, player goes, if turncount is odd, AI goes

//             Player
            if(turnCount%2==0){
                pv.displayName(player.getName());
                draw(playerHand);
                pv.displayHand(playerHand);
                gv.displayTopOfDiscardPile(discard.getCards().get(discard.getCards().size()-1));
                
                int playerChoice = pv.selectCard(playerHand);
                
                while(true){
                    if(playerChoice != 99&&playerChoice !=999){
                        UnoCard selected = playerHand.getCards().get(playerChoice);
                        if(compareCard(selected)==true){
                            playCard(playerHand, selected);
                            break;}
                        if(compareCard(selected)==false){
                            pv.pickAgain();
                            playerChoice = pv.selectCard(playerHand);
                            continue;}
                    }
                    
//                    If player selected draw
                    if(playerChoice==999){
                        draw(playerHand);
                        if(playerChoice != 99&&playerChoice !=999){
                            UnoCard selected = playerHand.getCards().get(playerChoice);
                            if(compareCard(selected)==true){
                                playCard(playerHand, selected);
                                break;}
                            if(compareCard(selected)==false){
                                pv.pickAgain();
                                playerChoice = pv.selectCard(playerHand);
                                continue;}
                    }
                        break;
                    }
                    
//                    If player selected UNO
                    if(playerChoice==99){
                        pv.sayUno();
                        playerCalledUno=true;
                        if(playerChoice != 99&&playerChoice !=999){
                            UnoCard selected = playerHand.getCards().get(playerChoice);
                            if(compareCard(selected)==true){
                                playCard(playerHand, selected);
                                break;}
                            if(compareCard(selected)==false){
                                pv.pickAgain();
                                playerChoice = pv.selectCard(playerHand);
                                continue;}
                    }
                        
                    }
                }
                

                

                
                turnCount+=1;
            }
            
            
//            AI
            if(turnCount%2==1){
                pv.displayName(ai.getName());
                draw(aiHand);
                
//                Logic for choosing card automatically, plays the first valid card the for loop encounters
                boolean canPlay;
                UnoCard playableCard;
                for(int i = 0;i<aiHand.getCards().size()-1;i++){
                    if(compareCard(aiHand.getCards().get(i))==true){
                        canPlay=true;
                        playableCard=aiHand.getCards().get(i);
                    }
                    
                }
                
                turnCount+=1;
                
            }
            




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

    public boolean compareCard(UnoCard playedCard) {
        UnoCard topCard;
        
        topCard = discard.getCards().get(discard.getCards().size()-1);
        if(
            topCard.getColor().equals(playedCard.getColor())
            ||topCard.getColor().equals(NONE)
            ||topCard.getValue().equals(playedCard.getValue())
            ||playedCard.getColor().equals(NONE)
            ){
                return true;
            }
        else{return false;}
          

            
            
            
            
            
  
    }

    public void playCard(Hand hand, UnoCard playedCard){
        hand.getCards().remove(playedCard);
        discard.getCards().add(playedCard);
    }
    public void checkForVictory(Hand hand, boolean calledUno) {
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
