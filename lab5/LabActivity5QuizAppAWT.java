import java.awt.*;
import java.awt.event.*;

public class LabActivity5QuizAppAWT {
    Frame frame;
    Label questionLabel, feedbackLabel;
    CheckboxGroup optionsGroup;
    Checkbox option1, option2, option3, option4;
    Button nextButton;

    String[] questions = {
        "What is CPU?",
        "Not OOP Language?",
        "Hex of 10?"
    };

    String[][] choices = {
        {"A. Central Processing Unit", "B. Computer Processing Unit", "C. Control Panel Unit", "D. Central Performance Utility"},
        {"A. Python", "B. C++", "C. HTML", "D. Java"},
        {"A. A", "B. 10", "C. C", "D. B"}
    };

    char[] answers = {'A', 'C', 'A'};

    int currentQuestion = 0;
    int score = 0;



    public LabActivity5QuizAppAWT() {
        frame = new Frame("Quiz App");
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Top Panel para sa question
        Panel topPanel = new Panel();
        questionLabel = new Label();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(questionLabel);

        // Center Panel para sa options
        Panel centerPanel = new Panel(new GridLayout(2, 2));
        optionsGroup = new CheckboxGroup();
        option1 = new Checkbox("", optionsGroup, false);
        option2 = new Checkbox("", optionsGroup, false);
        option3 = new Checkbox("", optionsGroup, false);
        option4 = new Checkbox("", optionsGroup, false);

        // Customizations
        option1.setForeground(Color.GREEN);
        option2.setForeground(Color.GREEN);
        option3.setForeground(Color.GREEN);
        option4.setForeground(Color.GREEN);

        centerPanel.add(option1);
        centerPanel.add(option2);
        centerPanel.add(option3);
        centerPanel.add(option4);

        // Bottom Panel para sa feedback at button
        Panel bottomPanel = new Panel(new GridLayout(2, 1));
        feedbackLabel = new Label("Please select an answer.", Label.CENTER);
        nextButton = new Button("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Checkbox selected = optionsGroup.getSelectedCheckbox();
                if (selected == null) {
                    feedbackLabel.setText("Please select an answer.");
                    return;
                }
                char userAnswer = selected.getLabel().charAt(0);
                if (userAnswer == answers[currentQuestion]) {
                    score++;
                }
                currentQuestion++;
                if (currentQuestion < questions.length) {
                    loadQuestion();
                } else {
                    questionLabel.setText("Quiz Completed!");
                    option1.setEnabled(false);
                    option2.setEnabled(false);
                    option3.setEnabled(false);
                    option4.setEnabled(false);
                    nextButton.setEnabled(false);
                    feedbackLabel.setText("You got " + score + " out of " + questions.length + " correct!");
                }
            }
        });

        bottomPanel.add(feedbackLabel);
        bottomPanel.add(nextButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        loadQuestion();
        frame.setVisible(true);
    }

    void loadQuestion() {
        questionLabel.setText(questions[currentQuestion]);
        option1.setLabel(choices[currentQuestion][0]);
        option2.setLabel(choices[currentQuestion][1]);
        option3.setLabel(choices[currentQuestion][2]);
        option4.setLabel(choices[currentQuestion][3]);
        optionsGroup.setSelectedCheckbox(null);
        feedbackLabel.setText("Please select an answer.");
    }

    public static void main(String[] args) {
        new LabActivity5QuizAppAWT();
    }
}
