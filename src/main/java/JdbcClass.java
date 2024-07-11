import java.sql.*;
public class JdbcClass {
    public static void main(String[] args) {
        /**
         * 7 Steps required for JDBC connection
         * Step 1-: import the required package -(JDBC package)
         * Step 2-: load  the Driver
         * Step 3-: register the driver
         * Step 4-: create statement
         * Step 5-: Execute Statement
         * Step 6-: Process the result
         * Step 7-: Close the connection
         */

        String url="jdbc:postgresql://localhost:5432/Telusko-Learning";
        String username="postgres";
        String password="Upanshu1#";
        String query="SELECT * from Student";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection= DriverManager.getConnection(url, username, password);
            System.out.println("Connection: Establish: ");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println("Student Id-  "+resultSet.getString("student_id") + "  Student Name-  "+resultSet.getString("student_name")+"  Student Mark- "+resultSet.getString("student_marks") );
            }
            System.out.println("closing connection");
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
