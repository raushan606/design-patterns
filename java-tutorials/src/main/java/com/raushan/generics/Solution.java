package com.raushan.generics;

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] happiness = { 12,1,42};
        System.out.println(sol.maximumHappinessSum(happiness, 3));
    }
    public long maximumHappinessSum(int[] happiness, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int h : happiness) {
            maxHeap.add(h);
        }
        long res = 0L;
        int cnt = 0;
        while (k > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            int val = top - cnt;
            cnt++;
            k--;
            res += val;
        }
        return res;
    }
}