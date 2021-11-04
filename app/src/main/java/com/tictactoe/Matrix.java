package com.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {

    private final int size;
    private final List<List<Integer>> values = new ArrayList<>();

    public Matrix(int s) {
        size = s;
        IntStream.range(0, size)
                .forEach(i -> values.add(
                        IntStream.range(0, size).mapToObj(j -> j + i * size).collect(Collectors.toList())
                ));
        IntStream.range(0, size)
                .forEach(i -> values.add(
                        IntStream.range(0, size).mapToObj(j -> j * size + i).collect(Collectors.toList())
                ));
        List<Integer> sum = IntStream.range(0, values.get(0).size()).mapToObj(i -> values.get(0).get(i) + values.get(size).get(i)).collect(Collectors.toList());
        values.add(sum);

        values.add(IntStream.range(0, size).mapToObj(j -> (size - 1) + j * (size - 1)).collect(Collectors.toList()));

        System.out.println(values);
    }

    public int getSize() {
        return size;
    }

    public List<List<Integer>> getValues() {
        return values;
    }
}

