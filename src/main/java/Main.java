import ru.lifanova.convert.ConvertUtils;
import ru.lifanova.domain.Employee;
import ru.lifanova.exception.CsvParseException;

import java.util.List;

public class Main {
    public static void main(String[] args) throws CsvParseException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String fileName = "data.csv";

        List<Employee> employeeList = ConvertUtils.parseCSV(columnMapping, fileName);

        if (employeeList == null) {
            throw new CsvParseException("Файл не распарсился!");
        }

        String json = ConvertUtils.listToJson(employeeList);
        System.out.println(json);
    }
}
