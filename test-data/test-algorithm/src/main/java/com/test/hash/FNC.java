package com.test.hash;

import org.junit.Test;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * FNV算法
 * 参考：https://www.cnblogs.com/charlieroro/archive/2018/03/01/8486941.html
 */
public class FNC {

    public static int FNV1_32_HASH(String data) {
        //  p = FNV_prime：FNV用于散列的质数
        final int p = 16777619;
        // hash = offset_basis：初始的哈希值
        int hash = (int)2166136261L;
        for(int i=0;i<data.length();i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    /**
     * 测试散列IP的情况
     */
    @Test
    public void testIP_FNV1_32_HASH() {
        // 总个数
        long totalIp = 0;
        // 小于0个数
        long ltCount = 0;
        // 等于0个数
        long eqCount = 0;
        // 大于0个数
        long gtCount = 0;
        int maxIpHash = 0;
        int minIpHash = 0;

        SortedMap<Integer, Long> sortedMap = new TreeMap<>();
        // 生成ip
//        int[] ip = new int[]{0, 0, 0, 0};
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    for (int l = 0; l < 100; l++) {
//                        ip[0] = i;
//                        ip[1] = j;
//                        ip[2] = k;
//                        ip[3] = l;
                        StringBuilder ip = new StringBuilder();
                        ip.append(i);
                        ip.append(".");
                        ip.append(j);
                        ip.append(".");
                        ip.append(k);
                        ip.append(".");
                        ip.append(l);
                        int hash = FNC.FNV1_32_HASH(ip.toString());

                        // 统计
                        // 小于0个数
                        if(hash < 0) {
                            minIpHash = Math.min(hash, minIpHash);
                            ltCount++;
                        } else if (hash == 0) {
                            eqCount++;
                        } else {
                            maxIpHash = Math.max(hash, maxIpHash);
                            gtCount++;
                            // 计算正数的区间
                            int key = hash / (Integer.MAX_VALUE / 100);
                            if (key == 100) {
                                System.out.println("key = " + key + "\thash = " + hash);
                            }
                            sortedMap.put(key, sortedMap.getOrDefault(key, 0l) + 1);
                        }
                        totalIp++;
                    }
                }
            }
        }

        // ----------------------
        long endTime = System.currentTimeMillis();
        System.out.println("endTime - startTime = " + (endTime - startTime));

        System.out.println("totalIp = " + totalIp);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("minIpHash = " + minIpHash);
        System.out.println("maxIpHash = " + maxIpHash);
        System.out.println("ltCount = " + ltCount);
        System.out.println("eqCount = " + eqCount);
        System.out.println("gtCount = " + gtCount);
        long[] mapArr = new long[]{Long.MAX_VALUE, 0l, 0l, 0l};
        sortedMap.forEach((k, v) -> {
            long min = Math.min(v, mapArr[0]);
            if (min != mapArr[0]) {
                mapArr[1] = mapArr[0];
                mapArr[0] = min;
            }
            long max = Math.max(v, mapArr[3]);
            if (max != mapArr[3]) {
                mapArr[2] = mapArr[3];
                mapArr[3] = max;
            }
            System.out.println("(k, v) = " + "("+k+", "+v+")");
        });
        System.out.println("Arrays.toString(mapArr) = " + Arrays.toString(mapArr));
        System.out.println("-------------------");
//        System.out.println("256*256*256*256 = " + 256l*256*256*256);
    }
}
