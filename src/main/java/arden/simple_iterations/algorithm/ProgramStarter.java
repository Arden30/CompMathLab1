package arden.simple_iterations.algorithm;

import arden.simple_iterations.matrix_reader.ReadFromConsole;
import arden.simple_iterations.matrix_reader.ReadFromFile;
import arden.simple_iterations.matrix_reader.Readable;

import java.util.Scanner;

public class ProgramStarter {
    public void start() {
        try {
            System.out.println("Выберете формат ввода: консоль (введите 1) или файл (введите 2)");
            Scanner scanner = new Scanner(System.in);
            int format = scanner.nextInt();

            Readable readable;
            switch (format) {
                case 1 -> readable = new ReadFromConsole();
                case 2 -> {
                    System.out.println("""
                            В файле все данные должны быть указаны в порядке:
                            1. Точность
                            2. Размерность матрицы
                            3. Коэффициенты матрицы"""
                    );
                    System.out.println("Укажите путь к файлу: ");
                    String path = scanner.next();
                    readable = new ReadFromFile(path);
                }
                default -> throw new Exception("Ошибка выбора формата");
            }

            SimpleIterator simpleIterator = new SimpleIterator(readable.read());
            double[] matrix = simpleIterator.iterate();
            if (matrix == null) {
                System.out.println("Диагональное преобладание невозможно");
            }
        } catch (Exception e) {
            System.out.println("Ошибка пользовательского ввода (неверные данные или несуществующий путь к файлу)");
        }
    }
}
