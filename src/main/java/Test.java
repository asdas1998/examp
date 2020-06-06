import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        //Test.conn("postgres","123");
        //Test.createTable();
        Student student = Test.selectOne(new Student(4));
        System.out.println(student);
    }

    private static Statement getStatement() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123"
        );
        return connection.createStatement();
    }

    public static boolean connect(String DB_URL, String USER, String PASS) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    USER,
                    PASS
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void insertRecord(Student student) throws SQLException {
        Statement statement = Test.getStatement();
        statement.executeUpdate("insert into student values (" + student.getId() + ",\'" + student.getName() + "\')");
    }

    public static void deleteRecord(Student student) throws SQLException {
        Statement statement = Test.getStatement();
        statement.executeUpdate("delete from student values (" + student.getId() + ",\'" + student.getName() + "\')");
    }

    List<Student> selectAll(Class<Student> studentClass) throws SQLException {
        List<Student> studentList = new ArrayList<Student>();
        Statement statement = Test.getStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from student");
        while (resultSet.next()) {
            int id = resultSet.getInt("st_id");
            String studentName = resultSet.getString("st_name");
            studentList.add(new Student(id, studentName));
        }
        return studentList;
    }

    public static void createTable() throws SQLException {
        Statement statement = Test.getStatement();
        ResultSet resultSet = statement.executeQuery("Create table student5(id int, name varchar (100))");
        System.out.println(resultSet.getInt("id"));
    }

    public static void updateRecord(Student student) throws SQLException {
        Statement statement = Test.getStatement();
        statement.executeUpdate("update student set st_id =" + student.getId() + ", st_name =\'" + student.getName() + "\' where st_id = " + student.getId());
    }

    public static Student selectOne(Student student) throws SQLException {
        Statement statement = Test.getStatement();
        ResultSet resultSet = statement.executeQuery("SELECT st_id, st_name from student where st_id =" + student.getId());
        while (resultSet.next()) {
            return  new Student(resultSet.getInt("st_id"), resultSet.getString("st_name"));
        }
         return null;
    }

    public static void conn(String usr, String pass) throws SQLException {

        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                usr,
                pass
        );
        Statement stmt = con.createStatement();
        //String sqlCom = "insert into weapon values ("+id+",\'"+ s1+"\',\'"+  s2+"\')";
        //String sqlCom = "insert into weapon values (6,'a','b')";
        //int rs = stmt.executeUpdate(sqlCom);


        //ResultSet rs = stmt.executeQuery("SELECT name from weapon");
        /*while (rs.next()){
            String s = rs.getString("name");
            //String x = rs.getString("caliber");
            System.out.println(s);
            //System.out.println(x);
        }*/
    }
}
