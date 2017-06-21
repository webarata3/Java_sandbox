package link.webarata3.fx.dentaku;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DentakuModel {
    public interface Observer {
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

    private DentakuModel() {
        observerList = new ArrayList<>();
        clear();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public static DentakuModel getInstance() {
        return dentakuModel;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void clear() {
        beforeValue = null;
        currentValue = BigDecimal.ZERO;
        isResult = false;

        notifyUpdate();
    }

    public void appendNumber(int num) {
        if (isResult) {
            beforeValue = currentValue;
            currentValue = BigDecimal.ZERO;
            isResult = false;
        }

        currentValue = currentValue.multiply(BigDecimal.TEN).add(new BigDecimal(num));

        notifyUpdate();
    }

    public void setOperator(Operator operator) {
        // 直前に計算結果が出ている場合には、演算子のみを変更する
        if (isResult) {
            currentOperator = operator;
            return;
        }

        if (beforeValue != null) {
            calc();
            currentOperator = operator;
            beforeValue = currentValue;
            isResult = true;
            return;
        }

        currentOperator = operator;

        beforeValue = currentValue;
        currentValue = BigDecimal.ZERO;
    }

    public void calc() {
        // 直前に結果の計算をしている場合には、同じ計算を再度行う
        if (isResult) {
            currentValue = currentOperator.calc(currentValue, beforeValue);
            notifyUpdate();
            return;
        }
        BigDecimal tempValue = currentValue;
        currentValue = currentOperator.calc(beforeValue, currentValue);
        beforeValue = tempValue;

        isResult = true;

        notifyUpdate();
    }

    private void notifyUpdate() {
        observerList.forEach(Observer::updateCurrentValue);
    }

    enum Operator {
        PLUS {
            public BigDecimal calc(BigDecimal beforeValue, BigDecimal currentValue) {
                return beforeValue.add(currentValue);
            }
        }, MINUS {
            public BigDecimal calc(BigDecimal beforeValue, BigDecimal currentValue) {
                return beforeValue.subtract(currentValue);
            }
        }, MULTIPLY {
            public BigDecimal calc(BigDecimal beforeValue, BigDecimal currentValue) {
                return beforeValue.multiply(currentValue);
            }
        }, DIVIDE {
            public BigDecimal calc(BigDecimal beforeValue, BigDecimal currentValue) {
                return beforeValue.divide(currentValue, 10, RoundingMode.HALF_UP);
            }
        };

        public abstract BigDecimal calc(BigDecimal beforeValue, BigDecimal currentValue);

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
