package com.vjames19;

/**
 * A MobileEntity has a direction either left or right.
 */
public class MobileEntity<E extends Comparable<E>> implements Comparable<MobileEntity<E>>  {

    private E entity;
    private int direction = -1;

    public MobileEntity(E entity) {
        this.entity = entity;
    }

    public void reverseDirection() {
        direction = -direction;
    }

    public boolean goingLeft() {
        return direction == -1;
    }

    public boolean goinRight() {
        return direction == 1;
    }

    @Override
    public int compareTo(MobileEntity<E> other) {
        return entity.compareTo(other.entity);
    }

    public E getEntity() {
        return entity;
    }
}
