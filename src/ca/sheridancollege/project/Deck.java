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

       
        Color colors[]=Color.values();
        Value values[]=Value.values();
        for(Color color: colors){
            for(Value value:values){
                if(value.equals(WILD)==false&&value.equals(WILD4)==false&&color.equals(NONE)==false&&value.equals(ZERO)==false){

                    getCards().add(new UnoCard(color, value));
                    getCards().add(new UnoCard(color, value));
                }
// There are 4 of each wild and wild4   
                if(color.equals(NONE)&&(value.equals(WILD)||value.equals(WILD4))){
                    getCards().add(new UnoCard(color, value));
                    getCards().add(new UnoCard(color, value));
                    getCards().add(new UnoCard(color, value));
                    getCards().add(new UnoCard(color, value));
                }
// There are one zero for each color
                if(color.equals(NONE)==false&&value.equals(ZERO)){
                                        getCards().add(new UnoCard(color, value));
                }
            }
        }

        
    }
    
    
}
