import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
	int id;
	String title;
	String firstName;
	String lastName;
	String streetAddress;
	String zipCode;
	String city;
	String state;
	String emailAddress;
	String company;
	String position;
	
	public Customer(ResultSet rs)throws SQLException{
		id=rs.getInt(1);
		title=rs.getString(2);
		firstName=rs.getString(3);
		lastName=rs.getString(4);
		streetAddress=rs.getString(5);
		city=rs.getString(6);
		state=rs.getString(7);
		zipCode=rs.getString(8);
		emailAddress=rs.getString(9);
		company=rs.getString(10);
		position=rs.getString(11);
		
	}
	
	public String printSummary(){
		String output= title+" "+firstName+" "+lastName+" of "+city +" ID#:"+id;
		return output;
	}
	
	public String printFull(){
		String output="Customer Number: "+id+"\n"+title+" "+firstName+" "+lastName;
		output+="\n"+streetAddress+"\n"+city+" "+state+" "+zipCode+"\n";
		output+=emailAddress+"\n"+position+ " at "+company;
		return output;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
