/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Color.*;
import static ca.sheridancollege.project.Value.*;

/**
 *
 * @author jacob
 */
public class Deck extends UnoGroupOfCards {
    public Deck(int size){super(size);}
    
    public void buildDeck(){
        GroupOfCards deck = new GroupOfCards(107);
        UnoCard uc = new UnoCard();
        
        for(Color color:uc.getAllColors()){
            for(Value value : uc.getAllValues()){
                if(value.equals(WILD)==false&&value.equals(WILD4)==false&&color.equals(NONE)==false){
//                    Since there are two of each card, the same card is added twice to the deck.
                    deck.getCards().add(new UnoCard(color, value));
                    deck.getCards().add(new UnoCard(color, value));
                   
                }
            }
        }
//WILD and WILD4 are added manually because the only combinations of NONE and WILD/WILD4 are with themselves, not with the other colors and values
        for(int i = 0;i<4;i++){deck.getCards().add(new UnoCard(NONE, WILD));} //Adds four copies of WILD
        for(int i = 0;i<4;i++){deck.getCards().add(new UnoCard(NONE, WILD4));} //Adds four copies of WILD4
        
//Zeros are added separately because there is only one zero per color. 
        for(Color color : uc.getAllColors()){
            if(color.equals(NONE)==false){
            deck.getCards().add(new UnoCard(color, ZERO));
            }
        }
      
        
        
        
    }
    
    
}
