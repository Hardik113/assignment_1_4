package assignment.four;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseThread implements Runnable {
	
	RawCollection rawCollection;
	
	public DatabaseThread(RawCollection rawCollection) {
		this.rawCollection = rawCollection;
	}

	@Override
	public void run() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itemsDb?useSSL=false", "root", "Harcha1347;");		         
				Statement stmt = conn.createStatement();
		    ) {
			
		         String strSelect = "select * from items";

		         ResultSet rset = stmt.executeQuery(strSelect);

		         int rowCount = 0;
		         while(rset.next()) { 
		            String name = rset.getString("name");
		            float price = rset.getFloat("price");
		            int    qty   = rset.getInt("qty");
		            String type = rset.getString("type");
		            rowCount++;
		            
		            rawCollection.add(name, type, price, qty);
		            
		         }
		 
		     } catch(SQLException ex) {
		         ex.printStackTrace();
		     } catch (Exception e) {
				e.printStackTrace();
			}
	}

}
