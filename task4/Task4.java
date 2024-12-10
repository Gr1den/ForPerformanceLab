//Задание4
//Дан массив целых чисел nums.
//Напишите программу,выводящую минимальное количество ходов, требуемых для
//приведения всех элементов к одному числу.
// За один ход можно уменьшить или увеличить число массива на 1
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Task4 {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу.");
            return;
        }

        String filePath = args[0];
        List<Integer> numsList;
        try {
            numsList = Files.lines(Paths.get(filePath)) // Считываем строки из файла
                    .map(String::trim)                  // Убираем пробелы
                    .map(Integer::parseInt)             // Преобразуем в Integer
                    .collect(Collectors.toList());      // Собираем в список
        } catch (NumberFormatException e) {
            System.out.println("Файл должен содержать только целые числа.");
            return;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        int result = minMovesCount(numsList);
        System.out.println("Количество ходов: " + result);
    }

    public static int minMovesCount(List<Integer> nums) {
        nums.sort(Comparator.naturalOrder());
        int median = nums.get(nums.size() / 2);           // Находим медиану
        return nums.stream()
                .mapToInt(num -> Math.abs(num - median))  // Считаем абсолютную разность
                .sum();
    }
}
