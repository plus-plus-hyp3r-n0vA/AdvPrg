package com.company;

import java.util.*;
import java.util.stream.IntStream;

import com.github.javafaker.*;


public class Main {

    public static void main(String[] args) {
        Resident[] r = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Resident("R" + i))
                .toArray(Resident[]::new);
        Hospital[] h = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Hospital("H" + i, i + 1))
                .toArray(Hospital[]::new);
        h[2].setCapacity(2);

        List<Resident> residentList = new ArrayList<>(Arrays.asList(r));
        Collections.sort(residentList);
        Set<Hospital> hospitalSet = new TreeSet<>(Arrays.asList(h));

        System.out.println(residentList);
        System.out.println(hospitalSet);

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0], h[1]));


        Map<Hospital, List<Resident>> hosPrefMap = new TreeMap<>();
        hosPrefMap.put(h[0], Arrays.asList(r[3], r[0], r[1], r[2]));
        hosPrefMap.put(h[1], Arrays.asList(r[0], r[2], r[1]));
        hosPrefMap.put(h[2], Arrays.asList(r[0], r[1], r[3]));

        System.out.println(resPrefMap);
        System.out.println(hosPrefMap);

        System.out.println("the residents who find acceptable H0 and H2");
        List<Hospital> target = Arrays.asList(h[0], h[2]);
        Arrays.stream(r)
                .filter(res -> resPrefMap.get(res).containsAll(target))
                .forEach(System.out::println);
        System.out.println("hospitals that have R0 as their top preference:");
        Arrays.stream(h)
                .filter(hos -> hosPrefMap.get(hos).get(0).equals(r[0]))
                .forEach(System.out::println);

        Faker faker = new Faker();
        System.out.println(faker.name());
    }
}
