package edu.illinois.cs.cs125.spring2019.lab12;


import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/**
 * Main activity for Final Project.
 */
public final class MainActivity extends AppCompatActivity {
    /**
     * last attack used.
     */
    private static Attack lastAttack;

    /**
     * last attack used on computer.
     */
    private static Attack lastAttackA;

    /**
     * last attack used on player.
     */
    private static Attack lastAttackB;

    /**
     * number of types of benders.
     */
    private static final int NUM_TYPES = 4;

    /**
     * types of benders for computer to randomly choose.
     */
    private static String[] types = {"fire", "water", "earth", "air"};

    /**
     * Run when this activity comes to the foreground.
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView check = findViewById(R.id.check);
        String invalid = "Please Enter a Valid Bender Type.";
        check.setText(invalid);
        check.setVisibility(View.GONE);
        final TextInputEditText inputName = findViewById(R.id.inputName), inputType = findViewById(R.id.inputType);
        final Button startGame = findViewById(R.id.start);
        startGame.setOnClickListener(b -> {
            try {
                String type = inputType.getText().toString();
                switch (type) {
                    case "fire":
                        break;
                    case "water":
                        break;
                    case "earth":
                        break;
                    case "air":
                        break;
                    default:
                        throw new NullPointerException();
                }
                Bender player = new Bender(type, inputName.getText().toString());
                Random rand = new Random();
                Bender opponent = new Bender(types[rand.nextInt(NUM_TYPES)], "Computer");
                setContentView(R.layout.activity_main);
                Button restart = findViewById(R.id.restart);
                restart.setOnClickListener(v -> finish());
                TextView display = findViewById(R.id.log), playerName = findViewById(R.id.player);
                playerName.setText(player.getName());
                TextView opponentName = findViewById(R.id.opponent);
                opponentName.setText(opponent.getName());
                setImages(player, opponent);
                ImageView playerImage = findViewById(R.id.playerImage), oppImage = findViewById(R.id.opponentImage);
                Button one = findViewById(R.id.attackOne), two = findViewById(R.id.attackTwo);
                Button three = findViewById(R.id.attackThree), four = findViewById(R.id.attackFour);
                one.setText(player.getAttacks()[0].getName());
                two.setText(player.getAttacks()[NUM_TYPES - 2 - 1].getName());
                three.setText(player.getAttacks()[NUM_TYPES - 2].getName());
                four.setText(player.getAttacks()[NUM_TYPES - 1].getName());
                String info = "i", dis = "FIGHT!";
                Button infoOne = findViewById(R.id.infoOne);
                infoOne.setText(info);
                infoOne.setOnClickListener(v -> {
                    if (display.getText().toString().equals(dis)) {
                        display.setText(player.getAttacks()[0].getDescription());
                    } else {
                        display.setText(dis);
                    }
                });
                Button infoTwo = findViewById(R.id.infoTwo);
                infoTwo.setText(info);
                infoTwo.setOnClickListener(v -> {
                    if (display.getText().toString().equals(dis)) {
                        display.setText(player.getAttacks()[1].getDescription());
                    } else {
                        display.setText(dis);
                    }
                });
                Button infoThree = findViewById(R.id.infoThree);
                infoThree.setText(info);
                infoThree.setOnClickListener(v -> {
                    if (display.getText().toString().equals(dis)) {
                        display.setText(player.getAttacks()[2].getDescription());
                    } else {
                        display.setText(dis);
                    }
                });
                Button infoFour = findViewById(R.id.infoFour);
                infoFour.setText(info);
                infoFour.setOnClickListener(v -> {
                    if (display.getText().toString().equals(dis)) {
                        display.setText(player.getAttacks()[NUM_TYPES - 1].getDescription());
                    } else {
                        display.setText(dis);
                    }
                });
                TextView health = findViewById(R.id.health);
                String pH = "Health: " + player.getHealth();
                health.setText(pH);
                TextView oppHealth = findViewById(R.id.oppHealth);
                String oH = "Health: " + opponent.getHealth();
                oppHealth.setText(oH);
                Animation slide = AnimationUtils.loadAnimation(MainActivity.this, R.anim.lefttoright);
                Animation bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                Animation reverse = AnimationUtils.loadAnimation(MainActivity.this, R.anim.righttoleft);
                one.setOnClickListener(v -> {
                    playerImage.startAnimation(slide);
                    oppImage.startAnimation(bounce);
                    if (!executeAttack(player.getAttacks()[0], opponent)) {
                        return;
                    }
                    finish(player, opponent);
                    oppImage.startAnimation(reverse);
                    playerImage.startAnimation(bounce);
                    if (!opponentExecuteAttack(opponent.getAttacks()[rand.nextInt(NUM_TYPES)], player)) {
                        return;
                    }
                    finish(player, opponent);
                });
                two.setOnClickListener(v -> {
                    if (!executeAttack(player.getAttacks()[1], opponent)) {
                        return;
                    }
                    playerImage.startAnimation(slide);
                    oppImage.startAnimation(bounce);
                    finish(player, opponent);
                    oppImage.startAnimation(reverse);
                    playerImage.startAnimation(bounce);
                    if (!opponentExecuteAttack(opponent.getAttacks()[rand.nextInt(NUM_TYPES)], player)) {
                        return;
                    }
                    finish(player, opponent);
                });
                three.setOnClickListener(v -> {
                    playerImage.startAnimation(slide);
                    oppImage.startAnimation(bounce);
                    if (!executeAttack(player.getAttacks()[2], opponent)) {
                        return;
                    }
                    finish(player, opponent);
                    oppImage.startAnimation(reverse);
                    playerImage.startAnimation(bounce);
                    if (!opponentExecuteAttack(opponent.getAttacks()[rand.nextInt(NUM_TYPES)], player)) {
                        return;
                    }
                    finish(player, opponent);
                });
                four.setOnClickListener(v -> {
                    playerImage.startAnimation(slide);
                    oppImage.startAnimation(bounce);
                    if (!executeAttack(player.getAttacks()[NUM_TYPES - 1], opponent)) {
                        return;
                    }
                    finish(player, opponent);
                    oppImage.startAnimation(reverse);
                    playerImage.startAnimation(bounce);
                    if (!opponentExecuteAttack(opponent.getAttacks()[rand.nextInt(NUM_TYPES)], player)) {
                        return;
                    }
                    finish(player, opponent);
                });
            } catch (NullPointerException e) {
                findViewById(R.id.check).setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * finish off the program.
     * @param player player
     * @param opponent opponent
     */
    public void finish(final Bender player, final Bender opponent) {
        if (player.getHealth() <= 0) {
            TextView oppDisplay = findViewById(R.id.oppLog);
            String nothing = "";
            oppDisplay.setText(nothing);
            TextView health = findViewById(R.id.health);
            String pH = "Health: 0";
            health.setText(pH);
            TextView display = findViewById(R.id.log);
            String state = "YOU LOSE";
            display.setText(state);
        } else if (opponent.getHealth() <= 0) {
            TextView oppDisplay = findViewById(R.id.oppLog);
            String nothing = "";
            oppDisplay.setText(nothing);
            TextView oppHealth = findViewById(R.id.oppHealth);
            String oH = "Health: 0";
            oppHealth.setText(oH);
            TextView display = findViewById(R.id.log);
            String state = "YOU WIN!";
            display.setText(state);
        }
    }

