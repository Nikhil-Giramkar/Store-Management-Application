import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.Test;

class Sell_productTest {
	
	@Test
	void testOkButton() {
		Sell_product sell_product = new Sell_product();
		try {
			String prodName = "TEST";
			String pricePerItem = "25.00";
			int stock = 40;
			
			ResultSet rs = sell_product.okButton("test01");
			
			while(rs.next()) {
				assertEquals(prodName, rs.getString("prodName"));
				assertEquals(pricePerItem, rs.getString("pricePerItem"));
				assertEquals(stock, rs.getInt("stock"));
			}
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testAddToCartButton() {
//		Testing AddToCartButton function
		
		Sell_product sellProduct = new Sell_product();
		
		try(Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");){
			try(Statement stCheck = connection.createStatement()){
				connection.setAutoCommit(false);
					
				// Initial cleanup
//				stCheck.executeUpdate("DELETE FROM Sell");
				
				String Sellid = "saumen96";
				String prodId = "conc001";
				String prodName = "nescafe coffee";
				int qtyint = 5;
				String pricePerItem = "20.00";
				String amt = "100.00";
				java.util.Date date = new java.util.Date();

				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());	
				connection.commit();
				sellProduct.addToCartButton(Sellid, prodId, prodName, qtyint, pricePerItem, amt, sqlDate, sqlTime);
				
				ResultSet rs = stCheck.executeQuery("SELECT * FROM store_management.sell where sellId='" + Sellid + "'");
				
				while(rs.next()) {
//					System.out.println(rs.getString("prodName"));
//					System.out.print("adsf");
					assertEquals(Sellid, rs.getString("sellId"));
					assertEquals(prodId, rs.getString("prodId"));
					assertEquals(prodName, rs.getString("prodName"));
					assertEquals(qtyint, rs.getInt("qtyBought"));
					assertEquals(pricePerItem, rs.getString("pricePerItem"));
					assertEquals(amt, rs.getString("amount"));
				}
				
			}
			finally {
				connection.rollback();
			}
		}
		catch (SQLException e)
        {
            fail(e.toString());
        }
	}
}
