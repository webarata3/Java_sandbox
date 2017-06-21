package link.webarata3.fx.dentaku;

import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static link.webarata3.fx.dentaku.DentakuModel.Operator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class DentakuModelEnclosedTest {

    @RunWith(Theories.class)
    public static class 正常系_calcテスト {
        private DentakuModel dentakuModel;

        @Before
        public void before() {
            dentakuModel = DentakuModel.getInstance();
            dentakuModel.clear();
        }

        @DataPoints
        public static Fixture[] PARAMs = {
            new Fixture(2, Operator.PLUS, 8, new BigDecimal(10)),
            new Fixture(-1, Operator.PLUS, 2, new BigDecimal(1)),
            new Fixture(1, Operator.PLUS, -10, new BigDecimal(-9)),
            new Fixture(2, Operator.MINUS, 8, new BigDecimal(-6)),
            new Fixture(10, Operator.MINUS, 8, new BigDecimal(2)),
            new Fixture(2, Operator.MULTIPLY, 4, new BigDecimal(8)),
            new Fixture(1, Operator.MULTIPLY, 1, new BigDecimal(1))
        };

        static class Fixture {
            int x;
            Operator operator;
            int y;
            BigDecimal answer;

            Fixture(int x, Operator operator, int y, BigDecimal answer) {
                this.x = x;
                this.operator = operator;
                this.y = y;
                this.answer = answer;
            }

            @Override
            public String toString() {
                return "Fixture{" +
                    "x=" + x +
                    ", operator=" + operator +
                    ", y=" + y +
                    ", answer=" + answer +
                    '}';
            }
        }

        @Theory
        public void test(Fixture fixture) {
            dentakuModel.appendNumber(fixture.x);
            dentakuModel.setOperator(fixture.operator);
            dentakuModel.appendNumber(fixture.y);
            dentakuModel.calc();
            assertThat(dentakuModel.getCurrentValue(), is(fixture.answer));
        }
    }

    @RunWith(Theories.class)
    public static class 正常系_calc_割り算テスト {
        private DentakuModel dentakuModel;

        @Before
        public void before() {
            dentakuModel = DentakuModel.getInstance();
            dentakuModel.clear();
        }

        @DataPoints
        public static Fixture[] PARAMs = {
            new Fixture(4, Operator.DIVIDE, 2, new BigDecimal(2)),
            new Fixture(2, Operator.DIVIDE, 5, new BigDecimal(0.4)),
            new Fixture(1, Operator.DIVIDE, 3, new BigDecimal(0.3333333333))
        };

        static class Fixture {
            int x;
            Operator operator;
            int y;
            BigDecimal answer;

            Fixture(int x, Operator operator, int y, BigDecimal answer) {
                this.x = x;
                this.operator = operator;
                this.y = y;
                this.answer = answer;
            }

            @Override
            public String toString() {
                return "Fixture{" +
                    "x=" + x +
                    ", operator=" + operator +
                    ", y=" + y +
                    ", answer=" + answer +
                    '}';
            }
        }

        @Theory
        public void test(Fixture fixture) {
            dentakuModel.appendNumber(fixture.x);
            dentakuModel.setOperator(fixture.operator);
            dentakuModel.appendNumber(fixture.y);
            dentakuModel.calc();
            BigDecimal resultValue = dentakuModel.getCurrentValue().setScale(10, RoundingMode.HALF_UP);
            assertThat(resultValue, is(fixture.answer.setScale(10, RoundingMode.HALF_UP)));
        }
    }
}
