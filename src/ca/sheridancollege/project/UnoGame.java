/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Color.*;
import static ca.sheridancollege.project.Value.*;
import java.util.Random;

/**
 *@author Hoang Nguyen
 * @author jacob
 */
public class UnoGame {
    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        game.pv.enterName();
        
        while(true){
            game.startRound();
            game.handleTurns();
            game.endRound(game.losers_hand, game.roundWinner);
            if(game.player_points>=60){
                game.gv.displayFinalWinner(game.player);
                break;
            }
            
            if(game.ai_points>=60){
                game.gv.displayFinalWinner(game.ai);
                break;
            }
        }}
    //    Used to check win condition for the round
    private int player_turnCount=0;
    private int ai_turnCount=0;
    private boolean playerCalledUno;
    private boolean AICalledUno;
    private int whenPlayerSaidUNO;
    private int whenAISaidUNO;
    private String gameWinner;
//    
//    Used for calculating points
    private UnoPlayer roundWinner;
    private Hand losers_hand;
    
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
    
    private int player_points=0;
    private int ai_points=0;
    
    private int roundNumber=0;
//    public UnoGame(String name) {
//        super(name);
//    }

  
//Having two different turn orders to account for which player goes first and if the turn order changes using skip/reverse/wild4
    public void handleTurns(){
        outerLoop: 
            while(true){
                
//            If turncount is even, player goes, if turncount is odd, AI goes

//             Players turn
            if(turnCount%2==0){
                if(playerHand.getCards().size()>1){playerCalledUno=false;}
                gv.spacer();
                turnCount+=1;
                player_turnCount+=1;
                pv.displayName(player.getName());
                pv.displayHand(playerHand);
                gv.displayTopOfDiscardPile(discard.getCards().get(discard.getCards().size()-1));
                
                int playerChoice = pv.selectCard(playerHand);
                
//                If player chooses a card and not draw or uno
                while(true){
                    if(playerChoice != 99&&playerChoice !=999){
                        UnoCard selected = playerHand.getCards().get(playerChoice);
                        if(compareCard(selected)==true){
                            playCard(playerHand, selected);
                            if(checkForVictory(playerHand, playerCalledUno,player_turnCount,whenPlayerSaidUNO,player)==true){
                                break outerLoop;
                            }
                            specialCard(selected, aiHand, player);

                            break;}
                        if(compareCard(selected)==false){
                            pv.pickAgain();
                            playerChoice = pv.selectCard(playerHand);
                            continue;}
                    }
                    
//                    If player selected draw, autoplays the card if valid
                    if(playerChoice==999){
                        draw(playerHand);
                        boolean player_canPlay = false;
                        UnoCard player_playableCard = null;
                        for(int i = 0;i<playerHand.getCards().size()-1;i++){
                            if(compareCard(playerHand.getCards().get(i))==true){
                                player_canPlay=true;
                                player_playableCard=playerHand.getCards().get(i);
                            }

                        }
                        if(player_canPlay==true){
                            playCard(playerHand, player_playableCard);
                            if(checkForVictory(playerHand, playerCalledUno,player_turnCount,whenPlayerSaidUNO,player)==true){
                                break outerLoop;
                            }
                            specialCard(player_playableCard, aiHand, player);
                            player_canPlay = false;
                            break;
                        }
                
                        break;
                    }
                    
//                  player selects uno when they have 2 cards left, then they play their card
                    if(playerChoice==99){

                        playerChoice = pv.selectCard(playerHand);
                        if(playerChoice != 99&&playerChoice !=999){
                            UnoCard selected = playerHand.getCards().get(playerChoice);
                            if(compareCard(selected)==true){
                                playCard(playerHand, selected);
                                if(checkForVictory(playerHand, playerCalledUno,player_turnCount,whenPlayerSaidUNO,player)==true){
                                    break outerLoop;
                                }
                                specialCard(selected, aiHand, player);

                                playerCalledUno=true;
                                whenPlayerSaidUNO=player_turnCount;
                                pv.sayUno();
                                if(playerHand.getCards().size()<=1){playerCalledUno=true;}
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
                gv.spacer();
                if(aiHand.getCards().size()>1){playerCalledUno=false;}
                pv.displayName(ai.getName());
                pv.aiCardsInHand(aiHand);
                turnCount+=1;
                ai_turnCount+=1;
                
                
//                Logic for choosing card automatically
                boolean canPlay=false;
                UnoCard playableCard = null;
                for(int i = 0;i<aiHand.getCards().size()-1;i++){
                    if(compareCard(aiHand.getCards().get(i))==true){
                        canPlay=true;
                        playableCard=aiHand.getCards().get(i);
                    }
                    
                }
                if(canPlay==true){
                    playCard(aiHand, playableCard);
                    pv.aiAction(playableCard);
                    if(checkForVictory(aiHand, AICalledUno,ai_turnCount,whenAISaidUNO,ai)){
                        break;
                    }

                    specialCard(playableCard, playerHand, ai);
                    if(aiHand.getCards().size()==1){
                        pv.sayUno();
                        AICalledUno=true;
                        whenAISaidUNO=ai_turnCount;
                    }

                }
                
                if(canPlay==false){
                    draw(aiHand);
                    pv.aiDraw();
                    for(int i = 0;i<aiHand.getCards().size()-1;i++){
                        if(compareCard(aiHand.getCards().get(i))==true){
                            canPlay=true;
                            playableCard=aiHand.getCards().get(i);
                    }
                    
                }
                    if(canPlay==true){
                        playCard(aiHand, playableCard);
                        pv.aiAction(playableCard);
                        if(checkForVictory(aiHand, AICalledUno,ai_turnCount,whenAISaidUNO,ai)){
                            break;
                    }
                        specialCard(playableCard, playerHand, ai);
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
    

    
    public void specialCard(UnoCard playedCard, Hand opponent_hand, UnoPlayer activePlayer) {
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
        
        if(playedCard.getValue().equals(WILD)||playedCard.getValue().equals(WILD4)){

            if(activePlayer.equals(player)){playedCard.setColor(pv.pickColor());}
            
//            If AI plays wild or wild draw 4, sets color to a color that exists in their hand
            if(activePlayer.equals(ai)){
                for(int i =0;i<aiHand.getCards().size()-1;i++){
                    if(aiHand.getCards().get(i).getColor().equals(RED)||aiHand.getCards().get(i).getColor().equals(BLUE)||aiHand.getCards().get(i).getColor().equals(GREEN)||aiHand.getCards().get(i).getColor().equals(YELLOW)){
                        playedCard.setColor(aiHand.getCards().get(i).getColor());
                    }
                }
                
            }
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
        
        
        for(int i =0;i<2;i++){
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
            roundWinner = player;
            if(roundWinner.equals(this.player)){losers_hand=aiHand;}
            if(roundWinner.equals(ai)){losers_hand=playerHand;}
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
        roundNumber+=1;
        gv.displayRoundNumber(roundNumber);
        Random rand = new Random();
        playerGoesFirst = rand.nextBoolean();
        
//        If the player goes first, turnCount will be set to 0, will be used in handleTurns()
        if(playerGoesFirst==true){
            turnCount=0;
            pv.playerGoesFirstMessage();
        }
        
        if(playerGoesFirst==false){
            turnCount=1;
            pv.playerGoesSecondMessage();
        }
        
    }

//    Calculate points
    public void endRound(Hand losers_hand, UnoPlayer player) {
        for(int i =0;i<losers_hand.getCards().size();i++){
            if(losers_hand.getCards().get(i).getValue().equals(ONE)){player.setScore(player.getScore()+1);}
            if(losers_hand.getCards().get(i).getValue().equals(TWO)){player.setScore(player.getScore()+2);}
            if(losers_hand.getCards().get(i).getValue().equals(THREE)){player.setScore(player.getScore()+3);}
            if(losers_hand.getCards().get(i).getValue().equals(FOUR)){player.setScore(player.getScore()+4);}
            if(losers_hand.getCards().get(i).getValue().equals(FIVE)){player.setScore(player.getScore()+5);}
            if(losers_hand.getCards().get(i).getValue().equals(SIX)){player.setScore(player.getScore()+6);}
            if(losers_hand.getCards().get(i).getValue().equals(SEVEN)){player.setScore(player.getScore()+7);}
            if(losers_hand.getCards().get(i).getValue().equals(EIGHT)){player.setScore(player.getScore()+8);}
            if(losers_hand.getCards().get(i).getValue().equals(NINE)){player.setScore(player.getScore()+9);}
            if(losers_hand.getCards().get(i).getValue().equals(DRAW2)||losers_hand.getCards().get(i).getValue().equals(SKIP)||losers_hand.getCards().get(i).getValue().equals(REVERSE)){player.setScore(player.getScore()+20);}
            if(losers_hand.getCards().get(i).getValue().equals(WILD)||losers_hand.getCards().get(i).getValue().equals(WILD4)){player.setScore(player.getScore()+50);}
        }
        gv.spacer();
        pv.displayScore(this.player);
        pv.displayScore(ai);
        
    }
    
    public void draw(Hand hand){
        UnoCard topCard = d.getCards().get(d.getCards().size()-1);
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
            

        
