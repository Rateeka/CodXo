import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// User class
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}

// Question class
class Question {
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int option) {
        return option == correctOption;
    }
}

// Quiz class
class Quiz {
    private String title;
    private List<Question> questions;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

// QuizManagementSystem class
class QuizManagementSystem {
    private List<User> users;
    private List<Quiz> quizzes;
    private User currentUser;

    public QuizManagementSystem() {
        users = new ArrayList<>();
        quizzes = new ArrayList<>();
        currentUser = null;
    }

    public boolean registerUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // User already exists
            }
        }
        users.add(new User(username, password));
        return true;
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logoutUser() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void createQuiz(String title) {
        if (currentUser != null) {
            quizzes.add(new Quiz(title));
        }
    }

    public void addQuestionToQuiz(String quizTitle, String questionText, String[] options, int correctOption) {
        for (Quiz quiz : quizzes) {
            if (quiz.getTitle().equals(quizTitle)) {
                quiz.addQuestion(new Question(questionText, options, correctOption));
                break;
            }
        }
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public Quiz getQuizByTitle(String title) {
        for (Quiz quiz : quizzes) {
            if (quiz.getTitle().equals(title)) {
                return quiz;
            }
        }
        return null;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        QuizManagementSystem qms = new QuizManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create Quiz");
            System.out.println("4. Add Question to Quiz");
            System.out.println("5. Take Quiz");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (qms.registerUser(username, password)) {
                        System.out.println("User registered successfully.");
                    } else {
                        System.out.println("Username already exists.");
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.next();
                    System.out.print("Enter password: ");
                    password = scanner.next();
                    if (qms.loginUser(username, password)) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 3:
                    if (qms.getCurrentUser() == null) {
                        System.out.println("Please login first.");
                    } else {
                        System.out.print("Enter quiz title: ");
                        String title = scanner.nextLine();
                        qms.createQuiz(title);
                        System.out.println("Quiz created successfully.");
                    }
                    break;
                case 4:
                    if (qms.getCurrentUser() == null) {
                        System.out.println("Please login first.");
                    } else {
                        System.out.print("Enter quiz title: ");
                        String quizTitle = scanner.nextLine();
                        Quiz quiz = qms.getQuizByTitle(quizTitle);
                        if (quiz == null) {
                            System.out.println("Quiz not found.");
                        } else {
                            System.out.print("Enter question text: ");
                            String questionText = scanner.nextLine();
                            System.out.print("Enter number of options: ");
                            int numOptions = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            String[] options = new String[numOptions];
                            for (int i = 0; i < numOptions; i++) {
                                System.out.print("Enter option " + (i + 1) + ": ");
                                options[i] = scanner.nextLine();
                            }
                            System.out.print("Enter correct option number: ");
                            int correctOption = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            qms.addQuestionToQuiz(quizTitle, questionText, options, correctOption);
                            System.out.println("Question added successfully.");
                        }
                    }
                    break;
                case 5:
                    if (qms.getCurrentUser() == null) {
                        System.out.println("Please login first.");
                    } else {
                        System.out.print("Enter quiz title: ");
                        String quizTitle = scanner.nextLine();
                        Quiz quiz = qms.getQuizByTitle(quizTitle);
                        if (quiz == null) {
                            System.out.println("Quiz not found.");
                        } else {
                            int score = 0;
                            for (Question question : quiz.getQuestions()) {
                                System.out.println(question.getQuestion());
                                String[] options = question.getOptions();
                                for (int i = 0; i < options.length; i++) {
                                    System.out.println((i + 1) + ". " + options[i]);
                                }
                                System.out.print("Enter your answer: ");
                                int answer = scanner.nextInt();
                                scanner.nextLine(); // consume newline
                                if (question.isCorrect(answer - 1)) {
                                    score++;
                                }
                            }
                            System.out.println("Your score: " + score + "/" + quiz.getQuestions().size());
                        }
                    }
                    break;
                case 6:
                    qms.logoutUser();
                    System.out.println("Logged out successfully.");
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
