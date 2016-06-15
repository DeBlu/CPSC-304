import java.util.Date;

public class PurchaseDetail {

	private String tID;
	private String fName;
	private String lName;
	private String oacode;
	private String dacode;
	private Date departureDate;
	private Date arrivalDate;
	private String seatType;
	private String seatNo;
	private String passportNo;
	// private DateFormat dateFormat = new SimpleDateFormat("YY-MM-DD");
	private String flightNo;
	private int price;

	public String gettID() {
		return tID;
	}

	public void settID(String tID) {
		this.tID = tID;
	}

	public String getflightNo() {
		return flightNo;
	}

	public void setflightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public int getprice() {
		return price;
	}

	public void setprice(String string) {
		this.price = Integer.parseInt(string);
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getseatNo() {
		return seatNo;
	}

	public void setseatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getOacode() {
		return oacode;
	}

	public void setOacode(String oacode) {
		this.oacode = oacode;
	}

	public String getDacode() {
		return dacode;
	}

	public void setDacode(String dacode) {
		this.dacode = dacode;
	}

	public Date getdepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
}
