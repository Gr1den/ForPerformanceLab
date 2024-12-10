import java.util.ArrayList;
import java.util.List;
//Задание 1
//Напишите программу, которая выводит путь, по которому, двигаясь интервалом длины
//m позаданному массиву, концом будет являться первый элемент.
//Началом одного интервала является конец предыдущего.
//Путь- массив из начальных элементов полученных интервалов.
public class Task1 {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Ошибка: необходимо указать два аргумента.");
            return;
        }

        int n = Integer.parseInt(args[0]);    // Параметр массива
        int m = Integer.parseInt(args[1])-1;  // Параметр интервала + поправка на совпадение конца с началом отрезка

        int[] circularArray = new int[n];     // Создание + заполнение массива

        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        int startIndex = 0;
        int currentIndex = 0;

        StringBuilder path = new StringBuilder();

        do {
            path.append(circularArray[currentIndex]);
            currentIndex = (currentIndex + m) % n;  // Круговой переход учтен тут
        } while (currentIndex != startIndex);

        System.out.println(path.toString());
    }
}
