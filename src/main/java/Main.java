import java.util.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.FindIterable;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class Main extends Application {
    // Read data from the MongoDB database and store it in the lists
    private List<Student> students = new ArrayList<>();
    private List<Profesor> profesors = new ArrayList<>();
    private List<Administrator> administrators = new ArrayList<>();

    public static void readDataFromDatabase(List<Student> students, List<Profesor> profesors, List<Administrator> administrators) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("university");
            MongoCollection<Document> Students = database.getCollection("students");
            MongoCollection<Document> Profesors = database.getCollection("professors");
            MongoCollection<Document> Administrators = database.getCollection("administrators");
            FindIterable<Document> studentsIterable = Students.find();
            for (Document studentDocument : studentsIterable) {
                Student student = new Student();
                student.setName(studentDocument.getString("name"));
                student.setPassword(studentDocument.getString("password"));
                student.setYearOfStudy(studentDocument.getInteger("yearOfStudy"));
                student.setFaculty(studentDocument.getString("faculty"));
                student.setSpecialization(studentDocument.getString("specialization"));
                student.setCourses((List<String>) studentDocument.get("courses"));
                student.setGrades((Map<String, List<Integer>>) studentDocument.get("grades"));
                students.add(student);
            }
            FindIterable<Document> profesorsIterable = Profesors.find();
            for (Document profesorDocument : profesorsIterable) {
                Profesor profesor = new Profesor();
                profesor.setName(profesorDocument.getString("name"));
                profesor.setPassword(profesorDocument.getString("password"));
                profesor.setCourse(profesorDocument.getString("course"));
                profesors.add(profesor);
            }
            FindIterable<Document> administratorsIterable = Administrators.find();
            for (Document administratorDocument : administratorsIterable) {
                Administrator administrator = new Administrator();
                administrator.setName(administratorDocument.getString("name"));
                administrator.setPassword(administratorDocument.getString("password"));
                administrators.add(administrator);
            }
        } catch (Exception e) {
            System.err.println("Error reading data from MongoDB: " + e.getMessage());
        }
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Servicii e-uvt");

        // Create buttons for different user types
        Button studentButton = new Button("Student Login");
        Button professorButton = new Button("Professor Login");
        Button administratorButton = new Button("Administrator Login");

        // Set actions for each button
        studentButton.setOnAction(e -> {
            loginAs("Student");
        });

        professorButton.setOnAction(e -> {
            loginAs("Professor");
        });

        administratorButton.setOnAction(e -> {
            loginAs("Administrator");
        });

        String style = "-fx-background-color: lightgray";
        // Create a VBox layout
        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(100, 100, 100, 100));

// Style the buttons
        studentButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        professorButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        administratorButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");

// Add buttons to the VBox
        vBox.getChildren().addAll(studentButton, professorButton, administratorButton);

// Create a larger scene
        Scene scene = new Scene(vBox, 500, 500);

// Set the background color using CSS
        scene.getRoot().setStyle(style);

