package com.lemon.commons.spider;

/**
 * Created by bob on 2017/1/17.
 */
public class StringPlus {

    public static int lastIndexOf(String wholeStr, String subStr, int fromIndex, int count) {
        int pos = fromIndex + 1;
        do {
            pos = wholeStr.lastIndexOf(subStr, pos-1);
            --count;
        } while(count > 0 && pos>=0);

        return pos;
    }

    public static int indexOf(String wholeStr, String subStr, int fromIndex, int count) {
        int pos = fromIndex - 1;
        do {
            pos = wholeStr.indexOf(subStr, pos+1);
            --count;
        } while(count>0 && pos>=0);

        return pos;
    }
}
