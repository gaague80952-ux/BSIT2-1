import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {

    // ---------- Part A: The Grade Scale (Array) ----------
    // Cutoffs and letters line up by position: index 0 is the A line, index 1 is B, etc.
    static double[] cutoffs = {90, 80, 70, 60};
    static char[] letters = {'A', 'B', 'C', 'D'};

    // Turns a numeric grade into a letter grade using the cutoffs above.
    public static char letterFor(double grade) {
        for (int i = 0; i < cutoffs.length; i++) {
            if (grade >= cutoffs[i]) {
                return letters[i];
            }
        }
        return 'F'; // below all cutoffs
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> roster = new ArrayList<>();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Grade Tracker Menu ---");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Class average");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.next();
                    System.out.print("Enter grade: ");
                    double grade = sc.nextDouble();
                    roster.add(new Student(name, grade));
                    System.out.println(name + " added.");
                    break;

                case 2:
                    if (roster.isEmpty()) {
                        System.out.println("No students yet.");
                    } else {
                        for (Student s : roster) {
                            System.out.printf("%s: %.1f (%c)%n", s.name, s.grade, letterFor(s.grade));
                        }
                    }
                    break;

                case 3:
                    if (roster.isEmpty()) {
                        // Guard against dividing by zero, which would print a confusing NaN.
                        System.out.println("No students yet, cannot compute average.");
                    } else {
                        double total = 0;
                        for (Student s : roster) {
                            total += s.grade;
                        }
                        double average = total / roster.size();
                        System.out.printf("Class average: %.2f%n", average);
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }

        sc.close();
    }
}

// ---------- Part B: Storing Students (ArrayList) ----------
class Student {
    String name;
    double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}