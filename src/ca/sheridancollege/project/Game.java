/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author Jacob Cruz
 * @author Hoang Ngoc Tram Nguyen
 */
public abstract class Game <T extends Player>{

    private final String name;
    private List<T> players;

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<T> getPlayers() {
        return players;
    }

    public void setPlayers(List<T> players) {
        this.players = players;
    }

    /**
     * An abstract method that subclasses will implement to define game-specific behavior.
     */
    public abstract void play();

    /**
     * Declare the winner of the game.
     */
    public abstract void declareWinner();
}
