/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalApp;


import java.sql.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class addCustomer extends Application {
     Stage addCustomerPage = new Stage();

    @Override
    public void start(Stage addCustomerPage) {
        Text main_lbl = new Text("Register Customer");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.ITALIC,60));
        Text firstname_lbl = new Text("First name");
        firstname_lbl.setFont(Font.font("Helvetica", FontPosture.REGULAR,20));
        Text surname_lbl = new Text("Surname"); 
        surname_lbl.setFont(Font.font("Helvetica", FontPosture.REGULAR,20));
        Text id_lbl = new Text("ID No."); 
        id_lbl.setFont(Font.font("Helvetica", FontPosture.REGULAR,20));
        Text tel_lbl = new Text("Telephone"); 
        tel_lbl.setFont(Font.font("Helvetica", FontPosture.REGULAR,20));
        Text username_lbl = new Text("UserName"); 
        username_lbl.setFont(Font.font("Helvetica", FontPosture.REGULAR,20));
        

        TextField firstname_tf = new TextField();
        firstname_tf.setPrefSize(200, 20);
        TextField  surname_tf = new TextField();
        surname_tf.setPrefSize(200, 20);
        TextField id_tf = new TextField();
        id_tf.setPrefSize(200, 20);
        TextField tel_tf = new TextField();
        tel_tf.setPrefSize(200, 20);
        TextField uname_tf = new TextField();
        uname_tf.setPrefSize(200, 20);
        
        
        Button register_btn = new Button("Register");
        register_btn.setPrefSize(150, 50);
        register_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String firstname = firstname_tf.getText();
                String surname = surname_tf.getText();
                String idNo =id_tf.getText();
                String tel = tel_tf.getText();
                String username = uname_tf.getText();
                 try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "INSERT INTO carrental.customer (`fname`, `lname`, `idNum`, `tel`, `username`) VALUES ('"+firstname+"', '"+surname+"', '"+idNo+"', '"+tel+"', '"+username+"')";
                    st.executeUpdate(query);
                    
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setContentText("Registration Successful");
                    al.show();
                    
                    firstname_tf.clear();
                    surname_tf.clear();
                    id_tf.clear();
                    tel_tf.clear();
                    uname_tf.clear();
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
                addCustomerPage.close();
            }});


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1000, 600);
        gridPane.setVgap(20);
        gridPane.setHgap(40);
        gridPane.setAlignment(Pos.CENTER_LEFT);
        
        gridPane.add(main_lbl, 1, 0,4,1);

        gridPane.add(firstname_lbl, 1, 1);
        gridPane.add(firstname_tf, 2, 1);
        
        gridPane.add(surname_lbl, 3, 1);
        gridPane.add(surname_tf, 4, 1);
        
        gridPane.add(id_lbl, 1, 2);
        gridPane.add(id_tf, 2, 2);

        gridPane.add(tel_lbl, 3, 2);
        gridPane.add(tel_tf, 4, 2);
        
        gridPane.add(username_lbl, 1, 3);
        gridPane.add(uname_tf,2, 3);
        
        gridPane.add(register_btn,4,5);
        gridPane.add(back_btn,4,6);
        
//        gridPane.add(login_lbl,1,6);
//        gridPane.add(login_btn,2,6);

        addCustomerPage.setTitle("Registration");

        Scene scene = new Scene(gridPane); 
        addCustomerPage.setScene(scene);
        addCustomerPage.show();
   

    }

    

}
