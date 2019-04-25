package edu.illinois.cs.cs125.spring2019.lab12;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
/*import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;*/

/**
 * Main activity for Final Project.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    //** Request queue for our API requests. */
    //private static RequestQueue requestQueue;

    /**
     * last attack used.
     */
    private static Attack lastAttack;

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
        findViewById(R.id.check).setVisibility(View.GONE);

        final TextInputEditText inputName = findViewById(R.id.inputName);

        final TextInputEditText inputType = findViewById(R.id.inputType);

        final Button startGame = findViewById(R.id.start);

        startGame.setOnClickListener(v -> {
            try {
                String type = inputType.getText().toString();
                switch (type) {
                    case "fire" : break;
                    case "water" : break;
                    case "earth" : break;
                    case "air" : break;
                    default : throw new NullPointerException();
                }
                Bender player = new Bender(type, inputName.getText().toString());
                Random rand = new Random();
                Bender opponent = new Bender(types[rand.nextInt(NUM_TYPES)], "Computer");
                runGame(player, opponent);
            } catch (NullPointerException e) {
                findViewById(R.id.check).setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Starts game between player and computer.
     * @param player player
     * @param opponent computer playing against player
     */
    public void runGame(final Bender player, final Bender opponent) {
        setContentView(R.layout.activity_main);
        final Button restart = findViewById(R.id.start);
        restart.setOnClickListener(v -> {
            setContentView(R.layout.activity_main);
            findViewById(R.id.check).setVisibility(View.GONE);
        });
        TextView playerName = findViewById(R.id.player);
        playerName.setText(player.getName());
        TextView opponentName = findViewById(R.id.opponent);
        opponentName.setText(opponent.getName());
        ImageView playerI = findViewById(R.id.playerImage);
        switch (player.getType()) {
            case "fire" : playerI.setImageResource(R.drawable.fire);
                break;
            case "water" : playerI.setImageResource(R.drawable.water);
                break;
            case "earth" : playerI.setImageResource(R.drawable.earth);
                break;
            case "air" : playerI.setImageResource(R.drawable.air);
                break;
            default : break;
        }
        ImageView opponentI = findViewById(R.id.opponentImage);
        switch (opponent.getType()) {
            case "fire" : opponentI.setImageResource(R.drawable.fire);
                break;
            case "water" : opponentI.setImageResource(R.drawable.water);
                break;
            case "earth" : opponentI.setImageResource(R.drawable.earth);
                break;
            case "air" : opponentI.setImageResource(R.drawable.air);
                break;
            default : break;
        }
        Button one = findViewById(R.id.attackOne);
        Button two = findViewById(R.id.attackTwo);
        Button three = findViewById(R.id.attackThree);
        Button four = findViewById(R.id.attackFour);
        one.setText(player.getAttacks()[0].getName());
        two.setText(player.getAttacks()[NUM_TYPES - 2 - 1].getName());
        three.setText(player.getAttacks()[NUM_TYPES - 2].getName());
        four.setText(player.getAttacks()[NUM_TYPES - 1].getName());
        TextView health = findViewById(R.id.health);
        String pH = "Health: " + player.getHealth();
        health.setText(pH);
        TextView oppHealth = findViewById(R.id.oppHealth);
        String oH = "Health: " + opponent.getHealth();
        oppHealth.setText(oH);
        while (player.getHealth() > 0 && opponent.getHealth() > 0) {
            one.setOnClickListener(v -> {
                Attack current = player.getAttacks()[0];
                String whatIsHappening = "You used " + current.getName() + "!";
                TextView display = findViewById(R.id.log);
                display.setText(whatIsHappening);
                opponent.setHealth(opponent.getHealth() - current.getDamage());
                lastAttack = current;
            });
            two.setOnClickListener(v -> {
                Attack current = player.getAttacks()[1];
                String whatIsHappening = "You used " + current.getName() + "!";
                TextView display = findViewById(R.id.log);
                display.setText(whatIsHappening);
                opponent.setHealth(opponent.getHealth() - current.getDamage());
                lastAttack = current;
            });
            three.setOnClickListener(v -> {
                Attack current = player.getAttacks()[2];
                String whatIsHappening = "You used " + current.getName() + "!";
                TextView display = findViewById(R.id.log);
                display.setText(whatIsHappening);
                opponent.setHealth(opponent.getHealth() - current.getDamage());
                lastAttack = current;
            });
            four.setOnClickListener(v -> {
                Attack current = player.getAttacks()[NUM_TYPES - 1];
                String whatIsHappening = "You used " + current.getName() + "!";
                TextView display = findViewById(R.id.log);
                display.setText(whatIsHappening);
                opponent.setHealth(opponent.getHealth() - current.getDamage());
                lastAttack = current;
            });
            pH = "Health: " + player.getHealth();
            health.setText(pH);
            oH = "Health: " + opponent.getHealth();
            oppHealth.setText(oH);
        }
        TextView display = findViewById(R.id.log);
        String won = "YOU WIN!";
        String lost = "YOU LOSE";
        if (player.getHealth() <= 0) {
            display.setText(won);
        } else {
            display.setText(lost);
        }
    }

    /*/**
     * Run when this activity is no longer visible.
     *
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the IP geolocation API.
     *
     * @param ipAddress IP address to look up
     */
    /*void startAPICall(final String ipAddress) {
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
    }

    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     *
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("hostname").toString());
        } catch (JSONException ignored) { }
    }*/
}
