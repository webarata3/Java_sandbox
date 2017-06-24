package link.webarata3.fx.dentaku;

import static link.webarata3.fx.dentaku.DentakuModel.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class DentakuController implements Initializable, Observer {
    private DentakuModel dentakuModel;

    @FXML
    private Label resultLabel;

    private void appendNumber(int num) {
        dentakuModel.appendNumber(num);
    }

    @FXML
    private void onClickNumButton(ActionEvent event) {
        String str = ((Button) event.getSource()).getText();
        appendNumber(Integer.parseInt(str));
    }

    @FXML
    private void onClickDecimalPointButton() {
        dentakuModel.appendDecimalPoint();
    }

    @FXML
    private void onClickClearButton() {
        dentakuModel.clear();
    }

    @FXML
    private void onClickOperatorButton(ActionEvent event) {
        String operator = ((Button) event.getSource()).getText();
        dentakuModel.setOperator(Operator.getOperator(operator));
    }

    @FXML
    private void onClickEqualButton() {
        dentakuModel.calc();
    }

    /**
     * 小数の点の後の0の連続を削除する
     */
    private String removeFollowingZero(BigDecimal decimal) {
        String target = decimal.toPlainString();

        int dotLoc = target.indexOf(".");

        if (dotLoc == -1) {
            return target;
        }

        StringBuilder sb = new StringBuilder(target);
        for (int i = target.length() - 1; i > dotLoc; i--) {
            if (sb.charAt(i) != '0') {
                break;
            }
            sb.deleteCharAt(i);
            // 0の前が . だったらそれも削除する
            if (sb.charAt(i - 1) == '.') {
                sb.deleteCharAt(i - 1);
                break;
            }
        }

        return sb.toString();
    }

    @FXML
    private void onClickDotButton() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dentakuModel = DentakuModel.getInstance();
        dentakuModel.addObserver(this);
    }

    @Override
    public void updateCurrentValue() {
        BigDecimal currentValue = dentakuModel.getCurrentValue();
        resultLabel.setText(removeFollowingZero(currentValue));
    }
}
