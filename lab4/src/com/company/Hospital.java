package com.company;

import java.util.Objects;

public class Hospital implements Comparable<Hospital>{
    private String name;
    private int capacity;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return capacity == hospital.capacity &&
                Objects.equals(name, hospital.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity);
    }

    public int compareTo(Hospital o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
