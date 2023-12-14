package src;

public class Main {
  public static void main(String[] args) {
    DataProvider data = new DataProvider();
    Generator generator = new Generator();
    data.setStr(generator.generatorStroki(10));

    CsvManipulator csvManipulator = new CsvManipulator();
    String name = generator.generatorStroki(3);
    csvManipulator.readCsvFile("aaff.csv");
    csvManipulator.createNewCsv(name + ".csv");
    csvManipulator.updateCsv(name + ".csv", 0, 0, "1");
    csvManipulator.editCsv(name + ".csv", name + ".csv");
    csvManipulator.searchCsvFile(name + ".csv");
    csvManipulator.deleteCsv(name + ".csv");
  }
}




