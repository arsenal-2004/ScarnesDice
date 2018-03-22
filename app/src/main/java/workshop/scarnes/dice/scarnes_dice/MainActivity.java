package workshop.scarnes.dice.scarnes_dice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static int comp_total, comp_turn;
    static int user_total, user_turn;
    static String user_total_score, user_turn_score;
    static String comp_total_score, comp_turn_score;
    static String msg;
    static TextView user;
    static TextView computer;
    static TextView message;
    static ImageView img;
    static Button roll, hold, reset;
    static final Handler handler = new Handler();
    static int i;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = (Button) findViewById(R.id.roll);
        roll.setOnClickListener(this);
        hold = (Button) findViewById(R.id.hold);
        hold.setOnClickListener(this);
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);
        user = (TextView) findViewById(R.id.user_score);
        user.setText("User Total: 0");
        computer = (TextView) findViewById(R.id.computer_score);
        computer.setText("Computer Total: 0");
        message = (TextView) findViewById(R.id.message);
        message.setText("");
        img = (ImageView) findViewById(R.id.dice);
    }

    static int randInt(int min, int max) {
        Random generator = new Random();
        int randomNum = generator.nextInt((max-min)+1) + min;
        return randomNum;
    }

    public void computerTurn() {
        roll.setEnabled(false);
        hold.setEnabled(false);
        reset.setEnabled(false);
        i = randInt(1, 6);
        if (i != 1 && comp_turn < 20) {
            comp_turn += i;
            computer.setText("Computer Total: " + comp_total + " Turn: " + comp_turn);
            message.setText("The computer rolled a " + i);
            String dice = "dice" + i;
            int resId = getResources().getIdentifier(dice, "drawable", getPackageName());
            img.setImageResource(resId);
            if (comp_total+comp_turn >= 100) {
                comp_total += comp_turn;
                comp_turn = 0;
                message.setText("The computer has won this game. Better luck next time.");
                computer.setText("Computer Total: " + comp_total + " Turn: " + comp_turn);
                reset.setEnabled(true);
                return;
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    computerTurn();}
            }, 2000);
            return;
        }
        if (i == 1) {
            message.setText("The computer has rolled a one. It's your turn to play now.");
            comp_turn = 0;
            computer.setText("Computer Total: " + comp_total + " Turn: " + comp_turn);
        }
        else {
            message.setText("The computer's turn is over. It's your turn to play now.");
            comp_total += comp_turn;
            comp_turn = 0;
            computer.setText("Computer Total: " + comp_total + " Turn: " + comp_turn);
        }
        roll.setEnabled(true);
        hold.setEnabled(true);
        reset.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.roll) {
            // Work here tomorrow
            //i = randInt(1, 6);
            //int i;
//            for (int j=0; j<5; j++) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        i = randInt(1, 6);
//                        String dice = "dice" + i;
//                        int resId = getResources().getIdentifier(dice, "drawable", getPackageName());
//                        img.setImageResource(resId);
//                    }
//                }, 1000);
//            }
//            i = 2;
            i = rollDice();
            if (i == 1) {
                msg = "You have rolled a 1. Your turn score is reset.";
                message.setText(msg);
                img.setImageResource(R.drawable.dice1);
                user_turn = 0;
                user_turn_score = "User Total: " + user_total + " Turn: " + user_turn;
                user.setText(user_turn_score);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        comp_turn = 0;
                        computerTurn();
                    }
                }, 2000);
                //computerTurn();
            } else {
                msg = "You have rolled a " + i + ".";
                message.setText(msg);
                String dice = "dice" + i;
                int resId = getResources().getIdentifier(dice, "drawable", getPackageName());
                img.setImageResource(resId);
                user_turn += i;
                user_turn_score = "User Total: " + user_total + " Turn: " + user_turn;
                user.setText(user_turn_score);
            }
        }
        else if (view.getId() == R.id.hold) {
            user_total += user_turn;
            user_turn = 0;
            user_total_score = "User Total: " + user_total + " Turn: 0";
            user.setText(user_total_score);
            message.setText("Your turn is over.");
            if (user_total >= 100) {
                message.setText("You have won this game. Hooray!!");
                hold.setEnabled(false);
                roll.setEnabled(false);
                reset.setEnabled(true);
                return;
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    comp_turn = 0;
                    computerTurn();
                }
            }, 2000);
        }
        else {
            user_total = 0;
            user_turn = 0;
            comp_total = 0;
            comp_turn = 0;
            user_total_score = "User Total: " + user_total + " Turn: " + user_turn;
            user.setText(user_total_score);
            computer.setText("Computer Total: 0 Turn: 0");
            message.setText("");
            roll.setEnabled(true);
            hold.setEnabled(true);
        }
    }

    int rollDice() {
//        j = 0;
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (j++ < 5)
//                    handler.postDelayed(this, 1000);
//                i = randInt(1, 6);
//                String dice = "dice" + i;
//                int resId = getResources().getIdentifier(dice, "drawable", getPackageName());
//                img.setImageResource(resId);
//            }
//        };
//        handler.post(runnable);
//        return i;

        int j = randInt(1, 6);
        String dice = "dice" + j;
        int resId = getResources().getIdentifier(dice, "drawable", getPackageName());
        img.setImageResource(resId);
        return j;
    }

}
