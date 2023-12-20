package org.example;
import org.testng.Assert;
import org.testng.annotations.*;
import src.CsvManipulator;

public class CsvManipulatorTest {
    private  String testFileName;
    private  String newName;
    private  CsvManipulator csvManipulator;
    private String testName;
    private String newTestName;

    @BeforeClass
    public void setup() {
        System.out.println("BEFORE");
        csvManipulator = new CsvManipulator();
        testFileName = "test_file.csv";
        newName = "new_test_file.csv";
        testName = "test_file_2.csv";
        newTestName = "new_test_file_2.csv";
    }
    @Test (priority = 0)
    public void testCreateNewCsv() {
        csvManipulator.createNewCsv(testFileName);
        Assert.assertTrue(csvManipulator.searchCsvFile(testFileName));
    }
    @Test (priority = 1)
    public void testUpdateCsv() {
        csvManipulator.updateCsv(testFileName, 0, 0, "pupu");
        Assert.assertEquals(csvManipulator.readField(testFileName, 0, 0), "pupu");
    }
    @Test (priority = 2)
    public void testEditCsv() throws InterruptedException {
        Thread.sleep(500);
        csvManipulator.editCsv(testFileName,newName);
        Assert.assertTrue(csvManipulator.searchCsvFile(newName));
    }
    @Test (priority = 3)
    public void testWrongName () {
        csvManipulator.editCsv("афафы","dsadas");
        Assert.assertFalse(csvManipulator.searchCsvFile("афафы"));
    }
    @Test (priority = 4)
    public void testCreateNewCsv2 () {
        csvManipulator.createNewCsv(testFileName);
        csvManipulator.createNewCsv(testFileName);
        Assert.assertTrue(csvManipulator.searchCsvFile(testFileName));
    }
    @Test (priority = 5)
    public void testDeleteCsv() {
        csvManipulator.deleteCsv(testFileName);
        Assert.assertFalse(csvManipulator.searchCsvFile(testFileName));
    }
    @Test (priority = 6)
    public void testRename() {
        csvManipulator.createNewCsv(testName);
        csvManipulator.updateCsv(testName,0,0,"что-то");
        csvManipulator.editCsv(testName,newTestName);
        Assert.assertEquals(csvManipulator.readField(newTestName, 0, 0), "что-то");
    }
    @AfterClass
    public  void endClass() {
        csvManipulator.deleteCsv(newName);
        csvManipulator.deleteCsv(newTestName);
    }
}
