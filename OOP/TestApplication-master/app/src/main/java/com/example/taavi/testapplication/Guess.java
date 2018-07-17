package com.example.taavi.testapplication;

import java.util.HashMap;
import java.util.Map;

public class Guess {
    //TODO - the below is perhaps not the most optimal solution...
    private final static Map<Integer, Integer> probabilityMap = new HashMap<>(); //sum to amount mapping
    private final static int TOTAL_SUMS = 36;

    static {
        for (DiceResult firstResult : DiceResult.values()) {
            for (DiceResult secondResult : DiceResult.values()) {
                int sum = firstResult.getValue() + secondResult.getValue();
                if (probabilityMap.containsKey(sum)) {
                    probabilityMap.put(sum, probabilityMap.get(sum) + 1);
                }
                else {
                    probabilityMap.put(sum, 1);
                }
            }
        }
    }

    private final Type type;
    private final int initialSum;

    public Guess(Type type, int initialSum) {
        this.type = type;
        this.initialSum = initialSum;
    }

    public double winPayout(int resultSum) {
        return type.winPayout(initialSum, resultSum);
    }

    public enum Type {
        LOWER {
            @Override
            double winPayout(int initialSum, int resultSum) {
                if (initialSum <= resultSum) {
                    return 0.0;
                }

                int lowerSums = 0;
                for (int i = 2; i <= initialSum; i++) {
                    lowerSums += probabilityMap.get(i);
                }

                double probability = (double)lowerSums / TOTAL_SUMS;
                return probability - 0.01; //house always wins
            }

            @Override
            boolean buttonEnabled(int currentSum) {
                return currentSum != 2; //2 is the lowest available sum, you can't guess 'lower' when you have that
            }
        },
        EQUAL {
            @Override
            double winPayout(int initialSum, int resultSum) {
                if (initialSum != resultSum) {
                    return 0.0;
                }
                //number of sums with this count
                int equalSums = probabilityMap.get(initialSum);
                double probability = (double)equalSums / TOTAL_SUMS;
                return probability - 0.01; //house always wins
            }

            @Override
            boolean buttonEnabled(int currentSum) {
                return true; //always enabled - you can always get the same value again
            }
        },
        HIGHER {
            @Override
            double winPayout(int initialSum, int resultSum) {
                if (resultSum <= initialSum) {
                    return 0.0;
                }

                int higherSums = 0;
                for (int i = initialSum + 1; i <= 12; i++) {
                    higherSums += probabilityMap.get(i);
                }

                double probability = (double)higherSums / TOTAL_SUMS;
                return probability - 0.01; //house always wins
            }

            @Override
            boolean buttonEnabled(int currentSum) {
                return currentSum != 12; //12 is the highest possible result; you can't guess higher when you have that
            }
        };

        abstract double winPayout(int initialSum, int resultSum);
        abstract boolean buttonEnabled(int currentSum);
    }
}