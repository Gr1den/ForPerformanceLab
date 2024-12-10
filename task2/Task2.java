
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.util.*;
//        Задание2
//        Напишите программу, котораярассчитывает положение точки относительно окружности.
//        Координаты центра окружности и его радиус считываются из файла 1
public class Task2 {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Ошибка: необходимо указать два файла.");
            return;
        }

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        // Чтение и обработка данных о центре окружности и радиусе из первого файла
        List<String> circleData = Files.readAllLines(Paths.get(circleFilePath));
        String[] centerCors =  circleData.get(0).trim().split(" ");
        var x0 = new BigDecimal(centerCors[0]);                 // Координата x центра окружности
        var y0 = new BigDecimal(centerCors[1]);                 // Координата y центра окружности
        var radius = new BigDecimal(circleData.get(1).trim());  // Радиус окружности

        // Чтение точек из второго файла
        List<String> pointLines = Files.readAllLines(Paths.get(pointsFilePath));

        // Обработка каждой точки и вывод результата
        for (String line : pointLines) {
            String[] coords = line.trim().split(" ");
            var x = new BigDecimal(coords[0]);  // Координата x точки
            var y = new BigDecimal(coords[1]);  // Координата y точки

            int result = checkPointPosition(x, y, x0, y0, radius);
            System.out.println(result);
        }
    }

    public static int checkPointPosition(BigDecimal x, BigDecimal y,
                                         BigDecimal x0, BigDecimal y0,
                                         BigDecimal r) {
        var dx = x.subtract(x0);                               // (x - x0)
        var dy = y.subtract(y0);                               // (y - y0)
        var distanceSquared = dx.pow(2).add(dy.pow(2));  // (x - x0)^2 + (y - y0)^2
        var radiusSquared = r.pow(2);
        int comparison = distanceSquared.compareTo(radiusSquared);
        if (comparison < 0) {
            return 1;
        } else if (comparison == 0) {
            return 0;
        } else {
            return 2;
        }
    }
}
