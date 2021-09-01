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
    public void t1() {
        String version1 = "1.01";
        String version2 = "1.00000003";
        System.out.println(compareVersion(version1, version2));
    }

    public int compareVersion(String version1, String version2) {
        return compare(version1, version2);
    }

    public int compare(String version1, String version2) {
        int v1 = 0, v2 = 0, indexV1 = 0, indexV2 = 0;
        boolean v1IsPoint = true;
        boolean v2IsPoint = true;
        while (true) {
            if (v1 == v2) {
                if (version1.length() > indexV1) v1 = filterChar(version1, indexV1++, v1IsPoint);
                if (version2.length() > indexV2) v2 = filterChar(version2, indexV2++, v2IsPoint);
                if(indexV1>=version1.length() || indexV2>=version2.length()) v1=-1;
                if(indexV2>=version2.length())  v2=-1;
            } else {
                int a = version1.length() - indexV1;
                int b = version2.length() - indexV2;
                for (int i = 0; i < (a<b?a:b); i++) {
                    if(version1.charAt(indexV1++) == '.' || version2.charAt(indexV2++) == '.'){
                        if(version1.charAt(indexV1++) == '.' && version2.charAt(indexV2++) == '.')  return a<b?-1:1;
                        return version1.charAt(indexV1++) == '.'?-1:1;
                    }
                }
                return a<b?-1:1;
            }
        }
    }

    public int filterChar(String version, int index, boolean isPoint) {
        if (version.length() <= index) return version.charAt(index - 1);
        char c = version.charAt(index);
        if ((isPoint && c == '0') || c == '.') {
            isPoint = true;
            return filterChar(version, ++index, isPoint);
        }
        isPoint = false;
        return version.charAt(index);
    }

}
