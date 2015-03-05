package com.vjames19;

import com.vjames19.generators.PermGenerator_v2;

import java.util.Arrays;

/**
 * Solves the job assignment problem using a brute-force algorithm. It generates all the possible permutations and
 * searches for the one with the minimum cost.
 */
public class JobAssignmentProblemSolver {

    private PermGenerator_v2 permutationGenerator;
    private int[][] cost;

    public JobAssignmentProblemSolver(int[][] cost) {
        this.cost = cost;
        permutationGenerator = new PermGenerator_v2(cost.length, cost.length);
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
