
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


public class RemoveClient {
    void display(){
    Stage stage= new Stage();
    Label idLabel = new Label("remove items using id");
    TextField idTextField = new TextField("type id here ");
    
    Button removeButton = new Button("Remove");
    Button cancelButton = new Button("Cancel");
    
    removeButton.setOnAction(e->{
        delete(idTextField.getText());
        stage.close();
    });
    
    cancelButton.setOnAction(e->{
    stage.close();
    });
    
    GridPane gridPane = new GridPane();
    
    gridPane.setPadding(new Insets(15));
    gridPane.setVgap(10);
    gridPane.setHgap(15);
    gridPane.add(new Label("attention \n once you remove a client ,"
            + " you will not be able to get it back"), 1, 0);
    gridPane.add(idLabel, 1, 1);
    gridPane.add(idTextField, 1, 2);
    gridPane.add(removeButton, 1, 3);
    gridPane.add(cancelButton, 2, 3);
    Scene scene = new Scene(gridPane, 520, 200);
    stage.setTitle("I'm happy because you finally took the decission :) ");
    stage.setScene(scene);
    stage.showAndWait();
}
    
    void delete(String id ){
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:bank.sqlite");
            Statement stmt  = conn.createStatement();
            stmt.executeQuery("delete from customer where id="+id);
            conn.close();
            stmt.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }    
    }
}