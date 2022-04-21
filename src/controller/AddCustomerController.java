package controller;

import Database.CountryQuery;
import Database.CustomerQuery;
import Database.DivisionQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Countries;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @FXML
    private Button addSave;

    @FXML
    private TextField txtAddCusId;
    @FXML
    private TextField txtAddCusName;
    @FXML
    private TextField txtAddCusAddress;
    @FXML
    private TextField txtAddCusPost;
    @FXML
    private TextField txtAddCusPhone;
    @FXML
    private ComboBox<Countries> comAddCusCountry;
    @FXML
    private ComboBox<Division> comAddCusDiv;




    public void onSave(ActionEvent actionEvent) throws IOException{
        String Customer_ID = txtAddCusId.getText();
        String Customer_Name = txtAddCusName.getText();
        String Address = txtAddCusAddress.getText();
        String Postal_Code = txtAddCusPost.getText();
        String Phone = txtAddCusPhone.getText();
        Countries Country = comAddCusCountry.getValue();
        Division Division = comAddCusDiv.getValue();

     //   Customer newCustomer = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Country, Division);
     //   Customer.add(newCustomer);
        CustomerQuery.createCustomer(Customer_Name, Address, Postal_Code, Phone, Country.getCountryName(), Division.getDivision());




        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 900, 400);
        stage.setTitle("Customer View");
        stage.setScene(scene);
        stage.show();
    }

    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}




    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 900, 400);
        stage.setTitle("Customer View");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Countries> allCountries = CountryQuery.getAllCountries();
        ObservableList<Division> allDivisions = DivisionQuery.getAllDivisions();


        comAddCusCountry.setItems(allCountries);
        comAddCusCountry.setPromptText("Select Country");

        try {
            if (comAddCusCountry.getValue().getCountryId() == 1) {
                comAddCusDiv.setItems(DivisionQuery.getStates());
            } else {

                comAddCusDiv.setItems(allDivisions);
                comAddCusDiv.setPromptText("Select Division");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //comAddCusCountry.setItems(CountryQuery.getAllCountries());
        //comAddCusDiv.setItems(DivisionQuery.getAllDivisions());



    }

    public void onComboCountry(ActionEvent actionEvent) {
    }

    public void onComboDivision(ActionEvent actionEvent) {
    }
}
