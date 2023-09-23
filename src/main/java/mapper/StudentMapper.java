package mapper;

import model.Student;
import model.StudentResponse;

public enum StudentMapper {

    STUDENT_MAPPER;

    public StudentResponse mapToStudentResponse(Student student) {

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getAge()
        );
    }
}
