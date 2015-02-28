package com.vjames19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.vjames19.Utils.isGreater;

/**
 * Created by vjames19 on 2/22/15.
 */
public class PermGenerator_v2 implements P_ADT {


    private List<MobileEntity<Integer>> entityList;

    /**
     * Range of numbers [0..range-1]
     */
    private int range;

    private int sequenceLength;
    private boolean hasMore = true;


    /**
     * Generates a permutation sequence with n Range and k length.
     * @param n Range of the number of this sequence [0..n-1]
     * @param k length of the sequence
     */
    public PermGenerator_v2(int n, int k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException();
        }

        entityList = new ArrayList<>(k);
        range = n;
        sequenceLength = k;

        reset();
    }
    @Override
    public boolean hasMore() {
        return hasMore;
    }

    @Override
    public int[] next() throws IllegalStateException {
        if (!hasMore()) {
            throw new IllegalStateException();
        }

        int[] current = toArray();

        int largestMobileIndex = getLargestMobileEntityIndex();
        if (largestMobileIndex < 0) {
            hasMore = false;
            return current;
        }


        MobileEntity<Integer> largestEntity = entityList.get(largestMobileIndex);
        // Swap the adjacent element in the direction of the largest mobile entity.
        if (largestEntity.goinRight()) {
            Collections.swap(entityList, largestMobileIndex, largestMobileIndex + 1);
        } else {
            Collections.swap(entityList, largestMobileIndex, largestMobileIndex - 1);
        }

        // Reverse the direction of the elements larger than the largest mobile.
        for (int i = 0; i < entityList.size(); i++) {
            MobileEntity<Integer> currentEntity = entityList.get(i);
            if (i != largestMobileIndex && isGreater(currentEntity, largestEntity)) {
                currentEntity.reverseDirection();
            }
        }

        return current;
    }

    @Override
    public void reset() {
        hasMore = true;
        entityList.clear();
        for (int i = 0; i < range; i++) {
            entityList.add(new MobileEntity<>(i));
        }
    }

    private int getLargestMobileEntityIndex() {
        int maxIndex = -1;
        for (int i = 0; i < entityList.size(); i++) {
            MobileEntity<Integer> current = entityList.get(i);
            if (isMobile(i)) {
                if (maxIndex < 0) {
                    maxIndex = i;
                } else if (isGreater(current, entityList.get(maxIndex))) {
                    maxIndex = i;
                }
            }
        }

        return maxIndex;
    }

    private boolean isMobile(int index) {
        MobileEntity<Integer> entity = entityList.get(index);
        if (entity.goingLeft() && index != 0 && entity.compareTo(entityList.get(index - 1)) > 0) {
            return true;
        }

        if (entity.goinRight() && index != entityList.size() - 1 && entity.compareTo(entityList.get(index + 1)) > 0) {
            return true;
        }

        return false;
    }

    private int[] toArray() {
        int[] array = new int[sequenceLength];

        for (int i = 0; i < entityList.size(); i++) {
            array[i] = entityList.get(i).getEntity();
        }

        return array;
    }
}
