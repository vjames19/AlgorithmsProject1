package com.vjames19;

public interface P_ADT {
    boolean hasMore();
    int[] next() throws IllegalStateException;
    void reset();
} 