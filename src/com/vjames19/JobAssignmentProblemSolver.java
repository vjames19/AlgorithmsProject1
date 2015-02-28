package com.vjames19;

import java.util.Arrays;

/**
 * Created by vjames19 on 2/22/15.
 */
public class JobAssignmentProblemSolver {

    private PermGenerator_v1 permutationGenerator;
    private int[][] cost;

    public JobAssignmentProblemSolver(int[][] cost) {
        this.cost = cost;
        permutationGenerator = new PermGenerator_v1(cost.length, cost.length);
    }

    public int[] solve() {

        int[] min = null;
        int minCost = Integer.MAX_VALUE;

        while(permutationGenerator.hasMore()) {
            int[] jobIndex = permutationGenerator.next();

            int currentCost = 0;
            for (int person = 0; person < cost.length; person++) {
                currentCost += cost[person][jobIndex[person]];
            }

            if (currentCost < minCost) {
                minCost = currentCost;
                min = jobIndex;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        int[][] cost = {
                {1, 2, 3},
                {3, 1, 2},
                {2, 3, 30}
        };

        JobAssignmentProblemSolver solver = new JobAssignmentProblemSolver(cost);
        System.out.println(Arrays.toString(solver.solve()));
    }
}
