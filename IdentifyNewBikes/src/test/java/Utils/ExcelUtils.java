package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static final String FILE_PATH = "src/test/resources/testdata.xlsx"; // adjust path as needed

    // ✅ Method 1: Read all Gmail addresses from the "gmail" column
    public static List<String> readGmailColumn() {
        List<String> gmailList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int gmailColIndex = -1;

            // Find the "gmail" column index
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase("gmail")) {
                    gmailColIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (gmailColIndex == -1) throw new RuntimeException("Gmail column not found!");

            // Read all values from the gmail column
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(gmailColIndex);
                    if (cell != null) {
                        gmailList.add(cell.getStringCellValue());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gmailList;
    }

    // ✍️ Method 2: Write result into the "result" column for each row
    public static void writeResultColumn(List<String> results) {
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int resultColIndex = -1;

            // Find the "result" column index
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase("result")) {
                    resultColIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (resultColIndex == -1) throw new RuntimeException("Result column not found!");

            // Write each result to the corresponding row
            for (int i = 0; i < results.size(); i++) {
                Row row = sheet.getRow(i + 1); // skip header
                if (row == null) row = sheet.createRow(i + 1);
                Cell cell = row.getCell(resultColIndex);
                if (cell == null) cell = row.createCell(resultColIndex);
                cell.setCellValue(results.get(i));
            }

            // Save changes
            try (FileOutputStream fos = new FileOutputStream(new File(FILE_PATH))) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
