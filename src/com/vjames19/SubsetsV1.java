package com.vjames19;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vjames19 on 2/22/15.
 */
public class SubsetsV1 implements P_ADT {

    /**
     * A sequence generator with range [0..1] and sequence length equal to the length of the set.
     *
     * A 1 at index i in the sequence indicates that the number i+1 should be added to the set.
     */
    private SeqGenerator generator;

    public SubsetsV1(int n) {

        generator = new SeqGenerator(2, n);
    }

    @Override
    public boolean hasMore() {
        return generator.hasMore();
    }

    @Override
    public int[] next() throws IllegalStateException {
        int[] sequence = generator.next();

        List<Integer> list = new ArrayList<>();
        for(int i=0; i < sequence.length; i++) {
            if (sequence[i] == 1) {
                list.add(i+1);
            }
        }

        return Utils.toArray(list);
    }

    @Override
    public void reset() {
        generator.reset();
    }
}
