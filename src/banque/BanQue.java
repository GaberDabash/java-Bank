
package banque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BanQue extends Application {
    
        ObservableList<Client> client = FXCollections.observableArrayList();
        
        public void connectTODB(){
            client.clear();
            try 
                {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:bank.sqlite");
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM customer");
            
            // loop through the result set
            while (rs.next()) { 
                
                client.add(
                        new Client(
                                rs.getInt("id"), 
                                rs.getString("name"), 
                                rs.getDouble("balance")

                        )
                );

            }
            
            conn.close();
            stmt.close();
            rs.close();
        }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    
    @Override
    public void start(Stage primaryStage) {
        connectTODB();
        TableView<Client> myTable  ;
        primaryStage.setTitle("Bank or MaBankesh? hahahahahahahahah");
        
        TableColumn<Client, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id")); 
        
        TableColumn<Client, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); 
        
        TableColumn<Client, String> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setMinWidth(120);
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        
        myTable = new TableView<>();
        myTable.setItems(client);
        myTable.getColumns().addAll(idColumn,nameColumn,balanceColumn);
        
        Button addButton = new Button("Add");
        addButton.setMinWidth(100);
        Button removeButton = new Button("Remove");
        removeButton.setMinWidth(100);
        Button depositButton = new Button("Deposit");
        depositButton.setMinWidth(100);
        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setMinWidth(100);
        
        addButton.setOnAction(e->{
            AddClient addClient = new AddClient();
            addClient.display();
            connectTODB();
            System.out.println("DB Updated");
        });
        
        removeButton.setOnAction(e->{
            RemoveClient removeClient = new RemoveClient();
            removeClient.display();
            connectTODB();
            System.out.println("DB Updated");
        });
        
        depositButton.setOnAction(e->{
            Deposite deposite = new Deposite();
            deposite.display();
            connectTODB();
            System.out.println("DB Updated");
        });
        
        withdrawButton.setOnAction(e->{
            Withdraw withdraw = new Withdraw();
            withdraw.display();
            connectTODB();
            System.out.println("DB Updated");
        });
        
        
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(15);
        hBox.getChildren().addAll(addButton,removeButton,depositButton,withdrawButton);
        hBox.setPadding(new Insets(10));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, myTable);
        Scene scene = new Scene(vBox,500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
