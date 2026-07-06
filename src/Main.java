import java.util.Scanner;

// Student class - holds all data for one student (OOP part of the program)
class Student {
    long id;
    String name;
    int age;
    String course;
    double grade;
    boolean enrolled;

    public Student(long id, String name, int age, String course, double grade, boolean enrolled) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.grade = grade;
        this.enrolled = enrolled;
    }

    public String getStanding() {
        if (grade >= 90) {
            return "Dean's Lister";
        } else if (grade >= 75) {
            return "Passed";
        } else {
            return "Failed";
        }
    }
}

public class Main {
    static final int MAX_STUDENTS = 10;
    static Student[] studentList = new Student[MAX_STUDENTS];
    static int totalStudents = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println(" === STUDENT INFORMATION SYSTEM ===");
            System.out.println("[1] Add Student");
            System.out.println("[2] View All Students");
            System.out.println("[3] Search Student by ID");
            System.out.println("[4] View Statistics");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    viewStatistics();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }

    public static void addStudent() {
        if (totalStudents >= MAX_STUDENTS) {
            System.out.println("Student list is full.");
            return;
        }

        System.out.print("ID: ");
        long id = Long.parseLong(sc.nextLine());

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());
        if (age <= 0) {
            System.out.println("Invalid age. Not added.");
            return;
        }

        System.out.print("Course: ");
        String course = sc.nextLine();

        System.out.print("Grade (0-100): ");
        double grade = Double.parseDouble(sc.nextLine());
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade. Not added.");
            return;
        }

        System.out.print("Enrolled? (True/False): ");
        boolean enrolled = sc.nextLine().equalsIgnoreCase("true");

        studentList[totalStudents] = new Student(id, name, age, course, grade, enrolled);
        totalStudents++;
        System.out.println("Student added.");
    }

    public static void viewAllStudents() {
        if (totalStudents == 0) {
            System.out.println("No students yet.");
            return;
        }

        System.out.println("\n=== STUDENT RECORDS ===");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n",
                "ID", "Name", "Age", "Course", "Grade", "Enrolled", "Standing");

        for (Student s : studentList) {
            if (s == null) {
                continue;
            }
            String status = s.enrolled ? "Yes" : "No";
            System.out.printf("%-15d%-15s%-15d%-15s%-15.2f%-15s%-15s%n",
                    s.id, s.name, s.age, s.course, s.grade, status, s.getStanding());
        }
    }

    public static void searchStudentById() {
        System.out.print("Enter ID: ");
        long searchId = Long.parseLong(sc.nextLine());

        for (int i = 0; i < totalStudents; i++) {
            if (studentList[i].id == searchId) {
                Student s = studentList[i];
                System.out.println(s.name + ", Age " + s.age + ", " + s.course + ", Grade " + s.grade);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void viewStatistics() {
        if (totalStudents == 0) {
            System.out.println("No students yet.");
            return;
        }

        double total = 0;
        Student top = studentList[0];

        for (int i = 0; i < totalStudents; i++) {
            total += studentList[i].grade;
            if (studentList[i].grade > top.grade) {
                top = studentList[i];
            }
        }

        System.out.println("Total Students: " + totalStudents);
        System.out.println("Average Grade: " + (total / totalStudents));
        System.out.println("Top Student: " + top.name + " (" + top.grade + ")");
    }
}