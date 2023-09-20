package enums;

public enum Queries {

    CREATE_STUDENT_TABLE("CREATE TABLE students(" +
            "                            id BIGINT PRIMARY KEY NOT NULL," +
            "                            name VARCHAR(30) NOT NULL," +
            "                            surname VARCHAR(30) NOT NULL," +
            "                            age INT NOT NULL)"),
    INSERT_STUDENT("INSERT INTO students(id,name,surname,age)" +
            "VALUES(?,?,?,?)"),
    FIND_BY_ID("SELECT * FROM students WHERE id = ?"),
    UPDATE_STUDENT_NAME("UPDATE students SET name = ? WHERE id = ?"),
    DELETE_STUDENT("DELETE FROM students WHERE id = ?"),
    GET_ALL_STUDENTS("SELECT * FROM students");

    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
