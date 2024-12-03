/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author cenanguyen
 */
public class test {
public static void main(String[] args) {
         Deck d = new Deck(108);
         d.buildDeck();
         int count =0;
         for(int i = 0;i<d.getCards().size();i++){
             count++;
             System.out.println(d.getCards().get(i).toString());
         }
         System.out.println(count);
    
     }
}