    /**
     * sets images for each player.
     * @param player the player
     * @param opponent the opponent (computer)
     */
    public void setImages(final Bender player, final Bender opponent) {
        ImageView playerI = findViewById(R.id.playerImage);
        switch (player.getType()) {
            case "fire":
                playerI.setImageResource(R.drawable.fire);
                break;
            case "water":
                playerI.setImageResource(R.drawable.water);
                break;
            case "earth":
                playerI.setImageResource(R.drawable.earth);
                break;
            case "air":
                playerI.setImageResource(R.drawable.air);
                break;
            default:
                break;
        }
        ImageView opponentI = findViewById(R.id.opponentImage);
        switch (opponent.getType()) {
            case "fire":
                opponentI.setImageResource(R.drawable.fire);
                break;
            case "water":
                opponentI.setImageResource(R.drawable.water);
                break;
            case "earth":
                opponentI.setImageResource(R.drawable.earth);
                break;
            case "air":
                opponentI.setImageResource(R.drawable.air);
                break;
            default:
                break;
        }
    }
    /**
     * execute a chosen attack.
     * @param current attack chosen by player
     * @param opponent player receiving attack
     * @return true or false
     */
    public boolean executeAttack(final Attack current, final Bender opponent) {
        String no = "You can't choose the same attack twice in a row.";
        TextView display = findViewById(R.id.log);
        if (current.equals(lastAttackA)) {
            display.setText(no);
            return false;
        }
        String whatIsHappening = "You used " + current.getName() + "!";
        display.setText(whatIsHappening);
        String oof = "The opponent's last attack was a defensive move! This attack did no damage!";
        if (lastAttack != null && lastAttack.getType().equals("def")) {
            display.setText(oof);
            lastAttackA = current;
            lastAttack = current;
            return true;
        }
        String superEffective = "Your " + current.getName() + " was super effective!";
        if (current.getOpp() != null && current.getOpp().equals(opponent.getType())) {
            display.setText(superEffective);
            opponent.setHealth(opponent.getHealth() - (NUM_TYPES - 1) * current.getDamage());
            TextView oppHealth = findViewById(R.id.oppHealth);
            String oH = "Health: " + opponent.getHealth();
            oppHealth.setText(oH);
            lastAttackA = current;
            lastAttack = current;
            return true;
        }
        opponent.setHealth(opponent.getHealth() - current.getDamage());
        TextView oppHealth = findViewById(R.id.oppHealth);
        String oH = "Health: " + opponent.getHealth();
        oppHealth.setText(oH);
        lastAttackA = current;
        lastAttack = current;
        return true;
    }

