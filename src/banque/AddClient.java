
package banque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddClient {
    void display(){
        Stage stage = new Stage();
       
        Label idLabel = new Label("Id");
        Label nameLabel = new Label("Name");
        Label balanceLabel = new Label("Balance");
        
        TextField idTextField = new TextField();
        TextField nameTextField = new TextField();
        TextField balaceTextField = new TextField();
        
        Button addButton = new Button("add new");
        Button cancelButton = new Button("cancel");
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(25);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(nameLabel, 0, 2);
        gridPane.add(balanceLabel, 0, 3);
        
        gridPane.add(idTextField, 1, 1);
        gridPane.add(nameTextField, 1, 2);
        gridPane.add(balaceTextField, 1, 3);
        
        gridPane.add(addButton, 0, 5);
        gridPane.add(cancelButton, 1, 5);
        gridPane.setPadding(new Insets(15));
        Scene scene = new Scene(gridPane,300,200);
        stage.setTitle("Add ya Negm");
        stage.setScene(scene);
        
        addButton.setOnAction(e->{
        sendToDB(
                        idTextField.getText(),
                        nameTextField.getText(),
                        balaceTextField.getText()
                );
                
                stage.close();
        });
        
        cancelButton.setOnAction(e->{
           stage.close();
        });
        
        stage.showAndWait();
        
        
        
    }
    void sendToDB(String id , String name , String balance){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:bank.sqlite");
            Statement stmt  = conn.createStatement();
            stmt.executeQuery("INSERT INTO customer VALUES ("+id+",'"+name+"',"+balance+")");
            conn.close();
            stmt.close();
        }
        catch (SQLException ex ){
            System.out.println("wrong values ya Negm");
        }
            
                 }
}
