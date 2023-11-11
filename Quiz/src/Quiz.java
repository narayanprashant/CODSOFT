import java.util.Scanner;

class Question {
    String question;
    String[] options;
    int correctAnswer;
    int userAnswer;
    int timeLimit;

    Question(String question, String[] options, int correctAnswer, int timeLimit) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.timeLimit = timeLimit;
    }
}

public class Quiz {
    private Question[] questions;
    private int score;

    Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
    }

    private void displayQuestion(Question question) {
        System.out.println(question.question);
        for (int i = 0; i < question.options.length; i++) {
            System.out.println((i + 1) + ". " + question.options[i]);
        }
    }

    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= questions[0].options.length) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + questions[0].options.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void runQuiz() {
        for (Question question : questions) {
            displayQuestion(question);
            System.out.print("Enter your choice: ");

            long startTime = System.currentTimeMillis();
            int userChoice = getUserInput();
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            if (elapsedTime > question.timeLimit * 1000) {
                System.out.println("Time's up! Moving to the next question.");
                continue;
            }

            if (question.correctAnswer == userChoice) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was " + question.correctAnswer + ": " + question.options[question.correctAnswer - 1] + "\n");
            }
        }

        displayResult();
    }

    private void displayResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
        System.out.println("Summary:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println((i + 1) + ". " + questions[i].question + " - " + (questions[i].correctAnswer == questions[i].userAnswer ? "Correct" : "Incorrect"));
        }
    }

    public static void main(String[] args) {
        Question[] quizQuestions = {
                new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3, 10),
                new Question("Which programming language is this quiz written in?", new String[]{"Java", "Python", "C++", "JavaScript"}, 2, 15)
                // Add more questions as needed
        };

        Quiz myQuiz = new Quiz(quizQuestions);
        myQuiz.runQuiz();
    }
}
