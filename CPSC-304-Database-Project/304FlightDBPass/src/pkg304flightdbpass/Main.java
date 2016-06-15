/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg304flightdbpass;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class Main extends Application {

    Button btnSearch, btnMyTick, btnReg, btnPassTick, btnMemTick, btnCreate,
            btnSFMM, btnMTMM, btnRegMM, btnSearchFlight, btnSortAirline, btnSortPrice, btnSortDate;
    Scene mmScene, sfScene, mtScene, regScene;
    Stage thestage;
    PasswordField userPassField;
    TextField userNameField, passNumField, originField, destField, dateField;

    @Override
    public void start(Stage primaryStage) {

        //Declare Stage Variable to use in other methods
        thestage = primaryStage;
            
        //MAIN MENU
        
        //Main Menu grid
        GridPane mmGrid = new GridPane();
        mmGrid.setAlignment(Pos.TOP_LEFT);
        mmGrid.setHgap(10);
        mmGrid.setVgap(10);
        mmGrid.setPadding(new Insets(25, 25, 25, 25));

        //Title
        Text mmTitle = new Text("Horizons DB");
        mmTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        mmGrid.add(mmTitle, 0, 0, 2, 1);

        //Instructions
        Text mmInstruct = new Text ("Please select one of the following options:");
        mmGrid.add(mmInstruct, 0, 1, 3, 1);
        
        //Search Flights Button
        btnSearch = new Button("Search Flights");
        btnSearch.setOnAction(e -> ChangeScene(e));
        mmGrid.add(btnSearch, 0, 2);
        
        //My Tickets button
        btnMyTick = new Button("My Tickets");
        btnMyTick.setOnAction(e -> ChangeScene(e));
        mmGrid.add(btnMyTick, 1, 2);
        
        //Register button
        btnReg = new Button("Register");
        btnReg.setOnAction(e -> ChangeScene(e));
        mmGrid.add(btnReg, 2, 2);
        
        //SEARCH FLIGHT
        
        //Search Flight grid
        GridPane sfGrid = new GridPane();
        sfGrid.setAlignment(Pos.TOP_LEFT);
        sfGrid.setHgap(10);
        sfGrid.setVgap(10);
        sfGrid.setPadding(new Insets(25, 25, 25, 25));
        
        //Search Flight header
        Text sfTitle = new Text("Search Flight");
        sfTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        sfGrid.add(sfTitle, 0, 0, 2, 1);

        //My Tickets Instructions
        Text sfInstruct = new Text ("Fill in all fields to find a flight.");
        sfGrid.add(sfInstruct, 0, 1, 2, 1);
        
        //Origin Field
        Label fOrigin = new Label("Origin:");
        sfGrid.add(fOrigin, 0, 2);
        originField = new TextField();
        sfGrid.add(originField, 1, 2);
        
        //Destination Field
        Label fDest = new Label("Destination:");
        sfGrid.add(fDest, 0, 3);
        destField = new TextField();
        sfGrid.add(destField, 1, 3);
        
        //Date Field
        Label fDate = new Label("Date:");
        sfGrid.add(fDate, 0, 4);
        dateField = new TextField();
        sfGrid.add(dateField, 1, 4);
        
        //Find Flights Button
        btnSearchFlight = new Button("Search Flights");
        btnSearchFlight.setOnAction(e -> searchFlight(e, originField, destField, dateField));
        sfGrid.add(btnSearchFlight, 1, 5);
        
        //Cancel button; return to Main Menu
        btnSFMM = new Button("Main Menu");
        btnSFMM.setOnAction(e -> ChangeScene(e));
        sfGrid.add(btnSFMM, 7, 0);
        
        //MY TICKETS
        
        //My Tickets grid
        GridPane mtGrid = new GridPane();
        mtGrid.setAlignment(Pos.TOP_LEFT);
        mtGrid.setHgap(10);
        mtGrid.setVgap(10);
        mtGrid.setPadding(new Insets(25, 25, 25, 25));
        
        //My Tickets header
        Text mtTitle = new Text("My Tickets");
        mtTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        mtGrid.add(mtTitle, 0, 0, 2, 1);

        //My Tickets Instructions
        Text mtInstruct = new Text ("Sign with your passport number, or Horizons DB Member credentials");
        mtGrid.add(mtInstruct, 0, 1, 2, 1);
        
        //Passport Number Field
        Label passNum = new Label("Passport #:");
        mtGrid.add(passNum, 0, 2);
        passNumField = new TextField();
        mtGrid.add(passNumField, 1, 2);
        
        //Passport Find Tickets Button
        btnPassTick = new Button("Find Tickets");
        btnPassTick.setOnAction(e -> findTicks(e, passNumField, null, null));
        mtGrid.add(btnPassTick, 1, 3);
        
        //User Name Field
        Label mtUserName = new Label ("User Name:");
        mtGrid.add(mtUserName, 0, 5);
        userNameField = new TextField();
        mtGrid.add(userNameField, 1, 5);

        //Password Field
        Label mtPassword = new Label("Password:");
        mtGrid.add(mtPassword, 0, 6);
        userPassField = new PasswordField();
        mtGrid.add(userPassField, 1, 6);
        
        //Member Find Tickets Button
        btnMemTick = new Button("Find Tickets");
        btnMemTick.setOnAction(e -> findTicks(e, null, userNameField, userPassField));
        mtGrid.add(btnMemTick, 1, 7);
        
        //Cancel button; return to Main Menu
        btnMTMM = new Button("Main Menu");
        btnMTMM.setOnAction(e -> ChangeScene(e));
        mtGrid.add(btnMTMM, 0, 0);
        
        
        //REGISTER
        
        //Register grid
        GridPane regGrid = new GridPane();
        mmGrid.setAlignment(Pos.TOP_LEFT);
        mmGrid.setHgap(10);
        mmGrid.setVgap(10);
        mmGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text regTitle = new Text("Register");
        regTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        regGrid.add(regTitle, 0, 0, 2, 1);

        //User Name Field
        Label regUserName = new Label("User Name:");
        regGrid.add(regUserName, 0, 1);
        userNameField = new TextField();
        regGrid.add(userNameField, 1, 1);

        //Password Field
        Label regPassword = new Label("Password");
        regGrid.add(regPassword, 0, 2);
        userPassField = new PasswordField();
        regGrid.add(userPassField, 1, 2, 2, 1);
        
        //Create Member button
        btnCreate = new Button("Create");
        btnCreate.setOnAction(e -> Register(e, userNameField, userPassField));
        regGrid.add(btnCreate, 1, 3);
        
        //Cancel button; return to Main Menu
        btnRegMM = new Button("Main Menu");
        btnRegMM.setOnAction(e -> ChangeScene(e));
        regGrid.add(btnRegMM, 7, 0);
        
        //Scene and Stage builder
        mmScene = new Scene(mmGrid, 350, 150);
        sfScene = new Scene(sfGrid, 350, 150);
        mtScene = new Scene(mtGrid, 450, 320);
        regScene = new Scene(regGrid, 300, 150);
        primaryStage.setTitle("Horizons DB");
        primaryStage.setScene(regScene);
        primaryStage.show();
    }

 public void ChangeScene(ActionEvent e)
    {
        if (e.getSource()==btnSearch)
            thestage.setScene(sfScene);
        if (e.getSource()==btnMyTick)
            thestage.setScene(mtScene);
        if (e.getSource()==btnReg)
            thestage.setScene(regScene);
        if (e.getSource()==btnSFMM||e.getSource()==btnMTMM||e.getSource()==btnRegMM)
            thestage.setScene(mmScene);
    }
 public void Register( ActionEvent e, TextField userNameField, PasswordField userPassField) {
        //TODO Verify Name and Password, then create DB entry
    
    }
 public void findTicks( ActionEvent e, TextField passNumField, TextField userNameField, PasswordField userPassField){
        //TODO Find tickets passed on Passport or Member login
        
        if (e.getSource()==btnPassTick) {
            //TODO find tickets associated with passport
        } else {
            //TODO verify login, and then find tickets associated with member
        }
 }       
 public void searchFlight( ActionEvent e, TextField originField, TextField destField, TextField dateField) {
     
     //TODO 
 }     
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
