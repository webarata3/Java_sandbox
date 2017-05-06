package link.webarata3.poi;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTest {
    public static void main(String[] args) {
        try (Workbook wb = WorkbookFactory.create(ClassLoader.getSystemResourceAsStream("book1.xlsx"))) {
            Sheet sheet = wb.getSheetAt(0);
            System.out.println(sheet.getRow(1).getCell(0));
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
