package task4;

import java.lang.reflect.Array;

public class ArrayActions {

    public static Object createArray(Class<?> type, int size) {
        return Array.newInstance(type, size);
    }

    public static Object createMatrix(Class<?> type, int rows, int cols) {
        return Array.newInstance(type, rows, cols);
    }

    public static Object resizeArray(Object array, int newSize) {
        Class<?> type = array.getClass().getComponentType();
        Object newArray = Array.newInstance(type, newSize);
        int length = Math.min(Array.getLength(array), newSize);
        System.arraycopy(array, 0, newArray, 0, length);
        return newArray;
    }

    public static Object resizeMatrix(Object matrix, int newRows, int newCols) {
        Class<?> type = matrix.getClass().getComponentType().getComponentType();
        Object newMatrix = Array.newInstance(type, newRows, newCols);
        int rows = Math.min(Array.getLength(matrix), newRows);
        for (int i = 0; i < rows; i++) {
            Object row = Array.get(matrix, i);
            int cols = Math.min(Array.getLength(row), newCols);
            Object newRow = Array.newInstance(type, newCols);
            System.arraycopy(row, 0, newRow, 0, cols);
            Array.set(newMatrix, i, newRow);
        }
        return newMatrix;
    }

    public static String arrayToString(Object array) {
        StringBuilder sb = new StringBuilder();
        sb.append(array.getClass().getComponentType().getSimpleName())
                .append("[").append(Array.getLength(array)).append("] = {");
        for (int i = 0; i < Array.getLength(array); i++) {
            sb.append(Array.get(array, i));
            if (i < Array.getLength(array) - 1)
                sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static String matrixToString(Object matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append(matrix.getClass().getComponentType().getComponentType().getSimpleName())
                .append("[").append(Array.getLength(matrix)).append("][").append(Array.getLength(Array.get(matrix, 0))).append("] = {");
        for (int i = 0; i < Array.getLength(matrix); i++) {
            sb.append(arrayToString(Array.get(matrix, i)));
            if (i < Array.getLength(matrix) - 1)
                sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {

        int[] intArray = (int[]) createArray(int.class, 5);
        for(int i = 0; i < 5; i++){
            intArray[i] = i;
        }
        System.out.println(arrayToString(intArray));
        System.out.println("Після зміни розміру");
        intArray = (int[]) resizeArray(intArray, 10);
        System.out.println(arrayToString(intArray));



        String[] stringArray = (String[]) createArray(String.class, 5);
        System.out.println(arrayToString(stringArray));
        System.out.println("Після зміни розміру");

        stringArray = (String[]) resizeArray(stringArray, 10);
        System.out.println(arrayToString(stringArray));



        int[][] intMatrix = (int[][]) createMatrix(int.class, 3, 5);
        System.out.println(matrixToString(intMatrix));
        System.out.println("Після зміни розміру");

        intMatrix = (int[][]) resizeMatrix(intMatrix, 4, 6);
        System.out.println(matrixToString(intMatrix));
    }
}

