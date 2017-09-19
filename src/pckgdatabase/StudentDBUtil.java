package pckgdatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pckgmodel.Student;

public class StudentDBUtil {

    PreparedStatement preparedstatement;
    ResultSet resultset;
    Connection connection = DatabaseConnector.databaseConnector();

    ObservableList<Student> data = FXCollections.observableArrayList();//making the list of students

    public boolean createStudent(Student student) {

        if (connection != null) {
            String query = "INSERT INTO student(name, course, sem, username, password,registeredDate, DOB) VALUES(?,?,?,?,?,?,?)";
            try {
                preparedstatement = connection.prepareStatement(query);

                preparedstatement.setString(1, student.getName());
                preparedstatement.setString(2, student.getCourse());
                preparedstatement.setInt(3, student.getSem());
                preparedstatement.setString(4, student.getUsername());
                preparedstatement.setString(5, student.getPassword());
                preparedstatement.setDate(6, student.getRegdate());
                preparedstatement.setString(7, student.getDob());

                preparedstatement.execute();
                preparedstatement.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    public ObservableList<Student> fetchdata() {//returning the list Student object after executing SQL 
        try {
            String query = "SELECT * FROM student";
            preparedstatement = connection.prepareStatement(query);
            resultset = preparedstatement.executeQuery(query);
            while (resultset.next()) {
                Integer id = resultset.getInt("id");
                String name = resultset.getString("name");
                String course = resultset.getString("course");
                Integer sem = resultset.getInt("sem");
                String username = resultset.getString("username");
                String password = resultset.getString("password");
                Date date = resultset.getDate("registeredDate");
                String dob = resultset.getString("DOB");

                data.add(new Student(id, name, course, sem, username, password, dob, date));//creatin student object and adding to the list
            }
            return data;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public void fetchbyid(int id) {
        try {
            if (connection != null) {
                String query = "SELECT * FROM Student Where id=?";
                preparedstatement = connection.prepareStatement(query);
                preparedstatement.setInt(1, id);

                resultset = preparedstatement.executeQuery();
                while (resultset.next()) {
                    System.out.println("ID: " + resultset.getInt("id"));
                    System.out.println("NAME : " + resultset.getString("name"));
                    System.out.println("COURSE : " + resultset.getString("course"));
                    System.out.println("SEMESTER : " + resultset.getInt("sem"));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean updatedata(Student student) {
        if (connection != null) {
            String query = "UPDATE student SET name=?, course=?, sem=? username = ? , password = ?, DOB=? WHERE id=?";
            try {
                preparedstatement = connection.prepareStatement(query);

                preparedstatement.setString(1, student.getName());
                preparedstatement.setString(2, student.getCourse());
                preparedstatement.setInt(3, student.getSem());
                preparedstatement.setString(4, student.getUsername());
                preparedstatement.setString(5,student.getPassword());
                preparedstatement.setString(6, student.getDob());
                preparedstatement.setInt(7, student.getId());
                preparedstatement.executeUpdate();
                preparedstatement.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    public boolean deletedata(int id) {
        String query = "DELETE FROM Student WHERE id=?";
        try {
            preparedstatement = connection.prepareStatement(query);

            preparedstatement.setInt(1, id);
            preparedstatement.executeUpdate();
            preparedstatement.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean loginasAdmin(String username, String password) {
        try {
            String query = "SELECT username, password FROM Student Where username= ? and password=?";
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, username);
            preparedstatement.setString(2, password);
            resultset = preparedstatement.executeQuery();
            while (resultset.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteData(String username, String password) {
        String query = "DELETE FROM Student WHERE username=?, password=?";
        try {
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, username);
            preparedstatement.setString(2, password);

            preparedstatement.executeUpdate();
            preparedstatement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
