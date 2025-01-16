import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        float[] unsortedArr = {3, 7, 6, -10, 15, 23, 55, -13};
        Merge_Sort merge;
        merge = new Merge_Sort();
        System.out.println(Arrays.toString(merge.mergeSort(unsortedArr)));

    }
}


