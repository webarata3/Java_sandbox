package link.webarata3.fx.dentaku;

import sun.tools.jstat.Operator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DentakuModel {

    public static interface Observer {
        void updateCurrentValue();
    }

    private List<Observer> observerList;

    private static final DentakuModel dentakuModel = new DentakuModel();

    private BigDecimal beforeValue;
    private BigDecimal currentValue;

    /**
     * 現在の演算子
     */
    private Operator currentOperator;

    /**
     * 結果を表示した後かどうか
     */
    private boolean isResult;

    /**
     * 数字を入れた後かどうか
     */
    private boolean isNumberInput;

    private DentakuModel() {
        observerList = new ArrayList<>();
        clearBuffer();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public static DentakuModel getInstance() {
        return dentakuModel;
    }

    public BigDecimal getCurrentBuffer() {
        return currentValue;
    }

    public void clearBuffer() {
        beforeValue = null;
        currentValue = BigDecimal.ZERO;
        isResult = false;

        notifyUpdate();
    }

    public void appendNumber(int num) {
        if (isResult) {
            currentValue = beforeValue;
            currentValue = BigDecimal.ZERO;
        }

        currentValue = currentValue.multiply(BigDecimal.TEN).add(new BigDecimal(num));

        notifyUpdate();
    }

    public void setOperator(String operator) {
        if (beforeValue != null) {
            calc();
            currentOperator = Operator.getOperator(operator);
            beforeValue = currentValue;
            isResult = true;
            return;
        }

        currentOperator = Operator.getOperator(operator);


        beforeValue = currentValue;
        currentValue = BigDecimal.ZERO;

        isNumberInput = false;
    }

    public void calc() {
        if (beforeValue == null) {
            beforeValue = BigDecimal.ZERO;
        }
        currentValue = currentOperator.calc(beforeValue, currentValue);
        isResult = true;

        notifyUpdate();
    }

    private void notifyUpdate() {
        observerList.forEach(Observer::updateCurrentValue);
    }

    enum Operator {
        PLUS {
            public BigDecimal calc(BigDecimal beforeNum, BigDecimal num) {
                return beforeNum.add(num);
            }
        }, MINUS {
            public BigDecimal calc(BigDecimal beforeNum, BigDecimal num) {
                return beforeNum.subtract(num);
            }
        }, MULTIPLY {
            public BigDecimal calc(BigDecimal beforeNum, BigDecimal num) {
                return beforeNum.multiply(num);
            }
        }, DIVIDE {
            public BigDecimal calc(BigDecimal beforeNum, BigDecimal num) {
                return beforeNum.divide(num, 10, RoundingMode.HALF_UP);
            }
        };

        public abstract BigDecimal calc(BigDecimal beforeNum, BigDecimal num);

        public static Operator getOperator(String operator) {
            switch (operator) {
                case "＋":
                    return PLUS;
                case "－":
                    return MINUS;
                case "×":
                    return MULTIPLY;
                case "÷":
                    return DIVIDE;
                default:
                    return null;
            }
        }
    }
}
