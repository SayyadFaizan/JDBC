import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BoilerPlate_JDBC {
    //    Create connection
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String name = "root";
    private static final String password = "Faizan@2006";

    public static void main(String[] args) {
//        Load necessary drivers
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){ //Use (Exception e) class for all type of exception
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

//        Connection with database
        try{
            Connection connection = DriverManager.getConnection(url, name, password);

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
