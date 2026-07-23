import java.util.ArrayList;

/**
 * Represents a student registered in the system.
 * Demonstrates encapsulation: all fields are private and accessed
 * only through getters or controlled behavior methods.
 */
public class Student {

    private final String studentId;
    private String name;
    private int yearLevel;
    private final ArrayList<String> enrolledCourseCodes;

    public Student(String studentId, String name, int yearLevel) {
        this.studentId = studentId;
        this.name = name;
        this.yearLevel = yearLevel;
        this.enrolledCourseCodes = new ArrayList<>();
    }

    // ----- Getters -----
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public ArrayList<String> getEnrolledCourseCodes() {
        return enrolledCourseCodes;
    }

    // ----- Behavior methods -----

    /** Adds a course code to this student's schedule if not already present. */
    public boolean enrollInCourse(String courseCode) {
        if (enrolledCourseCodes.contains(courseCode)) {
            return false;
        }
        enrolledCourseCodes.add(courseCode);
        return true;
    }

    /** Total number of units currently loaded is computed by EnrollmentSystem,
     *  but the count of enrolled courses is available here. */
    public int getLoadCount() {
        return enrolledCourseCodes.size();
    }

    @Override
    public String toString() {
        return String.format("[%s] %-20s | Year %d | %d course(s) enrolled",
                studentId, name, yearLevel, enrolledCourseCodes.size());
    }
}