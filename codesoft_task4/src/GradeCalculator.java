import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends JFrame {

    private JTextField[] subjectFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public GradeCalculator() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Create subject input fields
        JLabel[] subjectLabels = new JLabel[5];
        subjectFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " marks:");
            subjectFields[i] = new JTextField(10);
            mainPanel.add(subjectLabels[i]);
            mainPanel.add(subjectFields[i]);
        }

        // Total marks, average percentage, grade labels
        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");
        mainPanel.add(totalMarksLabel);
        mainPanel.add(new JLabel()); // placeholder
        mainPanel.add(averagePercentageLabel);
        mainPanel.add(new JLabel()); // placeholder
        mainPanel.add(gradeLabel);
        mainPanel.add(new JLabel()); // placeholder

        // Calculate button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplay();
            }
        });
        mainPanel.add(calculateButton);

        add(mainPanel);
        setVisible(true);
    }

    private void calculateAndDisplay() {
        try {
            int totalMarks = 0;
            int numSubjects = subjectFields.length;
            for (int i = 0; i < numSubjects; i++) {
                totalMarks += Integer.parseInt(subjectFields[i].getText());
            }

            double averagePercentage = (double) totalMarks / numSubjects;

            // Determine grade
            String grade;
            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C";
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            // Display results
            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage));
            gradeLabel.setText("Grade: " + grade);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid marks (numeric values).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculator();
            }
        });
    }
}