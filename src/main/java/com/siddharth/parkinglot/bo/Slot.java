package com.siddharth.parkinglot.bo;

/**
 * Created by Siddharth on 12/7/17.
 */
public class Slot {

    private int id;

    public Slot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slot slot = (Slot) o;

        return id == slot.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