// Set the scene
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // Method to handle login based on user type
    private void loginAs(String userType) {
        Stage loginStage = new Stage();
        loginStage.setTitle(userType + " Login");

        Label label = new Label("Enter your credentials:");
        if (students.isEmpty() || profesors.isEmpty() || administrators.isEmpty())
            readDataFromDatabase(students, profesors, administrators);
        // TextFields for username and password
        TextField usernameField = new TextField();
        usernameField.setPromptText("Name");
        //make it so that the password is not visible
        TextField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean isLoggedIn = false;

            if (userType.equals("Student")) {
                for (Student student : students) {
                    if (student.getName().equals(username) && student.getPassword().equals(password)) {
                        System.out.println("Student logged in: " + student.getName());
                        isLoggedIn = true;
                        break;
                    }
                }
            } else if (userType.equals("Professor")) {
                for (Profesor profesor : profesors) {
                    if (profesor.getName().equals(username) && profesor.getPassword().equals(password)) {
                        System.out.println("Professor logged in: " + profesor.getName());
                        isLoggedIn = true;
                        break;
                    }
                }
            } else if (userType.equals("Administrator")) {
                for (Administrator administrator : administrators) {
                    if (administrator.getName().equals(username) && administrator.getPassword().equals(password)) {
                        System.out.println("Administrator logged in: " + administrator.getName());
                        isLoggedIn = true;
                        break;
                    }
                }
            }

            if (!isLoggedIn) {
                System.out.println("Invalid username or password!");
            } else {
                // Close the login stage
                loginStage.close();
                //Open the view options for the user
                if (userType.equals("Student")) {

                    String loggedInStudentName = usernameField.getText();
                    displayStudentOptions(loggedInStudentName);
                } else if (userType.equals("Professor")) {
                    String loggedInProfessorName = usernameField.getText();
                    displayProfessorOptions(loggedInProfessorName);
                } else if (userType.equals("Administrator")) {
                    String loggedInAdministratorName = usernameField.getText();
                    displayAdministratorOptions(loggedInAdministratorName);
                }
            }
        });

        String style = "-fx-background-color: lightgray";
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        loginButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        label.setStyle("-fx-font-size: 16pt;");
        usernameField.setStyle("-fx-font-size: 16pt;");
        passwordField.setStyle("-fx-font-size: 16pt;");
        vBox.getChildren().addAll(label, usernameField, passwordField, loginButton);

        Scene scene = new Scene(vBox, 400, 400);
        scene.getRoot().setStyle(style);
        loginStage.setScene(scene);
        loginStage.show();

    }


    // Method to display options for the user in JavaFx
    private void displayStudentOptions(String name) {
        Stage studentStage = new Stage();
        studentStage.setTitle("Student Options");

        Button viewGradesButton = new Button("View Grades");
        Button viewProfileButton = new Button("View Profile");

        // Create a text area to display the profile information
        TextArea profileTextArea = new TextArea();
        profileTextArea.setEditable(false); // Set to read-only

        viewGradesButton.setOnAction(e -> {
            for (Student student : students) {
                if (student.getName().equals(name)) {
                    // Clear the text area before displaying new grades
                    profileTextArea.clear();
                    // Get the grades for the current student and display them in the text area
                    Map<String, List<Integer>> grades = student.getGrades();
                    for (Map.Entry<String, List<Integer>> entry : grades.entrySet()) {
                        String course = entry.getKey();
                        List<Integer> courseGrades = entry.getValue();
                        profileTextArea.appendText(course + ": " + courseGrades.toString() + "\n");
                    }
                }
            }
        });

        viewProfileButton.setOnAction(e -> {
            for (Student student : students) {
                if (student.getName().equals(name)) {
                    // Clear the text area before displaying new profile information
                    profileTextArea.clear();
                    // Display the profile information in the text area
                    profileTextArea.appendText("Name: " + student.getName() + "\n");
                    profileTextArea.appendText("Year of Study: " + student.getYearOfStudy() + "\n");
                    profileTextArea.appendText("Faculty: " + student.getFaculty() + "\n");
                    profileTextArea.appendText("Specialization: " + student.getSpecialization() + "\n");
                    profileTextArea.appendText("Courses: " + student.getCourses().toString() + "\n");
                }
            }
        });
        String style = "-fx-background-color: lightgray";
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        viewGradesButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        viewProfileButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        profileTextArea.setStyle("-fx-font-size: 16pt;");

        vBox.getChildren().addAll(viewGradesButton, viewProfileButton, profileTextArea);


        Scene scene = new Scene(vBox, 400, 600);
        scene.getRoot().setStyle(style);
        studentStage.setScene(scene);
        studentStage.show();

    }

    private void displayProfessorOptions(String name) {
        Stage professorStage = new Stage();
        professorStage.setTitle("Professor Options");

        Button viewStudentsGradesButton = new Button("View Students Grades");
        Button addGradeButton = new Button("Add Grade");

        // Create a text area to display the students and their grades
        TextArea studentsTextArea = new TextArea();
        studentsTextArea.setEditable(false);

        viewStudentsGradesButton.setOnAction(e -> {
            for (Profesor profesor : profesors) {
                if (profesor.getName().equals(name)) {
                    // Clear the text area before displaying new student grades
                    studentsTextArea.clear();
                    // Get the students for the current professor's course and display their grades in the text area
                    for (Student student : students) {
                        if (student.getCourses().contains(profesor.getCourse())) {
                            studentsTextArea.appendText(student.getName() + ": " + student.getGrades().get(profesor.getCourse()) + "\n");
                        }
                    }
                }
            }
        });

        addGradeButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Grade");
            dialog.setHeaderText("Enter student name and grade");
            dialog.setContentText("Student name: ");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(studentName -> {
                for (Student student : students) {
                    if (student.getName().equals(studentName)) {
                        TextInputDialog gradeDialog = new TextInputDialog();
                        gradeDialog.setTitle("Add Grade");
                        gradeDialog.setHeaderText("Enter grade for " + studentName);
                        gradeDialog.setContentText("Grade: ");

                        Optional<String> gradeResult = gradeDialog.showAndWait();
                        gradeResult.ifPresent(gradeStr -> {
                            try {
                                int grade = Integer.parseInt(gradeStr);
                                // Update the student's grade for the professor's course
                                Profesor profesorLogin = null;
                                for (Profesor profesor : profesors) {
                                    if (profesor.getName().equals(name)) {
                                        ;
                                        profesorLogin = new Profesor();
                                        profesorLogin.setName(profesor.getName());
                                        profesorLogin.setPassword(profesor.getPassword());
                                        profesorLogin.setCourse(profesor.getCourse());
                                    }
                                }
                                student.getGrades().get(profesorLogin.getCourse()).add(grade);
                                // Write the updated student data to the database
                                try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                                    MongoDatabase database = mongoClient.getDatabase("university");
                                    MongoCollection<Document> studentsCollection = database.getCollection("students");
                                    studentsCollection.updateOne(Filters.eq("name", student.getName()), new Document("$set", new Document("grades", student.getGrades())));
                                } catch (Exception ex) {
                                    System.err.println("Error writing data to MongoDB: " + ex.getMessage());
                                }
                                // Show confirmation message
                                System.out.println("Grade added for " + studentName);
                            } catch (NumberFormatException ex) {
                                // Handle invalid grade input
                                System.err.println("Invalid grade format. Please enter a valid integer.");
                            }
                        });
                    }
                }
            });
        });

        String style = "-fx-background-color: lightgray";
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        viewStudentsGradesButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        addGradeButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        studentsTextArea.setStyle("-fx-font-size: 16pt;");

        vBox.getChildren().addAll(viewStudentsGradesButton, addGradeButton, studentsTextArea);


        Scene scene = new Scene(vBox, 400, 600);
        scene.getRoot().setStyle(style);
        professorStage.setScene(scene);
        professorStage.show();
    }

    private void displayAdministratorOptions(String name) {
        Stage AdministratorStage = new Stage();
        AdministratorStage.setTitle("Administrator Options");

        Button addStudentButton = new Button("Add Student");
        Button addProfessorButton = new Button("Add Professor");
        Button deleteStudentButton = new Button("Delete Student");
        Button deleteProfessorButton = new Button("Delete Professor");

        addStudentButton.setOnAction(e -> {
            System.out.println("Add student: ");
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Student");
            dialog.setHeaderText("Enter student details");
            dialog.setContentText("Name: ");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(studentName -> {
                TextInputDialog passwordDialog = new TextInputDialog();
                passwordDialog.setTitle("Add Student");
                passwordDialog.setHeaderText("Enter student password");
                passwordDialog.setContentText("Password: ");
                Optional<String> passwordResult = passwordDialog.showAndWait();
                passwordResult.ifPresent(password -> {
                    TextInputDialog yearOfStudyDialog = new TextInputDialog();
                    yearOfStudyDialog.setTitle("Add Student");
                    yearOfStudyDialog.setHeaderText("Enter student year of study");
                    yearOfStudyDialog.setContentText("Year of Study: ");
                    Optional<String> yearOfStudyResult = yearOfStudyDialog.showAndWait();
                    yearOfStudyResult.ifPresent(yearOfStudyStr -> {
                        try {
                            int yearOfStudy = Integer.parseInt(yearOfStudyStr);
                            TextInputDialog facultyDialog = new TextInputDialog();
                            facultyDialog.setTitle("Add Student");
                            facultyDialog.setHeaderText("Enter student faculty");
                            facultyDialog.setContentText("Faculty: ");
                            Optional<String> facultyResult = facultyDialog.showAndWait();
                            facultyResult.ifPresent(faculty -> {
                                TextInputDialog specializationDialog = new TextInputDialog();
                                specializationDialog.setTitle("Add Student");
                                specializationDialog.setHeaderText("Enter student specialization");
                                specializationDialog.setContentText("Specialization: ");
                                Optional<String> specializationResult = specializationDialog.showAndWait();
                                specializationResult.ifPresent(specialization -> {
                                    TextInputDialog coursesDialog = new TextInputDialog();
                                    coursesDialog.setTitle("Add Student");
                                    coursesDialog.setHeaderText("Enter student courses");
                                    coursesDialog.setContentText("Courses (comma-separated): ");
                                    Optional<String> coursesResult = coursesDialog.showAndWait();
                                    coursesResult.ifPresent(coursesStr -> {
                                        List<String> courses = Arrays.asList(coursesStr.split(","));
                                        TextInputDialog gradesDialog = new TextInputDialog();
                                        gradesDialog.setTitle("Add Student");
                                        gradesDialog.setHeaderText("Enter student grades");
                                        gradesDialog.setContentText("Grades (course:grade, comma-separated): ");
                                        Optional<String> gradesResult = gradesDialog.showAndWait();
                                        gradesResult.ifPresent(gradesStr -> {
                                            Map<String, List<Integer>> grades = new HashMap<>();
                                            String[] gradePairs = gradesStr.split(",");
                                            for (String gradePair : gradePairs) {
                                                String[] pair = gradePair.split(":");
                                                String course = pair[0];
                                                int grade = Integer.parseInt(pair[1]);
                                                grades.computeIfAbsent(course, k -> new ArrayList<>()).add(grade);
                                            }
                                            Student student = new Student(studentName, password, yearOfStudy, faculty, specialization, courses, grades);
                                            students.add(student);
                                            // Write the new student data to the database
                                            try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                                                MongoDatabase database = mongoClient.getDatabase("university");
                                                MongoCollection<Document> studentsCollection = database.getCollection("students");

                                                Document studentDocument = new Document("name", student.getName())
                                                        .append("password", student.getPassword())
                                                        .append("yearOfStudy", student.getYearOfStudy())
                                                        .append("faculty", student.getFaculty())
                                                        .append("specialization", student.getSpecialization())
                                                        .append("courses", student.getCourses())
                                                        .append("grades", student.getGrades());
                                                studentsCollection.insertOne(studentDocument);
                                            } catch (Exception ex) {
                                                System.err.println("Error writing data to MongoDB: " + ex.getMessage());
                                            }
                                            // Show confirmation message
                                            System.out.println("Student added: " + studentName);
                                        });
                                    });
                                });
                            });
                        } catch (NumberFormatException ex) {
                            // Handle invalid year of study input
                            System.err.println("Invalid year of study format. Please enter a valid integer.");

                        }
                    });
                });
            });
        });


        addProfessorButton.setOnAction(e -> {
            System.out.println("Add professor: ");
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Professor");
            dialog.setHeaderText("Enter professor details");
            dialog.setContentText("Name: ");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(professorName -> {
                TextInputDialog passwordDialog = new TextInputDialog();
                passwordDialog.setTitle("Add Professor");
                passwordDialog.setHeaderText("Enter professor password");
                passwordDialog.setContentText("Password: ");
                Optional<String> passwordResult = passwordDialog.showAndWait();
                passwordResult.ifPresent(password -> {
                    TextInputDialog courseDialog = new TextInputDialog();
                    courseDialog.setTitle("Add Professor");
                    courseDialog.setHeaderText("Enter professor course");
                    courseDialog.setContentText("Course: ");
                    Optional<String> courseResult = courseDialog.showAndWait();
                    courseResult.ifPresent(course -> {
                        Profesor profesor = new Profesor(professorName, password, course);
                        profesors.add(profesor);
                        // Write the new professor data to the database
                        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                            MongoDatabase database = mongoClient.getDatabase("university");
                            MongoCollection<Document> professorsCollection = database.getCollection("professors");

                            Document professorDocument = new Document("name", profesor.getName())
                                    .append("password", profesor.getPassword())
                                    .append("course", profesor.getCourse());
                            professorsCollection.insertOne(professorDocument);
                        } catch (Exception ex) {
                            System.err.println("Error writing data to MongoDB: " + ex.getMessage());
                        }
                        // Show confirmation message
                        System.out.println("Professor added: " + professorName);
                    });
                });
            });
        });

        deleteStudentButton.setOnAction(e -> {
            System.out.println("Delete student: ");
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Student");
            dialog.setHeaderText("Enter student name");
            dialog.setContentText("Name: ");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(studentName -> {
                Iterator<Student> iterator = students.iterator();
                while (iterator.hasNext()) {
                    Student student = iterator.next();
                    if (student.getName().equals(studentName)) {
                        iterator.remove();
                        // Delete the student data from the database
                        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                            MongoDatabase database = mongoClient.getDatabase("university");
                            MongoCollection<Document> studentsCollection = database.getCollection("students");
                            studentsCollection.deleteOne(Filters.eq("name", student.getName()));
                        } catch (Exception ex) {
                            System.err.println("Error writing data to MongoDB: " + ex.getMessage());
                        }
                        // Show confirmation message
                        System.out.println("Student deleted: " + studentName);
                    }
                }
            });
        });
        deleteProfessorButton.setOnAction(e -> {
            System.out.println("Delete professor: ");
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Professor");
            dialog.setHeaderText("Enter professor name");
            dialog.setContentText("Name: ");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(professorName -> {
                Iterator<Profesor> iterator = profesors.iterator();
                while (iterator.hasNext()) {
                    Profesor profesor = iterator.next();
                    if (profesor.getName().equals(professorName)) {
                        iterator.remove();
                        // Delete the professor data from the database
                        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                            MongoDatabase database = mongoClient.getDatabase("university");
                            MongoCollection<Document> professorsCollection = database.getCollection("professors");
                            professorsCollection.deleteOne(Filters.eq("name", profesor.getName()));
                        } catch (Exception ex) {
                            System.err.println("Error writing data to MongoDB: " + ex.getMessage());
                        }
                        // Show confirmation message
                        System.out.println("Professor deleted: " + professorName);
                    }
                }
            });
        });

        String style = "-fx-background-color: lightgray";
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        addStudentButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        addProfessorButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        deleteStudentButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        deleteProfessorButton.setStyle("-fx-font-size: 16pt; -fx-min-width: 200px;");
        vBox.getChildren().addAll(addStudentButton, addProfessorButton, deleteStudentButton, deleteProfessorButton);

        Scene scene = new Scene(vBox, 400, 600);
        scene.getRoot().setStyle(style);
        AdministratorStage.setScene(scene);
        AdministratorStage.show();


    }


    public static void main(String[] args) {
        // Start the JavaFX application
        launch(args);


    }
}