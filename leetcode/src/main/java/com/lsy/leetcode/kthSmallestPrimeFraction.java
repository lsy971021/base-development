package com.lsy.leetcode;

import org.junit.Test;

/**
 * leetcode 786. 第 K 个最小的素数分数
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数组成，且其中所有整数互不相同。
 * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
 * 那么第k个最小的分数是多少呢?以长度为 2 的整数数组返回你的答案, 这里answer[0] == arr[i]且answer[1] == arr[j] 。
 * <p>
 * 示例 ：
 * 输入：arr = [1,2,3,5], k = 3
 * 输出：[2,5]
 * 解释：已构造好的分数,排序后如下所示:
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 * 很明显第三个最小的分数是 2/5
 */
public class kthSmallestPrimeFraction {

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        if (arr.length < 4) {
            if (arr.length == 2) {
                return new int[]{arr[0], arr[1]};
            }
            boolean isSmall = arr[0] * arr[2] <= arr[1] * arr[1];
            switch (k) {
                case 1:
                    return new int[]{arr[0], arr[arr.length - 1]};
                case 2: {
                    if (isSmall) return new int[]{arr[0], arr[1]};
                    else return new int[]{arr[1], arr[2]};
                }
                case 3: {
                    if (!isSmall) return new int[]{arr[0], arr[1]};
                    else return new int[]{arr[1], arr[2]};
                }
            }
        }
        int count = 4;
        int big = arr.length - 1;
        int small = 0;
        int oneY = small + 1;
        int oneX = big;
        int anotherX = big - 2;
        int anotherY = small;
        int x = big;
        while (true) {
            if (anotherX + 1 == oneX) {
                if (++count == k)
                    return new int[]{arr[anotherY], arr[anotherX]};
                oneX++;
                oneY++;
                anotherX++;
                anotherY++;

            }
            if (arr[oneY] * arr[anotherX] <= arr[anotherY] * arr[oneX]) {
                if (count == k)
                    return new int[]{arr[oneY], arr[oneX]};
                if (oneX != big) {
                    oneX++;
                    oneY++;
                    count++;
                } else {
                    oneX = --x;
                    count++;
                    if (anotherX + 1 == oneX) {
                        if (anotherX - 1 > small) {
                            if (count++ == k) {
                                return new int[]{arr[anotherY], arr[anotherX]};
                            }
                            anotherX--;
                            count++;
                        }
                    }
                }
            } else {
                if (count++ == k)
                    return new int[]{arr[anotherY], arr[anotherX]};
                if (anotherX - 1 > small) {
                    anotherX--;
                } else {
                    if (oneX != big) {
                        if(count == k) {
                            return new int[]{arr[oneY], arr[oneX]};
                        }
                        oneX++;
                        oneY++;
                    } else {
                        oneX = --x;
                        oneY = small + 1;
                    }
                }
            }
        }
    }

    @Test
    public void test() {
        int[] ints = kthSmallestPrimeFraction1(new int[]{1, 7, 23, 29, 47}, 8);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


    public int[] kthSmallestPrimeFraction1(int[] arr, int k) {
        int count = 1;
        int big = arr.length - 1;
        int oneY = 0;
        int oneX = big;
        int anotherX = big - 1;
        int anotherY = 0;
        int x = big;
        int y = 0;
        if(count==k)    return create(arr[oneY], arr[oneX]);
        while (true) {
            if(anotherX+1==oneX && anotherY==oneY){
                if(oneY<big && y<big){
                    oneY++;
                    y++;
                }
            }
            if(anotherY * oneX > oneY * anotherX) {
                if(++count==k)
                    return create(arr[oneY], arr[oneX]);
                if(oneX==big){
                    if(x>1)
                        oneX = --x;
                    oneY = y;
                } else{
                    oneX++;
                    if(oneY<big)    oneY++;
                }
            }else {
                if (++count == k) return create(arr[anotherY], arr[anotherX]);
                if(anotherX>1 && anotherY+1!=anotherX)
                    anotherX--;
                else{
                    if(oneX-1 != anotherX+1 && oneY+1!=oneX) {
                        anotherX = oneX - 1;
                        anotherY = oneY;
                    }else {
                        if(++anotherX==oneX && ++anotherY==oneY){
                            anotherX++;
                            anotherY++;
                        }
                    }
                }
            }
        }
    }

    public int[] create(int x,int y){
        return new int[]{x, y};
    }
}
