import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class Calculator {
 
    JFrame frame;
    JTextField textField;
    JLabel label;
    JButton[] numberButtons = new JButton[10];
    JButton addBtn, subBtn, mulBtn, divBtn;
    JButton percentBtn, sqrtBtn, powerBtn, dotBtn,negBtn, factBtn;
    JButton clearBtn, delBtn, equalBtn;
 
    //Colors for Buttons
    Color pastelBg = new Color(255, 250, 240);
    Color pastelBtn = new Color(255, 204, 229);
    Color pastelAccent = new Color(204, 255, 229);
    Color pastelDark = new Color(179, 179, 255);
    Color pastelText = new Color(60, 60, 60);
 
    double number, answer;
    int calculation = 0;
 
    public Calculator() {
        frame = new JFrame("Pastel Calculator");
        frame.setSize(400, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(pastelBg);
 
        label = new JLabel("");
        label.setFont(new Font("Consolas", Font.PLAIN, 18));
        label.setForeground(pastelText);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 15, 0, 15));
        frame.add(label, BorderLayout.NORTH);
 
        textField = new JTextField("");
        textField.setFont(new Font("Consolas", Font.BOLD, 34));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        frame.add(textField, BorderLayout.CENTER);
 
        JPanel pad = new JPanel(new GridLayout(6, 4, 5, 5));
        pad.setBackground(pastelBg);
 
        // Calling Buttons
        for (int i = 0; i <= 9; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            styleButton(numberButtons[i], pastelBtn, pastelText);
        }
 
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        percentBtn = new JButton("%");
        sqrtBtn = new JButton("√");
        powerBtn = new JButton("^");
        dotBtn = new JButton(".");
        clearBtn = new JButton("Clear");
        delBtn = new JButton("DEL");
        equalBtn = new JButton("=");
        negBtn = new JButton("±");
        factBtn = new JButton("!");
 
 
        // Styling operation and special buttons
        styleButton(addBtn, pastelAccent, pastelText);
        styleButton(subBtn, pastelAccent, pastelText);
        styleButton(mulBtn, pastelAccent, pastelText);
        styleButton(divBtn, pastelAccent, pastelText);
        styleButton(percentBtn, pastelAccent, pastelText);
        styleButton(sqrtBtn, pastelAccent, pastelText);
        styleButton(powerBtn, pastelAccent, pastelText);
        styleButton(dotBtn, pastelBtn, pastelText);
        styleButton(clearBtn, pastelDark, Color.WHITE);
        styleButton(delBtn, pastelDark, Color.WHITE);
        styleButton(negBtn, pastelAccent, pastelText);
        styleButton(factBtn, pastelAccent, pastelText);
        styleButton(equalBtn, pastelDark, Color.WHITE);
 
        // Adding action listeners
        ActionListener actionListener = new ActionListener() {
            double firstNumber = 0;
            String operator = "";
            boolean startNewNumber = true;
 
            public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            String current = textField.getText();
 
            try {
                if (source == clearBtn) {
                label.setText("");
                textField.setText("");
                firstNumber = 0;
                operator = "";
                startNewNumber = true;
                } else if (source == delBtn && !current.isEmpty()) {
                textField.setText(current.substring(0, current.length() - 1));
                if (textField.getText().isEmpty()) label.setText("");
                } else if (source == dotBtn && !current.contains(".")) {
                textField.setText(current + ".");
                } else if (source == sqrtBtn && !current.isEmpty()) {
                double input = Double.parseDouble(current);
                if (input < 0) {
                    textField.setText("Error");
                    label.setText("");
                } else {
                    textField.setText(removeTrailingZero(Math.sqrt(input)));
                    label.setText("√(" + current + ")");
                }
                } else if (source == equalBtn && !operator.isEmpty() && !startNewNumber) {
                double currentNumber = Double.parseDouble(current);
                switch (operator) {
                    case "+": firstNumber += currentNumber; break;
                    case "-": firstNumber -= currentNumber; break;
                    case "*": firstNumber *= currentNumber; break;
                    case "/": 
                    if (currentNumber == 0) throw new ArithmeticException();
                    firstNumber /= currentNumber; 
                    break;
                    case "%": firstNumber += (firstNumber * currentNumber / 100); break;
                    case "^": firstNumber = Math.pow(firstNumber, currentNumber); break;
                }
                textField.setText(removeTrailingZero(firstNumber));
                label.setText("");
                operator = "";
                startNewNumber = true;
                } else if (source == negBtn && !current.equals("0")) {
                textField.setText(current.startsWith("-") ? current.substring(1) : "-" + current);
                } else if (source == factBtn && !current.isEmpty()) {
                double input = Double.parseDouble(current);
                if (input < 0 || input != Math.floor(input)) {
                    textField.setText("Error");
                    label.setText("");
                } else {
                    long result = 1;
                    for (long i = 1; i <= (long) input; i++) result *= i;
                    textField.setText(String.valueOf(result));
                    label.setText((long) input + "!");
                }
                } else if (source == addBtn || source == subBtn || source == mulBtn ||
                       source == divBtn || source == powerBtn) {
                double currentNumber = Double.parseDouble(current);
                if (!operator.isEmpty() && !startNewNumber) {
                    switch (operator) {
                    case "+": firstNumber += currentNumber; break;
                    case "-": firstNumber -= currentNumber; break;
                    case "*": firstNumber *= currentNumber; break;
                    case "/": 
                        if (currentNumber == 0) throw new ArithmeticException();
                        firstNumber /= currentNumber; 
                        break;
                    case "^": firstNumber = Math.pow(firstNumber, currentNumber); break;
                    }
                    textField.setText(removeTrailingZero(firstNumber));
                } else {
                    firstNumber = currentNumber;
                }
                operator = ((JButton) source).getText();
                startNewNumber = true;
                label.setText(removeTrailingZero(firstNumber) + operator);
                } else if (source == percentBtn && !current.isEmpty()) {
                double currentNumber = Double.parseDouble(current);
                if (!operator.isEmpty() && !startNewNumber) {
                    firstNumber += (firstNumber * currentNumber / 100);
                    textField.setText(removeTrailingZero(firstNumber));
                    label.setText("");
                    operator = "";
                    startNewNumber = true;
                } else {
                    textField.setText(removeTrailingZero(currentNumber / 100));
                    label.setText(current + "%");
                }
                } else {
                for (int i = 0; i <= 9; i++) {
                    if (source == numberButtons[i]) {
                    textField.setText(startNewNumber || current.equals("0") ? String.valueOf(i) : current + i);
                    startNewNumber = false;
                    }
                }
                }
            } catch (Exception ex) {
                textField.setText("Error");
                label.setText("");
                operator = "";
            }
            }
        };
 
        // Action listeners for action buttons
        for (int i = 0; i <= 9; i++) {
            numberButtons[i].addActionListener(actionListener);
        }
        addBtn.addActionListener(actionListener);
        subBtn.addActionListener(actionListener);
        mulBtn.addActionListener(actionListener);
        divBtn.addActionListener(actionListener);
        percentBtn.addActionListener(actionListener);
        powerBtn.addActionListener(actionListener);
        sqrtBtn.addActionListener(actionListener);
        dotBtn.addActionListener(actionListener);
        clearBtn.addActionListener(actionListener);
        delBtn.addActionListener(actionListener);
        equalBtn.addActionListener(actionListener);
        negBtn.addActionListener(actionListener);
        factBtn.addActionListener(actionListener);
 
        // Layout buttons in the panel 
        pad.add(clearBtn);
        pad.add(delBtn);
        pad.add(percentBtn);
        pad.add(divBtn);
        pad.add(numberButtons[7]);
        pad.add(numberButtons[8]);
        pad.add(numberButtons[9]);
        pad.add(mulBtn);
        pad.add(numberButtons[4]);
        pad.add(numberButtons[5]);
        pad.add(numberButtons[6]);
        pad.add(subBtn);
        pad.add(numberButtons[1]);
        pad.add(numberButtons[2]);
        pad.add(numberButtons[3]);
        pad.add(addBtn);
        pad.add(numberButtons[0]);
        pad.add(dotBtn);
        pad.add(powerBtn);
        pad.add(sqrtBtn);
        pad.add(equalBtn);
        pad.add(negBtn);
        pad.add(factBtn);
 
        frame.add(pad, BorderLayout.SOUTH);
    }
 
    //Assigning style to buttons
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Consolas", Font.BOLD, 22));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
 
    //for removing .0 on whole numbers
    private String removeTrailingZero(double value) {
        if (value == (long) value)
            return String.valueOf((long) value);
        else
            return String.valueOf(value);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::showCalculator);
    }
 
    private static void showCalculator() {
        new Calculator().frame.setVisible(true);
    }
}