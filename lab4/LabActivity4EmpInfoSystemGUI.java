import java.awt.event.*;
import javax.swing.*;

public class LabActivity4EmpInfoSystemGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Laboratory Activity 4");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        
        JLabel lblFname = new JLabel("First Name");
        lblFname.setBounds(20, 20, 100, 20);
        JTextField txtFname = new JTextField();
        txtFname.setBounds(130, 20, 300, 20);

        JLabel lblLname = new JLabel("Last Name");
        lblLname.setBounds(20, 50, 100, 20);
        JTextField txtLname = new JTextField();
        txtLname.setBounds(130, 50, 300, 20);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(20, 80, 100, 20);
        JTextField txtAge = new JTextField();
        txtAge.setBounds(130, 80, 300, 20);

        JLabel lblHours = new JLabel("Hours Worked");
        lblHours.setBounds(20, 110, 100, 20);
        JTextField txtHours = new JTextField();
        txtHours.setBounds(130, 110, 300, 20);

        JLabel lblRate = new JLabel("Hourly Rate");
        lblRate.setBounds(20, 140, 100, 20);
        JTextField txtRate = new JTextField();
        txtRate.setBounds(130, 140, 300, 20);

        
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(200, 180, 100, 30);

        
        JLabel lblOutput = new JLabel("Output:");
        lblOutput.setBounds(20, 230, 100, 20);

        JTextArea outputArea = new JTextArea();
        outputArea.setBounds(20, 260, 440, 100);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);

       
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fname = txtFname.getText().trim();
                String lname = txtLname.getText().trim();
                String ageText = txtAge.getText().trim();
                String hoursText = txtHours.getText().trim();
                String rateText = txtRate.getText().trim();
        
                // Check if any field is empty
                if (fname.isEmpty() || lname.isEmpty() || ageText.isEmpty() ||
                    hoursText.isEmpty() || rateText.isEmpty()) {
                    outputArea.setText("All fields must be filled out.");
                    return;
                }
        
                int age, hours, rate;
        
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    outputArea.setText("Age must be a valid integer.");
                    return;
                }
        
                try {
                    hours = Integer.parseInt(hoursText);
                } catch (NumberFormatException ex) {
                    outputArea.setText("Hours worked must be a valid integer.");
                    return;
                }
        
                try {
                    rate = Integer.parseInt(rateText);
                } catch (NumberFormatException ex) {
                    outputArea.setText("Hourly rate must be a valid integer.");
                    return;
                }
        
                // Validate age range
                if (age < 18 || age > 65) {
                    outputArea.setText("Invalid age: Must be between 18 and 65.");
                    return;
                }
        
                // Validate hours range
                if (hours <= 0 || hours > 24) {
                    outputArea.setText("Invalid hours worked: Must be between 1 and 24.");
                    return;
                }
        
                int dailySalary = hours * rate;
        
                outputArea.setText(
                    "Full Name: " + fname + " " + lname + "\n" +
                    "Age: " + age + " years old\n" +
                    String.format("Daily Salary: PHP %d", dailySalary)
                );
            }
        });
        
        
        frame.add(lblFname);
        frame.add(txtFname);
        frame.add(lblLname);
        frame.add(txtLname);
        frame.add(lblAge);
        frame.add(txtAge);
        frame.add(lblHours);
        frame.add(txtHours);
        frame.add(lblRate);
        frame.add(txtRate);
        frame.add(submitBtn);
        frame.add(lblOutput);
        frame.add(outputArea);

        frame.setVisible(true);
    }
}
