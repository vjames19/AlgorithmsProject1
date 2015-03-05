package com.vjames19;

import java.util.concurrent.TimeUnit;

/**
 * Created by vjames19 on 3/5/15.
 */
public class JobAssignmentProblemRunner {

    private static int[][] createCostMatrix(int n) {
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = (int) (Math.random() * 100);
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        long minute = TimeUnit.MINUTES.toNanos(1);
        int n = 8;
        long executionTime = 0;
        Timer timer = new Timer();

        while (executionTime < minute) {
            int[][] cost = createCostMatrix(n);

            JobAssignmentProblemSolver solver = new JobAssignmentProblemSolver(cost);
            timer.start();

            solver.solve();
            executionTime = timer.stop();
            System.out.printf("cost matrix of size %d took %f ms\n", n, executionTime / 1000000.0);
            n++;
        }

        System.out.println(n);
    }
}
