import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

import org.junit.jupiter.api.Test;

class Delete_ProductTest {

	@Test
	public void testOkButton() {
		Delete_Product dp = new Delete_Product();
		
		try {
			String prodId = "test01";
			String sellId = "testing101";
			String prodName = "TEST";
			String pricePerItem = "25.00";
			int qtyBought = 5;
			String amt = "125.00";
			
			ResultSet rs = dp.okButton(prodId, sellId);
			
			while(rs.next()) {
				assertEquals(prodName, rs.getString("prodName"));
				assertEquals(pricePerItem, rs.getString("pricePerItem"));
				assertEquals(amt, rs.getString("amount"));
				assertEquals(qtyBought, rs.getInt("qtyBought"));
			}
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testDeletingProduct() {
		Delete_Product dp = new Delete_Product();
		
		try(Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");){
			try(Statement st = connection.createStatement();){
				connection.setAutoCommit(false);
				
				
				
				String sellId = "testing101";
				String prodId = "test01";
				String prodName = "TEST";
				int qtyint = 5;
				String pricePerItem = "25.00";
				String amt = "125.00";
				java.util.Date date = new java.util.Date();

				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());	
//				connection.commit();
				
//				PreparedStatement sta = connection.prepareStatement(
//						"insert into Sell(sellId,prodId,prodName,qtyBought,pricePerItem,amount,timeWhenSold,dateWhenSold) values(?,?,?,?,?,?,?,?)");
				
				String insert = "insert into Sell(sellId,prodId,prodName,qtyBought,pricePerItem,amount,timeWhenSold,dateWhenSold) "
						+ "values('"+sellId+"','"+prodId+"','"+prodName+"','"+qtyint+"','"+pricePerItem+"','"+amt+"','"+sqlTime+"','"+sqlDate+"')";
//				sta.setString(1, sellId);
//				sta.setString(2, prodId);
//				sta.setString(3, prodName);
//				sta.setInt(4, qtyint);
//				sta.setString(5, pricePerItem);
//				sta.setString(6, amt);
//				sta.setTimestamp(7, sqlTime);
//				sta.setDate(8, sqlDate);
				
				st.executeUpdate(insert);
				
//				dp.deletingProduct(prodId, sellId);
				System.out.println("hi");
				
				
				String sql = "Select prodId from sell where sellId='testing101';";
				ResultSet rs = st.executeQuery(sql);
				
				System.out.println(rs.next());
				
				while(rs.next()) {
					System.out.println(rs.getString("prodId"));
					assertEquals(sellId, rs.getString("prodId"));
				}
//				
////				assertNull(rs.getString("prodId"));
//				System.out.println(rs.getString("prodId"));
			}
			finally {
//				connection.rollback();
			}
		}
		catch(SQLException e) {
			fail(e.toString());
		}
	}

}
