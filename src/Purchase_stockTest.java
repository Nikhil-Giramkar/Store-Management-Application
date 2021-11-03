import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Purchase_stockTest {
	@Test
	void testAddStock() {
		Purchase_stock purchase_stock = new Purchase_stock();
		
		try(Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");){
			try(Statement stCheck = connection.createStatement()){
				connection.setAutoCommit(false);
				
				String prodId = "test01";
				String prodName = "Test";
				String pricePerItem = "10.00";
				int stock = 25;
				java.util.Date date = new java.util.Date();

				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
				
				connection.commit();
				
				purchase_stock.addStock(prodId, prodName, pricePerItem, stock, sqlDate, sqlTime);
				
				ResultSet rs = stCheck.executeQuery("SELECT * FROM PRODUCT WHERE PRODID='" + prodId + "';");
				
				while(rs.next()) {
					assertEquals(prodId, rs.getString("prodId"));
					assertEquals(prodName, rs.getString("prodName"));
					assertEquals(pricePerItem, rs.getString("pricePerItem"));
					assertEquals(stock, rs.getInt("stock"));
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
