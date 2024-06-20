import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {

    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JButton guessButton;
    private JButton playAgainButton;
    private int randomNumber;
    private int attemptsLeft;
    private int score;
    private final int MAX_ATTEMPTS = 10;

    public GuessingGame() {
        setTitle("Number Guessing Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel titleLabel = new JLabel("Guess the Number!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0x4CAF50));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        contentPanel.add(new JLabel("Guess the number between 1 and 100:", SwingConstants.CENTER));

        guessField = new JTextField();
        guessField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        contentPanel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        guessButton.setBackground(new Color(0x2196F3));
        guessButton.setForeground(Color.WHITE);
        guessButton.addActionListener(new GuessButtonListener());
        contentPanel.add(guessButton);

        feedbackLabel = new JLabel("Enter your guess and click 'Guess'", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        feedbackLabel.setForeground(new Color(0xFF5722));
        contentPanel.add(feedbackLabel);

        attemptsLabel = new JLabel("Attempts left: " + MAX_ATTEMPTS, SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        attemptsLabel.setForeground(new Color(0xFF9800));
        contentPanel.add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        scoreLabel.setForeground(new Color(0x9C27B0));
        contentPanel.add(scoreLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        playAgainButton.setBackground(new Color(0x4CAF50));
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setEnabled(false);
        playAgainButton.addActionListener(new PlayAgainButtonListener());
        mainPanel.add(playAgainButton, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);

        startNewGame();
    }

    private void startNewGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attemptsLeft = MAX_ATTEMPTS;
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        feedbackLabel.setText("Enter your guess and click 'Guess'");
        guessField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
        System.out.println("Random Number (for debugging): " + randomNumber); // You can remove this line
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attemptsLeft--;
            attemptsLabel.setText("Attempts left: " + attemptsLeft);

            if (guess == randomNumber) {
                feedbackLabel.setText("Correct! You guessed the number!");
                score++;
                scoreLabel.setText("Score: " + score);
                endGame();
            } else if (guess < randomNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }

            if (attemptsLeft <= 0) {
                feedbackLabel.setText("You've run out of attempts! The number was " + randomNumber);
                endGame();
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    private void endGame() {
        guessButton.setEnabled(false);
        playAgainButton.setEnabled(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkGuess();
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuessingGame game = new GuessingGame();
                game.setVisible(true);
            }
        });
    }
}