/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg304flighdbdebug;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class Main extends Application {
	Passenger p = new Passenger();
	Employee emp = new Employee();
    Button btnSearch, btnMyTick, btnReg, btnPassTick, btnMemTick, btnCreate,
            btnSFMM, btnMTMM, btnRegMM, btnTPMM,btnCIMM,btnCTMM, btnDAMM,btnCSMM, 
            btnSearchFlight, btnSortAirline, btnSortPrice, btnSortDate,btnBusiness,
            btnFirst, btnEcon,btnEconPlus,btnBuy, btnTPPass, btnTPMem, btnMoreInfo,
            btnMTRMM, btnChangeInfo, btnUpdateInfo, btnCI, btnCT, btnCS, btnDA, btnTP,
            btnChange, btnCancel, btnDelete, btnFS, btnFSMM, btnFindSeat, btnSCT, btnSET, btnPHOBIA;
    Scene mmScene, sfScene, srScene,  mtScene, regScene, fiScene, ssScene, tpScene, mtrScene, ciScene,
            csScene, ctScene, daScene, fsScene, setScene, sctScene;
    GridPane srGrid, fiGrid, ssGrid;
    Stage thestage;
    PasswordField userPassField;
    ArrayList<HashMap<String, Object>> flightResult;
    TextField tpPassField, tpNameField, tpPassport, mtPassField, mtNameField, mtPassport,
            ciPassField, ciEmailField, ciNameField, regNameField, regEmailField,
            regPassport, originField, destField, dateField, oldSeat, 
            newSeat, cancelNum, daNameField, daPassport, daEmailField, tpFNameField,
            tpLNameField, tpFnoField, tpSeatField, tpFnoMemField, tpSeatMemField,
            fsFnoField;
    
    @Override
    public void start(Stage primaryStage) {

        //Declare Stage Variable to use in other methods
        thestage = primaryStage;
            
        //MAIN MENU
        
        //Main Menu grid
        GridPane mmGrid = new GridPane();
        mmGrid.setAlignment(Pos.CENTER);
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
        btnSearch = new Button("Searh Flights");
        btnSearch.setOnAction(e -> changeScene(e));
        mmGrid.add(btnSearch, 0, 2);
        /*
        //My Tickets button
        btnMyTick = new Button("My Tickets");
        btnMyTick.setOnAction(e -> changeScene(e));
        mmGrid.add(btnMyTick, 2, 2);
        */
        
        //Search Seats button
        btnFS = new Button("Find Seat");
        btnFS.setOnAction(e -> changeScene(e));
        mmGrid.add(btnFS, 1, 2);
        
        //Register button
        btnReg = new Button("Register");
        btnReg.setOnAction(e -> changeScene(e));
        mmGrid.add(btnReg, 2, 2);
        
        //Change Info button
        btnCI = new Button("Change Info");
        btnCI.setOnAction(e -> changeScene(e));
        mmGrid.add(btnCI, 0, 3);
        
        //Delete Account
        btnDA = new Button("Delete Account");
        btnDA.setOnAction(e -> changeScene(e));
        mmGrid.add(btnDA, 1, 3);
        
        //Purchase Ticket
        btnTP = new Button("Purchase Ticket");
        btnTP.setOnAction(e -> changeScene(e));
        mmGrid.add(btnTP, 2, 3);
        
        //Sort By Min Price
        btnSCT = new Button("Show Cheapest Tickets");
        btnSCT.setOnAction(e -> cheapestTickets());
        mmGrid.add(btnSCT, 3, 3);
        
        //Sort By Max Price
        btnSET = new Button("Show Most Expensive Tickets");
        btnSET.setOnAction(e -> expensiveTickets());
        mmGrid.add(btnSET, 3, 2);
        
        //Phobia Button
        btnPHOBIA = new Button("***For Smallplanephobia Peeps");
        btnPHOBIA.setOnAction(e -> phobia());
        mmGrid.add(btnPHOBIA, 4, 2);
       
       //Instructions
        Text mmInstruct2 = new Text ("Employee Functions");
        mmGrid.add(mmInstruct2, 0, 5, 3, 1);
        
        //Change Seat
        btnCS = new Button("Change Seat");
        btnCS.setOnAction(e -> changeScene(e));
        mmGrid.add(btnCS, 0, 6);
        
        //Cancel Ticket
        btnCT = new Button("Cancel Ticket");
        btnCT.setOnAction(e -> changeScene(e));
        mmGrid.add(btnCT, 1, 6);

        //SEARCH FLIGHT
        
        //Search Flight grid
        GridPane sfGrid = new GridPane();
        sfGrid.setAlignment(Pos.CENTER);
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
        dateField.setPromptText("YYYY-MM-DD");
        sfGrid.add(dateField, 1, 4);
        
        //Find Flights Button
        btnSearchFlight = new Button("Search Flights");
        btnSearchFlight.setOnAction(e -> searchFlight(e, originField, destField, dateField));
        sfGrid.add(btnSearchFlight, 1, 5);
        
        //Cancel button; return to Main Menu
        btnSFMM = new Button("Main Menu");
        btnSFMM.setOnAction(e -> changeScene(e));
        sfGrid.add(btnSFMM, 4, 0);
        
        
        //TICKET PURCHASE
        
        //Ticket Purchase grid
        GridPane tpGrid = new GridPane();
        tpGrid.setAlignment(Pos.CENTER);
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
        tpPassField = new TextField();
        tpGrid.add(tpPassField, 1, 2);
        
        //Passport Number Field
        Label tpFLabel = new Label("First Name:");
        tpGrid.add(tpFLabel, 0, 3);
        tpFNameField = new TextField();
        tpGrid.add(tpFNameField, 1, 3);
        
        //Passport Number Field
        Label tpLLabel = new Label("Last Name:");
        tpGrid.add(tpLLabel, 0, 4);
        tpLNameField = new TextField();
        tpGrid.add(tpLNameField, 1, 4);
        
        
        //Flight Number Field
        Label tpFnoLabel = new Label("Flight #:");
        tpGrid.add(tpFnoLabel, 0, 5);
        tpFnoField = new TextField();
        tpGrid.add(tpFnoField, 1, 5);
        
        //Seat# Field
        Label tpSeatLabel = new Label("Seat #:");
        tpGrid.add(tpSeatLabel, 0, 6);
        tpSeatField = new TextField();
        tpGrid.add(tpSeatField, 1, 6);
       
        //Passport Buy Ticket Button
        btnTPPass = new Button("Buy Ticket");
        btnTPPass.setOnAction(e -> buyTicket(e, tpPassField, tpFNameField, 
                tpLNameField, tpFnoField,tpSeatField));
        tpGrid.add(btnTPPass, 1, 7);
        
        /*
        //User Name Field
        Label tpUserName = new Label ("User Name:");
        tpGrid.add(tpUserName, 0, 8);
        tpNameField = new TextField();
        tpGrid.add(tpNameField, 1, 8);

        //Password Field
        Label tpPassword = new Label("Passport #:");
        tpGrid.add(tpPassword, 0, 9);
        tpPassport = new TextField();
        tpGrid.add(tpPassport, 1, 9);
        
        //Flight Number Field
        Label tpFnoLabelMem = new Label("Flight #:");
        tpGrid.add(tpFnoLabelMem, 0, 10);
        tpFnoMemField = new TextField();
        tpGrid.add(tpFnoMemField, 1, 10);
        
        //Seat# Field
        Label tpSeatMemLabel = new Label("Seat #:");
        tpGrid.add(tpSeatMemLabel, 0, 11);
        tpSeatMemField = new TextField();
        tpGrid.add(tpSeatMemField, 1, 11);
       
        
        //Member Buy Ticket Button
        btnTPMem = new Button("Buy Ticket");
        btnTPMem.setOnAction(e -> buyTicketMem(e, tpNameField, tpPassport, tpFnoMemField,
                tpSeatMemField));
        tpGrid.add(btnTPMem, 1, 12);
        
        */
        
        //Cancel button; return to Main Menu
        btnTPMM = new Button("Main Menu");
        btnTPMM.setOnAction(e -> changeScene(e));
        tpGrid.add(btnTPMM, 4, 0);
        
        /*
        //MY TICKETS
        
        //My Tickets grid
        GridPane mtGrid = new GridPane();
        mtGrid.setAlignment(Pos.CENTER);
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
        mtPassField = new TextField();
        mtGrid.add(mtPassField, 1, 2);
        
        //Passport Find Tickets Button
        btnPassTick = new Button("Find Tickets");
        btnPassTick.setOnAction(e -> findTicks(e, mtPassField, null, null));
        mtGrid.add(btnPassTick, 1, 3);
        
        //User Name Field
        Label mtUserName = new Label ("User Name:");
        mtGrid.add(mtUserName, 0, 5);
        mtNameField = new TextField();
        mtGrid.add(mtNameField, 1, 5);

        //Password Field
        Label mtPassword = new Label("Passport #:");
        mtGrid.add(mtPassword, 0, 6);
        mtPassport = new TextField();
        mtGrid.add(mtPassport, 1, 6);
        
        //Member Find Tickets Button
        btnMemTick = new Button("Find Tickets");
        btnMemTick.setOnAction(e -> findTicks(e, null, mtNameField, mtPassport));
        mtGrid.add(btnMemTick, 1, 7);
        
        //Cancel button; return to Main Menu
        btnMTMM = new Button("Main Menu");
        btnMTMM.setOnAction(e -> changeScene(e));
        mtGrid.add(btnMTMM, 4, 0);
        
        */
        //CHANGE INFO
        
        //Change Info grid
        GridPane ciGrid = new GridPane();
        ciGrid.setAlignment(Pos.CENTER);
        ciGrid.setHgap(10);
        ciGrid.setVgap(10);
        ciGrid.setPadding(new Insets(25, 25, 25, 25));
        
        //Change Info header
        Text ciTitle = new Text("Change Info");
        ciTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        ciGrid.add(ciTitle, 0, 0, 2, 1);

        
        //Change Info Instructions
        Text ciInstruct1 = new Text ("Enter Passport #");
        ciGrid.add(ciInstruct1, 0, 1, 2, 1);
            
        //Passport Number Field
        Label ciPassNum = new Label("Passport #:");
        ciGrid.add(ciPassNum, 0, 2);
        ciPassField = new TextField();
        ciGrid.add(ciPassField, 1, 2);
        
        //Change Info Instructions 2
        Text ciInstruct2 = new Text ("Enter info you want to change");
        ciGrid.add(ciInstruct2, 0, 3, 2, 1);
        
        //User Name Field
        Label ciUserName = new Label ("User Name:");
        ciGrid.add(ciUserName, 0, 4);
        ciNameField = new TextField();
        ciGrid.add(ciNameField, 1, 4);

        //Email Field
        Label ciEmail = new Label("Email:");
        ciGrid.add(ciEmail, 0, 5);
        ciEmailField = new TextField();
        ciGrid.add(ciEmailField, 1, 5);
       
        //Update button; return to Main Menu
        btnUpdateInfo = new Button("Update");
        btnUpdateInfo.setOnAction(e -> updateInfo(e, ciPassField, ciNameField, ciEmailField));
        ciGrid.add(btnUpdateInfo, 1, 6);
        
        //Cancel button; return to Main Menu
        btnCIMM = new Button("Main Menu");
        btnCIMM.setOnAction(e -> changeScene(e));
        ciGrid.add(btnCIMM, 4, 0);
        
        
        //REGISTER
       
        
        //Register grid
        GridPane regGrid = new GridPane();
        regGrid.setAlignment(Pos.CENTER);
        regGrid.setHgap(10);
        regGrid.setVgap(10);
        regGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text regTitle = new Text("Register");
        regTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        regGrid.add(regTitle, 0, 0, 2, 1);

        //User Name Field
        Label regUserName = new Label("User Name:");
        regGrid.add(regUserName, 0, 1);
        regNameField = new TextField();
        regGrid.add(regNameField, 1, 1);

        //Email Field
        Label regEmail = new Label("Email :");
        regGrid.add(regEmail, 0, 2);
        regEmailField = new TextField();
        regGrid.add(regEmailField, 1, 2, 2, 1);
         
        //Password Field
        Label regPassword = new Label("Passport #:");
        regGrid.add(regPassword, 0, 3);
        regPassport = new TextField();
        regGrid.add(regPassport, 1, 3, 2, 1);
        
        //Create Member button
        btnCreate = new Button("Create");
        btnCreate.setOnAction(e -> Register(e, regNameField, regEmailField, regPassport));
        regGrid.add(btnCreate, 1, 4);
        
        //Cancel button; return to Main Menu
        btnRegMM = new Button("Main Menu");
        btnRegMM.setOnAction(e -> changeScene(e));
        regGrid.add(btnRegMM, 7, 0);
        
        //FIND SEAT
                
        //Find Seat grid
        GridPane fsGrid = new GridPane();
        fsGrid.setAlignment(Pos.CENTER);
        fsGrid.setHgap(10);
        fsGrid.setVgap(10);
        fsGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text fsTitle = new Text("Find Seat");
        fsTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        fsGrid.add(fsTitle, 0, 0, 2, 1);

        //Original Seat Number
        Label fsFNo = new Label("Flight #:");
        fsGrid.add(fsFNo, 0, 1);
        fsFnoField= new TextField();
        fsGrid.add(fsFnoField, 1, 1);
        
        //Find Seat Button
        btnFindSeat = new Button("Find Seat");
        btnFindSeat.setOnAction(e -> findSeat(e, fsFnoField));
        fsGrid.add(btnFindSeat, 1, 2);
        
        //Cancel button; return to Main Menu
        btnFSMM = new Button("Main Menu");
        btnFSMM.setOnAction(e -> changeScene(e));
        fsGrid.add(btnFSMM, 7, 0);
        
        //CHANGE SEAT
                
        //Change Seat grid
        GridPane csGrid = new GridPane();
        csGrid.setAlignment(Pos.CENTER);
        csGrid.setHgap(10);
        csGrid.setVgap(10);
        csGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text csTitle = new Text("Change Seat");
        csTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        csGrid.add(csTitle, 0, 0, 2, 1);

        //Original Seat Number
        Label csOldSeat = new Label("New Seat #:");
        csGrid.add(csOldSeat, 0, 1);
        oldSeat = new TextField();
        csGrid.add(oldSeat, 1, 1);

        //New Seat Number
        Label csNewSeat = new Label("Old Seat #:");
        csGrid.add(csNewSeat, 0, 2);
        newSeat = new TextField();
        csGrid.add(newSeat, 1, 2, 2, 1);
        
        //Change Seat button
        btnChange = new Button("Change");
        btnChange.setOnAction(e -> changeSeat(e, newSeat, oldSeat));
        csGrid.add(btnChange, 1, 4);
        
        //Cancel button; return to Main Menu
        btnCSMM = new Button("Main Menu");
        btnCSMM.setOnAction(e -> changeScene(e));
        csGrid.add(btnCSMM, 7, 0);
        
        //CANCEL TICKET
        
        //Change Seat grid
        GridPane ctGrid = new GridPane();
        ctGrid.setAlignment(Pos.CENTER);
        ctGrid.setHgap(10);
        ctGrid.setVgap(10);
        ctGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text ctTitle = new Text("Cancel Ticket");
        ctTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        ctGrid.add(ctTitle, 0, 0, 2, 1);

        //Ticket Number
        Label ctTickNum = new Label("Ticket #:");
        ctGrid.add(ctTickNum, 0, 1);
        cancelNum = new TextField();
        ctGrid.add(cancelNum, 1, 1);

        //Create Member button
        btnCancel = new Button("Cancel Ticket");
        btnCancel.setOnAction(e -> cancelTicket(e, cancelNum));
        ctGrid.add(btnCancel, 1, 4);
        
        //Cancel button; return to Main Menu
        btnCTMM = new Button("Main Menu");
        btnCTMM.setOnAction(e -> changeScene(e));
        ctGrid.add(btnCTMM, 7, 0);
        
        //DELETE ACCOUNT
        
        // username, user email, passportnum
        
        //Change Seat grid
        GridPane daGrid = new GridPane();
        daGrid.setAlignment(Pos.CENTER);
        daGrid.setHgap(10);
        daGrid.setVgap(10);
        daGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text daTitle = new Text("Delete Account");
        daTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        daGrid.add(daTitle, 0, 0, 2, 1);

        //User Name Field
        Label daUserName = new Label("User Name:");
        daGrid.add(daUserName, 0, 1);
        daNameField = new TextField();
        daGrid.add(daNameField, 1, 1);

        //Email Field
        Label daEmail = new Label("Email :");
        daGrid.add(daEmail, 0, 2);
        daEmailField = new TextField();
        daGrid.add(daEmailField, 1, 2, 2, 1);
                
        //Password Field
        Label daPassword = new Label("Passport #:");
        daGrid.add(daPassword, 0, 3);
        daPassport = new TextField();
        daGrid.add(daPassport, 1, 3, 2, 1);

        //Delete Account button
        btnDelete = new Button("Delete Account");
        btnDelete.setOnAction(e -> deleteAccount(e, daNameField, daEmailField, daPassport));
        daGrid.add(btnDelete, 1, 4);
        
        //Cancel button; return to Main Menu
        btnDAMM = new Button("Main Menu");
        btnDAMM.setOnAction(e -> changeScene(e));
        daGrid.add(btnDAMM, 7, 0);
        
        //Scene and Stage builder
        mmScene = new Scene(mmGrid, 450, 320);
        sfScene = new Scene(sfGrid, 450, 320);
        tpScene = new Scene (tpGrid, 450, 500);
        //mtScene = new Scene(mtGrid, 450, 320);
        ciScene = new Scene (ciGrid, 450, 320);
        regScene = new Scene(regGrid, 450, 320);
        csScene = new Scene (csGrid, 450, 320);
        ctScene = new Scene (ctGrid, 450, 320);
        daScene = new Scene (daGrid, 450, 320);
        fsScene = new Scene (fsGrid, 450, 320);
 
        
        
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
        if (e.getSource()==btnCI)
            thestage.setScene(ciScene);
        if (e.getSource()==btnReg)
            thestage.setScene(regScene);
        if (e.getSource()==btnCS)
            thestage.setScene(csScene);
        if (e.getSource()==btnCT)
            thestage.setScene(ctScene);
        if (e.getSource()==btnDA)
            thestage.setScene(daScene);
        if (e.getSource()==btnTP)
            thestage.setScene(tpScene);
        if (e.getSource()==btnFS)
            thestage.setScene(fsScene);
        if (e.getSource()==btnSCT)
        	thestage.setScene(sctScene);
        if (e.getSource()==btnSET)
        	thestage.setScene(setScene);
        if (e.getSource()==btnSFMM||e.getSource()==btnMTMM||e.getSource()==btnRegMM
                ||e.getSource()==btnDAMM||e.getSource()==btnCSMM||e.getSource()==btnCTMM
                ||e.getSource()==btnTPMM||e.getSource()==btnRegMM||e.getSource()==btnCIMM
                ||e.getSource()==btnFSMM)
            thestage.setScene(mmScene);
    }
	public void expensiveTickets() {
		Alert alert = new Alert(AlertType.INFORMATION);
 		alert.setTitle("WOW you must be rich!");
 		alert.setHeaderText("Click 'Show details' underneath.");
 		String mainDialog = "";

 		ArrayList<HashMap<String,Object>> result = p.searchByMaxPrice();
	    if (result.isEmpty()) {
	    	mainDialog = "No results found.";
	    } else {
	    	for (int i = 0; i < result.size(); i++) {
	    		String number = String.valueOf(i+1);
	    		HashMap<String,Object> row = result.get(i);
	    		String dialog = "Result " + number + ":\n" + 
	    								"Price: " + String.valueOf(row.get("TOTALCOST")) + "\n" +
 										"Flight number: " + String.valueOf(row.get("FLIGHTNO")) + "\n" +
 										"Seat Number: " + String.valueOf(row.get("SEATNO")) + "\n\n";

	    		mainDialog = mainDialog + dialog;
	    	}
	    }
 		
	    //source: http://code.makery.ch/blog/javafx-dialogs-official/
 		TextArea textArea = new TextArea(mainDialog);
 		textArea.setEditable(false);
 		textArea.setWrapText(true);

 		textArea.setMaxWidth(Double.MAX_VALUE);
 		textArea.setMaxHeight(Double.MAX_VALUE);
 		GridPane.setVgrow(textArea, Priority.ALWAYS);
 		GridPane.setHgrow(textArea, Priority.ALWAYS);

 		GridPane expContent = new GridPane();
 		expContent.setMaxWidth(Double.MAX_VALUE);
 		expContent.add(textArea, 0, 1);

 		// Set expandable Exception into the dialog pane.
 		alert.getDialogPane().setExpandableContent(expContent);

 		//alert.setContentText("Please input an origin!");
 		alert.showAndWait();
}

 	public void cheapestTickets() {
 		Alert alert = new Alert(AlertType.INFORMATION);
 		alert.setTitle("WOW WHAT A BARGAIN!");
 		alert.setHeaderText("Click 'Show details' underneath.");
 		String mainDialog = "";

 		ArrayList<HashMap<String,Object>> result = p.searchByMinPrice();
	    if (result.isEmpty()) {
	    	mainDialog = "No results found.";
	    } else {
	    	for (int i = 0; i < result.size(); i++) {
	    		String number = String.valueOf(i+1);
	    		HashMap<String,Object> row = result.get(i);
	    		String dialog = "Result " + number + ":\n" + 
	    								"Price: " + String.valueOf(row.get("TOTALCOST")) + "\n" +
 										"Flight number: " + String.valueOf(row.get("FLIGHTNO")) + "\n" +
 										"Seat Number: " + String.valueOf(row.get("SEATNO")) + "\n\n";

	    		mainDialog = mainDialog + dialog;
	    	}
	    }
 		// source: http://code.makery.ch/blog/javafx-dialogs-official/
 		TextArea textArea = new TextArea(mainDialog);
 		textArea.setEditable(false);
 		textArea.setWrapText(true);

 		textArea.setMaxWidth(Double.MAX_VALUE);
 		textArea.setMaxHeight(Double.MAX_VALUE);
 		GridPane.setVgrow(textArea, Priority.ALWAYS);
 		GridPane.setHgrow(textArea, Priority.ALWAYS);

 		GridPane expContent = new GridPane();
 		expContent.setMaxWidth(Double.MAX_VALUE);
 		expContent.add(textArea, 0, 1);

 		// Set expandable Exception into the dialog pane.
 		alert.getDialogPane().setExpandableContent(expContent);

 		//alert.setContentText("Please input an origin!");
 		alert.showAndWait();
 	}
 	public void Register( ActionEvent e, TextField userNameField, TextField userEmail, TextField userPassport) {          
        String uName = userNameField.getText();
        String uEmail = userEmail.getText();
        String uPass = userPassport.getText();
        
        p.checkCredentials(uPass);
        p.addMember(uName, uEmail, uPass);
    
    }
 	// ********* THIS METHOD ADDED AFTER THE DEADLINE ******** //
 	public void phobia() {
 		ArrayList<String> result = p.phobia();
 		Alert alert = new Alert(AlertType.INFORMATION);
 		alert.setTitle("Fear of small planes D:");
 		alert.setHeaderText("For those who fear flying on small planes (ie. Bombardier CRJ700)");
 		String dialog = "";
 		
 		for (int i = 0; i < result.size(); i++) {
 			String flightNo = result.get(i);
 			String pt = result.get(i+1);
 			i++;
 			dialog = dialog + "Flight number: " + flightNo + "\n" + "Plane type: " + pt + "\n\n"; 
 		}
 		
 	// source: http://code.makery.ch/blog/javafx-dialogs-official/
 	 		TextArea textArea = new TextArea(dialog);
 	 		textArea.setEditable(false);
 	 		textArea.setWrapText(true);

 	 		textArea.setMaxWidth(Double.MAX_VALUE);
 	 		textArea.setMaxHeight(Double.MAX_VALUE);
 	 		GridPane.setVgrow(textArea, Priority.ALWAYS);
 	 		GridPane.setHgrow(textArea, Priority.ALWAYS);

 	 		GridPane expContent = new GridPane();
 	 		expContent.setMaxWidth(Double.MAX_VALUE);
 	 		expContent.add(textArea, 0, 1);

 	 		// Set expandable Exception into the dialog pane.
 	 		alert.getDialogPane().setExpandableContent(expContent);

 		alert.showAndWait();
 	}
 /*
 public void findTicks( ActionEvent e, TextField passNumField, TextField userNameField, TextField userPassport){
        //TODO Find tickets passed on Passport or Member login
        
        if (e.getSource()==btnPassTick) {
            //TODO find tickets associated with passport
            String mtPass = passNumField.getText();
           
            
        } else {
            //TODO verify login, and then find tickets associated with member
            String mtName = userNameField.getText();
            String mtPassNum = userPassport.getText();
        }
 }       
 */

 	public void searchFlight( ActionEvent e, TextField originField, TextField destField, TextField dateField) {   
     if (originField.getText() ==  null || originField.getText().trim().isEmpty()) {
    	 Alert alert = new Alert(AlertType.INFORMATION);
    	 alert.setTitle("Input an origin.");
    	 alert.setHeaderText(null);
    	 alert.setContentText("Please input an origin!");
    	 alert.showAndWait();
     } else {   
    	 if (destField.getText() ==  null || destField.getText().trim().isEmpty()) {
    		 Alert alert = new Alert(AlertType.INFORMATION);
    		 alert.setTitle("Input an origin.");
    		 alert.setHeaderText(null);
    		 alert.setContentText("Please input a destination!");
    		 alert.showAndWait();
    	 } else {
    	 	     if (dateField.getText() ==  null || dateField.getText().trim().isEmpty()) {
    	 	    	 Alert alert = new Alert(AlertType.INFORMATION);
    	 	    	 alert.setTitle("Input an origin.");
    	 	    	 alert.setHeaderText(null);
    	 	    	 alert.setContentText("Please input an travel date!");
    	 	    	 alert.showAndWait();		
    	 	} else {
    	 			Alert alert = new Alert(AlertType.INFORMATION);
    	 	    	alert.setTitle("Results");
    	 	    	alert.setHeaderText("Results: ");
    	 	    	String mainDialog = "";

    	 	    	ArrayList<HashMap<String,Object>> result = p.searchMain(originField.getText(), destField.getText(), dateField.getText());
    	 	    	if (result.isEmpty()) {
    	 	    		mainDialog = "None found.";
    	 	    	} else {
    	 	    		for (int i = 0; i < result.size(); i++) {
    	 	    			String number = String.valueOf(i+1);
    	 	    			HashMap<String,Object> row = result.get(i);
    	 	    			String dialog = "Result " + number + ":\n" + 
    	 	    									"Origin: " + String.valueOf(row.get("ORIGIN_ACODE")) + "\n" +
        	 										"Destination: " + String.valueOf(row.get("DESTINATION_ACODE")) + "\n" +
        	 										"Departure Date: " + String.valueOf(row.get("DEPARTUREDATE")) + "\n" +
        	 										"Departure time:" + String.valueOf(row.get("DEPARTURETIME")) + "\n" +
        	 										"Arrival Date:" + String.valueOf(row.get("ARRIVALDATE")) + "\n" +
        	 										"Arrival time:" + String.valueOf(row.get("ARRIVALTIME")) + "\n\n";

    	 	    			mainDialog = mainDialog + dialog;
    	 	    		}
    	 	    	} 
    	 	    	alert.setContentText(mainDialog);
        	 		alert.showAndWait();
    	 		}
    	 	} 
     	}
 	}     
 
  public void findSeat( ActionEvent e, TextField fsFNoField) {
     String fsFno = fsFNoField.getText();
     
     if (fsFno ==  null || fsFno.trim().isEmpty()) {
    	 Alert alert = new Alert(AlertType.INFORMATION);
    	 alert.setTitle("Input a flight number.");
    	 alert.setHeaderText(null);
    	 alert.setContentText("Please input a flight number!");
    	 alert.showAndWait();
     } else {
    	 Alert alert = new Alert(AlertType.INFORMATION);
    	 alert.setTitle("Seat Results.");
    	 alert.setHeaderText("Available seats for flight number: " + fsFno);
    	 
    	 String dialog = "";
    	 ArrayList<String> result = p.checkSeatAvailabilities(fsFno);
    	 if (result.isEmpty()) {
    		 dialog = "No results found.";
    	 } else {
    		 for (int i = 0; i < result.size(); i++) {
    			 dialog = dialog + result.get(i) + "\n";
    		 }	 
    	 }
    	 alert.setContentText(dialog);
    	 alert.showAndWait();
     }     
 }   
 
  
  public void buyTicket( ActionEvent e, TextField passNumField, TextField fNameField, TextField lNameField,
          TextField fNoField, TextField seatNoField){
      
            String passNum = passNumField.getText();
            String fName = fNameField.getText();
            String lName = lNameField.getText();
            String fNo = fNoField.getText();
            String seatNo = seatNoField.getText();
        p.purchaseTicket(fName, lName, seatNo, fNo, passNum);
 }     
/*
 public void buyTicketMem( ActionEvent e, TextField userNameField, TextField userPassport,
         TextField fNoField, TextField seatNoField){
                  
            String uName = userNameField.getText();
            String uPass = userPassport.getText();
            String fNoMem = fNoField.getText();
            String seatNoMem = seatNoField.getText();
            
     
 }  
  */
  
  public void updateInfo (ActionEvent e, TextField passNumField, TextField userNameField, TextField userEmailField) {
      //TODO update Info
      
          String uName = userNameField.getText();
          String uEmail = userEmailField.getText();
          String uPass = passNumField.getText();
	  p.changePersonalInformation(uPass, uEmail, uName);
  }

  public void changeSeat (ActionEvent e, TextField oldSeat, TextField newSeat) {
      String oSeat = oldSeat.getText();
      String nSeat = newSeat.getText();
      emp.changeSeatType(nSeat,oSeat);
  }
  
   public void cancelTicket (ActionEvent e, TextField cancelNum) {
      int tickNum = parseInt(cancelNum.getText());
      emp.cancelTicket(tickNum);
  }
   
   public void deleteAccount (ActionEvent e, TextField daNameField, TextField daEmailField, TextField daPassport) {      
      String daName = daNameField.getText();
      String daEmail = daEmailField.getText();
      String daPass = daPassport.getText();
      p.deleteAccount(daName, daEmail, daPass);
   }
  
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
