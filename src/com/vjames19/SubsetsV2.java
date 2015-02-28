package com.vjames19;

/**
 * Created by vjames19 on 2/22/15.
 */
public class SubsetsV2 implements P_ADT {


    private PermGenerator_v1 current;
    private int currentSetLength;
    private int n;

    public SubsetsV2(int n) {
        this.n = n;
        reset();
    }

    @Override
    public boolean hasMore() {
        return current.hasMore();
    }

    @Override
    public int[] next() throws IllegalStateException {
        if (!hasMore()) {
            throw new IllegalStateException("No more");
        }

        int[] subset = current.next();
        // Increment by 1 to make it be in the 1..n range.
        for (int i = 0; i < subset.length; i++) {
            subset[i]++;
        }

        if (!current.hasMore() && currentSetLength < n) {
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
        current = new PermGenerator_v1(n, currentSetLength);
    }
}
