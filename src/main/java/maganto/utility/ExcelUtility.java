package maganto.utility;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class              ExcelUtility {
    public String readFromExcelCell(String fileName,String sheetName,int rowNumber,int columNumber){
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook workbook= null;
        try {
            assert fileInputStream != null;
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //XSSFSheet sheet=workbook.getSheetAt(0);
        XSSFSheet sheet=workbook.getSheet(sheetName);
        XSSFRow row=sheet.getRow(rowNumber);
        String cellValue=null;
        if(row==null){
            System.out.println("Empty row!!!!");
        }else {
            XSSFCell cell=row.getCell(columNumber);
            CellType cellType=cell.getCellType();
            switch (cellType){
                case NUMERIC:
                    cellValue=String.valueOf(cell.getNumericCellValue());
                    //System.out.println(cellValue);
                    break;
                case STRING:
                    cellValue=cell.getStringCellValue();
                    // System.out.println(cellValue);
                    break;
            }
        }
        return cellValue;
    }

    public TestDataHolder readAttributeInfoFromExcel(String fileName, String sheetName){
        ExcelUtility excelUtility=new ExcelUtility();
        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestDataHolder testDataHolder=new TestDataHolder();
        testDataHolder.setAttributeCode(excelUtility.readFromExcelCell(fileName,sheetName,0,0));
        testDataHolder.setAdminName(excelUtility.readFromExcelCell(fileName,sheetName,0,1));
        return testDataHolder;
    }


    public TestDataHolder readStoreInfoFromExcel(String fileName, String sheetName){
        ExcelUtility excelUtility=new ExcelUtility();
        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestDataHolder testDataHolder=new TestDataHolder();
        testDataHolder.setWebsiteName(excelUtility.readFromExcelCell(fileName,sheetName,1,0));
        testDataHolder.setWebsiteCode(excelUtility.readFromExcelCell(fileName,sheetName,1,1));
        testDataHolder.setStoreName(excelUtility.readFromExcelCell(fileName,sheetName,1,2));
        return testDataHolder;
    }
    public TestDataHolder readReportingInfoFromExcel(String fileName, String sheetName){
        ExcelUtility excelUtility=new ExcelUtility();
        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestDataHolder testDataHolder=new TestDataHolder();
        testDataHolder.setStartDate(excelUtility.readFromExcelCell(fileName,sheetName,1,0));
        testDataHolder.setEndDate(excelUtility.readFromExcelCell(fileName,sheetName,1,1));
        return testDataHolder;
    }
    public TestDataHolder readCatalogAddProductInfoFromExcel(String fileName, String sheetName){
        ExcelUtility excelUtility=new ExcelUtility();
        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestDataHolder testDataHolder=new TestDataHolder();
        testDataHolder.setProductName(excelUtility.readFromExcelCell(fileName,sheetName,1,0));
        testDataHolder.setProductDescription(excelUtility.readFromExcelCell(fileName,sheetName,1,1));
        testDataHolder.setShortDescription(excelUtility.readFromExcelCell(fileName,sheetName,1,2));
        testDataHolder.setSKU(excelUtility.readFromExcelCell(fileName,sheetName,1,3));
        testDataHolder.setWeight(excelUtility.readFromExcelCell(fileName,sheetName,1,4));
        testDataHolder.setPrice(excelUtility.readFromExcelCell(fileName,sheetName,1,5));
        return testDataHolder;
    }

    public TestDataHolder readSalesInfoFromExcel(String fileName, String sheetName){
        ExcelUtility excelUtility=new ExcelUtility();
        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestDataHolder testDataHolder=new TestDataHolder();
        testDataHolder.setStartFrom(excelUtility.readFromExcelCell(fileName,sheetName,1,0));
        testDataHolder.setEndTo(excelUtility.readFromExcelCell(fileName,sheetName,1,1));
        testDataHolder.setBillToName(excelUtility.readFromExcelCell(fileName,sheetName,1,2));
        return testDataHolder;
    }

}

