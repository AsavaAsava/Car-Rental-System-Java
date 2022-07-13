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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class viewRentedVehicles extends Application {
    Stage pageviewVehiclesRented = new Stage();
    
    private ObservableList<vehicle> rentedCars = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage pageviewVehiclesRented) {
        
        
        
        Text main_lbl = new Text("Rented Vehicles");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,60));
        
        TableView<vehicle> table = new TableView<>();
        //Creating columns
        TableColumn vinCol = new TableColumn("VIN");
        vinCol.setCellValueFactory(new PropertyValueFactory<>("vin"));
        TableColumn makeCol = new TableColumn("Make");
        makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        TableColumn modelCol = new TableColumn("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        
        modelCol.setPrefWidth(200);
        table.getColumns().addAll(vinCol, makeCol, modelCol);
        
         try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "SELECT * from carrental.vehicle WHERE available='1'";
                    ResultSet rs = st.executeQuery(query);
                    
                    while(rs.next()){
                        rentedCars.add(new vehicle(rs.getString("vin"),rs.getString("make"),rs.getString("model"),rs.getString("capacity"),rs.getString("bodyType"),rs.getString("rentRate")
                                                  ,rs.getString("terrain"),rs.getString("fuelUse"),rs.getString("performance"),rs.getString("luggageCapacity")));
                    }  
                    
                    
                } catch (Exception ee) {
                    System.out.println(ee);
                    System.out.println("Connection Error");
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setContentText("Connection Error");
                    al.show();
                }
        //Setting the size of the table
        table.setItems(rentedCars);
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
                pageviewVehiclesRented.close();
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
        
        
        pageviewVehiclesRented.setTitle("Rental - View Rented Vehicles");
        pageviewVehiclesRented.setScene(my_scene);
        pageviewVehiclesRented.show();
    }

}
