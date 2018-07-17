package com.example.taavi.testapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final static int DICE_ANIMATION_RESULTS_TO_GENERATE = 20;

    //TODO - there must be a way to get rid of this..
    private final Map<Button, Guess.Type> betButtons = new HashMap<>(); //lazily loaded!

    private DiceResult[] currentDiceResults = new DiceResult[]{DiceResult.THREE, DiceResult.FOUR};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSeekBarListeners();
    }

    //TODO - instead of having three different methods, implement one that chooses guess type based on some button binding / value?
    public void onClickLowerButton(View v) {
        performGuess(new Guess(Guess.Type.LOWER, getCurrentSum()));
    }

    public void onClickEqualButton(View v) {
        performGuess(new Guess(Guess.Type.EQUAL, getCurrentSum()));
    }

    public void onClickHigherButton(View v) {
        performGuess(new Guess(Guess.Type.HIGHER, getCurrentSum()));
    }

    private void performGuess(Guess guess) {
        updateButtonStates(false);
        //TODO - disable slider

        final ArrayList<DiceResult>  dice1Results = new ArrayList<>();
        for (int i = 0; i < DICE_ANIMATION_RESULTS_TO_GENERATE; i++) {
            dice1Results.add(dice1Results.size() == 0 ? currentDiceResults[0] : DiceResult.getRandomResultExcept(dice1Results.get(dice1Results.size() - 1)));
        }

        final ArrayList<DiceResult>  dice2Results = new ArrayList<>();
        for (int i = 0; i < DICE_ANIMATION_RESULTS_TO_GENERATE; i++) {
            dice2Results.add(dice2Results.size() == 0 ? currentDiceResults[1] : DiceResult.getRandomResultExcept(dice2Results.get(dice2Results.size() - 1)));
        }

        Handler handler = new Handler();

        for (int i = 0; i < DICE_ANIMATION_RESULTS_TO_GENERATE; i++) {
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   ((ImageView)findViewById(R.id.dice1ImageView)).setImageResource(dice1Results.get(finalI).getDrawable());
                                   ((ImageView)findViewById(R.id.dice2ImageView)).setImageResource(dice2Results.get(finalI).getDrawable());
                                   ContextCompat.getDrawable(getApplicationContext(), dice1Results.get(finalI).getDrawable()).invalidateSelf();
                                   ContextCompat.getDrawable(getApplicationContext(), dice2Results.get(finalI).getDrawable()).invalidateSelf();
                               }
                           },
                    finalI + finalI * 100);
        }

        //perform current round end:
        //TODO - display win or lose message
        //TODO - perform payout - calculate it based on guess#winpayout method
        //TODO - substitute currentDiceResults variable with actual results
        //TODO - set bet to 0
        //TODO - move slider to beginning
        //TODO - re-enable buttons - but only if the latest result allows it!
    }

    //TODO - this solution is awful. better way: extend button, have a guess.type binding as a button field
    private Map<Button, Guess.Type> getBetButtonBindings() {
        if (betButtons.isEmpty()) {
            betButtons.put((Button) findViewById(R.id.lowerButton), Guess.Type.LOWER);
            betButtons.put((Button) findViewById(R.id.equalButton), Guess.Type.EQUAL);
            betButtons.put((Button) findViewById(R.id.higherButton), Guess.Type.HIGHER);
        }
        return betButtons;
    }

    private void updateButtonStates(boolean enabled) {
        Drawable bgToSet = enabled ? ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_bg_active) :
                                     ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_bg_disabled);

        for (Button button : getBetButtonBindings().keySet()) {
            //TODO - below is confusing. there must be a simpler way of writing this...
            button.setClickable(getBetButtonBindings().get(button).buttonEnabled(getCurrentSum()) ? enabled : false);
            button.setBackground(bgToSet);
        }
    }

    private int getCurrentSum() {
        return currentDiceResults[0].getValue() + currentDiceResults[1].getValue();
    }

    private void initSeekBarListeners() {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.betAmountSeekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView betAmountTextView = (TextView) findViewById(R.id.betAmountTextView);
            TextView totalAmountTextView = (TextView) findViewById(R.id.totalAmountTextView);

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //nothing?
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //nothing?
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //TODO - balance & bet should probably stored in separate int fields rather than relying on textfield values?
                String currentTotal = totalAmountTextView.getText().toString();
                double totalAsDouble = Double.parseDouble(currentTotal);
                int totalAsInt = (int)(totalAsDouble * 100);
                int progressPercentage = progress * 100 / seekBar.getMax();
                int betAsInt = totalAsInt * progressPercentage / 100;
                double betAsDouble = betAsInt / 100.0;
                betAmountTextView.setText(Double.toString(betAsDouble));

                if (betAsInt == 0) {
                    updateButtonStates(false);
                }
                else {
                    updateButtonStates(true);
                }
            }
        });
    }
}
