//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Faizan@2006";
        try(Connection connection = DriverManager.getConnection(url,username,password)){
            System.out.println("Connected to the Database");
        } catch(SQLException e) {
            System.err.println("Connection failed: "+e.getMessage());
        }
    }
}
