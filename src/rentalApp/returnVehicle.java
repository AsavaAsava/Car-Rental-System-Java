/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class returnVehicle extends Application{
    Stage pageReturnVehicle = new Stage();
    
    @Override
    public void start(Stage pageReturnVehicle) {
        
        Text main_lbl = new Text("Return Vehicle");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,60));
        
        Text VIN = new Text("Vehicle Identification Number:");
        TextField rentedVIN = new TextField();
        rentedVIN.setPrefSize(400, 30);
        
        Button returnCar = new Button("Return Car");
        returnCar.setPrefSize(100, 40);
         returnCar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String vehicle = rentedVIN.getText();
                 try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    
                    
                    String query = "UPDATE carrental.vehicle SET available = 0 where vin = "+vehicle ;
                    
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setContentText("Vehicle Returned Succesfully");
                    al.show();
                    
                    st.executeUpdate(query);
                    
                Dashboard a = new Dashboard();
                a.start(a.page2);
                pageReturnVehicle.close();

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
        gridPane.setMinSize(800, 400);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.add(main_lbl, 2,1);
        gridPane.add(VIN, 2,2);
        gridPane.add(rentedVIN, 2,3);
        gridPane.add(returnCar, 3,5);
        

        Scene my_scene = new Scene(gridPane);
        
        pageReturnVehicle.setTitle("Return Vehicle");
        pageReturnVehicle.setScene(my_scene);
        pageReturnVehicle.show();
    }
   
}
