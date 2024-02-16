package arden.simple_iterations.algorithm;

import arden.simple_iterations.model.Matrix;
import arden.simple_iterations.utils.PrettyPrinter;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class SimpleIterator {
    private final Matrix matrix;
    private final double[][] elements;
    private final int size;
    private int strict = 0;

    public SimpleIterator(Matrix matrix) {
        this.matrix = matrix;
        elements = matrix.matrix();
        size = matrix.size();
    }

    public double[] iterate() {
        int iterations = 100;
        boolean diag = checkAndMakeDiagonalDomination();
        if (diag) {
            iterations = 10000000;
            System.out.println("\nМатрица с диагональным преобладанием:");
            PrettyPrinter.printMatrix(matrix);
        } else {
            System.out.println("Диагональное преобладание не выполнено, проверим сходимость (до 100 итераций):");
        }
        PrettyPrinter.printTableHeader(size);

        getDiagonalElements();

        double[] approximation = new double[size];
        for (int i = 0; i < size; i++) {
            approximation[i] = elements[i][size];
        }

        double[] newApproximation = new double[size];
        double inaccuracy = 0;

        int cnt = 0;

        PrettyPrinter.printRow(cnt++, approximation, inaccuracy);
        while (cnt <= iterations) {
            for (int i = 0; i < size; i++) {
                double sum = 0;
                for (int j = 0; j < size; j++) {
                    sum += elements[i][j] * approximation[j];
                }

                sum += elements[i][size];
                newApproximation[i] = sum;
            }

            inaccuracy = countInaccuracy(approximation, newApproximation);

            PrettyPrinter.printRow(cnt++, newApproximation, inaccuracy);

            if (inaccuracy > matrix.accuracy()) {
                System.arraycopy(newApproximation, 0, approximation, 0, size);
            } else break;
        }

        if (cnt == 101) {
            System.out.println("Итерационный процесс не сходится");
        } else {
            PrettyPrinter.printResult(cnt - 1, approximation, newApproximation);
        }


        return newApproximation;
    }

    private double countInaccuracy(double[] approximation, double[] newApproximation) {
        double inaccuracy = 0;
        for (int i = 0; i < size; i++) {
            double diff = Math.abs(approximation[i] - newApproximation[i]);
            if (diff > inaccuracy) {
                inaccuracy = diff;
            }
        }
        return inaccuracy;
    }

    private boolean checkAndMakeDiagonalDomination() {
        int row = 0, prev = -1;
        while (row < size) {
            int tmp = row;
            OptionalInt maxIndex = IntStream.range(0, size)
                    .reduce((k, l) -> Math.abs(elements[tmp][k]) > Math.abs(elements[tmp][l]) ? k : l);
            if (maxIndex.isPresent()) {
                int index = maxIndex.getAsInt();
                double sum = sumWithoutDiagonal(elements, row, index);
                if (Math.abs(elements[row][index]) > sum) {
                    strict++;
                }
                if (index == prev || Math.abs(elements[row][index]) < sum) {
                    return false;
                } else if (index != row) {
                    permutation(elements, row, index);
                    prev = index;
                } else {
                    prev = index;
                    row++;
                }
            } else {
                return false;
            }
        }
        return strict != 0;
    }

    private double sumWithoutDiagonal(double[][] elements, int row, int index) {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            if (i != index) {
                sum += Math.abs(elements[row][i]);
            }
        }
        return sum;
    }

    private void permutation(double[][] elements, int row, int index) {
        for (int i = 0; i < size + 1; i++) {
            double tmp = elements[row][i];
            elements[row][i] = elements[index][i];
            elements[index][i] = tmp;
        }
    }

    private void getDiagonalElements() {
        for (int i = 0; i < size; i++) {
            double diagCoefficient = elements[i][i];
            elements[i][i] = 0;
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    elements[i][j] /= -diagCoefficient;
                }
            }
            elements[i][size] /= diagCoefficient;
        }
    }
}
