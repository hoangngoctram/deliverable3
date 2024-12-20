
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *@author Hoang Nguyen
 * @author jacob
 */
public class GameView {
    public void displayTopOfDiscardPile(Card card) {
        System.out.println("\nTop of discard pile: " + card);
    }

    public void displayRoundNumber(int round) {
        System.out.println("Round: " + round);
    }
    
    public void displayTurnCount(int turnCount){
        System.out.println("Turn "+turnCount);
    }
    
    public void displayFinalWinner(UnoPlayer winner){
        System.out.println("Winner: "+winner.getName());
    }
    
    public void spacer(){System.out.println("_____________________________________________");}

}

