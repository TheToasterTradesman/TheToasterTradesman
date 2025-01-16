import java.util.Arrays;

public class Merge_Sort {
    public float[] mergeSort(float[] Array) {
        if (Array.length <= 1) {
            return Array;

        }
        //this splits the data up into a single character
        int middle = Array.length / 2;
        float[] LeftHalf = Arrays.copyOfRange(Array, 0, middle);
        float[] rightHalf = Arrays.copyOfRange(Array, middle, Array.length);


        float[] sortedRight = mergeSort(rightHalf);
        float[] sortedLeft = mergeSort(LeftHalf);

        return merge(sortedLeft, sortedRight);
    }

    public float[] merge(float[] Left, float[] Right) {
        float[] result = new float[Left.length + Right.length];
        int i = 0, j = 0, k = 0;

        while (i < Left.length && j < Right.length) {
            if (Left[i] < Right[j]) {
                result[k++] = Left[i++];
            } else {
                result[k++] = Right[j++];
            }
        }

        // one of the lists is empty this means I can add both and it will not change the data
        while (i < Left.length) {
            result[k++] = Left[i++];
        }
        while (j < Right.length) {
            result[k++] = Right[j++];
        }

        return result;
    }
}


