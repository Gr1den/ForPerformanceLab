
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//Задание 3
//На вход в качестве аргументов программы поступают три пути к файлу (в приложении
// к заданию находятся примеры этих файлов):
// ● values.json содержит результаты прохождения тестов с уникальными id
// ● tests.json содержит структуру для построения отчета на основе прошедших
//тестов (вложенность может быть большей, чем в примере)
// ● report.json- сюда записывается результат.
//Напишите программу, которая формирует файл report.json с заполненными полями
//value для структуры tests.json на основании values.json.
public class Task3 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Необходимо 3 параметра <values.json> <tests.json> <report.json>");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        ObjectMapper mapper = new ObjectMapper();

        try {
            // Чтение values.json
            JsonNode valuesRoot = mapper.readTree(new File(valuesFilePath));
            Map<Integer, String> valuesMap = new HashMap<>();
            for (JsonNode valueNode : valuesRoot.get("values")) {
                int id = valueNode.get("id").asInt();
                String value = valueNode.get("value").asText();
                valuesMap.put(id, value);
            }

            JsonNode testsRoot = mapper.readTree(new File(testsFilePath));  // Чтение tests.json

            fillTestValues(testsRoot.get("tests"), valuesMap);              // Заполнение значений в tests.json

            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(reportFilePath), testsRoot);         // Запись результата в report.json

            System.out.println("Report был сохранен в  " + reportFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void fillTestValues(JsonNode tests, Map<Integer, String> valuesMap) {
        for (JsonNode test : tests) {                           //valuesMap : id -> value
            int id = test.get("id").asInt();                    // Получение ID теста
            if (valuesMap.containsKey(id)) {                    // Заполнение поля values
                ((ObjectNode) test).put("value", valuesMap.get(id));
            }
            if (test.has("values")) {
                fillTestValues(test.get("values"), valuesMap);   // Рекурсивная обработка

            }
        }
    }
}
