/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginsystem2;

import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pckgcommon.common;
import pckgdatabase.EmployeeDBUtils;
import pckgdatabase.StudentDBUtil;

/**
 *
 * @author safalshrestha
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private ComboBox<String> cmbUsertype;
    common common1 = new common();

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> usertype = FXCollections.observableArrayList("Admin", "User");
        cmbUsertype.setItems(usertype);

    }

    @FXML
    private void handleBtnLoginAction(ActionEvent event) throws IOException {
        String UserType = cmbUsertype.getSelectionModel().getSelectedItem();//for combobox
        StudentDBUtil studentDBUtil = new StudentDBUtil();
        EmployeeDBUtils employeeDBUtils = new EmployeeDBUtils();
        
        if (UserType.equals("Admin")) {
            if (studentDBUtil.loginasAdmin(txtusername.getText(), txtpassword.getText())) {
                System.out.println("login Successful");
                common1.nextStage("/pckgadmin1/AdminWindow.fxml", "Admin Window", true);
                Stage current = (Stage) txtusername.getScene().getWindow();
                current.hide();

            }
            else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Login Message");
                 alert.setHeaderText("Login fail");
                 alert.setContentText("Try again to login");
                 alert.showAndWait();
            }
           

        } else if (UserType.equals("User")) {
            if (employeeDBUtils.loginAsUser(txtusername.getText(), txtpassword.getText())) {
                System.out.println("Login Successful as user..");
                common1.nextStage("/pckguser1/UserWindow.fxml", "User Window", true);
                Stage current = (Stage) txtusername.getScene().getWindow();
                current.hide();
            }
           else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Login Message");
                 alert.setHeaderText("Login fail");
                 alert.setContentText("Try again to login");
                 alert.showAndWait();
            }
           
        }

    }

    @FXML
    private void handleBtnResetAction(ActionEvent event) {
        txtusername.setText(null);
        txtpassword.setText(null);
        
    }

    @FXML
    private void handleBtnExitAction(ActionEvent event) {
        exit(0);
    }

}
