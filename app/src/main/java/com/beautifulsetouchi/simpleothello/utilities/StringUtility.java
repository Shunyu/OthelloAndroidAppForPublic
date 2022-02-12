package com.beautifulsetouchi.simpleothello.utilities;

/**
 * Stringに関連するutilityクラス
 */
public class StringUtility {

    /**
     * int型の配列に格納された数値を文字列に変換する。
     * @param numbers
     * @return
     */
    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            // StringBuilderにintをappendしているがOK?
            sNumbers.append(number);
        return sNumbers.toString();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
