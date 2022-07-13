/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalApp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Wayne
 */
public class Dashboard extends Application {
    Stage page2 = new Stage();
    
    
    
    @Override
    public void start(Stage page2) {
        Text main_lbl = new Text("Rental Dashboard");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,60));
        Button addCustomer_btn = new Button("Add Customer");
         addCustomer_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                addCustomer a = new addCustomer();
                a.start(a.addCustomerPage);
                page2.close();
            }});
        addCustomer_btn.setPrefSize(150,50);
        Button viewCustomers_btn = new Button("View Customers");
        viewCustomers_btn.setPrefSize(150,50);
        viewCustomers_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                viewCustomers a = new viewCustomers();
                a.start(a.pageviewCustomers);
            }});
        Button addVehicle_btn = new Button("Add Vehicle");
        addVehicle_btn.setPrefSize(150,50);
        addVehicle_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                addVehicles a = new addVehicles();
                a.start(a.pageAddVehicles);
                page2.close();
            }});
        Button viewAvailableVehicle_btn = new Button("Available Vehicles");
        viewAvailableVehicle_btn.setPrefSize(150,50);
        viewAvailableVehicle_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                viewAvailableVehicles a = new viewAvailableVehicles();
                a.start(a.pageviewVehiclesAvailable);
            }});
        Button viewRentedVehicle_btn  = new Button("Rented Vehicles");
        viewRentedVehicle_btn.setPrefSize(150,50);
        viewRentedVehicle_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                viewRentedVehicles a = new viewRentedVehicles();
                a.start(a.pageviewVehiclesRented);
            }});
        Button checkOutVehicle_btn = new Button("Rent Out Car");
        checkOutVehicle_btn.setPrefSize(150,50);
        checkOutVehicle_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                checkOutVehicle a = new checkOutVehicle();
                a.start(a.pageCheckOut);
            }});
        Button checkInVehicle_btn = new Button("Return Car");
        checkInVehicle_btn.setPrefSize(150,50);
        checkInVehicle_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                returnVehicle a = new returnVehicle();
                a.start(a.pageReturnVehicle);
            }});
        Button exit_btn = new Button("Log Out");
        exit_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                
                page2.close();
            }});
        exit_btn.setPrefSize(150,50);
        
        String totVehicles = " ";
        String totCustomers = " ";
        String totVehiclesA = " ";
         String totVehiclesR = " ";
        
        try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "SELECT COUNT('vin') FROM carrental.vehicle";
                    
                    System.out.println(query);
                    ResultSet rs = st.executeQuery(query);
                    
                    while(rs.next()){
                       totVehicles= rs.getString("COUNT('vin')");
                    }  
                    
                    
                    query = "SELECT COUNT('vin') FROM carrental.vehicle where available = 0";
                    
                    System.out.println(query);
                    rs = st.executeQuery(query);
                    
                    while(rs.next()){
                        totVehiclesA= rs.getString("COUNT('vin')");
                    }
                    query = "SELECT COUNT('vin') FROM carrental.vehicle where available = 1";
                    
                    System.out.println(query);
                    rs = st.executeQuery(query);
                    
                    while(rs.next()){
                        totVehiclesR= rs.getString("COUNT('vin')");
                    }
                    query = "SELECT COUNT('customerID') FROM carrental.customer";
                    
                    System.out.println(query);
                    rs = st.executeQuery(query);
                    
                    while(rs.next()){
                        totCustomers= rs.getString("COUNT('customerID')");
                    }
                  
                } catch (Exception ee) {
                    System.out.println(ee);
                    System.out.println("Connection Error");
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setContentText("Connection Error");
                    al.show();
                }
        
        String username = "Wayne";
        Text welcome_lbl = new Text("Welcome " + username );
        welcome_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,40));
        
        
        Text totalVehicles = new Text("Total Vehicles: "+ totVehicles);
        totalVehicles.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,30));
        
        Text totalCustomers = new Text("Total Customers: "+ totCustomers);
        totalCustomers.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,30));
        
        Text availableVehicles = new Text("Total Available Vehicles: "+ totVehiclesA);
        availableVehicles.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,30));
       
        Text activeRentals = new Text("Active Rentals: "+ totVehiclesR);
        activeRentals.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,30));
        
        
        
        Separator sep1 = new Separator(Orientation.VERTICAL);
        Separator sep2 =new Separator(Orientation.HORIZONTAL);
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1000, 500);
        gridPane.setVgap(20);
        gridPane.setHgap(50);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        
        gridPane.add(addCustomer_btn,1,2);
        gridPane.add(viewCustomers_btn,1,3);
        gridPane.add(addVehicle_btn,1,4);
        gridPane.add(viewAvailableVehicle_btn,1,5);
        gridPane.add(viewRentedVehicle_btn,1,6);
        gridPane.add(checkOutVehicle_btn,1,7);
        gridPane.add(checkInVehicle_btn,1,8);
        gridPane.add(exit_btn,1,8);
        
        gridPane.add(sep1, 2, 0,1,10);
        
        gridPane.add(main_lbl,3,1);
        gridPane.add(welcome_lbl, 3, 2);
        gridPane.add(sep2, 2, 3, 10,1);

        gridPane.add(totalVehicles,3,4);
        gridPane.add(totalCustomers,3,5);
        gridPane.add(activeRentals,3,6);
        gridPane.add(availableVehicles,3,7);
     
        page2.setTitle("Rental - Dashboard");

        Scene my_scene = new Scene(gridPane);

        page2.setScene(my_scene);
        page2.show();
    }

}

