package com.vjames19.generators;

public interface P_ADT {
    boolean hasMore();
    int[] next() throws IllegalArgumentException;
    void reset();
} 