package pkg304flighdbdebug;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Flight {
	private final SimpleStringProperty flightNo;
	private final SimpleStringProperty origin;
	private final SimpleStringProperty destination;
	private final SimpleStringProperty airline;
	private final SimpleStringProperty deptDate;
	private final SimpleStringProperty deptTime;
	private final SimpleStringProperty arrivalDate;
	private final SimpleStringProperty arrivalTime;
	
	Flight(String flightNo, String origin, String destination, String airline, String deptDate, String deptTime, String arrivalDate, String arrivalTime) {
		this.flightNo = new SimpleStringProperty(flightNo);
		this.origin = new SimpleStringProperty(origin);
		this.destination = new SimpleStringProperty(destination);
		this.airline = new SimpleStringProperty(airline);
		this.deptDate = new SimpleStringProperty(deptDate);
		this.deptTime = new SimpleStringProperty(deptTime);
		this.arrivalDate = new SimpleStringProperty(arrivalDate);
		this.arrivalTime = new SimpleStringProperty(arrivalTime);
	}
	
	public String getFlightNo() {
		return flightNo.get();
	}
	
	public void setFlightNo(String flightNo){
		this.flightNo.set(flightNo);
	}
	
	public String getOrigin() {
		return origin.get();
	}
	
	public void setOrigin(String origin){
		this.origin.set(origin);
	}
	
	public String getDestination() {
		return destination.get();
	}
	
	public void setDestination(String destination){
		this.destination.set(destination);
	}
	
	public String getAirline() {
		return airline.get();
	}
	
	public void setAirline(String airline){
		this.airline.set(airline);
	}
	
	public String getDeptDate() {
		return deptDate.get();
	}
	
	public void setDeptDate(String deptDate){
		this.deptDate.set(deptDate);
	}
	
	public String getDeptTime() {
		return deptTime.get();
	}
	
	public void setDeptTime(String deptTime){
		this.deptTime.set(deptTime);
	}
	
	public String getArrivalDate() {
		return arrivalDate.get();
	}
	
	public void setArrivalDate(String arrivalDate){
		this.arrivalDate.set(arrivalDate);
	}
	
	public String getArrivalTime() {
		return arrivalTime.get();
	}
	
	public void setArrivalTime(String arrivalTime){
		this.arrivalTime.set(arrivalTime);
	}
	
}
