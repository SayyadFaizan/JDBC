import java.sql.*;
import java.util.Scanner;

public class TransactionHandling {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String name = "root";
    private static final String password = "Faizan@2006";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url, name, password);
            connection.setAutoCommit(false);
            String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            String credit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement debitpreparedStatement = connection.prepareStatement(debit_query);
            PreparedStatement creditpreparedStatement = connection.prepareStatement(credit_query);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Account number for Debit : ");
            int account_number1 = scanner.nextInt();
            System.out.print("Enter Account number for Credit : ");
            int account_number2 = scanner.nextInt();
            System.out.print("Enter Amount: ");
            double amount = scanner.nextDouble();
            debitpreparedStatement.setDouble(1, amount);
            debitpreparedStatement.setInt(2, account_number1);
            creditpreparedStatement.setDouble(1, amount);
            creditpreparedStatement.setInt(2, account_number2);
            debitpreparedStatement.executeUpdate();
            creditpreparedStatement.executeUpdate();
            if(isSufficient(connection, account_number1, amount)){
                connection.commit();
                System.out.println("Transaction Succesfully!!");
            } else {
                connection.rollback();
                System.out.println("Transaction Failed!!");
            }
            debitpreparedStatement.close();
            creditpreparedStatement.close();
            scanner.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    static boolean isSufficient(Connection connection, int account_number, double amount){
        try {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, account_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double current_balance = resultSet.getDouble("balance");
                if(amount > current_balance){
                    return false;
                } else {
                    return true;
                }
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}