package com.lsy.leetcode;

import org.junit.Test;

public class Code {
    /**
     * 给你两个版本号 version1 和 version2 ，请你比较它们。
     * <p>
     * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。
     * 每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。
     * 例如，2.5.33 和 0.1 都是有效的版本号。
     * <p>
     * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。
     * 也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。
     * 例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
     * <p>
     * 返回规则如下：
     * <p>
     * 如果 version1 > version2 返回 1，
     * 如果 version1 < version2 返回 -1，
     * 除此之外返回 0。
     * <p>
     * 示例 ：
     * <p>
     * 输入：version1 = "1.01", version2 = "1.001"     输出：0
     * 输入：version1 = "1.0", version2 = "1.0.0"      输出：0
     * 输入：version1 = "0.1", version2 = "1.1"        输出：-1
     * 输入：version1 = "1.0.1", version2 = "1"        输出：1
     * 输入：version1 = "7.5.2.4", version2 = "7.5.3"  输出：-1
     */

    @Test
    public int t2(String version1, String version2) {
        int bigger = version1.length() > version2.length() ? version1.length() : version2.length();
        char v1 = '-', v2 = '-';
        int v1Index = 0, v2Index = 0, valueV1 = -1, valueV2 = -2;
        boolean v1Right = true, v2Right = true;
        boolean begin = false;
        for (int i = 0; i < bigger; i++) {
            if (v1Index > i || v2Index > i) {
                if (v1Index > i && v2Index > i) {
                    if (valueV1 == valueV2) return 0;
                }
                if (v1Index > i) {
                    while (true) {
                        if (++v2Index < version2.length()) {

                        }
                    }
                }
                return -1;
            }
            if (begin) {
                //int one = Integer.valueOf(String.valueOf(version1.charAt(indexV1++)));
                //int another = Integer.valueOf(String.valueOf(version2.charAt(indexV2++)));
                char one = version1.charAt(indexV1++);
                char another = version2.charAt(indexV2++);
                if (one == '.' || another == '.') {
                    if (one == '.' && another == '.') {
                        return valueV1 > valueV2 ? 1 : -1;
                    }
                    if (one == '.') return -1;
                    return 1;
                }
                continue;
            }
            boolean v1Cor = (v1Right && v1 == '0') || v1 == '.';
            boolean v2Cor = (v2Right && v2 == '0') || v2 == '.';
            if (v1Cor || v2Cor) {
                if (v1Cor && v2Cor) {
                    v1Right = true;
                    v2Right = true;
                    v1Index++;
                    v2Index++;
                    continue;
                }
                if (v1Cor) {
                    v1Right = true;
                    v1Index++;
                    continue;
                }
                v2Index++;
                v2Right = true;
                continue;
            }
            if (v1Right) v1 = version1.charAt(indexV1);
            if (v2Right) v2 = version2.charAt(indexV2);
            valueV1 = Integer.valueOf(String.valueOf(v1));
            valueV2 = Integer.valueOf(String.valueOf(v2));
            if (valueV1 == valueV2) {
                v1Index++;
                v2Index++;
                v1Right = false;
                v2Right = false;
                continue;
            } else begin = true;         // 11.332.1   11.2333.1
        }
        return 0;
    }


    @Test
    public void t1() {
        String version1 = "1.01";
        String version2 = "1.00000001";
        System.out.println(compareVersion(version1, version2));
    }

    public int compareVersion(String version1, String version2) {
        return compare(version1, version2);
    }

    boolean v1IsPoint = true;
    boolean v2IsPoint = true;
    int indexV1 = 0, indexV2 = 0;

    public int compare(String version1, String version2) {
        int v1 = 0, v2 = 0;

        while (true) {      // 1.001   1.01
            if (v1 == v2) {
                if (v1 == -1) return 0;
                if (version1.length() > indexV1) v1 = filterChar(version1, true);
                else v1 = -1;
                if (version2.length() > indexV2) v2 = filterChar(version2, false);
                else v2 = -1;
            } else {
                int a = version1.length() - indexV1;
                int b = version2.length() - indexV2;
                for (int i = 0; i < (a < b ? a : b); i++) {
                    if (version1.charAt(indexV1++) == '.' || version2.charAt(indexV2++) == '.') {
                        if (version1.charAt(indexV1++) == '.' && version2.charAt(indexV2++) == '.')
                            return a < b ? -1 : 1;
                        return version1.charAt(indexV1++) == '.' ? -1 : 1;
                    }
                }
                return a < b ? -1 : 1;
            }
        }
    }

    public int filterChar(String version, boolean isV1) {
        if (version.length() <= (isV1 ? indexV1 : indexV2))
            return Integer.valueOf(String.valueOf(version.charAt((isV1 ? indexV1 : indexV2) - 1)));
        char c = version.charAt(isV1 ? indexV1 : indexV2);
        boolean isPoint = isV1 ? v1IsPoint : v2IsPoint;
        if ((isPoint && c == '0') || c == '.') {
            if (isV1) {
                ++indexV1;
                v1IsPoint = true;
            } else {
                ++indexV2;
                v2IsPoint = true;
            }
            return filterChar(version, isV1);
        }
        if (isV1) v1IsPoint = false;
        else v2IsPoint = false;
        return Integer.valueOf(String.valueOf(version.charAt(isV1 ? indexV1 : indexV2)));
    }

}
