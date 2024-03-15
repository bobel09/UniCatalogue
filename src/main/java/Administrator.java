/**
 * The Administrator class represents an administrator with a name and password.
 */
public class Administrator {
    private String name;      // The name of the administrator
    private String password;  // The password associated with the administrator's account

    /**
     * Default constructor for Administrator class.
     * Initializes name and password to empty strings.
     */
    public Administrator() {
        this.name = "";
        this.password = "";
    }

    /**
     * Constructor for Administrator class with specified name and password.
     *
     * @param name     The name of the administrator.
     * @param password The password associated with the administrator's account.
     */
    public Administrator(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Get the name of the administrator.
     *
     * @return The name of the administrator.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the password associated with the administrator's account.
     *
     * @return The password of the administrator.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set the name of the administrator.
     *
     * @param name The new name for the administrator.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the password associated with the administrator's account.
     *
     * @param password The new password for the administrator.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Override toString() method to provide a string representation of the Administrator object.
     *
     * @return A string representation of the Administrator object.
     */
    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * Print information about the Administrator.
     * Used for displaying administrator details.
     */
    public void printAdministrator() {
        System.out.println("// Administrator: " + this.name + "//");
    }
}