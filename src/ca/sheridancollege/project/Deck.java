/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Color.*;
import static ca.sheridancollege.project.Value.*;

/**
 *@author Hoang Nguyen
 * @author jacob 
 */
public class Deck extends GroupOfCards {

   public Deck() {
        super(108);  // Uno has 108 cards.
        buildDeck();
    }

    public void buildDeck() {
        for (Color color : Color.values()) {
            if (color != Color.NONE) {
                for (Value value : Value.values()) {
                    if (value != Value.WILD && value != Value.WILD4) {
                        addCard(new UnoCard(color, value));
                    }
                }
            }
        }

        // Adding Wild and Wild Draw Four cards.
        for (int i = 0; i < 4; i++) {
            addCard(new UnoCard(Color.NONE, Value.WILD));
            addCard(new UnoCard(Color.NONE, Value.WILD4));
        }
        shuffle();
    }
}