package com.level1.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 *
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 *
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *
 * 示例:
 *
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 *
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 *
 * 所以，4 是第一个错误的版本。 
 *
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnto1s/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class FirstBadVersion {

    private int count = 0;

    public int firstBadVersion(int n) {

        int left = 1;
        int right = n;
        while (left != right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public boolean isBadVersion(int n) {
//        给定 n = 5，并且 version = 4 是第一个错误的版本。
//
//        调用 isBadVersion(3) -> false
//        调用 isBadVersion(5) -> true
//        调用 isBadVersion(4) -> true
        count++;
        if (n <= 3) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        FirstBadVersion version = new FirstBadVersion();
        int i = version.firstBadVersion(2126753390);
        System.out.printf("n = %d, count = %d", i, version.count);
    }
}
