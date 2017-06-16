package link.webarata3.fx.dentaku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DentakuController implements Initializable {
    private DentakuModel dentakuModel;

    @FXML
    private Label resultLabel;

     private void appendNumber(int num) {
        dentakuModel.appendNumber(num);
        display();
    }

    @FXML
    private void onClickNumButton(ActionEvent event) {
        String str = ((Button) event.getSource()).getText();
        appendNumber(Integer.parseInt(str));
    }

    @FXML
    private void onClickClearButton() {
        dentakuModel.clearBuffer();

        display();
    }

    @FXML
    private void onClickOperatorButton(ActionEvent event) {
        String operator = ((Button) event.getSource()).getText();
        dentakuModel.setOperator(operator);
   }

    @FXML
    private void onClickEqualButton() {
        dentakuModel.calc();

        display();
    }

    /**
     * 小数の点の後の0の連続を削除する
     */
    private String removeFollowingZero(String target) {
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

    private void display() {
        String currentBuffer = dentakuModel.getCurrentBuffer();
        if (currentBuffer.equals("")) {
            resultLabel.setText("0");
        } else {
            currentBuffer = removeFollowingZero(currentBuffer);
            resultLabel.setText(currentBuffer);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dentakuModel = DentakuModel.getInstance();

        display();
    }
}
