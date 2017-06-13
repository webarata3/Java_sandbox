package link.webarata3.fx.dentaku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DentakuController implements Initializable {
    enum Operator {
        PLUS {
            public int calc(int beforeNum, int num) {
                return beforeNum + num;
            }
        }, MINUS {
            public int calc(int beforeNum, int num) {
                return beforeNum - num;
            }
        }, MULTIPLY {
            public int calc(int beforeNum, int num) {
                return beforeNum * num;
            }
        }, DIVIDE {
            public int calc(int beforeNum, int num) {
                return beforeNum / num;
            }
        };

        public abstract int calc(int beforeNum, int num);

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

    @FXML
    private Label resultLabel;

    private String beforeBuffer;
    private String buffer;
    private boolean isResult;
    private Operator currentOperator;

    private void appendNumber(int num) {
        if (isResult) {
            buffer = "";
            isResult = false;
        }
        if (buffer.length() >= 10) return;
        if (num == 0 && buffer.equals("")) return;
        buffer += num;
        display();
    }

    @FXML
    private void onClickNumButton(ActionEvent event) {
        String str = ((Button) event.getSource()).getText();
        appendNumber(Integer.parseInt(str));
    }

    @FXML
    private void onClickClearButton() {
        beforeBuffer = "";
        buffer = "";
        display();
    }

    @FXML
    private void onClickOperatorButton(ActionEvent event) {
        String operator = ((Button) event.getSource()).getText();
        currentOperator = Operator.getOperator(operator);
        if (buffer.equals("")) {
            buffer = beforeBuffer;
        }
        beforeBuffer = buffer;
        buffer = "";
    }

    @FXML
    private void onClickEqualButton() {
        int beforeNum = Integer.parseInt(beforeBuffer);
        int num = beforeNum;
        if (!buffer.equals("")) {
            num = Integer.parseInt(buffer);
        }
        buffer = String.valueOf(currentOperator.calc(beforeNum, num));

        isResult = true;
        display();
    }

    @FXML
    private void onClickDotButton() {
    }

    private void display() {
        if (buffer.equals("")) {
            resultLabel.setText("0");
        } else {
            resultLabel.setText(buffer);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        beforeBuffer = "";
        buffer = "";
        isResult = false;

        display();
    }
}
