package com.test.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        System.out.println(10_000_000);
    }


    Map<Key, Integer> map;
    int mapSize = 100;

    @Test
    public void setUp() throws Exception {
        Map<Key, Integer> map = new HashMap();
        map = new HashMap<>(mapSize);
        long start = System.currentTimeMillis();
        for (int i = 0; i < mapSize; ++i) {
            map.put(Keys.of(i), i);
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("map.size() = " + map.size());
        System.out.println("time = " + time);
        System.out.println("time/mapSize = " + time/mapSize);

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < mapSize; i++) {
            map.get(Keys.of(i % mapSize));
        }
        long end2 = System.currentTimeMillis();
        long time2 = end2 - start2;
        System.out.println("map.size() = " + map.size());
        System.out.println("time2 = " + time2);
        System.out.println("time2/mapSize = " + time2/mapSize);
    }
    public void timeMapGet(int reps) {
        for (int i = 0; i < reps; i++) {
            map.get(Keys.of(i % mapSize));
        }
    }
}

class Key implements Comparable<Key> {
    private final int value;
    Key(int value) {
        this.value = value;
    }
    @Override
    public int compareTo(Key o) {
        return Integer.compare(this.value, o.value);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Key key = (Key) o;
        return value == key.value;
    }
    @Override
    public int hashCode() {
        return value;
    }
}


class Keys {
    public static final int MAX_KEY = 10_000_000;
    private static final Key[] KEYS_CACHE = new Key[MAX_KEY];

    static {
        for (int i = 0; i < MAX_KEY; ++i) {
            KEYS_CACHE[i] = new Key(i);
        }
    }

    public static Key of(int value) {
        return KEYS_CACHE[value];
    }
}


//class MapBenchmark extends SimpleBenchmark {
//    private HashMap<Key, Integer> map;
//
//    @Param
//    private int mapSize;
//
//    @Override
//    protected void setUp() throws Exception {
//        map = new HashMap<>(mapSize);
//        for (int i = 0; i < mapSize; ++i) {
//            map.put(Keys.of(i), i);
//        }
//    }
//    public void timeMapGet(int reps) {
//        for (int i = 0; i < reps; i++) {
//            map.get(Keys.of(i % mapSize));
//        }
//    }
//}