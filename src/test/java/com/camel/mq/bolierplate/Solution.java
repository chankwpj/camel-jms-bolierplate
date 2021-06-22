package com.camel.mq.bolierplate;

import java.util.Arrays;
import java.util.LinkedList;

//https://app.codility.com/programmers/challenges/may_the_4th_2021/
//https://app.codility.com/demo/results/trainingVMDP5T-BU8/
class Solution {
    public int solution(int[] A, int L, int R) {
        // write your code in Java SE 8
        Arrays.sort(A);

        LinkedList<Integer> qAsc = new LinkedList<>();
        for(int i : A) {
            qAsc.addLast(i);
        }

        LinkedList<Integer> qDesc = new LinkedList<>();

        int counter = 0;
        int pCount;
        do {
            pCount = qAsc.size() + qAsc.size();
            while (qAsc.size() > 0) {
                int d = qAsc.poll();
                if (d > R) {
                    R = d;
                    counter++;
                } else {
                    qDesc.addFirst(d);
                }
            }

            while (qDesc.size() > 0) {
                int d = qDesc.poll();
                if (d < L) {
                    L = d;
                    counter++;
                } else {
                    qAsc.addFirst(d);
                }
            }
        } while (qAsc.size() + qAsc.size() != pCount);

        return counter;
    }
}
