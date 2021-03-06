package link.webarata3.poi.excel;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class BenrippoiUtilTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private Optional<Workbook> getOptionalWorkbook(String fileName) throws Exception {
        File tempFile = new File(tempFolder.getRoot(), "temp.xlsx");
        Files.copy(this.getClass().getResourceAsStream(fileName), tempFile.toPath());
        return BenrippoiUtil.open(Files.newInputStream(tempFile.toPath()));
    }

    private Workbook getWorkbook(String fileName) throws Exception {
        return getOptionalWorkbook(fileName).get();
    }

    @Test
    public void openTest() throws Exception {
        Optional<Workbook> optionalWb = getOptionalWorkbook("book1.xlsx");
        assertThat(optionalWb.isPresent(), is(true));
        Workbook wb = optionalWb.get();
        wb.close();
    }

    @Test
    public void cellIndexToCellNameTest() {
        assertThat(BenrippoiUtil.cellIndexToCellLabel(0, 0), is("A1"));
        assertThat(BenrippoiUtil.cellIndexToCellLabel(1, 0), is("B1"));
        assertThat(BenrippoiUtil.cellIndexToCellLabel(2, 0), is("C1"));
        assertThat(BenrippoiUtil.cellIndexToCellLabel(26, 0), is("AA1"));
        assertThat(BenrippoiUtil.cellIndexToCellLabel(27, 0), is("AB1"));
        assertThat(BenrippoiUtil.cellIndexToCellLabel(28, 0), is("AC1"));
    }

    @Test
    public void test() throws Exception {
        Workbook wb = getWorkbook("book1.xlsx");
        Sheet sheet = wb.getSheet("Sheet1");
        Cell cell = BenrippoiUtil.getCell(sheet, "B1");
        System.out.println(BenrippoiUtil.cellToString(cell));
        System.out.println(cell.getStringCellValue());
    }
}
