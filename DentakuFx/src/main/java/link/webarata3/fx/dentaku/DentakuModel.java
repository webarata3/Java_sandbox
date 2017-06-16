package link.webarata3.fx.dentaku;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DentakuModel {
    private static final DentakuModel dentakuModel = new DentakuModel();

    private String beforeBuffer;
    private String currentBuffer;

    /**
     * 現在の演算子
     */
    private Operator currentOperator;

    /**
     * 結果を表示した後かどうか
     */
    private boolean isResult;

    private DentakuModel() {
        clearBuffer();
    }

    public static DentakuModel getInstance() {
        return dentakuModel;
    }

    public String getCurrentBuffer() {
        return currentBuffer;
    }

    public void clearBuffer() {
        beforeBuffer = "";
        currentBuffer = "";
    }

    public void appendNumber(int num) {
        if (isResult) {
            currentBuffer = beforeBuffer;
        }
        if (currentBuffer.length() >= 10) return;
        if (num == 0 && currentBuffer.equals("")) return;
        currentBuffer = currentBuffer + num;
    }

    public void setOperator(String operator) {
        currentOperator = Operator.getOperator(operator);
        if (currentBuffer.equals("")) {
            currentBuffer = beforeBuffer;
        }
        beforeBuffer = currentBuffer;
        currentBuffer = "";
    }

    public void calc() {
        BigDecimal beforeNum = new BigDecimal(beforeBuffer);
        BigDecimal currentNum = beforeNum;
        if (!currentBuffer.equals("")) {
            currentNum = new BigDecimal(currentBuffer);
        }

        currentBuffer = currentOperator.calc(beforeNum, currentNum).toPlainString();
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
