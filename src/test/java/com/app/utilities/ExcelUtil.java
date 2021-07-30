package com.app.utilities;

import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private Sheet workSheet;
    private Workbook workBook;
    private String path;
    // private FileInputStream ExcelFile;

    public List<Map<String, String>> allExcelData = null;

    public static void main(String[] args) {

    }

    public ExcelUtil(String sheetName) {
        this.path = ConfigurationReader.get("excelPath");
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(ConfigurationReader.get("excelPath"));


            // Access the required test data sheet
            workBook = WorkbookFactory.create(ExcelFile);
            workSheet = workBook.getSheet(sheetName);
            // check if sheet is null or not. null means  sheetname was wrong
            Assert.assertNotNull("Sheet: \"" + sheetName + "\" does not exist\n", workSheet);

            getAllData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public ExcelUtil(String path, String sheetName) {
        this.path = path;
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(ConfigurationReader.get("excelPath"));


            // Access the required test data sheet
            workBook = WorkbookFactory.create(ExcelFile);
            workSheet = workBook.getSheet(sheetName);
            // check if sheet is null or not. null means  sheetname was wrong
            Assert.assertNotNull("Sheet: \"" + sheetName + "\" does not exist\n", workSheet);

            getAllData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllData() {
        for (String sheetName : getSheetNames()) {


        }

    }


    public List<String> getSheetNames() {

        List<String> sheetNames = new ArrayList<String>();
        for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
            sheetNames.add(workBook.getSheetName(i));
        }

        System.out.println("sheetNames = " + sheetNames);

        return sheetNames;

    }

    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String[][] getDataArray() {

        String[][] data = new String[rowCount()][columnCount()];

        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;

    }

    //this method will return data table as 2d array
    //so we need this format because of data provider.
    public String[][] getDataArrayWithoutFirstRow() {

        String[][] data = new String[rowCount() - 1][columnCount()];

        for (int i = 1; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i - 1][j] = value;
            }
        }
        return data;

    }

    public List<Map<String, String>> getDataList() {
        // get all columns
        List<String> columns = getColumnsNames();

        // this will be returned
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // create map of the row using the column and value
            // column map key, cell value --> map bvalue
            Map<String, String> rowMap = new HashMap<String, String>();
            try {
                for (Cell cell : row) {
                    try {
                        int columnIndex = cell.getColumnIndex();
                        rowMap.put(columns.get(columnIndex), cell.toString());

                    } catch (IndexOutOfBoundsException e) {

                    }
                }
            } catch (NullPointerException n) {
                return data;
            }

            data.add(rowMap);
        }


        return data;
    }


    public List<Map<String, String>> getDataList(String sheetName) {

        new ExcelUtil(ConfigurationReader.get("excelPath"), sheetName);

        // get all columns
        List<String> columns = getColumnsNames();

        // this will be returned
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // create map of the row using the column and value
            // column map key, cell value --> map bvalue
            Map<String, String> rowMap = new HashMap<String, String>();
            try {
                for (Cell cell : row) {
                    try {
                        int columnIndex = cell.getColumnIndex();
                        rowMap.put(columns.get(columnIndex), cell.toString());

                    } catch (IndexOutOfBoundsException e) {

                    }
                }
            } catch (NullPointerException n) {
                return data;
            }

            data.add(rowMap);
        }


        return data;
    }


    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();

        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;

        try {
            row = workSheet.getRow(rowNum);
            cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            workBook.write(fileOut);

            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCellData(String value, String columnName, int row) {
        int column = getColumnsNames().indexOf(columnName);
        setCellData(value, row, column);
    }

    public int columnCount() {
        return workSheet.getRow(0).getLastCellNum();
    }

    public int rowCount() {
        return workSheet.getLastRowNum() + 1;
    }


    //-----------------------------------------------------------------------------


}