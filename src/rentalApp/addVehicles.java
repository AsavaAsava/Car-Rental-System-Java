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
import javafx.collections.FXCollections;
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

/**
 *
 * @author Wayne
 */
public class addVehicles extends Application {
    
    
    Stage pageAddVehicles = new Stage();
    
    @Override
    public void start(Stage pageAddVehicle) {
        
        Text main_lbl = new Text("Add Vehicle");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,60));
        
    Text vehicleMake_lbl = new Text("Make");
    TextField vehicleMake = new TextField();
    
    Text vehicleModel_lbl = new Text("Model");
    TextField vehicleModel = new TextField();
    
    Text vehicleCapacity_lbl = new Text("Capacity");
    TextField vehicleCapacity = new TextField();
    
    Text vehicleType_lbl = new Text("Body Type");
    String[] bodyTypes ={"Sedan","Convertible"};
    ChoiceBox vehicleType = new ChoiceBox(FXCollections.observableArrayList(bodyTypes));
    vehicleType.setPrefSize(200,30);
    
    Text rentRate_lbl = new Text("Rate");
    TextField rentRate = new TextField();
    
    Text terrain_lbl = new Text("Intended Terrain");
    String[] terrains ={"City Road","Gravel","Off-Road","Wet-Road"};
        ChoiceBox terrain = new ChoiceBox(FXCollections.observableArrayList(terrains));
        terrain.setPrefSize(200,30);
        
        Text fuelUse_lbl = new Text("Fuel Consumption");
        ToggleGroup fuelChoices = new ToggleGroup();
        RadioButton lowFuel = new  RadioButton("Low");
        RadioButton avgFuel = new  RadioButton("Average");
        lowFuel.setToggleGroup(fuelChoices);
        avgFuel.setToggleGroup(fuelChoices);
        HBox fuelButtons = new HBox(10);
        fuelButtons.getChildren().add(lowFuel);
        fuelButtons.getChildren().add(avgFuel);
        
        
        Text performance_lbl = new Text("Vehicle Performance");
        ToggleGroup performanceChoices = new ToggleGroup();
        RadioButton avgPerformance = new  RadioButton("Average");
        RadioButton highPerformance = new  RadioButton("High");
        avgPerformance.setToggleGroup(performanceChoices);
        highPerformance.setToggleGroup(performanceChoices);
        HBox performanceButtons = new HBox(10);
        performanceButtons.getChildren().add(avgPerformance);
        performanceButtons.getChildren().add(highPerformance);
      
        Text luggageCapacity_lbl = new Text("Luggage Capacity");
        String[] luggSizes ={"Small","Regular","High Capacity","Commercial"};
        ChoiceBox luggage = new ChoiceBox(FXCollections.observableArrayList(luggSizes));
         luggage.setPrefSize(200,30);
        
        Button submit = new Button("Save Entry");
       submit.setPrefSize(200,50);
       
       submit.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String make = vehicleMake.getText();
                String model = vehicleModel.getText();
                String capacity = vehicleCapacity.getText();
                String bodyType = bodyTypes[vehicleType.getSelectionModel().selectedIndexProperty().intValue()];
                String rent = rentRate.getText();
                String terrainVal = terrains[terrain.getSelectionModel().selectedIndexProperty().intValue()];
                String fuelUse;
                if(lowFuel.isSelected()){fuelUse = "low";}else{fuelUse = "average";}
                String performance;
                if(avgPerformance.isSelected()){performance = "average";}else{ performance  = "high";}
         
                String lugCapacity =luggSizes[luggage.getSelectionModel().selectedIndexProperty().intValue()];
               
                
                 try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "INSERT INTO carrental.vehicle (`make`, `model`, `capacity`, `bodyType`, `rentRate`,`terrain`,`fuelUse`, `performance`, `luggageCapacity`) "
                            + "VALUES ('"+make+"', '"+model+"', '"+capacity+"', '"+bodyType+"', '"+rent+"', '"+terrainVal+"', '"+fuelUse+"', '"+performance+"', '"+lugCapacity+"')";
                    
                     System.out.println(query);
                    st.executeUpdate(query);
                    
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setContentText("Vehicle Added Successfully");
                    al.show();
                    
                    vehicleMake.clear();
                    vehicleCapacity.clear();
                    vehicleModel.clear();
                    
                } catch (Exception ee) {
                    System.out.println(ee);
                    System.out.println("Connection Error");
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setContentText("Connection Error");
                    al.show();
                }
            }
        });
        Button back_btn = new Button("Back");
        back_btn.setPrefSize(200,50);
        back_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                Dashboard a = new Dashboard();
                a.start(a.page2);
                pageAddVehicle.close();
            }});
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setVgap(15);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        
        gridPane.add(main_lbl,2,1,2,1);
        gridPane.add(vehicleMake_lbl,2,2);
        gridPane.add(vehicleMake,3,2);
        gridPane.add(vehicleModel_lbl,2,3);
        gridPane.add(vehicleModel,3,3);
        gridPane.add(vehicleCapacity_lbl,2,4);
        gridPane.add(vehicleCapacity,3,4);
        gridPane.add(vehicleType_lbl,2,5);
        gridPane.add(vehicleType,3,5);
        gridPane.add(rentRate_lbl,2,6);
        gridPane.add(rentRate,3,6);
        gridPane.add(terrain_lbl,2,7);
        gridPane.add(terrain,3,7);
        gridPane.add(fuelUse_lbl,2,8);
        gridPane.add(fuelButtons,3,8);
        gridPane.add(performance_lbl,2,9);
        gridPane.add(performanceButtons,3,9);
        gridPane.add(luggageCapacity_lbl,2,10);
        gridPane.add(luggage,3,10);
        gridPane.add(submit,4,11);
        gridPane.add(back_btn,5,11);

        

        

        Scene my_scene = new Scene(gridPane);
        
        
        pageAddVehicle.setTitle("Rental - Select Vehicle");
        pageAddVehicle.setScene(my_scene);
        pageAddVehicle.show();
    }
     
}
