import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private boolean isPresent;
    private boolean isExcused;

    private boolean isAbsent;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.isPresent = false;
        this.isExcused = false;
        this.isAbsent = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public boolean isExcused() {
        return isExcused;
    }
    public boolean isAbsent() {
        return isAbsent;
    }

    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public void setExcused(boolean isExcused) {
        this.isExcused = isExcused;
    }
    public void setAbsent(boolean isAbsent) {
        this.isAbsent = isAbsent;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Present: " + (isPresent ? "Yes" : "No") + ", Absent: " + (isAbsent ? "Yes" : "No") +", Excused: " + (isExcused ? "Yes" : "No");
    }
}


public class AttendanceManagementSystem {
    private ArrayList<Student> students;
    private Scanner scanner;

    public AttendanceManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid student ID.");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(id, name));
        System.out.println("Student added successfully!");
    }

    public void markAttendance() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("Students:");
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
        }

        System.out.print("Enter student ID or name to mark attendance:  ");

        String input = scanner.nextLine();
        Student selectedStudent = null;

        for (Student student : students) {
            if (String.valueOf(student.getId()).equals(input) || student.getName().equalsIgnoreCase(input)) {
                selectedStudent = student;
                break;
            }
        }

        if (selectedStudent == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Selected Student: " + selectedStudent.getName());
        System.out.println("Press 1 for Present, 2 for Absent, 3 for Excuse");

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter 1, 2, or 3.");
            scanner.next();
        }

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                selectedStudent.setPresent(true);
                selectedStudent.setAbsent(false);
                selectedStudent.setExcused(false);
                System.out.println("Marked Present");
                break;
            case 2:
                selectedStudent.setPresent(false);
                selectedStudent.setAbsent(true);
                selectedStudent.setExcused(false);
                System.out.println("Marked Absent");
                break;
            case 3:
                selectedStudent.setPresent(false);
                selectedStudent.setAbsent(false);
                selectedStudent.setExcused(true);
                System.out.println("Marked Excused");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public void displayAttendance() {
        System.out.println("Attendance Records:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        AttendanceManagementSystem system = new AttendanceManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Display Attendance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.addStudent();
                    break;
                case 2:
                    system.markAttendance();
                    break;
                case 3:
                    system.displayAttendance();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
