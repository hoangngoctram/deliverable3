/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author cenanguyen
 */
public class Deck extends GroupOfCards  {
 public Deck() {
        super(108); // Standard Uno deck size
        buildDeck();
    }
public void buildDeck() {
    for (Color color : Color.values()) {
        if (color != Color.NONE) {
            for (Value value : Value.values()) {
                getCards().add(new UnoCard(color, value));
            }
        }
    }
    shuffle();
}
      
}
