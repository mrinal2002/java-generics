import java.util.*;
// Abstract base class for course types
abstract class CourseType {
    protected String evaluationMethod;

    public CourseType(String evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public abstract void displayEvaluation();
}

// Concrete course types
class ExamCourse extends CourseType {
    private int examDuration;

    public ExamCourse(int examDuration) {
        super("Exam-Based");
        this.examDuration = examDuration;
    }

    @Override
    public void displayEvaluation() {
        System.out.println("Evaluation: " + evaluationMethod + ", Duration: " + examDuration + " mins");
    }
}

class AssignmentCourse extends CourseType {
    private int numberOfAssignments;

    public AssignmentCourse(int numberOfAssignments) {
        super("Assignment-Based");
        this.numberOfAssignments = numberOfAssignments;
    }

    @Override
    public void displayEvaluation() {
        System.out.println("Evaluation: " + evaluationMethod + ", Assignments: " + numberOfAssignments);
    }
}

// Generic course class
class Course<T extends CourseType> {
    private String courseName;
    private String instructor;
    private T courseType;

    public Course(String courseName, String instructor, T courseType) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.courseType = courseType;
    }

    public void displayCourseDetails() {
        System.out.println("Course: " + courseName + ", Instructor: " + instructor);
        courseType.displayEvaluation();
    }
}

// Department class that can handle any course type
class Department {
    private List<? extends Course<? extends CourseType>> courses;

    public void setCourses(List<? extends Course<? extends CourseType>> courses) {
        this.courses = courses;
    }

    public void displayAllCourses() {
        for (Course<?> course : courses) {
            course.displayCourseDetails();
            System.out.println("-----");
        }
    }
}

// Usage example
public class UniversitySystem {
    public static void main(String[] args) {
        // Create courses
        Course<ExamCourse> mathCourse = new Course<>("Calculus", "Dr. Smith", new ExamCourse(120));
        Course<AssignmentCourse> csCourse = new Course<>("Algorithms", "Prof. Johnson", new AssignmentCourse(5));
        
        List<Course<? extends CourseType>> courses = new ArrayList<>();
        courses.add(mathCourse);
        courses.add(csCourse);
        
        Department csDepartment = new Department();
        csDepartment.setCourses(courses);
        csDepartment.displayAllCourses();
    }
}