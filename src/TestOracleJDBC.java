import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestOracleJDBC {
	static Scanner sc = new Scanner(System.in);
	static boolean dataPresent = false;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println("Enter last name to retrieve records: ");
			String lastname = sc.nextLine();
			getData("SELECT c.fullname, c.title, c.firstname, c.lastname, c.streetaddress, c.zipcode, c.emailaddress, p.position, d.company, s.state, m.city FROM customers c inner join position p on c.positionID = p.positionID inner join company d on c.COMPANYID = d.COMPANYID inner join state s on c.STATEID = s.STATEID inner join cities m on c.cityid = m.cityid where c.lastname = '"
					+ lastname + "'");
			if (dataPresent == false) {
				System.out.println("Enter Salutation: ");
				String salutation = sc.nextLine();
				System.out.println("Enter First Name: ");
				String fname = sc.nextLine();
				System.out.println("Enter Last Name: ");
				String lname = sc.nextLine();
				System.out.println("Enter Street Address: ");
				String address = sc.nextLine();
				System.out.println("Enter Zipcode: ");
				String zip = sc.nextLine();
				System.out.println("Enter Email: ");
				String email = sc.nextLine();
				System.out.println("Enter Company: ");
				String company = sc.nextLine();
				int companyID = getCompany(company);
				System.out.println("Enter State: ");
				String state = sc.nextLine();
				int stateID = getState(state);
				System.out.println("Enter City: ");
				String city = sc.nextLine();
				int cityID = getCity(city);
				System.out.println("Enter Position: ");
				String position = sc.nextLine();
				int positionID = getPosition(position);
				String query = "insert into customers values ('" + fname + " " + lname + "','" + salutation + "','"
						+ fname + "','" + lname + "','" + address + "','" + zip + "','" + email + "','" + companyID
						+ "','" + stateID + "','" + cityID + "','" + positionID + "')";
				setData(query);
				break;
			}
			System.out.println("Press (1) to search for another customer or press (2) to Edit the customer's address.");
			int action = sc.nextInt();
			sc.nextLine();
			if (action == 2) {
				System.out.println("Enter new street address: ");
				String address = sc.nextLine();
				System.out.println("Enter new city: ");
				String city = sc.nextLine();
				int cityID = getCity(city);
				System.out.println("Enter new state: ");
				String state = sc.nextLine();
				int stateID = getState(state);
				System.out.println("Enter new zipcode: ");
				String zipcode = sc.nextLine();
				setData("update customers set streetaddress='" + address + "', zipcode='" + zipcode + "', stateid='"
						+ stateID + "', cityid='" + cityID + "' where LASTNAME = '" + lastname + "'");
				System.out.println(
						"Press (1) to search for another customer or press (2) to Edit the customer's address.");
			}
		}
	}

	public static void getData(String sql) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dataPresent = true;
				System.out.println(rs.getString(2) + " " + rs.getString(1));
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(11) + " " + rs.getString(10) + " " + rs.getString(6));
				System.out.println(rs.getString(7));
				System.out.println(rs.getString(8) + " at " + rs.getString(9) + "\n\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static int getPosition(String ID) {
		Connection con = null;
		int returnInt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from position where position = '" + ID + "'");
			while (rs.next()) {
				returnInt = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnInt;
	}

	public static int getState(String ID) {
		Connection con = null;
		int returnInt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from state where state = '" + ID + "'");
			while (rs.next()) {
				returnInt = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnInt;
	}

	public static int getCity(String ID) {
		Connection con = null;
		int returnInt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from cities where city = '" + ID + "'");
			while (rs.next()) {
				returnInt = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnInt;
	}

	public static int getCompany(String ID) {
		Connection con = null;
		int returnInt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from company where company = '" + ID + "'");
			while (rs.next()) {
				returnInt = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnInt;
	}

	public static void setData(String sql) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}