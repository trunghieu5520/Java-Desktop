/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Minh
 */
public class ControlCalculator {

    private Double firstNumber;
    private Double secondNumber;
    private JTextField view;
    private boolean isCalculating = false;
    private boolean isReset = false;
    private String operator = "";
    private Double memory = 0.0;
    private boolean backspace = false;
    DecimalFormat dc = new DecimalFormat("#.########");
    private final int check = 0;
    private boolean checkrs = false;

    public void setOperator(String operator) {
        this.operator = operator;

    }

    public ControlCalculator(JTextField view) {
        this.view = view;
        operator = "";
        view.setText("0");
    }

    public void Number(JButton btn) {
        if (isCalculating || isReset) {
            // nếu đang tính thì set text = 0 để số sau không đè lên số trước
            view.setText("0");
            // set lại để bấm lại được nhiều lần cho 1 buttom
            isCalculating = false;
            isReset = false;
        }
        String temp;
        if (view.getText().equalsIgnoreCase("0")) {
            view.setText(btn.getText() + "");
        } else {
            temp = view.getText() + btn.getText();
            view.setText(temp + "");
        }
        // có thể xóa được
        backspace = true;
    }

    public void Sqrt() {
        if (view.getText().equalsIgnoreCase("ERROR")) {
            return;
        }
        double result = new BigDecimal(view.getText()).doubleValue();
        if (result < 0) {
            view.setText("Error");
        } else {
            String display = dc.format(Math.sqrt(result));
            view.setText(display);
        }
        isReset = true;
        backspace = false;
    }

    public void Dot() {
        if (view.getText().equalsIgnoreCase("ERROR")) {
            return;
        }
//        cho chấm vào kết quả hoặc khi đã bấm phép tính
//        các số sau sẽ đc thêm vào sau dấu chấm
        
        if (isCalculating||isReset) {
//            return;
            view.setText("0");
            isCalculating = false;
        } else {
            Double result = Double.parseDouble(view.getText());
            // nếu có chấm rồi thì không chấm nữa
            if (!view.getText().contains(".")) {
                view.setText(dc.format(result) + ".");
            }
        }
        //cho chấm kết quả
        isReset = false;
        backspace = true;
    }

    public void Negative() {
        if (isCalculating) {
            return;

        }
        if (view.getText().equalsIgnoreCase("ERROR")) {
            return;
        }
        StringBuilder temp = new StringBuilder(view.getText());
        if (!view.getText().equalsIgnoreCase("0.")&&!view.getText().equalsIgnoreCase("0.0")
                &&!view.getText().equalsIgnoreCase("0.00")&&!view.getText().equalsIgnoreCase("0.00")
                &&!view.getText().equalsIgnoreCase("0.000")&&!view.getText().equalsIgnoreCase("0.000")
                &&!view.getText().equalsIgnoreCase("0.0000")&&!view.getText().equalsIgnoreCase("0.0000")
                &&!view.getText().equalsIgnoreCase("0.00000")&&!view.getText().equalsIgnoreCase("0.000000")
                &&!view.getText().equalsIgnoreCase("0.0000000")&&!view.getText().equalsIgnoreCase("0.00000000 ")) {
            if (temp.charAt(0) != '-') {
                temp.insert(0, "-");
            }
            else {
                temp.deleteCharAt(0);
            }
        }
        view.setText(temp + "");
        isCalculating = false;
        isReset = false;
        if (isReset) {
            backspace = false;
        } 
    }

    public void Persent() {
        if (view.getText().equalsIgnoreCase("ERROR")) {
            return;
        }
        double result = new BigDecimal(view.getText()).doubleValue() / 100;
        String display = dc.format(result);
        view.setText(display);
        isReset = true;
        backspace = false;
    }

    public void Invert() {
        if (view.getText().equalsIgnoreCase("ERROR")) {
            return;
        }
        double result = new BigDecimal(view.getText()).doubleValue();
        if (result != 0) {
            String display = dc.format(1 / result);
            view.setText(display);
        } else {
            view.setText("Erorr");
        }
        isReset = true;
        backspace = false;
    }

    public void Madd() {
        if (!view.getText().equalsIgnoreCase("Error")) {
            memory = memory + Double.parseDouble(view.getText());
//            isCalculating = false;
        }
        isReset = true;
        backspace = false;
    }

    public void Msub() {
        if (!view.getText().equalsIgnoreCase("Error")) {
            memory = memory - Double.parseDouble(view.getText());
//            isCalculating = false;

        }
        isReset = true;
        backspace = false;
    }

    public void MC() {
        memory = 0.0;
        isReset = true;
    }

    public void Clear() {
        view.setText("0");
        isCalculating = true;
        isReset = false;
        operator = "";
        backspace = false;
    }

    public void MR() {
        view.setText(dc.format(memory) + "");
        backspace = false;
    }

    public void Result() {
        
        if (view.getText().equalsIgnoreCase("Error")) {
            view.setText("0");
        } else {
            calculate();
            // set phép toán không có gì tránh lưu phép toán 
            // khi người dùng nhập 1 chữ số và ấn bằng thì phép toán sẽ lưu
            operator = "";
        }
        isCalculating = false;
        isReset = true;
        backspace = false;
    }
  

    public void calculate() {
        // kiểm tra xem có tính toán chưa, nếu có tính toán
        // nhưng không nhập số thứ 2 thì sẽ bỏ qua bước này
        if (!isCalculating) {
            if ("".equals(operator)) {
                firstNumber = Double.parseDouble(view.getText());
            } // khi mà có phép toán
            else {
                // lấy số thứ 2 
                secondNumber = Double.parseDouble(view.getText());
                switch (operator) {
                    case "+":
                        firstNumber = firstNumber + secondNumber;
                        break;
                    case "-":
                        firstNumber = firstNumber - secondNumber;
                        break;
                    case "*":
                        firstNumber = firstNumber * secondNumber;
                        break;
                    case "/":
                        // to divide value
                        if (secondNumber == 0) {
                            view.setText("ERROR");
                            return;
                        } else {
                            firstNumber = firstNumber / secondNumber;
                            break;
                        }

                }
            }

        }
        // đã có phép tính -> xóa số thứ nhất và nhập số thứ 2 ở pressButtom
        isCalculating = true;
        backspace = false;
        String result = dc.format(firstNumber);
        view.setText(result);
    }

    public void Backspace() {
        if (backspace) {
            StringBuilder temp = new StringBuilder(view.getText());
            if (temp.length() > 1) {
                // nếu xóa đến số gần dấu chấm chỉ còn 1 số đằng sau phẩy thì xóa phẩy
                if (temp.length() == 3 && temp.charAt(0) == '-' && temp.charAt(2) == '.') {
                    view.setText("0");
                } else if (temp.length() == 2 && temp.charAt(0) == '-') {
                    view.setText("0");
                } else {
                    temp.deleteCharAt(temp.length() - 1);
                    view.setText(temp.toString());
                }
            } else {
                view.setText("0");
            }
        }
    }

}
