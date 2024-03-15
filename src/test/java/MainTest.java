import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class MainTest {
    @Test
    public void testStudent() {
        Student student = new Student("Bobel", "bobel09", 2, "FMI", "CTI", null, null);
        Assertions.assertEquals("Bobel", student.getName());
        Assertions.assertEquals("bobel09", student.getPassword());
        Assertions.assertEquals(2, student.getYearOfStudy());
        Assertions.assertEquals("FMI", student.getFaculty());
        Assertions.assertEquals("CTI", student.getSpecialization());
        Assertions.assertNull(student.getCourses());
        Assertions.assertNull(student.getGrades());
        student.setName("Bobel09");
        student.setPassword("bobel");
        student.setYearOfStudy(3);
        student.setFaculty("FMI");
        student.setSpecialization("CTI");
        Assertions.assertEquals("Bobel09", student.getName());
        Assertions.assertEquals("bobel", student.getPassword());
        Assertions.assertEquals(3, student.getYearOfStudy());
        Assertions.assertEquals("FMI", student.getFaculty());
        Assertions.assertEquals("CTI", student.getSpecialization());
    }
    @Test
    public void testProfesor() {
        Profesor profesor = new Profesor("Bobel", "bobel09", "CTI");
        Assertions.assertEquals("Bobel", profesor.getName());
        Assertions.assertEquals("bobel09", profesor.getPassword());
        Assertions.assertEquals("CTI", profesor.getCourse());
        profesor.setName("Bobel09");
        profesor.setPassword("bobel");
        profesor.setCourse("CTI");
        Assertions.assertEquals("Bobel09", profesor.getName());
        Assertions.assertEquals("bobel", profesor.getPassword());
        Assertions.assertEquals("CTI", profesor.getCourse());
    }

    @Test
    public void testAdministrator() {
        Administrator administrator = new Administrator("Bobel", "bobel09");
        Assertions.assertEquals("Bobel", administrator.getName());
        Assertions.assertEquals("bobel09", administrator.getPassword());
        administrator.setName("Bobel09");
        administrator.setPassword("bobel");
        Assertions.assertEquals("Bobel09", administrator.getName());
        Assertions.assertEquals("bobel", administrator.getPassword());
    }

}