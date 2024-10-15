import java.sql.*;

public class CRUD_PrepaidStatement {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String name = "root";
    private static final String password = "Faizan@2006";
    static  void create(){
        try{
            Connection connection = DriverManager.getConnection(url, name, password);
            String query = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Ankita");
            preparedStatement.setInt(2, 25);
            preparedStatement.setDouble(3, 84.7);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Data Inserted Succesfully!");
            } else {
                System.out.println("Data not Inserted");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    static void read(){
        try{
            Connection connection = DriverManager.getConnection(url, name, password);
            String query = "select * from students";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double marks = resultSet.getDouble("marks");
                System.out.println("ID: "+id);
                System.out.println("NAME: "+name);
                System.out.println("AGE: "+age);
                System.out.println("MARKS: "+marks);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    static  void update(){
        try{
            Connection connection = DriverManager.getConnection(url, name, password);
            String query = "UPDATE students SET marks = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, 87.5);
            preparedStatement.setInt(2, 3);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Data Updated Succesfully!");
            } else {
                System.out.println("Data not Updated");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    static  void delete(){
        try{
            Connection connection = DriverManager.getConnection(url, name, password);
            String query = "DELETE FROM students WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 10);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Data Deleted Succesfully!");
            } else {
                System.out.println("Data not Deleted");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        //create();
        //read();
        //update();
        //delete();
    }
}