/**
 * The Profesor class represents a professor with a name, password, and the course they are assigned to teach.
 */
public class Profesor {
    private String name;        // The name of the professor
    private String password;    // The password associated with the professor's account
    private String course;      // The course the professor is assigned to teach

    /**
     * Default constructor for the Profesor class.
     * Initializes name, password, and course to default values.
     */
    public Profesor() {
        this.name = "";
        this.password = "";
        this.course = "";
    }

    /**
     * Constructor for the Profesor class with specified attributes.
     *
     * @param name     The name of the professor.
     * @param password The password associated with the professor's account.
     * @param course   The course the professor is assigned to teach.
     */
    public Profesor(String name, String password, String course) {
        this.name = name;
        this.password = password;
        this.course = course;
    }

    /**
     * Get the name of the professor.
     *
     * @return The name of the professor.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the password associated with the professor's account.
     *
     * @return The password of the professor.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get the course the professor is assigned to teach.
     *
     * @return The course of the professor.
     */
    public String getCourse() {
        return this.course;
    }

    /**
     * Set the name of the professor.
     *
     * @param name The new name of the professor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the password associated with the professor's account.
     *
     * @param password The new password of the professor.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the course the professor is assigned to teach.
     *
     * @param course The new course of the professor.
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Override the toString method to provide a string representation of the Profesor object.
     *
     * @return A string representation of the Profesor object.
     */
    @Override
    public String toString() {
        return "Profesor{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", course='" + course + '\'' +
                '}';
    }

    /**
     * Print information about the professor, including name and the course they are assigned to teach.
     */
    public void printProfesor() {
        System.out.println("// Profesor: " + this.name + "// Course: " + this.course + "//");
    }
}
