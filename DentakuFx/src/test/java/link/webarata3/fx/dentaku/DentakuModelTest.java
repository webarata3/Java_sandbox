package link.webarata3.fx.dentaku;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class DentakuModelTest {
    private DentakuModel dentakuModel;

    @Before
    public void before() {
        dentakuModel = DentakuModel.getInstance();
    }

    @Test
    public void 数字入力のテスト() {
        dentakuModel.appendNumber(5);
        assertThat(dentakuModel.getCurrentBuffer(), is(new BigDecimal(5)));
    }
}