    /**
     * execute a chosen attack.
     * @param current attack chosen by player
     * @param opponent player receiving attack
     * @return true or false
     */
    public boolean opponentExecuteAttack(final Attack current, final Bender opponent) {
        if (current.equals(lastAttackB)) {
            return false;
        }
        TextView oppDisplay = findViewById(R.id.oppLog);
        String whatIsHappening = "The opponent used " + current.getName() + "!";
        oppDisplay.setText(whatIsHappening);
        String oof = "Your last attack was a defensive move! The opponent's attack did no damage!";
        if (lastAttack != null && lastAttack.getType().equals("def")) {
            oppDisplay.setText(oof);
            lastAttackB = current;
            lastAttack = current;
            return true;
        }
        String superEffective = "The opponent's " + current.getName() + " was super effective!";
        if (current.getOpp() != null && current.getOpp().equals(opponent.getType())) {
            oppDisplay.setText(superEffective);
            opponent.setHealth(opponent.getHealth() - (NUM_TYPES - 1) * current.getDamage());
            TextView health = findViewById(R.id.health);
            String pH = "Health: " + opponent.getHealth();
            health.setText(pH);
            lastAttackB = current;
            lastAttack = current;
            return true;
        }
        opponent.setHealth(opponent.getHealth() - current.getDamage());
        TextView health = findViewById(R.id.health);
        String pH = "Health: " + opponent.getHealth();
        health.setText(pH);
        lastAttackB = current;
        lastAttack = current;
        return true;
    }

    /*/**
     * Run when this activity is no longer visible.
     *
    @Override
    protected void onPause() {
        super.onPause();
    }
*/
/*    /**
     * Make a call to the IP geolocation API.
     *
     * @param ipAddress IP address to look up
     *
    public void startAPICall(final String ipAddress) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://ipinfo.io/" + ipAddress + "/json",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     *
     *
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("hostname").toString());
        } catch (JSONException ignored) { }
    }*/
}
