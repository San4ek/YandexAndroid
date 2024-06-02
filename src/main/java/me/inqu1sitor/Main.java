package me.inqu1sitor;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int value;
    static int maxWeight;
    static int[] mellonList;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            value = in.nextInt();
            maxWeight = in.nextInt();

            mellonList = new int[value];

            int mellon;

            for (int i = 0; i < value; ++i) {
                if ((mellon = in.nextInt()) <= maxWeight) {
                    mellonList[i] = mellon;
                } else {
                    System.out.println("Impossible");
                    System.exit(-1);
                }
            }
        }

        Instant startInst=Instant.now();
        int count = 0;

        int start = 0;
        int end = value - 1;

        Instant sortStart=Instant.now();

        Arrays.sort(mellonList);
        //QuickSort(mellonList, start, end);
        //Встроенная сортировка быстрее
        Instant sortEnded=Instant.now();

        while (start < end) {
            int mellon = mellonList[start];
            ++count;

            while (start < end && mellonList[end--] > maxWeight - mellon) {
                ++count;
            }

            ++start;
        }

        if ((value & 1) == 1) {
            ++count;
        }
        Instant processEnded=Instant.now();

        System.out.println("All time consumption: "+ Duration.between(startInst,processEnded));
        System.out.println("Sorting: "+Duration.between(sortStart,sortEnded));
        System.out.println("Process consumption: "+Duration.between(sortEnded,processEnded));

        System.out.println(count);
    }

    public static void QuickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int partition = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < partition) {
                i++;
            }

            while (array[j] > partition) {
                j--;
            }

            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (low < j)
            QuickSort(array, low, j);

        if (high > i)
            QuickSort(array, i, high);
    }

    public static void swap(int[] arr, int i, int j) {
        int swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp;
    }
}