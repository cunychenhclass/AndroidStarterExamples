package edu.cuny.bc.cisc3171.toolsandgames;

import java.util.Random;

public class GuessNumberGameModel {
    private final int level;
    private int minNum;
    private int maxNum;
    private int target;
    private final Random rng;

    public static boolean isValidLevel(int level) {
        return level >= 1 && level <= 8;
    }

    public static int getLowestLevel() {
        return 1;
    }

    public static int getHighestLevel() {
        return 8;
    }

    public GuessNumberGameModel(int level) {
        this.level = level;
        setRange();
        rng = new Random();
        reset();
    }

    public int takeGuess(int guess) {
        if (guess == target) {
            return 0; // Bingo!
        }
        return Integer.signum(guess - target);
    }

    public int getMinNum() {
        return minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }



    public void reset() {
        target = rng.nextInt(maxNum - minNum + 1) + minNum;
    }

    private void setRange() {
        minNum = 0;
        maxNum = (int)Math.pow(10, level);
    }
}
