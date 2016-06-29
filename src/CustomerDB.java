import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Random;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CustomerDB {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Random rand = new Random();

		Connection con = null;
		ResultSet rs = null;
		ResultSet s = null;
		ResultSet ci = null;
		ResultSet co = null;
		ResultSet po = null;
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			
			String sql = "select id,title,firstname,lastname,streetaddress,city,state,zipcode,emailaddress,company,position from"
					+ " customers inner join companies on companies.companyid=customers.companyid inner join positions"
					+ " on customers.positionid=positions.positionid inner join states on states.stateid=customers.stateid"
					+ " inner join cities on customers.cityid=cities.cityid where lastname=?";
			PreparedStatement getList = con.prepareStatement(sql);
			
			sql = "update customers set streetaddress=?,cityid=?,stateid=?,zipcode=? where id=?";
			PreparedStatement updateCustomer = con.prepareStatement(sql);
			sql = "select stateid from states where state=?";
			PreparedStatement getStateID = con.prepareStatement(sql);
			sql = "select cityid from cities where city=?";
			PreparedStatement getCityID = con.prepareStatement(sql);
			sql = "insert into customers (title,firstname,lastname,streetaddress,"
					+ "cityid,stateid,zipcode,emailaddress,companyid,positionid)"
					+ " values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insertNewCustomer = con.prepareStatement(sql);
			sql = "select companyid from companies where company=?";
			PreparedStatement getCompanyID = con.prepareStatement(sql);
			sql = "select positionid from positions where position=?";
			PreparedStatement getPositionID = con.prepareStatement(sql);
			
			while(true){

				ArrayList<Customer> query = new ArrayList<Customer>();
				int select = 0;
				
				System.out.println("Enter last name:");
				String input = in.nextLine();
				getList.setString(1, input);
				rs = getList.executeQuery();
				
				if(rs.next()){
					query.add(new Customer(rs));
					while(rs.next()){
						query.add(new Customer(rs));
					}
					if(query.size()>1){
						System.out.println("Did you mean:");
						for(int i = 0; i < query.size();i++){
							System.out.println(i+": "+query.get(i).printSummary());
						}
						select = in.nextInt();
					}
					System.out.println(query.get(select).printFull());
					System.out.println("Enter 1 to update address, 2 for new search");
					int update=in.nextInt();
					in.nextLine();
					if(update==1){
						String newAddress;
						int cityID;
						int stateID;
						String newZip;
						System.out.println("Enter new address");
						newAddress=in.nextLine();
						while(true){
							System.out.println("Enter new city");
							String newCity=in.nextLine();
							getCityID.setString(1, newCity);
							ci=getCityID.executeQuery();
							if(ci.next()){
								cityID = ci.getInt(1);
								break;
							}
							else System.out.println("Unrecognized city");
						}
						while(true){
							System.out.println("Enter new state");
							String newState=in.nextLine();
							getStateID.setString(1, newState);
							s=getStateID.executeQuery();
							if(s.next()){
								stateID = s.getInt(1);
								break;
							}
							else System.out.println("Unrecognized state");
						}
						System.out.println("Enter new zip code");
						newZip=in.nextLine();
						updateCustomer.setString(1, newAddress);
						updateCustomer.setInt(2, cityID);
						updateCustomer.setInt(3, stateID);
						updateCustomer.setString(4, newZip);
						updateCustomer.setInt(5, query.get(select).getId());
						updateCustomer.executeUpdate();
						System.out.println("Update Successful");
					}
				}
				else{
					System.out.println("No customer by that name");
					System.out.println("Enter 1 to search again, 2 to add a customer");
					int choice = in.nextInt();
					in.nextLine();
					if(choice==2){
						System.out.println("Enter title:");
						String ntitle=in.nextLine();
						System.out.println("Enter first name:");
						String nfname=in.nextLine();
						System.out.println("Enter last name:");
						String nlname=in.nextLine();
						System.out.println("Enter street address");
						String nsa=in.nextLine();
						int ncity;
						while(true){
							System.out.println("Enter city");
							String newCity=in.nextLine();
							getCityID.setString(1, newCity);
							ci=getCityID.executeQuery();
							if(ci.next()){
								ncity = ci.getInt(1);
								break;
							}
							else System.out.println("Unrecognized city");
						}
						int nstate;
						while(true){
							System.out.println("Enter state");
							String newState=in.nextLine();
							getStateID.setString(1, newState);
							s=getStateID.executeQuery();
							if(s.next()){
								nstate = s.getInt(1);
								break;
							}
							else System.out.println("Unrecognized state");
						}
						System.out.println("Enter zip code");
						String nzip=in.nextLine();
						System.out.println("Enter email address");
						String nemail=in.nextLine();
						int ncompany;
						while(true){
							System.out.println("Enter company");
							String newCompany=in.nextLine();
							getCompanyID.setString(1, newCompany);
							co=getCompanyID.executeQuery();
							if(co.next()){
								ncompany = co.getInt(1);
								break;
							}
							else System.out.println("Unrecognized company");
						}
						int nposition;
						while(true){
							System.out.println("Enter position");
							String newPosition=in.nextLine();
							getPositionID.setString(1, newPosition);
							po=getPositionID.executeQuery();
							if(po.next()){
								nposition = po.getInt(1);
								break;
							}
							else System.out.println("Unrecognized position");
						}
						insertNewCustomer.setString(1,ntitle);
						insertNewCustomer.setString(2,nfname);
						insertNewCustomer.setString(3,nlname);
						insertNewCustomer.setString(4,nsa);
						insertNewCustomer.setInt(5,ncity);
						insertNewCustomer.setInt(6,nstate);
						insertNewCustomer.setString(7,nzip);
						insertNewCustomer.setString(8,nemail);
						insertNewCustomer.setInt(9,ncompany);
						insertNewCustomer.setInt(10,nposition);
						insertNewCustomer.executeUpdate();
						System.out.println("Entry for "+nfname+" "+nlname+" created");
					}
					
				}
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

	}
}
