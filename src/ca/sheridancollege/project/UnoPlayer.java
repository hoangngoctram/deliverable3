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
public class UnoPlayer extends Player {
    private int score;

    public UnoPlayer(String name, int score) {
        super(name);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void sayUno() {
        System.out.println(getName() + " says UNO!");
    }
}