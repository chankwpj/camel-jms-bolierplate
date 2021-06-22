package com.camel.mq.bolierplate;

public class Solution {

    public static String total(String[] groceryList) {
        String out = "";
        int sum = 0;
        for (String item : groceryList) {
            final int[] pair = getIntegerFromString(item, item.length() - 1);
            if (pair[0] == -1 && pair[1] == -1) {
                continue;
            }
            int index = pair[0];
            int amount = pair[1];

            int price = getIntegerFromString(item, index - 2)[1];

            final int subTotal = amount * price;
            if (out.length() != 0 ) {
                out += "+" + subTotal;
            } else {
                out += subTotal;
            }
            sum += subTotal;
        }
        return out + "=" + sum;
    }

    private static int[] getIntegerFromString(final String item, final int start) {
        //index, value
        int[] pair = new int[]{-1, -1};
        System.out.println(item);
        for (int i = start ; i >=0 ; i --) {
            System.out.println("substring index: " + i + " " + (start + 1));
            final String subString = item.substring(i, start + 1);
            System.out.println("subString: " + subString);
            if (isInt(subString)) {
                pair = new int[]{i, toInt(subString)};
            } else {
                return pair;
            }
        }
        return pair;
    }

    private static int toInt(String s) {
        return Integer.valueOf(s);
    }

    private static boolean isInt(String s) {
        try {
            return Integer.valueOf(s) > 0;
        } catch (Exception e) {
            return false;
        }
    }

}
