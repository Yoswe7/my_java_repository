import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class LabActivity6SwingToDoList {
    static JFrame mainFrame;
    static JFrame secondFrame;
    static DefaultTableModel tableModel;

    public static void main(String[] args) {
        mainFrame = new JFrame("To-Do List Viewer");
        mainFrame.setSize(500, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Task Name", "Task Description", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable todoTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(todoTable);
        todoTable.setDefaultEditor(Object.class, null);
        JPanel topPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        topPanel.add(addButton);

        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(scrollPane, BorderLayout.CENTER);

        // Kapag pinindot yung button, magbubukas yung second window
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (secondFrame == null || !secondFrame.isVisible()) {
                    openSecondWindow();
                }
            }
        });

        mainFrame.setVisible(true);
    }

    static void openSecondWindow() {
    secondFrame = new JFrame("Add New Task");
    secondFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    secondFrame.setLocationRelativeTo(mainFrame);
    secondFrame.setResizable(true);

    JPanel secondPanel = new JPanel();
    secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));
    secondPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JLabel lblSecondTaskName = new JLabel("Task Name:");
    lblSecondTaskName.setAlignmentX(Component.LEFT_ALIGNMENT); 
    JTextField txtSecondTaskName = new JTextField();
    txtSecondTaskName.setAlignmentX(Component.LEFT_ALIGNMENT); 
    txtSecondTaskName.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtSecondTaskName.getPreferredSize().height));

    JLabel lblSecondDescription = new JLabel("Task Description:");
    lblSecondDescription.setAlignmentX(Component.LEFT_ALIGNMENT); 
    JTextArea txtSecondDescription = new JTextArea(2, 20);
    txtSecondDescription.setLineWrap(true);
    txtSecondDescription.setWrapStyleWord(true);
    JScrollPane secondScrollPane = new JScrollPane(txtSecondDescription);
    secondScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); 
    secondScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

    JLabel lblSecondStatus = new JLabel("Status:");
    lblSecondStatus.setAlignmentX(Component.LEFT_ALIGNMENT); 
    String[] statuses = {"Not Started", "Ongoing", "Completed"};
    JComboBox<String> cmbSecondStatus = new JComboBox<>(statuses);
    cmbSecondStatus.setAlignmentX(Component.LEFT_ALIGNMENT); 
    cmbSecondStatus.setMaximumSize(new Dimension(Integer.MAX_VALUE, cmbSecondStatus.getPreferredSize().height));

    JButton saveSecondTaskButton = new JButton("Save Task");
    JPanel secondButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
    secondButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 
    secondButtonPanel.add(saveSecondTaskButton);

    secondPanel.add(lblSecondTaskName);
    secondPanel.add(txtSecondTaskName);
    secondPanel.add(Box.createVerticalStrut(10)); 

    secondPanel.add(lblSecondDescription);
    secondPanel.add(secondScrollPane);
    secondPanel.add(Box.createVerticalStrut(10));

    secondPanel.add(lblSecondStatus);
    secondPanel.add(cmbSecondStatus);
    secondPanel.add(Box.createVerticalStrut(20));

    secondPanel.add(secondButtonPanel);

        // Action Listener para sa saveSecondTaskButton
        saveSecondTaskButton.addActionListener(e -> {
            String taskName = txtSecondTaskName.getText().trim();
            String description = txtSecondDescription.getText().trim();
            String status = (String) cmbSecondStatus.getSelectedItem();

            if (taskName.isEmpty()) {
                JOptionPane.showMessageDialog(secondFrame, "Task Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[] rowData = {taskName, description, status};
            tableModel.addRow(rowData);
            secondFrame.dispose();
        });

        secondFrame.add(secondPanel);
        secondFrame.pack();
        secondFrame.setVisible(true);
    }
}
