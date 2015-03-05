package com.vjames19.generators;

import sun.plugin.dom.exception.InvalidStateException;

/**
 * Generates the power set of given set of numbers in the range [1..n].
 *
 * By generating all the permutations P(n, k). k in the range [0.. set length]
 */
public class SubsetsV2 implements P_ADT {


    /**
     * Generates the subsets.
     */
    private PermGenerator_v2 currentGenerator;

    /**
     * The length of the current set being generated.
     */
    private int currentSetLength;

    /**
     * Range of the numbers [1..n]
     */
    private int n;

    public SubsetsV2(int n) {
        this.n = n;
        reset();
    }

    @Override
    public boolean hasMore() {
        return currentGenerator.hasMore();
    }

    @Override
    public int[] next() throws InvalidStateException {
        if (!hasMore()) {
            throw new InvalidStateException("No more elements.");
        }

        int[] subset = currentGenerator.next();
        // Increment by 1 to make it be in the 1..n range.
        for (int i = 0; i < subset.length; i++) {
            subset[i]++;
        }

        // If the current generator doesn't have more try to create the next subset length generator.
        if (!currentGenerator.hasMore() && currentSetLength < n) {
            currentSetLength++;
            createSubsetGenerator();
        }

        return subset;
    }

    @Override
    public void reset() {
        currentSetLength = 0;
        createSubsetGenerator();
    }

    private void createSubsetGenerator() {
        currentGenerator = new PermGenerator_v2(n, currentSetLength);
    }
}
