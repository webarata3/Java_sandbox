package link.webarata3.fx.dentaku;

import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

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
            new Fixture(2, Operator.PLUS, 8, new BigDecimal(10))
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
}
