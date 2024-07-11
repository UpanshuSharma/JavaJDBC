import java.sql.*;
import java.util.Scanner;

public class JdbcClass {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

//        usingStatement(url, username, password);
        usingPreparedStatement(url, username, password);
    }

    private static void usingStatement(String url, String username, String password) {
        String fetchquery="SELECT * from Student";
        String insertquery="insert into student (student_name, student_marks) values('vinay goel', 95) , ('shubham sharma', 90)";
        String updatequery="delete from student where student_id=6";
        try {
            Class.forName("org.postgresql.Driver"); // this is optional now
            Connection connection= DriverManager.getConnection(url, username, password);
            System.out.println("Connection: Establish: ");
            Statement statement=connection.createStatement();
            //  Fettching data
            ResultSet resultSet=statement.executeQuery(fetchquery);
            while (resultSet.next()){
                System.out.println("Student Id-  "+resultSet.getString("student_id") + "  Student Name-  "+resultSet.getString("student_name")+"  Student Mark- "+resultSet.getString("student_marks") );
            }

            // insert the data
            /*
                   boolean isInsert=statement.execute(insertquery);
                    if(isInsert) System.out.println("Data inserted successfull");
                    else System.out.println("Data not inserted");
            */

              //update data
            /**
                     boolean isUpdated=statement.execute(updatequery);
                    if(isUpdated) System.out.println("Data Updated successfull");
                    else System.out.println("Data not updated");
             */
            System.out.println("closing connection");
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void usingPreparedStatement(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection= DriverManager.getConnection(url, username, password);

        /**
         * Inserting the data using prepared statement
         */
        String query="insert into student (student_name, student_marks) values (?,?)";
        String name="Lakhan Sharma";
        int marks=93;
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,marks);
        preparedStatement.executeUpdate();

        connection.close();
    }
}
