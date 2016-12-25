package com.adventofcode.day24;

import java.util.Arrays;
import java.util.Iterator;

import static java.util.Arrays.copyOf;

public class Permutation implements Iterable<int[]> {

    private int[] initial;

    public Permutation(int[] initial) {
        this.initial = copyOf(initial, initial.length);
        Arrays.sort(this.initial);
    }

    @Override
    public Iterator<int[]> iterator() {
        return new PermIterator(initial);
    }

    private static class PermIterator implements Iterator<int[]> {

        private int[] current;
        private boolean hasNext = true;

        public PermIterator(int[] initial) {
            this.current = copyOf(initial, initial.length);
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public int[] next() {
            int[] result = copyOf(current, current.length);
            hasNext = makeNextPermutation();

            return result;
        }

        private boolean makeNextPermutation() {
            int pivot = findNonIncreasingSuffixStart(current) - 1;

            if (pivot < 0) {
                return false;
            }

            int successor = findRightmostSuccessor(current, pivot);

            swap(current, pivot, successor);
            reverse(current, pivot + 1, current.length - 1);

            return true;
        }

        private int findNonIncreasingSuffixStart(int[] permutation) {
            int suffixStart = permutation.length - 1;
            while (suffixStart > 0 && permutation[suffixStart - 1] >= permutation[suffixStart]) {
                suffixStart--;
            }

            return suffixStart;
        }

        private int findRightmostSuccessor(int[] permutation, int pivot) {
            int successor = permutation.length - 1;
            while (permutation[successor] <= permutation[pivot]) {
                successor--;
            }

            return successor;
        }

        private void reverse(int[] array, int from, int to) {
            while (from < to) {
                swap(array, from++, to--);
            }
        }

        private void swap(int[] array, int from, int to) {
            int tmp = array[from];
            array[from] = array[to];
            array[to] = tmp;
        }
    }

}
