package com.vjames19;

import com.vjames19.generators.P_ADT;
import com.vjames19.generators.PermGenerator_v2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int n = 3;
        int k = 2;
//       P_ADT seq = new SubsetsV2(n);
//       P_ADT seq = new SeqGenerator(n, k);
//       P_ADT seq = new SeqGenerator(n, k);
        P_ADT seq = new PermGenerator_v2(n, k);
//        P_ADT seq = new PermGenerator_v3(n, k);

        while (seq.hasMore()) {
            System.out.println(Arrays.toString(seq.next()));
        }
    }

    public static void sequence(int[] p, int lvl, int n) {
        if (lvl  == p.length)
            process(p);
        else
            for (int j=0; j < n; j++) {
                p[lvl]=j;
                sequence(p, lvl+1, n);
            }
    }

    public static void process(int[] p) {
        System.out.println(Arrays.toString(p));
    }
}
