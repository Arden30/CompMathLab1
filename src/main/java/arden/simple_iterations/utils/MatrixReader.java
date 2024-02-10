package arden.simple_iterations.utils;

import java.util.Scanner;

public class MatrixReader {
    private MatrixReader() {

    }
    public static double[][] readMatrix(Scanner scanner, int size) {
        double[][] matrix = new double[size][size + 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                String str = scanner.next().replace(",", ".");
                matrix[i][j] = Double.parseDouble(str);
            }
        }
        return matrix;
    }
}
