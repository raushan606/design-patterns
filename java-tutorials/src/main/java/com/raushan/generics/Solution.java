package com.raushan.generics;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] nums = {87, 15, 26, 32, 32, 18};
        System.out.println(obj.minOperations(nums));
    }
    public int minOperations(int[] nums) {
        int i = 0;
        int n = nums.length;
        int count = 0;
        Set<Integer> st = new HashSet<>();
        if (n < 3) {
            for (int x = 0; x<n; x++) {
                st.add(nums[x]);
            }
            if (st.size() != n) {
                count++;
            }
        }
        while (i <= n - 3) {
            int j = 0;
            while (j < 3 && n > j) {
                st.add(nums[i + j]);
                j++;
            }

            if (st.size() != 3) {
                count++;
                i = i + 3;
            } else {
                i++;
            }
            st = new HashSet<>();
        }
        return count;
    }
}