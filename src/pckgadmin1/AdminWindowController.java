/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckgadmin1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pckgcommon.common;
import pckgdatabase.StudentDBUtil;
import pckgmodel.Student;

/**
 * FXML Controller class
 *
 * @author safalshrestha
 */
public class AdminWindowController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCourse;
    @FXML
    private TextField txtSem;
    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtpassword;
    @FXML
    private TableView<Student> tableview;
    @FXML
    private TableColumn<Student, Integer> colId;
    @FXML
    private TableColumn<Student,String > colName;
    @FXML
    private TableColumn<Student,String> colCourse;
    @FXML
    private TableColumn<Student, Integer> colSem;
    @FXML
    private TableColumn<Student,String> colUsername;
    @FXML
    private TableColumn<Student,String> colPassword;
    @FXML
    private TableColumn<Student, Integer> colRegdate;
    @FXML
    private TableColumn<Student, String> colDob;
    @FXML
    private DatePicker dateDob;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private StudentDBUtil studentDBUtil;
    private Student student;
    @FXML
    private void handleSaveAction(ActionEvent event) {
        student = new Student();
        
        student.setName(txtName.getText());
        student.setCourse(txtCourse.getText());
        student.setSem(Integer.parseInt(txtSem.getText()));
        student.setUsername(txtusername.getText());
        student.setPassword(txtpassword.getText());
        student.setDob(dateDob.getEditor().getText());//from calander
        
      if(studentDBUtil.createStudent(student)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Insert Message");
                 alert.setHeaderText("Information Inserted");
                 alert.setContentText("You have successfully Inserted Data");
                 alert.showAndWait();
          
          
      }
      
       
    }
    public void clear(){
    txtName.clear();
    txtCourse.clear();
    txtSem.clear();
    txtusername.clear();
    txtpassword.clear();
    
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) {
        student = new Student();
        studentDBUtil = new StudentDBUtil();
        
        student.setName(txtName.getText());
        student.setCourse(txtCourse.getText());
        student.setSem(Integer.parseInt(txtSem.getText()));
        student.setUsername(txtusername.getText());
        student.setPassword(txtpassword.getText());
        student.setDob(dateDob.getEditor().getText());
        
        if (studentDBUtil.createStudent(student)){
            refreshData();
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Update Message");
                 alert.setHeaderText("Information Updated");
                 alert.setContentText("You have successfully Updated Data");
                 alert.showAndWait();
          
        }
        
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
     studentDBUtil = new StudentDBUtil();
     student = new Student();
     
     
     if(studentDBUtil.deletedata(Integer.parseInt(txtId.getText())))
         
     {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Delete Message");
                 alert.setHeaderText("Information Deleted");
                 alert.setContentText("You have successfully Deleted Data");
                 alert.showAndWait();
          
     }
        
    }

    @FXML
    private void handleNewAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtCourse.clear();
        txtSem.clear();
        txtusername.clear();
        txtpassword.clear();
        
    }


   

    @FXML
    private void handleLoadAction(ActionEvent event) {
       refreshData();
        }

    @FXML
    private void handleMouseClickAction(MouseEvent event) {
      showTableData();
    }
    public void showTableData(){
      Student stdnt = (Student) tableview.getSelectionModel().getSelectedItem();
        
        txtId.setText(""+stdnt.getId());
        txtName.setText(stdnt.getName());
        txtCourse.setText(stdnt.getCourse());
        txtSem.setText(""+stdnt.getSem());
        txtusername.setText(stdnt.getUsername());
        txtpassword.setText(stdnt.getPassword());
        dateDob.getEditor().setText(stdnt.getDob());
    }

    @FXML
    private void handleKeyPressedAction(KeyEvent event) {
        showTableData();
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {
        common common1 = new common();
        common1.nextStage("/loginsystem2/FXMLDocument.fxml", "Login Window", true);
        Stage current = (Stage) txtusername.getScene().getWindow();
        current.hide();
        
    }
    private void refreshData(){
      studentDBUtil = new StudentDBUtil();
        ObservableList<Student>data;
        if((data= studentDBUtil.fetchdata())!=null){
            colId.setCellValueFactory(new PropertyValueFactory<>("id")); // the data in cotation are from gettersetter
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
            colSem.setCellValueFactory(new PropertyValueFactory<>("sem"));
            colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colRegdate.setCellValueFactory(new PropertyValueFactory<>("regdate"));
            colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
           
            tableview.setItems(data);
           
        }
    }
        
    }
    

