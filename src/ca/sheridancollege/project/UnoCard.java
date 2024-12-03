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
public class UnoCard extends Card {
  private Color color;
    private Value value;
    

//    Used to create a card with color and value, used in Deck class
    public UnoCard(Color color, Value value){
        this.color = color;
        this.value = value;
    }
    
//    Used to have a reference to getAllColors() and getAllValues, will be used to create a deck efficiently using for loops instead of manually creating cards one by one 108 times.
    public UnoCard(){}
   
//    These will be used to compare two card's color/value
    public Color getColor(){return color;}
    public Value getValue(){return value;}
    

//    To display hand and top card of discard pile to player
    @Override
    public String toString(){
        return "Color: "+color+", Value: "+value; 
    };
    
}