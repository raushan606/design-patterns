package com.raushan;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    
        String[] result = divideString("abcdefghij", 3, 'x');
        System.out.println("The divided string is: " + Arrays.toString(result));
    }

        public static String[] divideString(String s, int k, char fill) {

        while (s.length() % k != 0) {
            s += fill + "";
        }

        String[] res = new String[s.length() / k];
        int j = 0;
        for (int i = 0; i < s.length(); i += k) {
            res[j++] = s.substring(i, i + k);
        }
        return res;
    }
}