package com.test.netty.nio.zerocopy;

public class Test {
    public static void main(String[] args) {
        int[] nioNums = {89, 91, 95, 71, 60, 124, 71, 126, 81, 88};
        int[] bioNums = {20, 31, 28, 17, 65, 36, 36, 25, 20, 29};
        System.out.println("avgNioNums = " + avg(nioNums));
        System.out.println("avgBioNums = " + avg(bioNums));
    }
    
    public static Integer avg(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return total / nums.length;
    }
}
