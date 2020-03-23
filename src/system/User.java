package system;

import java.sql.*;

public class User {

	String Username;
	String Password;

	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}

	public User(String username, String password) {
		super();
		Username = username;
		Password = password;
	}

	public boolean canLogin() {
		boolean found = false;
		try {
			Connection conn = DatabaseConnection.getConnection();

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(
					"SELECT * FROM users where Username='" + this.Username + "' AND Password='" + this.Password + "'");

			// tries to read the data from the database
			if (rs.next())
				found = true;// Found the user..

			conn.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}
}
