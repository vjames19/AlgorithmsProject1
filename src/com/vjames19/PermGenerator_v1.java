package com.vjames19;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vjames19 on 2/22/15.
 */
public class PermGenerator_v1 implements P_ADT {

    private SeqGenerator generator;
    Set<Integer> set = new HashSet<>();
    private int[] current;

    public PermGenerator_v1(int n, int k) {
        generator = new SeqGenerator(n, k);
        reset();
    }

    @Override
    public boolean hasMore() {
        return current != null;
    }

    @Override
    public int[] next() throws IllegalStateException {
        int[] next = current;
        computeNext();
        return next;
    }

    @Override
    public void reset() {
        generator.reset();
        current = null;
        computeNext();
    }

    private void computeNext() {
        while(generator.hasMore()) {
            int[] sequence = generator.next();
            if (hasNoRepeatedValue(sequence)) {
                current = sequence;
                return;
            }
        }

        current = null;
    }

    private boolean hasNoRepeatedValue(int[] sequence) {
        set.clear();
        for(int i : sequence) {
            if (set.contains(i)) {
                return false;
            } else {
                set.add(i);
            }
        }

        return true;
    }
}
