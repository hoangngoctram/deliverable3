/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author cenanguyen
 */
public class Hand extends GroupOfCards {
    public Hand(int size) {
        super(size);
    }

    public void addCard(Card card) {
        if (cards.size() < size) {
            cards.add(card);
        }
    }
}
