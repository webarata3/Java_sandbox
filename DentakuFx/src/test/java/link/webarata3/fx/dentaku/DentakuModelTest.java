package link.webarata3.fx.dentaku;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static link.webarata3.fx.dentaku.DentakuModel.Operator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DentakuModelTest {
    private DentakuModel dentakuModel;

    @Before
    public void before() {
        dentakuModel = DentakuModel.getInstance();
        dentakuModel.clear();
    }

    @Test
    public void 数字の入力テスト() {
        dentakuModel.appendNumber(3);
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(3)));
    }

    @Test
    public void 数字の連続入力テスト() {
        dentakuModel.appendNumber(1);
        dentakuModel.appendNumber(9);
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(19)));
    }

    @Test
    public void 数字の0始まりの連続入力テスト() {
        dentakuModel.appendNumber(0);
        dentakuModel.appendNumber(1);
        dentakuModel.appendNumber(9);
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(19)));
    }

    @Test
    public void 小数点を入力し小数点数以下の数字を入力しない() {
        dentakuModel.appendDecimalPoint();
        assertThat(dentakuModel.getCurrentValue().setScale(0, RoundingMode.HALF_UP), is(new BigDecimal(0)));
    }

    @Test
    public void 小数点を連続入力した場合() {
        dentakuModel.appendDecimalPoint();
        dentakuModel.appendDecimalPoint();
        assertThat(dentakuModel.getCurrentValue().setScale(0, RoundingMode.HALF_UP), is(new BigDecimal(0)));
    }

    @Test
    public void 小数点point1と入力した場合() {
        dentakuModel.appendDecimalPoint();
        dentakuModel.appendNumber(1);
        assertThat(dentakuModel.getCurrentValue().setScale(1, RoundingMode.HALF_UP), is(new BigDecimal("0.1")));
    }

    @Test
    public void 小数点point1point2と入力() {
        dentakuModel.appendDecimalPoint();
        dentakuModel.appendNumber(1);
        dentakuModel.appendDecimalPoint();
        dentakuModel.appendNumber(2);
        assertThat(dentakuModel.getCurrentValue().setScale(2, RoundingMode.HALF_UP), is(new BigDecimal("0.12")));
    }

    @Test
    public void 小数点1point2と入力() {
        dentakuModel.appendNumber(1);
        dentakuModel.appendDecimalPoint();
        dentakuModel.appendNumber(2);
        assertThat(dentakuModel.getCurrentValue().setScale(1, RoundingMode.HALF_UP), is(new BigDecimal("1.2")));
    }

    @Test
    public void 演算子の後に小数点point1と入力() {
        dentakuModel.setOperator(Operator.PLUS);
        dentakuModel.appendDecimalPoint();
        dentakuModel.appendNumber(1);
        assertThat(dentakuModel.getCurrentValue().setScale(1, RoundingMode.HALF_UP), is(new BigDecimal("0.1")));
    }

    @Test
    public void 計算後の計算のテストかつequalの後に演算子を指定() {
        dentakuModel.appendNumber(1);
        dentakuModel.appendNumber(9);
        dentakuModel.setOperator(Operator.PLUS);
        dentakuModel.appendNumber(3);
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(22)));

        dentakuModel.setOperator(Operator.PLUS);
        dentakuModel.appendNumber(78);
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(100)));
    }

    @Test
    public void 数字入力後の計算をequalでなくoperatorで行うテスト() {
        dentakuModel.appendNumber(1);
        dentakuModel.appendNumber(9);
        dentakuModel.setOperator(Operator.PLUS);
        dentakuModel.appendNumber(3);
        dentakuModel.calc();
        dentakuModel.appendNumber(78);
        dentakuModel.setOperator(Operator.PLUS);
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(100)));
    }

    @Test
    public void 数値入力の計算後equalを連続して足し算をするテスト() {
        dentakuModel.appendNumber(-5);
        dentakuModel.setOperator(Operator.PLUS);
        dentakuModel.appendNumber(3);
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(-2)));
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(1)));
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(4)));
    }

    @Test
    public void 数値入力の計算後equalを連続して引き算をするテスト() {
        dentakuModel.appendNumber(5);
        dentakuModel.setOperator(Operator.MINUS);
        dentakuModel.appendNumber(3);
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(2)));
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(-1)));
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(-4)));
    }

    @Test
    public void 数値入力の計算後equalを連続して掛け算をするテスト() {
        dentakuModel.appendNumber(5);
        dentakuModel.setOperator(Operator.MULTIPLY);
        dentakuModel.appendNumber(3);
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(15)));
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(45)));
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue(), is(new BigDecimal(135)));
    }

    @Test
    public void 数値入力の計算後equalを連続して割り算をするテスト() {
        dentakuModel.appendNumber(2);
        dentakuModel.appendNumber(5);
        dentakuModel.appendNumber(6);
        dentakuModel.setOperator(Operator.DIVIDE);
        dentakuModel.appendNumber(2);
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue().setScale(0), is(new BigDecimal(128)));
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue().setScale(0), is(new BigDecimal(64)));
        dentakuModel.calc();
        assertThat(dentakuModel.getCurrentValue().setScale(0), is(new BigDecimal(32)));
    }
}
