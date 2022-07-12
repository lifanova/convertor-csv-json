import ru.lifanova.convert.ConvertUtils;
import ru.lifanova.domain.Employee;
import ru.lifanova.exception.CsvParseException;

import java.util.List;

public class Main {
    public static void main(String[] args) throws CsvParseException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String csvFileName = "data.csv";
        String jsonFileName = "data.json";

        List<Employee> employeeList = ConvertUtils.parseCSV(columnMapping, csvFileName);

        if (employeeList == null) {
            throw new CsvParseException("Файл не распарсился!");
        }

        String json = ConvertUtils.listToJson(employeeList);
        System.out.println(json);
        ConvertUtils.writeToFile(jsonFileName, json);
    }
}
