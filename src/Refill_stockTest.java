import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class Refill_stockTest {

	@Test
	void testOkButton() {
		Refill_stock r_s = new Refill_stock();
		
		try(Connection connecion = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");){
			String prodId = "test01";
			String prodName = "TEST";
			int stock = 20;
			
			ResultSet rs = r_s.okButton(prodId);
			
			while(rs.next()) {
				assertEquals(prodName, rs.getString("prodName"));
				assertEquals(stock, rs.getInt("stock"));
			}
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	void testRefillingStock() {
		Refill_stock r_s = new Refill_stock();
		
		try(Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");){
			try(Statement stCheck = connection.createStatement()){
				connection.setAutoCommit(false);
				
				String prodId = "test01";
				int total_stock = 20;
				
				connection.commit();
				
				r_s.refillingStock(total_stock,prodId);
				
				ResultSet rs = stCheck.executeQuery("SELECT * FROM PRODUCT WHERE PRODID='" + prodId + "';");
				
				while(rs.next()) {
					assertEquals(prodId, rs.getString("prodId"));
					assertEquals(total_stock, rs.getInt("stock"));
				}				
			}
			finally {
				connection.rollback();
			}
		}
		catch(SQLException e) {
			fail(e.toString());
		}
	}

}
