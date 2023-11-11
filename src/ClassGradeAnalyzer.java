import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassGradeAnalyzer {
    public static void main(String[] args) {
        String filePath = "путь_к_вашему_текстовому_файлу.txt";

        List<Student> students = new ArrayList<>();
        double totalGrade = 0;
        int studentCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String lastName = parts[0];
                    String firstName = parts[1];
                    int grade = Integer.parseInt(parts[2]);
                    students.add(new Student(lastName, firstName, grade));

                    totalGrade += grade;
                    studentCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Учащиеся с оценкой менее 3 баллов:");
        for (Student student : students) {
            if (student.getGrade() < 3) {
                System.out.println(student.getFullName());
            }
        }

        double averageGrade = (studentCount > 0) ? totalGrade / studentCount : 0;
        System.out.println("Средний балл по классу: " + averageGrade);
    }
}

class Student {
    private String lastName;
    private String firstName;
    private int grade;

    public Student(String lastName, String firstName, int grade) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.grade = grade;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public int getGrade() {
        return grade;
    }
}