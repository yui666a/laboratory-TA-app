package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

abstract public class DataBaseManager {
	static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	static final String dburl = "jdbc:sqlserver://mis.nagaokaut.ac.jp;DataBaseName=VueMessengerDB";

	static final String dbuser = "VueMessenger";

	static final String dbpassword = "Vue@20211129";

	protected void updateRecord(String query) {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dburl, dbuser, dbpassword);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException es) {
				es.printStackTrace();
			}
		}
	}

	public Object getRecord(String sql) {
		Connection con = null;
		Object object = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(dburl, dbuser, dbpassword);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				object = copyRecord(rs);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException es) {
				es.printStackTrace();
			}
		}
		return object;
	}

	public LinkedList getRecords(String sql) {
		Connection con = null;
		LinkedList list = new LinkedList();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(dburl, dbuser, dbpassword);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Object object = copyRecord(rs);
				list.addLast(object);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException es) {
				es.printStackTrace();
			}
		}
		return list;
	}

	abstract public Object copyRecord(ResultSet rs) throws Exception;

}
