package edu.illinois.cs.cs125.spring2019.lab12;

/**
 * Attacks.
 */
public class Attack {
    /**
     * damage caused by an attack.
     */
    private int damage;

    /**
     * type of attack.
     */
    private String type;

    /**
     * opposite type.
     */
    private String opp;

    /**
     * damage.
     */
    private static final int S = 10;

    /**
     * damage.
     */
    private static final int M = 15;

    /**
     * damage.
     */
    private static final int L = 20;

    /**
     * damage.
     */
    private static final int XL = 25;

    /**
     * creates an attack.
     * @param theType type of attack
     * @param theDamage damage done by attack
     */
    public Attack(final String theType, final int theDamage) {
        type = theType;
        damage = theDamage;
        switch (type) {
            case "fire" : opp = "water";
                break;
            case "water" : opp = "fire";
                break;
            case "earth" : opp = "air";
                break;
            case "air" : opp = "earth";
                break;
            default : opp = null;
                break;
        }
    }

    /**
     * four attacks available to firebenders.
     * @return array of fire attacks
     */
    public static Attack[] fireAttacks() {
        Attack lightning = new Attack("fire", M);
        Attack fireball = new Attack("fire", S);
        Attack kick = new Attack("none", L);
        Attack punch = new Attack("none", M);
        return new Attack[]{kick, punch, lightning, fireball};
    }

    /**
     * four attacks available to waterbenders.
     * @return array of water attacks
     */
    public static Attack[] waterAttacks() {
        Attack tidalWave = new Attack("water", L);
        Attack waterWhip = new Attack("water", S);
        Attack kick = new Attack("none", M);
        Attack punch = new Attack("none", M);
        return new Attack[]{kick, punch, tidalWave, waterWhip};
    }

    /**
     * four attacks available to earthbenders.
     * @return array of earth attacks
     */
    public static Attack[] earthAttacks() {
        Attack wall = new Attack("none", S);
        Attack boulderThrow = new Attack("earth", S);
        Attack kick = new Attack("none", L);
        Attack punch = new Attack("none", L);
        return new Attack[]{kick, punch, wall, boulderThrow};
    }

    /**
     * four attacks available to airbenders.
     * @return array of air attacks
     */
    public static Attack[] airAttacks() {
        Attack airBlast = new Attack("air", L);
        Attack quickDodge = new Attack("none", S);
        Attack kick = new Attack("none", L);
        Attack punch = new Attack("none", M);
        return new Attack[]{kick, punch, airBlast, quickDodge};
    }
}
