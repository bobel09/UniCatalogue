import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * The Student class represents a student with a name, password, year of study, faculty,
 * specialization, a list of courses, and a map of grades for each course.
 */
public class Student {
    private String name;                           // The name of the student
    private String password;                       // The password associated with the student's account
    private int yearOfStudy;                       // The year of study for the student
    private String faculty;                        // The faculty in which the student is enrolled
    private String specialization;                 // The specialization of the student
    private List<String> courses;                  // The list of courses the student is enrolled in
    private Map<String, List<Integer>> grades;    // The map of grades for each course

    /**
     * Default constructor for the Student class.
     * Initializes name, password, yearOfStudy, faculty, specialization,
     * courses, and grades to default values.
     */
    public Student() {
        this.name = "";
        this.password = "";
        this.yearOfStudy = 0;
        this.faculty = "";
        this.specialization = "";
        this.courses = null;
        this.grades = null;
    }

    /**
     * Constructor for the Student class with specified attributes.
     *
     * @param name           The name of the student.
     * @param password       The password associated with the student's account.
     * @param yearOfStudy    The year of study for the student.
     * @param faculty        The faculty in which the student is enrolled.
     * @param specialization The specialization of the student.
     * @param courses        The list of courses the student is enrolled in.
     * @param grades         The map of grades for each course.
     */
    public Student(String name, String password, int yearOfStudy, String faculty,
                   String specialization, List<String> courses, Map<String, List<Integer>> grades) {
        this.name = name;
        this.password = password;
        this.yearOfStudy = yearOfStudy;
        this.faculty = faculty;
        this.specialization = specialization;
        this.courses = courses;
        this.grades = grades;
    }

    /**
     * Get the name of the student.
     *
     * @return The name of the student.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the password associated with the student's account.
     *
     * @return The password of the student.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get the year of study for the student.
     *
     * @return The year of study of the student.
     */
    public int getYearOfStudy() {
        return this.yearOfStudy;
    }

    /**
     * Get the faculty in which the student is enrolled.
     *
     * @return The faculty of the student.
     */
    public String getFaculty() {
        return this.faculty;
    }

    /**
     * Get the specialization of the student.
     *
     * @return The specialization of the student.
     */
    public String getSpecialization() {
        return this.specialization;
    }

    /**
     * Get the list of courses the student is enrolled in.
     *
     * @return The list of courses of the student.
     */
    public List<String> getCourses() {
        return this.courses;
    }

    /**
     * Get the map of grades for each course.
     *
     * @return The map of grades for each course.
     */
    public Map<String, List<Integer>> getGrades() {
        return this.grades;
    }

    /**
     * Set the name of the student.
     *
     * @param name The new name of the student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the password associated with the student's account.
     *
     * @param password The new password of the student.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the year of study for the student.
     *
     * @param yearOfStudy The new year of study for the student.
     */
    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * Set the faculty in which the student is enrolled.
     *
     * @param faculty The new faculty of the student.
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Set the specialization of the student.
     *
     * @param specialization The new specialization of the student.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Set the list of courses the student is enrolled in.
     *
     * @param courses The new list of courses for the student.
     */
    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    /**
     * Set the map of grades for each course.
     *
     * @param grades The new map of grades for each course.
     */
    public void setGrades(Map<String, List<Integer>> grades) {
        this.grades = grades;
    }

    /**
     * Add a course to the list of courses the student is enrolled in.
     *
     * @param course The name of the course to be added.
     */
    public void addCourse(String course) {
        this.courses.add(course);
    }

    /**
     * Add a grade for a specific course to the map of grades.
     *
     * @param course The name of the course.
     * @param grade  The grade to be added.
     */
    public void addGrade(String course, int grade) {
        grades.computeIfAbsent(course, k -> new ArrayList<>()).add(grade);
    }

    /**
     * Print the list of courses the student is enrolled in.
     */
    public void printCourses() {
        for (int i = 0; i < this.courses.size(); i++) {
            System.out.println(this.courses.get(i));
        }
    }

    /**
     * Print the map of grades for each course.
     */
    public void printGrades() {
        for (Map.Entry<String, List<Integer>> entry : this.grades.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * Override the toString method to provide a string representation of the Student object.
     *
     * @return A string representation of the Student object.
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + this.name + '\'' +
                ", password='" + this.password + '\'' +
                ", yearOfStudy=" + this.yearOfStudy +
                ", faculty='" + this.faculty + '\'' +
                ", specialization='" + this.specialization + '\'' +
                ", courses=" + this.courses +
                ", grades=" + this.grades +
                '}';
    }

    /**
     * Print information about the student, including name, year of study, faculty, specialization,
     * enrolled courses, and grades.
     */
    public void printStudent() {
        System.out.println("// Name: " + this.name + "// Year of study: " + this.yearOfStudy + "// Faculty: " +
                this.faculty + "// Specialization: " + this.specialization + "//");
        System.out.println("Courses: ");
        this.printCourses();
        System.out.println("Grades: ");
        this.printGrades();
    }
}