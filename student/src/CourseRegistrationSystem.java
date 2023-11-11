import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    int studentID;
    String name;
    List<String> registeredCourses;

    Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.courseCode);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Schedule: " + course.schedule);
            System.out.println("Available Slots: " + (course.capacity - getRegisteredStudentsCount(course)));
            System.out.println();
        }
    }

    public void registerStudent(Student student, Course course) {
        if (getRegisteredStudentsCount(course) < course.capacity) {
            student.registeredCourses.add(course.courseCode);
            System.out.println(student.name + " has successfully registered for " + course.title);
        } else {
            System.out.println("Sorry, the course is already full. Cannot register " + student.name + " for " + course.title);
        }
    }

    public void dropCourse(Student student, Course course) {
        if (student.registeredCourses.contains(course.courseCode)) {
            student.registeredCourses.remove(course.courseCode);
            System.out.println(student.name + " has successfully dropped " + course.title);
        } else {
            System.out.println(student.name + " is not registered for " + course.title);
        }
    }

    private int getRegisteredStudentsCount(Course course) {
        int count = 0;
        for (Student student : students) {
            if (student.registeredCourses.contains(course.courseCode)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        // Example Courses
        Course c1 = new Course("CSC101", "Introduction to Computer Science", "Fundamentals of programming", 30, "Mon/Wed/Fri 10:00 AM");
        Course c2 = new Course("MAT201", "Calculus I", "Limits, derivatives, and integrals", 25, "Tue/Thu 2:00 PM");

        registrationSystem.addCourse(c1);
        registrationSystem.addCourse(c2);

        // Example Students
        Student s1 = new Student(1, "John Doe");
        Student s2 = new Student(2, "Jane Smith");

        registrationSystem.students.add(s1);
        registrationSystem.students.add(s2);

        // Display Available Courses
        registrationSystem.displayCourses();

        // Student Registration
        registrationSystem.registerStudent(s1, c1);
        registrationSystem.registerStudent(s2, c1);
        registrationSystem.registerStudent(s1, c2);

        // Display Available Courses after Registration
        registrationSystem.displayCourses();

        // Student Drops Course
        registrationSystem.dropCourse(s1, c1);

        // Display Available Courses after Course Drop
        registrationSystem.displayCourses();
    }
}
