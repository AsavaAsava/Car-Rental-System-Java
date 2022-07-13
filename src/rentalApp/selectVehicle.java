/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalApp;


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

public class selectVehicle extends Application {
    Stage pageSelectVehicle = new Stage();
     private final ObservableList<vehicle> availableCars = FXCollections.observableArrayList();
     private final ObservableList<vehicle> reccomendedCars = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage pageSelectVehicle) {
        
        Text main_lbl = new Text("Select Vehicle");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,60));
        
         Label label = new Label("Recommended Vehicles:");
        label.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,30));
        TableView<vehicle> table = new TableView<>();
        //Creating columns
        TableColumn vinCol0 = new TableColumn("VIN");
        vinCol0.setCellValueFactory(new PropertyValueFactory<>("vin"));
        TableColumn makeCol0 = new TableColumn("Make");
        makeCol0.setCellValueFactory(new PropertyValueFactory<>("make"));
        TableColumn modelCol0 = new TableColumn("Model");
        modelCol0.setCellValueFactory(new PropertyValueFactory<>("model"));
        
        modelCol0.setPrefWidth(200);
        table.getColumns().addAll(vinCol0, makeCol0, modelCol0);
        
         try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "SELECT * from carrental.vehicle WHERE available='1' and capacity >= '"+checkOutVehicle.capacity+"' and rentRate <= '"+checkOutVehicle.rent+
                                   "' and terrain = '"+checkOutVehicle.terrainVal+"' and fuelUse = '"+checkOutVehicle.fuelUse+"' and performance = '"+checkOutVehicle.performance+
                                    "' and luggageCapacity = '"+ checkOutVehicle.lugCapacity+"'";
                    
                    System.out.println(query);
                    ResultSet rs = st.executeQuery(query);
                    
                    while(rs.next()){
                        reccomendedCars.add(new vehicle(rs.getString("vin"),rs.getString("make"),rs.getString("model"),rs.getString("capacity"),rs.getString("bodyType"),rs.getString("rentRate")
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
        table.setItems(reccomendedCars);
        table.setPrefSize(600, 200);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 50, 50, 60));
        vbox.getChildren().addAll(label, table);


        Label label2 = new Label("All Vehicles:");
        label2.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,30));
        TableView<vehicle> tableAvailable = new TableView<>();
        //Creating columns
        TableColumn vinCol = new TableColumn("VIN");
        vinCol.setCellValueFactory(new PropertyValueFactory<>("vin"));
        TableColumn makeCol = new TableColumn("Make");
        makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));
        TableColumn modelCol = new TableColumn("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        
        modelCol.setPrefWidth(200);
        tableAvailable.getColumns().addAll(vinCol, makeCol, modelCol);
        
         try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "SELECT * from carrental.vehicle WHERE available='0'";
                    ResultSet rs = st.executeQuery(query);
                    
                    while(rs.next()){
                        availableCars.add(new vehicle(rs.getString("vin"),rs.getString("make"),rs.getString("model"),rs.getString("capacity"),rs.getString("bodyType"),rs.getString("rentRate")
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
        tableAvailable.setItems(availableCars);
        tableAvailable.setPrefSize(600, 200);
        VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setPadding(new Insets(10, 50, 50, 60));
        vbox2.getChildren().addAll(label2, tableAvailable);
        
        
        Text userChoice = new Text("National ID Number");
        TextField username = new TextField();
        username.setPrefWidth(200);
        Text vehicleChoice = new Text("Vehicle ID");
        TextField VIN = new TextField();
        VIN.setPrefWidth(200);
        
        
        
       Button submit = new Button("Rent Selected Vehicle");
       submit.setPrefSize(200,50);
       
       submit.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String user = username.getText();
                String vehicle = VIN.getText();
                
                 try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "INSERT INTO carrental.rentedcars (`customer`, `car`) "
                            + "VALUES ('"+user+"', '"+vehicle+"')";
                    
                     System.out.println(query);
                    st.executeUpdate(query);
                    
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setContentText("Rented Successfully");
                    al.show();
                    
                    query = "UPDATE carrental.vehicle SET available = 1 where vin = "+vehicle ;
                    
                    st.executeUpdate(query);
                    
                Dashboard a = new Dashboard();
                a.start(a.page2);
                pageSelectVehicle.close();

                } catch (Exception ee) {
                    System.out.println(ee);
                    System.out.println("Connection Error");
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setContentText("Connection Error");
                    al.show();
                }
            }
        });
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1000, 900);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        
        gridPane.add(main_lbl,2,1);
        gridPane.add(vbox,2,2,3,1);
        gridPane.add(vbox2,2,3,3,1);
        gridPane.add(userChoice,2,4);
        gridPane.add(username,2,5);
        gridPane.add(vehicleChoice,3,4);
        gridPane.add(VIN,3,5);
        gridPane.add(submit,3,6);
        

        

        Scene my_scene = new Scene(gridPane);
        
        
        pageSelectVehicle.setTitle("Rental - Select Vehicle");
        pageSelectVehicle.setScene(my_scene);
        pageSelectVehicle.show();
    }
     
}
