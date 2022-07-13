/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalApp;
import java.sql.*;
import java.sql.DriverManager;
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

/**
 *
 * @author Wayne
 */
public class logInPage extends Application {
    static Stage LogInGuiStage = new Stage();
    
    @Override
    public void start(Stage LogInGuiStage) {
         Text main_lbl = new Text("Rent-Rent Rentals");
        main_lbl.setFont(Font.font("Comic sans ms", FontPosture.REGULAR,60));
        Text name_lbl = new Text("User Name");
        Text pass_lbl = new Text("Password");

        TextField uname_tf = new TextField();
        PasswordField pass = new PasswordField(); 

        Button login_btn = new Button("Login");
        login_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String username = uname_tf.getText();
                String password = pass.getText();

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/carrental?", "root", "");
                    Statement st = con.createStatement();

                    String query = "SELECT * from carrental.users where username = '" + username + "' and pass = '" + password + "'";
                    ResultSet rs = st.executeQuery(query);
                    if (rs.next()) {
                        Alert al = new Alert(Alert.AlertType.INFORMATION);
                        al.setContentText("Log In Successful");
                        al.show();
                        Dashboard a = new Dashboard();
                        a.start(a.page2);
                        LogInGuiStage.close();
                    } else {
                        Alert al = new Alert(Alert.AlertType.ERROR);
                        al.setContentText("Incorrect Username / Password");
                        al.show();
                    }
                    con.close();
                } catch (Exception ee) {
                    System.out.println(ee);
                    System.out.println("Connection Error");
                }

            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 400);
        gridPane.setVgap(20);
        gridPane.setHgap(40);
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.add(main_lbl,1,0,3,1);
        gridPane.add(name_lbl, 1, 1);
        gridPane.add(pass_lbl, 1, 2);
        gridPane.add(login_btn, 1, 3);

        gridPane.add(uname_tf, 2, 1);
        gridPane.add(pass, 2, 2);

        LogInGuiStage.setTitle("Login");

        Scene scene = new Scene(gridPane); 
       LogInGuiStage.setScene(scene);
        LogInGuiStage.show();
   

    }
    public static void main(String[] args) {
        launch(args);
    } 

}

