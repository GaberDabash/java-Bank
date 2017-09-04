
package banque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Withdraw  {
    void display(){
    Stage stage= new Stage();
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(15));
    gridPane.setVgap(10);
    gridPane.setHgap(15);
    gridPane.add(new Label("are you greedy to take money!!? "), 1, 0);
    Label idLabel = new Label("ID");
    Label moneyLabel = new Label("money needed");
    TextField idTextField = new TextField(); 
    TextField moneyTextField = new TextField(); 
    
    Button depositeButton = new Button("im sure");
    Button sorryButton = new Button("nooo keep my money");
    
    gridPane.add(idLabel, 1, 1);
    gridPane.add(moneyLabel, 1, 2);
    gridPane.add(idTextField, 2, 1);
    gridPane.add(moneyTextField, 2, 2);
    gridPane.add(depositeButton, 1, 3);
    gridPane.add(sorryButton, 2, 3);
    
    depositeButton.setOnAction(e->{
        withdraw(idTextField.getText(), moneyTextField.getText());
        stage.close();
    });
    sorryButton.setOnAction(e->{
        stage.close();
    });
    
    Scene scene = new Scene(gridPane, 520, 200);
    stage.setTitle("wow !! , It's playing :) ");
    stage.setScene(scene);
    stage.showAndWait();
  }
   
    
    void withdraw(String id , String money){
    try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:bank.sqlite");
           Statement stmt  = conn.createStatement();
           stmt.executeQuery("update  customer set balance= balance -"+money+" where id="+id);
            conn.close();
            stmt.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }    
    }
    
    
}
