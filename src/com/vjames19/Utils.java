package com.vjames19;

import java.util.Collection;

/**
 * Created by vjames19 on 2/22/15.
 */
public class Utils {

    private Utils() {}

    public static int[] toArray(Collection<Integer> collection) {
        int[] array = new int[collection.size()];
        int i = 0;
        for(Integer integer : collection) {
            array[i] = integer;
            i++;
        }

        return array;
    }

    public static <T extends Comparable<T>> boolean isGreater(T c1, T c2) {
        return c1.compareTo(c2) > 0;
    }
}
