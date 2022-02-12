package com.beautifulsetouchi.simpleothello.utilities;

import java.util.List;

/**
 * 2種類のリストを与えた際に、長さが短い方のリストの長さを計算させるクラス
 */
public class MinListLengthCalculator {

    public int getMinListLength(
            List<Integer> list1,
            List<Integer> list2
    ) {
        int list1Length = list1.size();
        int list2Length = list2.size();
        int minListLength = Math.min(list1Length, list2Length);

        return minListLength;
    }
}
