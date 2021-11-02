import javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.*;

public class Delete_Product {
	JFrame f;

	public ResultSet okButton(String prodId, String sellId) throws SQLException{
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");

		Statement stmt = connection.createStatement();

		String sql = "SELECT prodName, pricePerItem, qtyBought, amount FROM Sell where prodId='"
				+ prodId + "' and sellId='" + sellId + "'";
		return stmt.executeQuery(sql);
	}
	
	public void deletingProduct(String prodId, String sellId) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management",
				"root", "myroot");

		Statement stmt = connection.createStatement();
		
		String sql = "DELETE from Sell where prodId ="+prodId+" and sellId="+sellId;
		
//		PreparedStatement sta = connection
//				.prepareStatement("DELETE from Sell where prodId = ? and sellId=?");
//
//		sta.setString(1, prodId);
//		sta.setString(2, sellId);

		stmt.executeUpdate(sql);
	}
	
	public void delete() {

		f = new JFrame();
		f.getContentPane().setBackground(new Color(255, 250, 240));

		JLabel title = new JLabel("Delete from Cart");
		title.setForeground(new Color(255, 0, 0));
		title.setBackground(new Color(255, 0, 0));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(239, 21, 250, 50);
		title.setFont(new Font("Serif", Font.PLAIN, 34));

		JLabel l1 = new JLabel("Product ID: ");
		l1.setFont(new Font("Arial", Font.PLAIN, 20));
		l1.setBounds(357, 111, 119, 20);

		JLabel l2 = new JLabel("Product Name: ");
		l2.setFont(new Font("Arial", Font.PLAIN, 20));
		l2.setBounds(153, 223, 137, 20);

		JLabel l3 = new JLabel("Price/Item in Rs. ");
		l3.setFont(new Font("Arial", Font.PLAIN, 20));
		l3.setBounds(134, 322, 167, 20);

		JLabel l4 = new JLabel("Quantity Purchased");
		l4.setFont(new Font("Arial", Font.PLAIN, 20));
		l4.setBounds(122, 268, 179, 20);

		// ----------ALL INPUT FIELDS--------------------------

		JTextField PID = new JTextField();
		PID.setBounds(486, 108, 150, 25);
		PID.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField PNAME = new JTextField();
		PNAME.setBounds(400, 221, 150, 23);
		PNAME.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField PRICE_PER_ITEM = new JTextField();
		PRICE_PER_ITEM.setBounds(400, 319, 150, 25);
		PRICE_PER_ITEM.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField STOCK = new JTextField();
		STOCK.setBounds(400, 265, 150, 25);
		STOCK.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField SELLID = new JTextField();
		SELLID.setBounds(165, 113, 137, 23);
		SELLID.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField AMOUNT = new JTextField();
		AMOUNT.setBounds(400, 379, 150, 25);
		AMOUNT.setFont(new Font("Arial", Font.PLAIN, 20));

		// --------------------------------------OK BUTTON-------------------

		JButton OK = new JButton("OK");
		OK.setFont(new Font("Arial", Font.PLAIN, 23));
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String prodId = PID.getText();
				String sellId = SELLID.getText();

				if (prodId.length() == 0)
					JOptionPane.showMessageDialog(OK, "Enter a valid Product ID");
				if (sellId.length() == 0)
					JOptionPane.showMessageDialog(OK, "Enter a valid Sell ID");

				else {
					try {
						// Connection

						ResultSet rs = okButton(prodId, sellId);
						// STEP 5: Extract data from result set
						while (rs.next()) {
							// Retrieve by column name
							String prodName = rs.getString("prodName");
							PNAME.setText(prodName);

							String pricePerItem = rs.getString("pricePerItem");
							PRICE_PER_ITEM.setText(pricePerItem);

							int qtybought = rs.getInt("qtyBought");
							String qty = Integer.toString(qtybought);
							STOCK.setText(qty);

							double amt = rs.getDouble("amount");
							String amts = Double.toString(amt);
							AMOUNT.setText(amts);

						}
						rs.close();
						JOptionPane.showMessageDialog(OK, "Entry Read Successfull");

					} catch (Exception exception) {
						exception.printStackTrace();
						JOptionPane.showMessageDialog(OK, "Product Id invalid");

					}
				}
			}
		});
		OK.setBounds(298, 168, 71, 30);
		OK.setBorder(new BevelBorder(BevelBorder.RAISED));

		// ----------------------------------SUBMIT BUTTON---------------------------

		JButton submit = new JButton("Delete Product");
		submit.setFont(new Font("Arial", Font.PLAIN, 23));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String prodId = PID.getText();
				String sellId = SELLID.getText();

				try {
					// Connection

					deletingProduct(prodId,sellId);
					JOptionPane.showMessageDialog(submit, "Entry Deleted Succesfully");

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		submit.setBounds(153, 461, 190, 42);

		submit.setBorder(new BevelBorder(BevelBorder.RAISED));

		// --------------------------RESET BUTTON-----------------------------
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 23));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PID.setText("");
				PNAME.setText("");
				PRICE_PER_ITEM.setText("");
				STOCK.setText("");
				SELLID.setText("");
				AMOUNT.setText("");
			}
		});
		reset.setBounds(425, 461, 97, 42);
		reset.setBorder(new BevelBorder(BevelBorder.RAISED));

		// ----------ADD ALL ENTITIES TO THE FORM-----------------
		f.getContentPane().add(title);
		f.getContentPane().add(l1);
		f.getContentPane().add(l2);
		f.getContentPane().add(l3);
		f.getContentPane().add(l4);

		f.getContentPane().add(PID);
		f.getContentPane().add(PNAME);
		f.getContentPane().add(PRICE_PER_ITEM);

		f.getContentPane().add(STOCK);

		f.getContentPane().add(submit);
		f.getContentPane().add(reset);

		f.getContentPane().add(OK);

		f.setSize(800, 600);
		f.getContentPane().setLayout(null);

		JLabel Sell_id = new JLabel("Sell ID:");
		Sell_id.setFont(new Font("Arial", Font.PLAIN, 20));
		Sell_id.setBounds(79, 117, 112, 20);
		f.getContentPane().add(Sell_id);

		f.getContentPane().add(SELLID);
		SELLID.setColumns(10);

		JLabel Amount = new JLabel("Amount in Rs.");
		Amount.setFont(new Font("Arial", Font.PLAIN, 20));
		Amount.setBounds(153, 380, 137, 25);
		f.getContentPane().add(Amount);

		f.getContentPane().add(AMOUNT);
		AMOUNT.setColumns(10);
		f.setVisible(true);
	}

//	public static void main(String args[]) {
//
//		new Delete_Product();
//	}
}
