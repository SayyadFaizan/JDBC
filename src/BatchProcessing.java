import java.sql.*;
import java.util.Scanner;

public class BatchProcessing {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String name = "root";
    private static final String password = "Faizan@2006";
    static  void create(){
        try{
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.print("Enter Name: ");
                String name = scanner.next();
                System.out.print("Enter Age: ");
                int age = scanner.nextInt();
                System.out.print("Enter Marks: ");
                double marks = scanner.nextDouble();
                System.out.print("Enter more data (Y/N): ");
                String choice = scanner.next();
                String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)", name, age, marks);
                statement.addBatch(query);
                if(choice.equalsIgnoreCase("N")){
                    break;
                }
            }
            int[] arr = statement.executeBatch();
            for (int i = 0; i < arr.length; i++){
                if(arr[i] == 0){
                    System.out.println("Query"+i+"not executed Sucessfully!!");
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    static  void create1(){
        try{
            Connection connection = DriverManager.getConnection(url, name, password);
            String query = "INSERT INTO students(name, age, marks) VALUES( ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.print("Enter Name: ");
                String name = scanner.next();
                System.out.print("Enter Age: ");
                int age = scanner.nextInt();
                System.out.print("Enter Marks: ");
                double marks = scanner.nextDouble();
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3,marks);
                System.out.print("Enter more data (Y/N): ");
                String choice = scanner.next();
                preparedStatement.addBatch();
                if(choice.equalsIgnoreCase("N")){
                    break;
                }
            }
            int[] arr = preparedStatement.executeBatch();
            for (int i = 0; i < arr.length; i++){
                if(arr[i] == 0){
                    System.out.println("Query"+i+"not executed Sucessfully!!");
                }
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
        //create1();
    }
}