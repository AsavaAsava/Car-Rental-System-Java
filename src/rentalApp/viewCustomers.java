/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalApp;

import java.sql.*;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class viewCustomers extends Application {
    Stage pageviewCustomers = new Stage();
     private ObservableList<customer> data = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage pageviewCustomers) {
        
        Text main_lbl = new Text("Registered Customers");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,60));
        
        TableView<customer> table = new TableView<>();
        //Creating columns
        TableColumn idCol = new TableColumn("Customer ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        TableColumn fnameCol = new TableColumn("First Name");
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        TableColumn lnameCol = new TableColumn("Surname");
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        TableColumn natIDCol = new TableColumn("National ID Number");
        natIDCol.setCellValueFactory(new PropertyValueFactory<>("idNum"));
        TableColumn telCol = new TableColumn("Telephone");
        telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        TableColumn unameCol = new TableColumn("Username");
        unameCol.setCellValueFactory(new PropertyValueFactory<>("uname"));
        natIDCol.setPrefWidth(200);
        table.getColumns().addAll(idCol, fnameCol, lnameCol, natIDCol,telCol,unameCol);
        
       
        
        try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "SELECT * from carrental.customer";
                    ResultSet rs = st.executeQuery(query);
                    
                    while(rs.next()){
                        data.add(new customer(rs.getString("customerID"),rs.getString("fname"),rs.getString("lname"),rs.getString("idNum"),rs.getString("tel"),rs.getString("username")));
                    }  
                    
                    
                } catch (Exception ee) {
                    System.out.println(ee);
                    System.out.println("Connection Error");
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setContentText("Connection Error");
                    al.show();
                }
        //Setting the size of the table
        table.setItems(data);
        table.setPrefSize(800, 400);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 50, 50, 60));
        vbox.getChildren().addAll(table);

        Button back_btn = new Button("Back");
        back_btn.setPrefSize(200,50);
        back_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                Dashboard a = new Dashboard();
                a.start(a.page2);
                pageviewCustomers.close();
            }});
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1000, 900);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        
        gridPane.add(main_lbl,2,1);
        gridPane.add(vbox,2,2);

        gridPane.add(back_btn,2,6);
        

        

        Scene my_scene = new Scene(gridPane);
        
        
        pageviewCustomers.setTitle("Rental - View Customers");
        pageviewCustomers.setScene(my_scene);
        pageviewCustomers.show();
    }
     
}
