package com.vjames19.generators;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.Arrays;

/**
 * Generates all the numbers of the given base and number of digits, by incrementing by one each time.
 *
 * The base being n and the number of digits being k. It will generate n^k numbers.
 */
public class SeqGenerator implements P_ADT {

    /**
     * Holds the current sequence.
     */
    private int[] sequence;

    /**
     * Base of the numbers in the sequence
     */
    private int base;

    /**
     * Indicates if this generator has more sequences to generate.
     */
    private boolean hasMore;


    /**
     *
     * @param n The base of the numbers in the sequence.
     * @param k Number of digits in the sequence.
     */
    public SeqGenerator(int n, int k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException();
        }

        sequence = new int[k];
        base = n;

        reset();
    }

    @Override
    public boolean hasMore() {
        return hasMore;
    }

    @Override
    public int[] next() throws InvalidStateException {
        if (!hasMore()) {
            throw new InvalidStateException("No more elements.");
        }

        int[] current = Arrays.copyOf(sequence, sequence.length);
        increment();
        return current;
    }

    private void increment() {
        boolean carry = true;
        int i = sequence.length - 1;
        while (carry && i >= 0) {
            int sum = sequence[i] + 1;
            carry = sum == base;
            sequence[i] = carry ? 0 : sum;
            i--;
        }

        hasMore = !(carry && i < 0);
    }

    @Override
    public void reset() {
        hasMore = true;
        Arrays.fill(sequence, 0);
    }
}
