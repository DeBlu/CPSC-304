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
import javafx.scene.layout.GridPane;
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
            btnSFMM, btnMTMM, btnRegMM, btnSRMM, btnFIMM, btnSSMM, btnTPMM,
            btnSearchFlight, btnSortAirline, btnSortPrice, btnSortDate,btnBusiness,
            btnFirst, btnEcon,btnEconPlus,btnBuy, btnTPPass, btnTPMem, btnMoreInfo;
    Scene mmScene, sfScene, srScene,  mtScene, regScene, fiScene, ssScene, tpScene;
    GridPane srGrid, fiGrid, ssGrid;
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
        btnSearch.setOnAction(e -> changeScene(e));
        mmGrid.add(btnSearch, 0, 2);
        
        //My Tickets button
        btnMyTick = new Button("My Tickets");
        btnMyTick.setOnAction(e -> changeScene(e));
        mmGrid.add(btnMyTick, 1, 2);
        
        //Register button
        btnReg = new Button("Register");
        btnReg.setOnAction(e -> changeScene(e));
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
        btnSFMM.setOnAction(e -> changeScene(e));
        sfGrid.add(btnSFMM, 4, 0);
        
        
        //SEARCH RESULTS
        
        //Search results Grid
        srGrid = new GridPane();
        srGrid.setAlignment(Pos.TOP_LEFT);
        srGrid.setHgap(10);
        srGrid.setVgap(10);
        srGrid.setPadding(new Insets(25, 25, 25, 25));
        
        //Search Result Header
        Text srTitle = new Text("Search Flight");
        srTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        srGrid.add(srTitle, 0, 0, 2, 1);

        //Search Result Instructions
        Text srInstruct = new Text ("Select a Flight below");
        srGrid.add(srInstruct, 0, 1, 2, 1);
        
        //Search Result Sort Instructions
        Text srSort = new Text ("Sort by:");
        srGrid.add(srSort, 0, 2);
        
        //Sort by Airline
        btnSortAirline = new Button("Airline");
        btnSortAirline.setOnAction(e -> Sort(e));
        srGrid.add(btnSortAirline, 1, 2);
        
        //Sort by Price
        btnSortPrice = new Button("Price");
        btnSortPrice.setOnAction(e -> Sort(e));
        srGrid.add(btnSortPrice, 2, 2);
        
        //Sort by Date
        btnSortDate = new Button("Date");
        btnSortDate.setOnAction(e -> Sort(e));
        srGrid.add(btnSortDate, 3, 2);
        
        //TODO DISPLAY TABLE
        
        
        //Select Flight Button
        btnMoreInfo = new Button("More Info");
        btnMoreInfo.setOnAction(e -> selectFlight(e));
        srGrid.add(btnMoreInfo, 1, 4);
        
        //Cancel button; return to Main Menu
        btnSRMM = new Button("Main Menu");
        btnSRMM.setOnAction(e -> changeScene(e));
        srGrid.add(btnSRMM, 4, 0);
        
        
        //FLIGHT INFO
        
        //Flight Info Grid
        fiGrid = new GridPane();
        fiGrid.setAlignment(Pos.TOP_LEFT);
        fiGrid.setHgap(10);
        fiGrid.setVgap(10);
        fiGrid.setPadding(new Insets(25, 25, 25, 25));
        
        //Flight Info Header
        Text fiTitle = new Text("Flight Info");
        fiTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        fiGrid.add(fiTitle, 0, 0, 2, 1);

        //Flight Info Instructions
        Text fiInstruct = new Text ("Select a Seat Option");
        fiGrid.add(fiInstruct, 0, 1, 2, 1);
        
        //Business Seat
        btnBusiness = new Button("Business");
        btnBusiness.setOnAction(e -> filterSeat(e));
        fiGrid.add(btnBusiness, 4, 2);
        
        //First Class
        btnFirst = new Button("First Class");
        btnFirst.setOnAction(e -> filterSeat(e));
        fiGrid.add(btnFirst, 4, 3);
        
        //Economy Plus
        btnEconPlus = new Button("Economy Plus");
        btnEconPlus.setOnAction(e -> filterSeat(e));
        fiGrid.add(btnEconPlus, 4, 4);
        
        //Ecoomy
        btnEcon = new Button("Economy");
        btnEcon.setOnAction(e -> filterSeat(e));
        fiGrid.add(btnEcon, 4, 5);
        
        //Cancel button; return to Main Menu
        btnFIMM = new Button("Main Menu");
        btnFIMM.setOnAction(e -> changeScene(e));
        fiGrid.add(btnFIMM, 4, 0);
       
        
        //SEAT SELECT
        
        //Seat Select Grid
        ssGrid = new GridPane();
        ssGrid.setAlignment(Pos.TOP_LEFT);
        ssGrid.setHgap(10);
        ssGrid.setVgap(10);
        ssGrid.setPadding(new Insets(25, 25, 25, 25));
        
        //Flight Info Header
        Text ssTitle = new Text("Seat Select");
        ssTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        ssGrid.add(ssTitle, 0, 0, 2, 1);

        //Flight Info Instructions
        Text ssInstruct = new Text ("Select a Seat");
        ssGrid.add(ssInstruct, 0, 1, 2, 1);
        
        //Buy Ticket
        btnBuy = new Button("Buy");
        btnBuy.setOnAction(e -> saveTicket(e));
        ssGrid.add(btnBuy, 2, 2);
        
        //Cancel button; return to Main Menu
        btnSSMM = new Button("Main Menu");
        btnSSMM.setOnAction(e -> changeScene(e));
        ssGrid.add(btnSSMM, 4, 0);
        
        
        //TICKET PURCHASE
        
        //Ticket Purchase grid
        GridPane tpGrid = new GridPane();
        tpGrid.setAlignment(Pos.TOP_LEFT);
        tpGrid.setHgap(10);
        tpGrid.setVgap(10);
        tpGrid.setPadding(new Insets(25, 25, 25, 25));
        
        //Ticket Purchase header
        Text tpTitle = new Text("Ticket Purchase");
        tpTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        tpGrid.add(tpTitle, 0, 0, 2, 1);

        //Ticket Purchase Instructions
        Text tpInstruct = new Text ("Buy with your passport number, or Horizons DB Member credentials");
        tpGrid.add(tpInstruct, 0, 1, 2, 1);
        
        //Passport Number Field
        Label tpPassNum = new Label("Passport #:");
        tpGrid.add(tpPassNum, 0, 2);
        passNumField = new TextField();
        tpGrid.add(passNumField, 1, 2);
        
        //Passport Buy Ticket Button
        btnTPPass = new Button("Buy Ticket");
        btnTPPass.setOnAction(e -> buyTicket(e, passNumField, null, null));
        tpGrid.add(btnTPPass, 1, 3);
        
        //User Name Field
        Label tpUserName = new Label ("User Name:");
        tpGrid.add(tpUserName, 0, 5);
        userNameField = new TextField();
        tpGrid.add(userNameField, 1, 5);

        //Password Field
        Label tpPassword = new Label("Password:");
        tpGrid.add(tpPassword, 0, 6);
        userPassField = new PasswordField();
        tpGrid.add(userPassField, 1, 6);
        
        //Member Buy Ticket Button
        btnTPMem = new Button("Buy Ticket");
        btnTPMem.setOnAction(e -> buyTicket(e, null, userNameField, userPassField));
        tpGrid.add(btnTPMem, 1, 7);
        
        //Cancel button; return to Main Menu
        btnTPMM = new Button("Main Menu");
        btnTPMM.setOnAction(e -> changeScene(e));
        tpGrid.add(btnTPMM, 4, 0);
        
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
        btnMTMM.setOnAction(e -> changeScene(e));
        mtGrid.add(btnMTMM, 4, 0);
        
        
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
        btnRegMM.setOnAction(e -> changeScene(e));
        regGrid.add(btnRegMM, 7, 0);
        
        //Scene and Stage builder
        mmScene = new Scene(mmGrid, 350, 150);
        sfScene = new Scene(sfGrid, 450, 320);
        srScene = new Scene (srGrid, 450, 320);
        fiScene = new Scene (fiGrid, 450, 320);
        ssScene = new Scene (ssGrid, 450, 320);
        tpScene = new Scene (tpGrid, 450, 320);
        mtScene = new Scene(mtGrid, 450, 320);
        regScene = new Scene(regGrid, 450, 320);
 
        
        
        primaryStage.setTitle("Horizons DB");
        primaryStage.setScene(mmScene);
        primaryStage.show();
    }

 public void changeScene(ActionEvent e)
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
     thestage.setScene(srScene);
 }     
 
 public void Sort (ActionEvent e){
     if (e.getSource()==btnSortAirline){
         //TODO Sort by Airline
     } else if (e.getSource()==btnSortPrice){
         //TODO Sort by Price
     } else {
         //TODO Sort by Date
     }
     thestage.setScene(srScene);
 }
 
 public void selectFlight (ActionEvent e){
     //TODO Select the indicated flight, and return seat info
     
     thestage.setScene(fiScene);
 }
 
  public void filterSeat (ActionEvent e){
     if (e.getSource()==btnBusiness){
         //TODO Select Business seats
     } else if (e.getSource()==btnFirst){
         //TODO Select Select First Class
     } else if (e.getSource()==btnEconPlus){
         //TODO Select Economy Plus
     } else { 
         //TODO Select Economy
    }
     thestage.setScene(ssScene);
  }
  
  public void saveTicket (ActionEvent e) {
      //TODO Save Flight Info and Seat
      thestage.setScene(tpScene);
  }
  
  public void buyTicket( ActionEvent e, TextField passNumField, TextField userNameField, PasswordField userPassField){
        //TODO Buy Ticket and Store with Pass num or Member ID
        
        if (e.getSource()==btnPassTick) {
            //TODO buy ticket and associate with passport
        } else {
            //TODO verify login, and then buy ticket as member
        }
        thestage.setScene(mtScene);
 }       
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
