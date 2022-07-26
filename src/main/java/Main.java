import ru.lifanova.convert.ConvertUtils;
import ru.lifanova.domain.Employee;
import ru.lifanova.exception.CsvParseException;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String csvFileName = "data.csv";
        String jsonFileName = "data.json";

        List<Employee> employeeList = null;
        try {
            employeeList = ConvertUtils.parseCSV(columnMapping, csvFileName);
        } catch (CsvParseException e) {
            e.printStackTrace();
            return;
        }

        String json = ConvertUtils.listToJson(employeeList);
        ConvertUtils.writeToFile(jsonFileName, json);
    }
}
