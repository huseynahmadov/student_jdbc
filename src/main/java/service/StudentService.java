package service;

import enums.Queries;
import exception.StudentNotFoundException;
import model.Student;
import model.StudentResponse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static connection.DBConnection.closeConnection;
import static connection.DBConnection.getConnection;
import static enums.Queries.*;
import static mapper.StudentMapper.STUDENT_MAPPER;

public class StudentService {

    private static PreparedStatement preparedStatement;

    public void createStudentTable() {
        var query = CREATE_STUDENT_TABLE.getQuery();
        var connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();
            System.out.println("Student table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            closeConnection(connection);
            System.out.println("Database connection closed");
        }
    }

    public void createStudent(Student student) {
        String query = INSERT_STUDENT.getQuery();
        var connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSurname());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.execute();
            System.out.println("Created student successfully : " + student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
            System.out.println("Database connection closed");
        }
    }

    public StudentResponse getStudentById(Long id) {
        var query = FIND_BY_ID.getQuery();
        var connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var student = fillStudent(resultSet);
                return STUDENT_MAPPER.mapToStudentResponse(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
            System.out.println("Database connection closed");
        }
        throw new StudentNotFoundException("Student not found with id " + id);
    }

    public void updateStudentName(Long id, String name) {
        String query = Queries.UPDATE_STUDENT_NAME.getQuery();
        var connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            var result = preparedStatement.executeUpdate();
            System.out.println("Student name updated successfully. Row count = " + result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
            System.out.println("Database connection closed");
        }
    }

    public void deleteStudent(Long id) {
        var query = DELETE_STUDENT.getQuery();
        var connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public List<Student> getStudents() {
        String query = Queries.GET_ALL_STUDENTS.getQuery();
        List<Student> studentList = new ArrayList<>();
        var connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var student = fillStudent(resultSet);
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Student fillStudent(ResultSet resultSet) throws SQLException {
        var studentId = resultSet.getLong(1);
        var studentName = resultSet.getString(2);
        var studentSurname = resultSet.getString(3);
        var studentAge = resultSet.getInt(4);
        return new Student(studentId, studentName, studentSurname, studentAge);
    }
}
