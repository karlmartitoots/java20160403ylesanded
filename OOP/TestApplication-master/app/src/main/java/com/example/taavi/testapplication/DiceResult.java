package com.example.taavi.testapplication;

import java.util.Random;

public enum DiceResult {
    ONE(1, R.drawable.dice1),
    TWO(2, R.drawable.dice2),
    THREE(3, R.drawable.dice3),
    FOUR(4, R.drawable.dice4),
    FIVE(5, R.drawable.dice5),
    SIX(6, R.drawable.dice6);

    private final int value;
    private final int drawable;

    DiceResult(int value, int drawable) {
        this.value = value;
        this.drawable = drawable;
    }

    int getValue() {
        return this.value;
    }

    int getDrawable() {
        return this.drawable;
    }

    static DiceResult getRandomResultExcept(DiceResult exceptResult) {
        while (true) {
            int randomInt = new Random().nextInt(6);
            if (randomInt == exceptResult.getValue()) {
                continue;
            }
            return DiceResult.values()[randomInt];
        }
    }
}
