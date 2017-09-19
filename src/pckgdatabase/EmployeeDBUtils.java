package pckgdatabase;

import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pckgmodel.Employee;
import pckgmodel.Student;

public class EmployeeDBUtils {

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection = DatabaseConnector.databaseConnector();
    ObservableList<Employee> list = FXCollections.observableArrayList();

    public boolean createEmployee(Employee employee)  {
        if (connection != null) {
            String query = "INSERT INTO employee (Ename, Eaddress, Faculty, Salary, Joinedyear, Username, Password) VALUES (?,?,?,?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query);
                
            preparedStatement.setString(1, employee.getEname());
            preparedStatement.setString(2, employee.getEaddress());
            preparedStatement.setString(3, employee.getFaculty());
            preparedStatement.setInt(4, employee.getSalary());
            preparedStatement.setString(5, employee.getJoinedyear());
            preparedStatement.setString(6, employee.getUsername());
            preparedStatement.setString(7, employee.getPassword());
            
            preparedStatement.execute();
            preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }

            return true;
        }

        return false;

    }

    public ObservableList<Employee> fetchEmpdata() {
        if (connection != null) {
            String query = "SELECT * FROM employee";
            try {
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Integer Eid = resultSet.getInt("Eid");
                    String Ename = resultSet.getString("Ename");
                    String Eaddress = resultSet.getString("Eaddress");
                    String Faculty = resultSet.getString("Faculty");
                    Integer Salary = resultSet.getInt("Salary");
                    String Joinedyear = resultSet.getString("Joinedyear");
                    String Username = resultSet.getString("Username");
                    String Password = resultSet.getString("Password");

                   

                    list.add(new Employee( Eid,Salary,  Ename, Eaddress, Faculty,  Joinedyear, Username,  Password));
                }
                
                return list;
                
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean deleteEData(int id){
      
       if (connection!=null){ 
           String query="DELETE FROM employee WHERE Eid=?";
           try {
            preparedStatement= connection.prepareStatement(query);
            
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
              
       }
        return false;
    
        }
       public boolean updateEdata(Employee employee){
           String query = "UPDATE employee SET Ename=?, Eaddress=?, Faculty=?, Salary=?,  Username=?, Password=? WHERE Eid=? ";
       if (connection!=null){
           try {
            preparedStatement= connection.prepareStatement(query);
            
                 
            preparedStatement.setString(1, employee.getEname());
            preparedStatement.setString(2, employee.getEaddress());
            preparedStatement.setString(3, employee.getFaculty());
            preparedStatement.setInt(4, employee.getSalary());
           
            preparedStatement.setString(5, employee.getUsername());
            preparedStatement.setString(6, employee.getPassword());
            preparedStatement.setInt(7, employee.getEid());
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } 
       catch (SQLException ex) {
            Logger.getLogger(EmployeeDBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       }
           return false;
        
        }
    public boolean loginAsUser(String Username, String Password) {
        String query = "SELECT Username, Password FROM employee WHERE Username = ? and Password=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBUtils.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
