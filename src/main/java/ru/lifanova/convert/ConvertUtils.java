package ru.lifanova.convert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import ru.lifanova.domain.Employee;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ConvertUtils {
    private static final String DATA_DIR_PATH = System.getProperty("user.dir") + "/src/main/resources/data/";

    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        List<Employee> resultList = null;

        if (fileName == null || fileName.isEmpty()) {
            System.out.println("[parseCSV]: Error: empty file name!");
            return null;
        }

        String filePath = DATA_DIR_PATH + fileName;
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            resultList = csv.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public static String listToJson(List<Employee> list) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        StringBuilder stringBuilder = new StringBuilder();

        for (Employee employee : list) {
            stringBuilder.append(gson.toJson(employee)).append("\n");
        }

        return stringBuilder.toString();
    }

    public static void writeToFile(String filename, String json) {
        String path = DATA_DIR_PATH + filename;
        File file = new File(path);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(json);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
