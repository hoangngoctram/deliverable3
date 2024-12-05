/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author jacob
 */
public class UnoPlayer extends Player{
    private int score;
    
    public UnoPlayer(String name) {
        super(name);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

 

}
