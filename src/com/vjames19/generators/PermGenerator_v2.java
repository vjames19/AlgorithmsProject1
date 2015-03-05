package com.vjames19.generators;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.Arrays;

/**
 * Generates permutations by skipping sequences that can have repeated values.
 */
public class PermGenerator_v2 implements P_ADT {

    /**
     * Holds the current permutation.
     */
    private int[] permutation;

    /**
     * Maps which integers have been used.
     */
    private int[] used;

    /**
     * Height of the virtual tree.
     */
    private final int HEIGHT;

    /**
     * The level in the virtual tree we are currently modifying.
     */
    private int level;

    /**
     * Range of numbers [0..range-1]
     */
    private int range;

    /**
     * @param n Range of the number of this sequence [0..n-1]
     * @param k length of the sequence
     */
    public PermGenerator_v2(int n, int k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException();
        }

        permutation = new int[k];
        used = new int[n];
        range = n;
        HEIGHT = k;


        reset();
    }

    @Override
    public boolean hasMore() {
        int max = range - 1;
        if (range > HEIGHT) {
            return !(permutation[0] == max && permutation[HEIGHT - 1] == max - 1);
        }

        return !(permutation[0] == range - 1 && permutation[HEIGHT - 1] == 0);
    }

    @Override
    public int[] next() throws InvalidStateException {
        if (!hasMore()) {
            throw new InvalidStateException("No more elements.");
        }


        boolean wentUp = level == HEIGHT;
        level = level == HEIGHT ? level - 1 : level;

        while (level >= 0 && level < HEIGHT) {
            int current = permutation[level];
            int next = current;

            // Get the next possible branch
            while(next < range && isUsed(next)) {
                next++;
            }

            if (next == range) { // overflow
                used[current] = 0; // mark it as unused.
                permutation[level] = 0; // Make it 0 since there was an overflow.
                wentUp = true;
                level--;
            } else {
                if (wentUp) { // If we came from a level below mark it as unused
                    used[current] = 0;
                }
                used[next] = 1; // mark it as used
                permutation[level] = next;
                wentUp = false;
                level++;
            }
        }

        return permutation;
    }

    private boolean isUsed(int n) {
        return used[n] == 1;
    }

    @Override
    public void reset() {
        Arrays.fill(permutation, 0);
        Arrays.fill(used, 0);
        level = 0;
    }
}
