package Database;

import javafx.scene.control.ComboBox;
import model.Customer;
import model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerQuery {


    public static ObservableList<Customer> getCustomer() throws SQLException{
        ObservableList<Customer> customersList = FXCollections.observableArrayList();


        try {

            String sql = "SELECT * FROM customers AS c LEFT JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID LEFT JOIN countries AS co ON co.Country_ID = d.COUNTRY_ID";


            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int Customer_ID = rs.getInt("Customer_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                String Division = rs.getString("Division");
                String Country = rs.getString("Country");
                int Division_ID = rs.getInt("Division_ID");

                Customer c = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division, Country, Division_ID);

                customersList.add(c);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return customersList;
    }

    public static void createCustomer(String Customer_Name, String Address, String Postal_Code, String Phone){
        try{
            String sqlcc = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?)";

            PreparedStatement pscc = DBConnection.getConnection().prepareStatement(sqlcc);

            pscc.setString(1, Customer_Name);
            pscc.setString(2, Address);
            pscc.setString(3, Postal_Code);
            pscc.setString(4, Phone);

            pscc.execute();

            ResultSet rs = pscc.getResultSet();
            rs.next();
            int Customer_ID = rs.getInt(1);



        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


   /* public static int updateCustomer (int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, ComboBox Country, ComboBox Division) throws SQLException {

        String sql = "UPDATE customers SET Customer_Name = ? AND Address = ? AND Postal_Code = ? AND Phone = ? AND Country = ? AND Division = ? WHERE Customer_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sql);

        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setString(5, ComboBox.valueOf(Country));
        ps.setString(6, String.valueOf(Division));

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static void deleteCustomer(){

    }

*/



}
