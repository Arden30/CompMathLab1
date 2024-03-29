package arden.simple_iterations.utils;

import arden.simple_iterations.model.Matrix;

public class PrettyPrinter {

    private PrettyPrinter() {

    }

    public static void printTableHeader(int size) {
        System.out.print("\n" + "k   ");
        for (int i = 1; i <= size; i++) {
            System.out.print(String.format("%" + 5 + "s", "") + "x" + i + "^k" + String.format("%" + 5 + "s", ""));
        }
        System.out.println(String.format("%" + 3 + "s", "") + "max|xi^k - xi^(k - 1)|");
    }

    public static void printRow(int cnt, double[] approximation, double inaccuracy) {
        System.out.print(cnt + String.format("%" + 2 + "s", ""));
        for (double v : approximation) {
            System.out.print(String.format("%" + 4 + "s", "") + String.format("%.6f", v) + String.format("%" + 2 + "s", ""));
        }
        if (cnt == 0) {
            System.out.printf("%" + 14 + "s" + "-%n", "");
        } else {
            System.out.println(String.format("%" + 12 + "s", "") + String.format("%.6f", inaccuracy));
        }
    }

    public static void printMatrix(Matrix matrix) {
        double[][] elements = matrix.matrix();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size() + 1; j++) {
                System.out.print(elements[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printVariables(double[] approximation) {
        System.out.print("\n" + "Вектор неизвестных: ");
        for (int i = 0; i < approximation.length - 1; i++) {
            System.out.print(String.format("%.6f", approximation[i]) + ", ");
        }
        System.out.printf("%.6f%n", approximation[approximation.length - 1]);
    }

    public static void printApproximation(double[] approximation, double[] newApproximation) {
        System.out.print("Вектор погрешностей: ");
        for (int i = 0; i < approximation.length - 1; i++) {
            System.out.print(String.format("%.6f", Math.abs(approximation[i] - newApproximation[i])) + ", ");
        }
        System.out.printf("%.6f", Math.abs(approximation[approximation.length - 1] - newApproximation[approximation.length - 1]));
    }

    public static void printResult(int cnt, double[] approximation, double[] newApproximation) {
        PrettyPrinter.printVariables(newApproximation);
        System.out.println("Количество итераций: " + cnt);
        PrettyPrinter.printApproximation(approximation, newApproximation);
    }
}
