package arden.simple_iterations.matrix_reader;

import arden.simple_iterations.model.Matrix;
import arden.simple_iterations.utils.MatrixReader;
import arden.simple_iterations.utils.PrettyPrinter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadFromFile implements Readable {
    private final Path path;

    public ReadFromFile(String path) {
        this.path = Path.of(path);
    }

    @Override
    public Matrix read() {
        try (Scanner scanner = new Scanner(path)){
            String str = scanner.next().replace(",", ".");
            double accuracy = Double.parseDouble(str);
            int size = scanner.nextInt();
            if (size > 20) {
                System.out.println("Размер матрицы не больше 20, попробуйте ещё раз");
                System.exit(1);
            }
            double[][] matrix = MatrixReader.readMatrix(scanner, size);

            Matrix mat = new Matrix(matrix, size, accuracy);
            System.out.println("Исходная матрица:");
            PrettyPrinter.printMatrix(mat);

            return mat;
        } catch (IOException e) {
            return null;
        }
    }
}
