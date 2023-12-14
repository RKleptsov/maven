package org.example;
import org.testng.Assert;
import org.testng.annotations.*;
import src.CsvManipulator;

public class CsvManipulatorTest {
    private  String testFileName;
    private  String newName;
    private  CsvManipulator csvManipulator;

    @BeforeClass
    public void setup() {
        System.out.println("BEFORE");
        csvManipulator = new CsvManipulator();
        testFileName = "test_file.csv";
        newName = "new_test_file.csv";
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
    }
    @Test (priority = 4)
    public void testCreateNewCsv2 () {
        csvManipulator.createNewCsv(testFileName);
        csvManipulator.createNewCsv(testFileName);
    }
    @Test (priority = 5)
    public void testDeleteCsv() {
        csvManipulator.deleteCsv(testFileName);
        Assert.assertFalse(csvManipulator.searchCsvFile(testFileName));
    }
    @AfterClass
    public  void endClass() {
        csvManipulator.deleteCsv(newName);
    }
}
