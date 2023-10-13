package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainTestStream {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 3, 5, 1, 3, 7, 2, 5}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> x * 10 + y);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream().collect(Collectors.groupingBy(value -> value % 2 == 0));
        return map.get(false).size() % 2 != 0 ? map.get(false) : map.get(true);
    }
}