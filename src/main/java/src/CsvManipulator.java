package src;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvManipulator {
    private static final String FILE_PATH = "E://JavaFiles//";
    private String getFilePath(String name) {
        return FILE_PATH + name;
    }
    public boolean readCsvFile(String name) {
        String filePath = getFilePath(name);
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] chtoto = line.split(splitBy);
                System.out.println("1 = " + chtoto[0] + ", 2 = " + chtoto[1] + ", 3 = " + chtoto[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void createNewCsv (String fileName) {
        try {
            String filePath = getFilePath(fileName);
            File file = new File(filePath);
            if (file.exists()) {
                System.out.println("файл уже создан " + filePath);
                return;
            }
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.append("raz,dva,tri\n");
            fileWriter.close();
            System.out.println("ксв файл создан");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean updateCsv(String name, int columnToUpdate, int lineToUpdate, String valueToUpdate) {
        try {
            String filePath = getFilePath(name);
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (lineToUpdate >= 0 && lineToUpdate < lines.size()) {
                String[] columns = lines.get(lineToUpdate).split(",");
                if (columnToUpdate >= 0 && columnToUpdate < columns.length) {
                    columns[columnToUpdate] = valueToUpdate;
                    lines.set(lineToUpdate, String.join(",", columns));
                    Files.write(Paths.get(filePath), lines);
                    System.out.println("ксв файл обновлен");
                    return true;
                }
            }
            throw new Errors(Enum.StringNotFound.getError());
        } catch (IOException | Errors e) {
            System.out.println("ПОЙМАЛ: " + e.getMessage());
        }
        return false;
    }
    public String readField(String name, int column, int line) {
        try {
            String filePath = getFilePath(name);
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (line >= 0 && line < lines.size()) {
                String[] columns = lines.get(line).split(",");
                if (column >= 0 && column < columns.length) {
                    return columns[column];
                }
            }
            throw new Errors(Enum.StringNotFound.getError());
        } catch (IOException | Errors e) {
            System.out.println("ПОЙМАЛ: " + e.getMessage());
            return null;
        }
    }
    public void editCsv(String name, String newName) {
        try {
            String filePath = getFilePath(name);
            File currentFile = new File(filePath);
            if (!currentFile.exists()) {
                throw new Errors(Enum.FileNotFound.getError() + filePath);
            }
            File newFile = new File(getFilePath(newName));
            currentFile.renameTo(newFile);
            System.out.println("ксв файл переименован");
        } catch (Errors e) {
            System.out.println("ошибка: " + e.getMessage());
        }
    }
    public boolean searchCsvFile(String name) {
        String filePath = getFilePath(name);
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                throw new Errors(Enum.FileNotFound.getError());
            }
            System.out.println("файл найден");
            return true;
        } catch (Errors e) {
            System.out.println("ПОЙМАЛ: " + e.getMessage());
            return false;
        }
    }
    public void deleteCsv(String name) {
        try {
            String filePath = getFilePath(name);
            Path path = Paths.get(filePath);
            Files.delete(path);
            System.out.println("ксв файл удален");
        } catch (Exception e) {
            System.out.println("ксв файл НЕ удален " + e.getMessage());
        }
    }
}
