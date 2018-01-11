package del.res.dao;

import java.sql.Connection;

import del.res.utilities.db_interact;

public class DAO {
	public Connection conn = null;
	
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}
	
	public void open() {
		conn = db_interact.newDatabase();
	}
}
