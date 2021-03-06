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
     * name of attack.
     */
    private String name;

    /**
     * type of attack.
     */
    private String type;

    /**
     * opposite type.
     */
    private String opp;

    /**
     * description of attack.
     */
    private String description;

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
     * @param theName name of attack.
     * @param theType type of attack
     * @param theDamage damage done by attack
     * @param theDescription description of attack
     */
    public Attack(final String theName, final String theType, final int theDamage, final String theDescription) {
        name = theName;
        type = theType;
        damage = theDamage;
        description = theDescription;
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
     * get name of attack.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get type of attack.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * get opposing type of bending.
     * @return opposing type
     */
    public String getOpp() {
        return opp;
    }

    /**
     * get description of attack.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * get damage caused by attack.
     * @return damage
     */
    public int getDamage() {
        return damage;
    }
    /**
     * four attacks available to firebenders.
     * @return array of fire attacks
     */
    public static Attack[] fireAttacks() {
        Attack lightning = new Attack("Lightning", "fire", M, "15 damage, triple damage against waterbenders");
        Attack fireball = new Attack("Fireball", "fire", L, "20 damage, huge, consuming fireball");
        Attack kick = new Attack("Kick", "none", M, "15 damage, kick");
        Attack punch = new Attack("Punch", "none", M, "15 damage, punch");
        return new Attack[]{kick, punch, lightning, fireball};
    }

    /**
     * four attacks available to waterbenders.
     * @return array of water attacks
     */
    public static Attack[] waterAttacks() {
        Attack tidalWave = new Attack("Tidal Wave", "none", XL, "25 damage, overwhelming tidal wave");
        Attack waterWhip = new Attack("Water Whip", "water", S, "10 damage, triple damage against firebenders");
        Attack kick = new Attack("Kick", "none", M, "15 damage, kick");
        Attack punch = new Attack("Punch", "none", M, "15 damage, punch");
        return new Attack[]{kick, punch, tidalWave, waterWhip};
    }

    /**
     * four attacks available to earthbenders.
     * @return array of earth attacks
     */
    public static Attack[] earthAttacks() {
        Attack wall = new Attack("Wall", "def", S, "10 damage, opponent's next attack will do no damage");
        Attack boulderThrow = new Attack("Boulder Throw", "earth", S, "10 damage, triple damage against airbenders");
        Attack kick = new Attack("Kick", "none", M, "15 damage, kick");
        Attack punch = new Attack("Punch", "none", L, "20 damage, punch");
        return new Attack[]{kick, punch, wall, boulderThrow};
    }

    /**
     * four attacks available to airbenders.
     * @return array of air attacks
     */
    public static Attack[] airAttacks() {
        Attack airBlast = new Attack("Air Blast", "air", M, "15 damage, triple damage against earthbenders");
        Attack quickDodge = new Attack("Quick Dodge", "def", S, "10 damage, opponent's next attack will do no damage");
        Attack kick = new Attack("Kick", "none", M, "15 damage, kick");
        Attack punch = new Attack("Punch", "none", M, "15 damage, punch");
        return new Attack[]{kick, punch, airBlast, quickDodge};
    }
}
