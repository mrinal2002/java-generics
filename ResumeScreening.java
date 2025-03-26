import java.util.*;
// Abstract base class for job roles
abstract class JobRole {
    protected String roleName;
    protected List<String> requiredSkills;

    public JobRole(String roleName, List<String> requiredSkills) {
        this.roleName = roleName;
        this.requiredSkills = requiredSkills;
    }

    public abstract void displayRequirements();
}

// Concrete job roles
class SoftwareEngineer extends JobRole {
    public SoftwareEngineer() {
        super("Software Engineer", List.of("Java", "Python", "Algorithms"));
    }

    @Override
    public void displayRequirements() {
        System.out.println("Role: " + roleName);
        System.out.println("Required Skills: " + requiredSkills);
    }
}

class DataScientist extends JobRole {
    public DataScientist() {
        super("Data Scientist", List.of("Python", "Machine Learning", "Statistics"));
    }

    @Override
    public void displayRequirements() {
        System.out.println("Role: " + roleName);
        System.out.println("Required Skills: " + requiredSkills);
    }
}

// Generic resume class
class Resume<T extends JobRole> {
    private String candidateName;
    private List<String> skills;
    private T targetRole;

    public Resume(String candidateName, List<String> skills, T targetRole) {
        this.candidateName = candidateName;
        this.skills = skills;
        this.targetRole = targetRole;
    }

    public void evaluate() {
        System.out.println("Evaluating resume for: " + candidateName);
        targetRole.displayRequirements();
        
        long matchCount = skills.stream()
            .filter(skill -> targetRole.requiredSkills.contains(skill))
            .count();
        
        double matchPercentage = (double) matchCount / targetRole.requiredSkills.size() * 100;
        System.out.printf("Match: %.1f%%%n", matchPercentage);
    }
}

// Screening system with wildcard method
class ScreeningSystem {
    public static void screenResumes(List<? extends Resume<? extends JobRole>> resumes) {
        for (Resume<?> resume : resumes) {
            resume.evaluate();
            System.out.println("-----");
        }
    }
}

// Usage example
public class ResumeScreening {
    public static void main(String[] args) {
        List<Resume<? extends JobRole>> resumes = new ArrayList<>();
        
        resumes.add(new Resume<>("Alice", List.of("Java", "Python", "SQL"), new SoftwareEngineer()));
        resumes.add(new Resume<>("Bob", List.of("Python", "Statistics"), new DataScientist()));
        
        ScreeningSystem.screenResumes(resumes);
    }
}