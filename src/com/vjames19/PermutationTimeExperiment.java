package com.vjames19;

import com.vjames19.generators.P_ADT;
import com.vjames19.generators.PermGenerator_v1;
import com.vjames19.generators.PermGenerator_v2;

/**
 * Created by vjames19 on 3/5/15.
 */
public class PermutationTimeExperiment {

    public static void main(String[] args) {
        for (int k = 2; k < 9; k++) {
            for (int n = 2; n < 9; n++) {
                double v1Time = timeGenerator(new PermGenerator_v1(n, n)) / 1000000.0;
                double v2Time = timeGenerator(new PermGenerator_v2(n, n)) / 1000000.0;

                System.out.printf("For n=%d, k=%d ; PermV1 time=%f; PermV2 time=%f; v2 is %f times faster\n",
                        n, k,v1Time, v2Time, v1Time / v2Time);
//                System.out.printf("%d, %d, %f, %f, %f\n",n, k,v1Time, v2Time, v1Time / v2Time);
            }
        }
    }

    private static long timeGenerator(P_ADT generator) {
        Timer timer = new Timer();
        timer.start();
        while (generator.hasMore()) {
            generator.next();
        }
        return timer.stop();
    }
}
