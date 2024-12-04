/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author HoangNguyen
 * @author Jacob Cruz
 */
public class PlayerView {
 public void displayHand(Hand hand) {
        System.out.println("Player's hand: " + hand.getSize() + " cards.");
    }

    public void displayName(String name) {
        System.out.println("Player: " + name);
    }

    public void selectCard() {
        // Logic for selecting a card
    }

    public void sayUno() {
        System.out.println("UNO!");
    }

    public void displayScore(int score) {
        System.out.println("Score: " + score);
    }
}