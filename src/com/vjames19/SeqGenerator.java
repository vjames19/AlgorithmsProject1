package com.vjames19;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by vjames19 on 2/22/15.
 */
public class SeqGenerator implements P_ADT {

    /**
     * Holds the current sequence
     */
    private Deque<Integer> sequence;

    /**
     * Range of numbers [0..range-1]
     */
    private int range;

    private int sequenceLength;
    private boolean hasMore = true;


    /**
     * Generates a sequence with n Range and k length.
     * @param n Range of the number of this sequence [0..n-1]
     * @param k length of the sequence
     */
    public SeqGenerator(int n, int k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException();
        }

        sequence = new LinkedList<>();
        range = n;
        sequenceLength = k;

        reset();
    }

    @Override
    public boolean hasMore() {
        return hasMore;
    }

    @Override
    public int[] next() throws IllegalStateException {
        if (!hasMore()) {
            throw new IllegalStateException();
        }

        int[] current = Utils.toArray(sequence);
        computeNext();

        return current;
    }

    private void computeNext() {
        while (!sequence.isEmpty()) {
            int next = sequence.removeLast() + 1;

            if (next < range) {
                // Append the next number in the sequence and pad with zeros.
                while (sequence.size() < sequenceLength) {
                    sequence.addLast(next);
                    next = 0;
                }
                break;
            }
        }

        if (sequence.isEmpty()) {
            hasMore = false;
        }
    }

    @Override
    public void reset() {
        hasMore = true;
        sequence.clear();
        while(sequence.size() < sequenceLength) {
            sequence.add(0);
        }
    }
}
