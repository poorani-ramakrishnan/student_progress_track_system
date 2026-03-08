# student_progress_track_system
Student Progress Tracking System is a web-based application used to monitor and manage students’ academic performance.
It helps teachers record and update student details, attendance, marks, and overall progress.
The system allows easy tracking and analysis of student performance through organized records and reports.

# Class creation 
## user.java
public class User {

    protected int userId;
    protected String name;
    protected String role;

    public User(int userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public void displayUser() {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Role: " + role);
    }
}

## Student.java
public class Student extends User {

    private int marks;

    public Student(int userId, String name, int marks) {
        super(userId, name, "Student");
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    public void updateMarks(int marks) {
        this.marks = marks;
    }

    public void displayStudent() {
        System.out.println("Student ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
    }
}
## Course.java
public class Course {

    private int courseId;
    private String courseName;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public void displayCourse() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
    }
}
## Progress.java
public class Progress {

    private Student student;
    private Course course;
    private int marks;

    public Progress(Student student, Course course, int marks) {
        this.student = student;
        this.course = course;
        this.marks = marks;
    }

    public void showProgress() {
        System.out.println("Student: " + student.name);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
    }
}

## Report.java
import java.util.List;

public class Report {

    public void generateReport(List<Student> students) {

        System.out.println("---- Student Progress Report ----");

        for (Student s : students) {
            s.displayStudent();
            System.out.println("-----------------------------");
        }
    }
}

## Main.java
import java.util.*;

public class Main {

    static List<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Marks");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateMarks();
                    break;

                case 4:
                    Report r = new Report();
                    r.generateReport(students);
                    break;

                case 5:
                    System.exit(0);
            }
        }
    }

    static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        students.add(new Student(id, name, marks));

        System.out.println("Student Added Successfully!");
    }

    static void viewStudents() {

        for (Student s : students) {
            s.displayStudent();
            System.out.println("----------------");
        }
    }

    static void updateMarks() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (Student s : students) {

            if (s.userId == id) {

                System.out.print("Enter New Marks: ");
                int m = sc.nextInt();

                s.updateMarks(m);

                System.out.println("Marks Updated!");
                return;
            }
        }

        System.out.println("Student Not Found!");
    }
}
