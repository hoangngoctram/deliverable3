/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Color.NONE;
import static ca.sheridancollege.project.Value.*;
import java.util.Random;

/**
 *
 * @author jacob
 */
public class UnoGame {
    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        while(true){
            
            game.startRound();
            game.handleTurns();
        }
            

        

        
        

        
        
    }
    //    Used to check win condition for the round
    private int player_turnCount=0;
    private int ai_turnCount=0;
    private boolean playerCalledUno;
    private boolean AICalledUno;
    private int whenPlayerSaidUNO;
    private int whenAISaidUNO;
    private String gameWinner;
    private String roundWinner;
    private int turnCount;
    

    
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
                turnCount+=1;
                player_turnCount+=1;
                pv.displayName(player.getName());
                draw(playerHand);
                pv.displayHand(playerHand);
                gv.displayTopOfDiscardPile(discard.getCards().get(discard.getCards().size()-1));
                
                int playerChoice = pv.selectCard(playerHand);
                
//                If player chooses a card and not draw or uno
                while(true){
                    if(playerChoice != 99&&playerChoice !=999){
                        UnoCard selected = playerHand.getCards().get(playerChoice);
                        if(compareCard(selected)==true){
                            playCard(playerHand, selected);
                            
                            specialCard(selected, aiHand);

                            break;}
                        if(compareCard(selected)==false){
                            pv.pickAgain();
                            playerChoice = pv.selectCard(playerHand);
                            continue;}
                    }
                    
//                    If player selected draw, autoplays the card if valid
                    if(playerChoice==999){
                        draw(playerHand);
                        boolean player_canPlay;
                        UnoCard player_playableCard = null;
                        for(int i = 0;i<playerHand.getCards().size()-1;i++){
                            if(compareCard(playerHand.getCards().get(i))==true){
                                player_canPlay=true;
                                player_playableCard=playerHand.getCards().get(i);
                            }

                        }
                        if(player_canPlay=true){
                            playCard(playerHand, player_playableCard);
                            specialCard(player_playableCard, aiHand);

                        }
                
                        break;
                    }
                    
//                    If player selected UNO
                    if(playerChoice==99){
                        pv.sayUno();
                        playerCalledUno=true;
                        playerChoice = pv.selectCard(playerHand);
                        if(playerChoice != 99&&playerChoice !=999){
                            UnoCard selected = playerHand.getCards().get(playerChoice);
                            if(compareCard(selected)==true){
                                playCard(playerHand, selected);
                                specialCard(selected, aiHand);

                                playerCalledUno=true;
                                whenPlayerSaidUNO=player_turnCount;
                                break;
                            }
                            if(compareCard(selected)==false){
                                pv.pickAgain();
                                playerChoice = pv.selectCard(playerHand);
                                continue;}
                    }
                        
                    }
                }
                

                

                
                
            }
            
            
//            AI turn
            if(turnCount%2==1){      
                turnCount+=1;
                ai_turnCount+=1;
                pv.displayName(ai.getName());
                draw(aiHand);
                
//                Logic for choosing card automatically
                boolean canPlay;
                UnoCard playableCard = null;
                for(int i = 0;i<aiHand.getCards().size()-1;i++){
                    if(compareCard(aiHand.getCards().get(i))==true){
                        canPlay=true;
                        playableCard=aiHand.getCards().get(i);
                    }
                    
                }
                if(canPlay=true){
                    playCard(aiHand, playableCard);
                    if(checkForVictory(aiHand, AICalledUno,ai_turnCount,whenAISaidUNO,ai)){
                        break;
                    }

                    specialCard(playableCard, playerHand);
                    if(aiHand.getCards().size()==0){
                        pv.sayUno();
                        AICalledUno=true;
                        whenAISaidUNO=ai_turnCount;
                    }

                }
                
                if(canPlay=false){
                    draw(aiHand);
                    for(int i = 0;i<aiHand.getCards().size()-1;i++){
                        if(compareCard(aiHand.getCards().get(i))==true){
                            canPlay=true;
                            playableCard=aiHand.getCards().get(i);
                    }
                    
                }
                    if(canPlay=true){
                        playCard(aiHand, playableCard);
                        specialCard(playableCard, playerHand);
                        if(aiHand.getCards().size()==0){
                            pv.sayUno();
                            AICalledUno=true;
                        }
                    }
                }
   
//                reset canPlay variable
                canPlay=false;
                
            }
            




// break loop if round win condition is fulfilled
        }
    }
    
    public void changeTurnOrder() {
        // Logic to change turn order
    }
    
    public void specialCard(UnoCard playedCard, Hand opponent_hand) {
        //Checks if the played card is skip/reverse/wild4, count+=1 is used with the count+=1 in handleTurns(), meaning the modulus does not change, this skips the oppposing players turn.
        if(playedCard.getValue().equals(SKIP)||playedCard.getValue().equals(REVERSE)||playedCard.getValue().equals(WILD4)){
            turnCount+=1;
        }
        
        if(playedCard.getValue().equals(DRAW2)){
            draw(opponent_hand);
            draw(opponent_hand);
        }
        
        if(playedCard.getValue().equals(WILD4)){
            draw(opponent_hand);
            draw(opponent_hand);
            draw(opponent_hand);
            draw(opponent_hand);
        }
        
        if(playedCard.equals(WILD)||playedCard.equals(WILD4)){
            
            playedCard.setColor(pv.pickColor());
        }
    }
    
    


    public void play() {
        d.getCards().clear();
        discard.getCards().clear();
        playerHand.getCards().clear();
        aiHand.getCards().clear();
        
        d.buildDeck();
        d.shuffle();
        
        UnoCard card = d.getCards().get(d.getCards().size()-1);
        d.getCards().remove(card);
        discard.getCards().add(card);
        
        
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
    public boolean checkForVictory(Hand hand, boolean calledUno, int individual_turnCount, int when_UNO_was_called, UnoPlayer player) {
//        Condition: if hand is empty, uno was called, uno was called the turn before
        if(hand.getCards().isEmpty()&&calledUno==true&&when_UNO_was_called==individual_turnCount-1){
            roundWinner = player.getName();
            return true;
        }
        return false;
    }

//    If deck runs out of cards
    public void discardToDeck() {
        
        if(d.getCards().size()==0){
            for(int i =0;i<discard.getCards().size()-1;i++){
                UnoCard card = discard.getCards().get(i);
                d.getCards().add(card);
                discard.getCards().remove(card);
            }
            
            d.shuffle();
            UnoCard card = d.getCards().get(d.getCards().size()-1);
            d.getCards().remove(card);
            discard.getCards().add(card);
            
            
        }
    }

    public void startRound() {
        play();
        Random rand = new Random();
        playerGoesFirst = rand.nextBoolean();
        
//        If the player goes first, turnCount will be set to 0, will be used in handleTurns()
        if(playerGoesFirst==true){
            turnCount=0;
        }
        
        if(playerGoesFirst==false){
            turnCount=1;
        }
        
    }

    public void endRound() {
        System.out.println("Ending the round.");
    }
    
    public void draw(Hand hand){
        UnoCard topCard = d.getCards().get(0);
        d.getCards().remove(topCard);
        hand.getCards().add(topCard);
        discardToDeck();

        
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
