package com.vjames19.generators;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.HashSet;
import java.util.Set;

/**
 * Generates permutations by only using the sequences that don't have repetitions.
 */
public class PermGenerator_v1 implements P_ADT {

    private SeqGenerator generator;

    /**
     * Holds the next permutation to return.
     */
    private int[] next;

    private Set<Integer> comparisonSet = new HashSet<>();

    public PermGenerator_v1(int n, int k) {
        generator = new SeqGenerator(n, k);
        reset();
    }

    @Override
    public boolean hasMore() {
        return next != null;
    }

    @Override
    public int[] next() throws InvalidStateException {
        if (!hasMore()) {
            throw new InvalidStateException("No more elements");
        }

        int[] next = this.next;
        computeNext();
        return next;
    }

    @Override
    public void reset() {
        generator.reset();
        next = null;
        computeNext();
    }

    private void computeNext() {
        while(generator.hasMore()) {
            int[] sequence = generator.next();
            if (hasNoRepeatedValue(sequence)) {
                next = sequence;
                return;
            }
        }

        next = null;
    }

    private boolean hasNoRepeatedValue(int[] sequence) {
        comparisonSet.clear();
        for(int i : sequence) {
            if (comparisonSet.contains(i)) {
                return false;
            } else {
                comparisonSet.add(i);
            }
        }

        return true;
    }
}
