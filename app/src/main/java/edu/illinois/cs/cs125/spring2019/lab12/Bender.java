package edu.illinois.cs.cs125.spring2019.lab12;

/**
 * Bender.
 */
public class Bender {

    /**
     * type of Bender.
     */
    private String type;

    /**
     * Name chosen by user.
     */
    private String name;

    /**
     * health out of 100.
     */
    private int health;

    /**
     * Attacks available to bender.
     */
    private Attack[] attacks;
    /**
     * Bender constructor.
     * @param theType type of bender created.
     * @param theName name entered by user.
     */
    public Bender(final String theType, final String theName) {
        health = 0;
        name = theName;
        type = theType;
        switch (type) {
            case "fire" : attacks = Attack.fireAttacks();
                break;
            case "water" : attacks = Attack.waterAttacks();
                break;
            case "earth" : attacks = Attack.earthAttacks();
                break;
            case "air" : attacks = Attack.airAttacks();
                break;
            default : break;
        }
    }

}
