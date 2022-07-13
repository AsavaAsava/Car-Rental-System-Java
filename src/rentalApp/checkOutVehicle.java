/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalApp;


import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class checkOutVehicle extends Application {
    Stage pageCheckOut = new Stage();
               public static String capacity;
               public static String rent;
               public static String terrainVal;
               public static String fuelUse;
               public static String performance;
               public static String lugCapacity;
    @Override
    public void start(Stage pageCheckOut) {
    
        pageCheckOut.setTitle("Rental - Check Out Vehicle");
        
        Text main_lbl = new Text("Choose User Preferences");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,60));
        Button addCustomer_btn = new Button("Add Customer");
        addCustomer_btn.setPrefSize(150,50);
        Button viewCusomers_btn = new Button("View Customers");
        viewCusomers_btn.setPrefSize(150,50);
        Button addVehicle_btn = new Button("Add Vehicle");
        addVehicle_btn.setPrefSize(150,50);
        Button viewVehicle_btn = new Button("View Vehicles");
        viewVehicle_btn.setPrefSize(150,50);
        Button checkOutVehicle_btn = new Button("Rent Out Car");
        checkOutVehicle_btn.setPrefSize(150,50);
        Button checkInVehicle_btn = new Button("Return Car");
        checkInVehicle_btn.setPrefSize(150,50);
        Button exit_btn = new Button("Log Out");
        exit_btn.setPrefSize(150,50);

        Separator sep1 = new Separator(Orientation.VERTICAL);
        Separator sep2 =new Separator(Orientation.HORIZONTAL);
        
        Text maxBudget_lbl = new Text("Maximmum Price");
        TextField maxBudget = new TextField();
        
        Text terrain_lbl = new Text("Intended Terrain");
        String[] terrains ={"City Road","Gravel","Off-Road","Wet-Road"};
        ChoiceBox terrain = new ChoiceBox(FXCollections.observableArrayList(terrains));
        
        Text fuelUse_lbl = new Text("Fuel Consumption");
        ToggleGroup fuelChoices = new ToggleGroup();
        RadioButton lowFuel = new  RadioButton("Low");
        RadioButton avgFuel = new  RadioButton("Average");
        RadioButton allFuel = new  RadioButton("Does not matter");
        lowFuel.setToggleGroup(fuelChoices);
        avgFuel.setToggleGroup(fuelChoices);
        allFuel.setToggleGroup(fuelChoices);
        HBox fuelButtons = new HBox(10);
        fuelButtons.getChildren().add(lowFuel);
        fuelButtons.getChildren().add(avgFuel);
        fuelButtons.getChildren().add(allFuel);
        
        
        Text performance_lbl = new Text("Vehicle Performance");
        ToggleGroup performanceChoices = new ToggleGroup();
        RadioButton avgPerformance = new  RadioButton("Average");
        RadioButton highPerformance = new  RadioButton("High");
        RadioButton anyPerformance = new  RadioButton("Does not matter");
        avgPerformance.setToggleGroup(performanceChoices);
        highPerformance.setToggleGroup(performanceChoices);
        anyPerformance.setToggleGroup(performanceChoices);
        HBox performanceButtons = new HBox(10);
        performanceButtons.getChildren().add(avgPerformance);
        performanceButtons.getChildren().add(highPerformance);
        performanceButtons.getChildren().add(anyPerformance);
      
        Text luggageCapacity_lbl = new Text("Luggage Capacity");
        String[] luggSizes ={"Small","Regular","High Capacity","Commercial"};
        ChoiceBox luggage = new ChoiceBox(FXCollections.observableArrayList(luggSizes));
        
        Text numberOfPassengers_lbl = new Text("Seating Capacity");
        TextField seatCapacity = new TextField();
        
        Button suggestVehicle = new Button("Next");
        suggestVehicle.setPrefSize(150,50);
        
        suggestVehicle.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                capacity = seatCapacity.getText();
                rent = maxBudget.getText();
                terrainVal = terrains[terrain.getSelectionModel().selectedIndexProperty().intValue()];
                if(lowFuel.isSelected()){fuelUse = "low";}else{fuelUse = "average";}
                if(highPerformance.isSelected()){performance = "high";}else{ performance  = "average";}
                lugCapacity =luggSizes[luggage.getSelectionModel().selectedIndexProperty().intValue()];

           
                selectVehicle a = new selectVehicle();
                a.start(a.pageSelectVehicle);
                pageCheckOut.close();
               
            }});
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1000, 800);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        
        gridPane.add(addCustomer_btn,1,2);
        gridPane.add(viewCusomers_btn,1,3);
        gridPane.add(addVehicle_btn,1,4);
        gridPane.add(viewVehicle_btn,1,5);
        gridPane.add(checkOutVehicle_btn,1,6);
        gridPane.add(checkInVehicle_btn,1,7);
        gridPane.add(exit_btn,1,8);
        
        gridPane.add(sep1, 2, 0,1,30);
        
        gridPane.add(main_lbl,3,1);
        gridPane.add(sep2, 2, 2, 29,1);
        
        
        gridPane.add(maxBudget_lbl, 3, 3);
        gridPane.add(maxBudget, 3, 4);
        gridPane.add(terrain_lbl, 3, 5);
        gridPane.add(terrain, 3, 6);
        gridPane.add(fuelUse_lbl, 3, 7);
        gridPane.add(fuelButtons, 3, 8);

        gridPane.add(performance_lbl, 3, 9);
        gridPane.add(performanceButtons, 3, 10);

        gridPane.add(luggageCapacity_lbl, 3, 11);
        gridPane.add(luggage, 3, 12);
        gridPane.add(numberOfPassengers_lbl, 3, 13);
        gridPane.add(seatCapacity, 3, 14);
        gridPane.add(suggestVehicle,4,16);
        
        
        
        Scene my_scene = new Scene(gridPane);
        

        pageCheckOut.setScene(my_scene);
        pageCheckOut.show();
    
    }
         

}
