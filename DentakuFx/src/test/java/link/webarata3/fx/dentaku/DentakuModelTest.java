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
        dentakuModel.clear();
    }

    @Test
    public void 数字入力のテスト() {
        dentakuModel.appendNumber(5);
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(5)));
    }

    @Test
    public void 計算のテスト() {
        dentakuModel.appendNumber(0);
        dentakuModel.appendNumber(2);
        dentakuModel.appendNumber(7);
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(27)));

        dentakuModel.setOperator(DentakuModel.Operator.PLUS);

        dentakuModel.appendNumber(9);
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(9)));

        dentakuModel.calc();

        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(36)));
    }
}
