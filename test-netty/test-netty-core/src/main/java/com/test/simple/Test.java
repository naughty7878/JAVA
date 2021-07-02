package com.test.simple;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        System.out.println("integers = " + integers);
        HashSet<Integer> integers1 = new HashSet<>(integers);
        System.out.println("integers1 = " + integers1);
        Set<Integer> integers2 = Collections.unmodifiableSet(integers1);
        integers2.add(5);
        System.out.println("integers2 = " + integers2);
    }
}
