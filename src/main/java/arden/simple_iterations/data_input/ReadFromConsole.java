package arden.simple_iterations.data_input;

import arden.simple_iterations.model.Matrix;
import arden.simple_iterations.utils.MatrixReader;
import arden.simple_iterations.utils.PrettyPrinter;

import java.util.Scanner;

public class ReadFromConsole implements Readable {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Matrix read() {
        double accuracy = accuracyRead();
        int size = sizeRead();

        System.out.println("Введите матрицу (коэффициенты уравнения): ");
        double[][] matrix = MatrixReader.readMatrix(scanner, size);

        Matrix mat = new Matrix(matrix, size, accuracy);
        System.out.println("Исходная матрица:");
        PrettyPrinter.printMatrix(mat);

        return mat;
    }

    private double accuracyRead() {
        System.out.println("Введите точность (не меньше чем 0.0001):");
        String str = scanner.next().replace(",", ".");
        double accuracy = Double.parseDouble(str);
        if (accuracy <= 0) {
            System.out.println("Точность вычислений должна быть больше 0, попробуйте ещё раз");
            accuracyRead();
        }
        return accuracy;
    }
    private int sizeRead() {
        System.out.println("Введите размер матрицы (не больше 20):");
        int size = scanner.nextInt();
        if (size > 20) {
            System.out.println("Размер матрицы не больше 20, попробуйте ещё раз");
            sizeRead();
        }
        return size;
    }
}
