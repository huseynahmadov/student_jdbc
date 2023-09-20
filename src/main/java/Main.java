import service.StudentService;

public class Main {

    public static void main(String[] args) {
        var studentService = new StudentService();
//        StudentService.createStudentTable();
//        StudentService.createStudent(new Student(
//                2L,
//                "Nizami",
//                "Jabrayilov",
//                19));

//        studentService.updateStudentName(2L, "Xeyyam");
//        System.out.println(studentService.getStudentById(2L));
//        studentService.deleteStudent(1L);
//        System.out.println(studentService.getStudentById(1L));
        System.out.println(studentService.getStudents());
        // TODO: delete and get all students, search
    }

}
