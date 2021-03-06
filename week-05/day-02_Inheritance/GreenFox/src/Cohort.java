import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

public class Cohort {
private String name;
private List<Student> students;
private List<Mentor> mentors;

public Cohort(String name) {
    this.name = name;
    students = new ArrayList<>();
    mentors = new ArrayList<>();
}

public void addStudent(Student student) {
    students.add(student);
}

    public void addMentor(Mentor mentor) {
        mentors.add(mentor);
    }

    public void info() {
        System.out.println("The " + name + " cohort has " + students.size() + " students and " + mentors.size() + " mentors.");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }
}
