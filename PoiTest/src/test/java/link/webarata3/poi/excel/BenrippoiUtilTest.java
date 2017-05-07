package link.webarata3.poi.excel;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class BenrippoiUtilTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void openTest() throws IOException {
        File tempFile = new File(tempFolder.getRoot(), "temp.xlsx");
        Files.copy(this.getClass().getResourceAsStream("book1.xlsx"), tempFile.toPath());
        Optional<Workbook> optionalWb = BenrippoiUtil.open(Files.newInputStream(tempFile.toPath()));
        assertThat(optionalWb.isPresent(), is(true));
        Workbook wb = optionalWb.get();
        wb.close();
    }

    @Test
    public void cellIndexToCellNameTest() {
        assertThat(BenrippoiUtil.cellIndexToCellName(0, 0), is("A1"));
        assertThat(BenrippoiUtil.cellIndexToCellName(1, 0), is("B1"));
        assertThat(BenrippoiUtil.cellIndexToCellName(2, 0), is("C1"));
        assertThat(BenrippoiUtil.cellIndexToCellName(26, 0), is("AA1"));
        assertThat(BenrippoiUtil.cellIndexToCellName(27, 0), is("AB1"));
        assertThat(BenrippoiUtil.cellIndexToCellName(28, 0), is("AC1"));
    }
}
