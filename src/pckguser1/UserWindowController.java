/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckguser1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import pckgdatabase.EmployeeDBUtils;
import pckgmodel.Employee;

/**
 * FXML Controller class
 *
 * @author safalshrestha
 */
public class UserWindowController implements Initializable {

    @FXML
    private TableView<Employee> tableViewE;
    @FXML
    private TableColumn<Employee, Integer> colEid;
    @FXML
    private TableColumn<Employee , String> colEname;
    @FXML
    private TableColumn<Employee , String> colEaddress;
    @FXML
    private TableColumn<Employee , String> colFaculty;
    @FXML
    private TableColumn<Employee, Integer> colSalary;
    @FXML
    private TableColumn<Employee, Integer> colJyear;
    @FXML
    private TableColumn<Employee , String> colEusername;
    @FXML
    private TextField txtEid;
    @FXML
    private TextField txtEname;
    @FXML
    private TextField txtEaddress;
    @FXML
    private TextField txtFaculty;
    @FXML
    private TextField txtSalary;
    @FXML
    private DatePicker txtJyear;
    @FXML
    private TextField txtEusername;
    @FXML
    private TextField txtEpassword;
      EmployeeDBUtils employeeDBUtils;
    Employee employee;
    @FXML
    private TableColumn<Employee, String> colEPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    
    

    @FXML
    private void handleSaveActionEmp(ActionEvent event)   {
        employee = new Employee();
        
      employee.setEname(txtEname.getText());
      employee.setEaddress(txtEaddress.getText());
      employee.setFaculty(txtFaculty.getText());
      employee.setSalary(Integer.parseInt(txtSalary.getText()));
      employee.setJoinedyear(txtJyear.getEditor().getText());
      employee.setUsername(txtEusername.getText());
      employee.setPassword(txtEpassword.getText());
      
      if (employeeDBUtils.createEmployee(employee)){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Employee Data");
      alert.setHeaderText("Inserted Information");
      alert.setContentText("Emoloyee Data Successfully Inserted");
      alert.showAndWait();
      }
    }

    @FXML
    private void handleUpdateActionEmp(ActionEvent event) {
        employee = new Employee();
        employeeDBUtils = new EmployeeDBUtils();
        
        employee.setEid(Integer.parseInt(txtEid.getText()));
        employee.setEname(txtEname.getText());
        employee.setEaddress(txtEaddress.getText());
        employee.setFaculty(txtFaculty.getText());
        employee.setSalary(Integer.parseInt(txtSalary.getText()));
        employee.setJoinedyear(txtJyear.getEditor().getText());
        employee.setUsername(txtEusername.getText());
        employee.setPassword(txtEpassword.getText());
        
        if(employeeDBUtils.updateEdata(employee))
        {
            reloadData();
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Update Message");
                 alert.setHeaderText("Information Updated");
                 alert.setContentText("You have successfully Updated Data");
                 alert.showAndWait();
          
        }
    }

    @FXML
    private void handleDeleteActionEmp(ActionEvent event) {
        employee = new Employee();
        employeeDBUtils = new EmployeeDBUtils();
        
        if (employeeDBUtils.deleteEData(Integer.parseInt(txtEid.getText())))
        {
            reloadData();
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Delete Message");
                 alert.setHeaderText("Information Deleted");
                 alert.setContentText("You have successfully Deleted Data");
                 alert.showAndWait();
          
        }
    }

    @FXML
    private void handleNewActionEmp(ActionEvent event) {
    }

    @FXML
    private void handleLoadActionEmp(ActionEvent event) {
        reloadData();
       
    }

    @FXML
    private void handleClickActionEmp(MouseEvent event) {
        showTableEmpData();
    }

   
    
    public void showTableEmpData(){
    Employee emp = (Employee) tableViewE.getSelectionModel().getSelectedItem();
     
    txtEid.setText(""+emp.getEid());
    txtEname.setText(emp.getEname());
    txtEaddress.setText(emp.getEaddress());
    txtFaculty.setText(emp.getFaculty());
    txtSalary.setText(""+emp.getSalary());
    txtJyear.getEditor().setText(emp.getJoinedyear());
    txtEusername.setText(emp.getUsername());
    txtEpassword.setText(emp.getPassword());
    }

    @FXML
    private void handlePressedActionEmp(KeyEvent event) {
        showTableEmpData();
    }

    @FXML
    private void handleLogoutUserAtion(ActionEvent event) throws IOException {
        common common1 = new common();
        common1.nextStage("/loginsystem2/FXMLDocument.fxml", "Login Window", true);
        Stage current =(Stage) txtEusername.getScene().getWindow();
        current.hide();
        
    }
    private void reloadData()
    {
         System.out.println("Load Button Pressed");
        employeeDBUtils = new EmployeeDBUtils();
        ObservableList<Employee> list;
        if((list=employeeDBUtils.fetchEmpdata())!=null){
        colEid.setCellValueFactory(new PropertyValueFactory<>("Eid"));
        colEname.setCellValueFactory(new PropertyValueFactory<>("Ename"));
        colEaddress.setCellValueFactory(new PropertyValueFactory<>("Eaddress"));
        colFaculty.setCellValueFactory(new PropertyValueFactory<>("Faculty"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colJyear.setCellValueFactory(new PropertyValueFactory<>("Joinedyear"));
        colEusername.setCellValueFactory(new PropertyValueFactory<>("Username"));
        colEPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        
        tableViewE.setItems(list);
        }else{
            System.out.println("List is empty");
        }
    }
}
