package com.tencent.arrstring;

public class MaxArea {

    /**
     * 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器。
     * 示例 1：
     * <p>
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
     * 示例 2：
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/tencent/xxcmit/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。s
     *
     * @param
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        int resualt = new MaxArea().maxArea(height);
        System.out.println("resualt = " + resualt);
//        String[] str1 = new String[5];
//        String[] str2 = new String[]{"11", "22"};
//        String[] str3 = {"33", "44"};

    }

    public int maxArea01(int[] height) {

        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int minHeigt = height[i] < height[j] ? height[i] : height[j];
                int area = minHeigt * (j - i);

                maxArea = area > maxArea ? area : maxArea;

            }
        }
        return maxArea;
    }


}
