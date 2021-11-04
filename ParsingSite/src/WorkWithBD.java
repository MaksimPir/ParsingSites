import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class WorkWithBD {
	private static Logger log = Logger.getLogger(WorkWithBD.class.getName());
	public Connection connection;
	private String urldb = "jdbc:sqlserver://LAPTOP-3PB23QEK\\instance:49173;databaseName=WordsDB";
	private String username = "user1";
	private String password = "user1";

	public void ConnectToBD() throws SecurityException, IOException {

		try {
			connection = DriverManager.getConnection(urldb, username, password);

		} catch (SQLException e) {
			FileHandler f = new FileHandler("mylogger1.log");
			log.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			log.log(Level.WARNING, "Exception: ", e);
		}
	}

	public boolean FindWord(String word) throws SecurityException, IOException {
		String sql = "SELECT * FROM TableWords Where Word='" + word + "'";
		Statement st;
		boolean b = false;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				b = true;
			}

		} catch (SQLException e) {
			FileHandler f = new FileHandler("mylogger1.log");
			log.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			log.log(Level.WARNING, "Exception: ", e);
		}

		return b;
	}

	public void InsertIntoTable(String word) throws SecurityException, IOException {
		String sql = "INSERT INTO TableWords VALUES('" + word + "',1)";
		Statement st;
		try {
			st = connection.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			FileHandler f = new FileHandler("mylogger1.log");
			log.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			log.log(Level.WARNING, "Exception: ", e);
		}

	}

	public void Increment(String word) throws SecurityException, IOException {
		String sql = "SELECT Count FROM TableWords Where Word='" + word + "'";
		Statement st;
		int k = 0;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				k = rs.getInt("Count") + 1;
			}
			sql = "UPDATE TableWords Set Count=" + k + " Where Word='" + word + "'";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			FileHandler f = new FileHandler("mylogger1.log");
			log.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			log.log(Level.WARNING, "Exception: ", e);
		}
	}

	public void PrintBD() throws SecurityException, IOException {
		String sql = "SELECT * FROM TableWords";
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("Word") + "-" + rs.getInt("Count"));
			}
			sql = "DELETE  FROM TableWords";
			st.executeUpdate(sql);
			connection.close();
		} catch (SQLException e) {
			FileHandler f = new FileHandler("mylogger1.log");
			log.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			log.log(Level.WARNING, "Exception: ", e);
		}

	}
}
