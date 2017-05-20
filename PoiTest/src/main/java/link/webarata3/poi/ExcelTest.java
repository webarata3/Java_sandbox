package link.webarata3.poi;

import static link.webarata3.poi.excel.WrapperSheet.sheet;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTest {
    public static void main(String[] args) {
        try (Workbook wb = WorkbookFactory.create(ExcelTest.class.getResourceAsStream("book1.xlsx"))) {
            Sheet sheet = wb.getSheetAt(0);
            System.out.println(sheet.getRow(1).getCell(0));
            System.out.println(sheet(sheet).cell(1, 2).toInt());
            System.out.println(sheet(sheet).cell("B3").toInt());
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
