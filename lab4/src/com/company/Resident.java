package com.company;

import java.util.Objects;

public class Resident implements Comparable<Resident> {

    private String name;

    public Resident(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return Objects.equals(name, resident.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int compareTo(Resident o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
