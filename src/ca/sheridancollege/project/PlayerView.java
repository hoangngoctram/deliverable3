/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Color.*;
import java.util.Scanner;

/**
 *@author HoangNguyen
 * @author jacob
 */
public class PlayerView {
    public void displayHand(Hand hand) {
        for(int i =0; i<hand.getCards().size();i++){
            System.out.println("["+i+"]"+hand.getCards().get(i).toString());
        }
        System.out.print("[99]UNO");
        System.out.print("[999]DRAW 1");
        
        
    }

    public void displayName(String name) {
        System.out.println("Player: " + name);
    }

    public int selectCard(Hand hand) {
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose card to play or say UNO");
                int select = sc.nextInt();
                if(
                    select<=hand.getCards().size()-1
                    ||select==99
                    ||select==999
                   ){return select;}
                
                if(
                    select>hand.getCards().size()-1
                    ||select!=99
                    ||select!=999){
                    System.out.println("Invalid option, pick again");
                    continue;
                }
                
                    
                
                
                

            }

            catch(Exception e){
                System.out.println("Error: please enter int");
                continue;
            }
        }   
    }
    

    public void sayUno() {
        System.out.println("UNO!");
    }

    public void displayScore(int score) {
        System.out.println("Score: " + score);
    }
    
    public String enterName(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name");
        return sc.nextLine();
    }
    
    public void pickAgain(){
        System.out.println("Color or value does not match, choose another card or draw");
    }
    
//    For wild and wild4
    public Color pickColor(){
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose color: RED/BLUE/GREEN/YELLOW");
                String input = sc.nextLine();
                if(input.equals("RED")==false&&input.equals("BLUE")==false&&input.equals("GREEN")==false&&input.equals("YELLOW")==false){
                    System.out.println("Error: input must be RED/BLUE/GREEN/YELLOW, choose again");
                    continue;
                }
                
                if(input.equals("RED")){
                    return RED;
                }
                
                if(input.equals("GREEN")){
                    return GREEN;
                }
                
                if(input.equals("BLUE")){
                    return BLUE;
                }
                
                if(input.equals("YELLOW")){
                    return YELLOW;
                }
            }
            catch(Exception e){
                System.out.println("Error: input must be RED/BLUE/GREEN/YELLOW");
                continue;
            }
        }
    }
}
