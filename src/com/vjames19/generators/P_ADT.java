package com.vjames19.generators;

import sun.plugin.dom.exception.InvalidStateException;

public interface P_ADT {
    boolean hasMore();
    int[] next() throws InvalidStateException;
    void reset();
} 