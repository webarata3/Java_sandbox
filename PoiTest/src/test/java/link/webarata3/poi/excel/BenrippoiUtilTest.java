package link.webarata3.poi.excel;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class BenrippoiUtilTest {
    @Test
    public void openTest() throws IOException {
        Optional<Workbook> optionalWb = BenrippoiUtil.open(this.getClass().getResourceAsStream("book1.xlsx"));
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
