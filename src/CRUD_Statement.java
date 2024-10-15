import java.sql.*;

public class CRUD_Statement {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String name = "root";
    private static final String password = "Faizan@2006";
    static  void create(){
        try{
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)","Rahul",23,74.5);
            int rowAffected = statement.executeUpdate(query);
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
            Statement statement = connection.createStatement();
            String query = "select * from students";
            ResultSet resultSet = statement.executeQuery(query);
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
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE students SET marks = %f WHERE id = %o",89.5,2);
            int rowAffected = statement.executeUpdate(query);
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
            Statement statement = connection.createStatement();
            String query = "DELETE FROM students WHERE id = 2";
            int rowAffected = statement.executeUpdate(query);
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