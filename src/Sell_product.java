import javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.*;

public class Sell_product {
	JFrame f;
	int stock = 0;

	Sell_product() {

		f = new JFrame();
		f.getContentPane().setBackground(new Color(250, 240, 230));

		JLabel title = new JLabel("Sell Product");
		title.setForeground(new Color(255, 0, 255));
		title.setBounds(280, 31, 209, 50);
		title.setFont(new Font("Serif", Font.PLAIN, 34));

		JLabel l1 = new JLabel("Product ID: ");
		l1.setFont(new Font("Arial", Font.PLAIN, 20));
		l1.setBounds(213, 130, 116, 30);

		JLabel l2 = new JLabel("Product Name: ");
		l2.setFont(new Font("Arial", Font.PLAIN, 20));
		l2.setBounds(29, 225, 151, 20);

		JLabel l3 = new JLabel("Price/Item in Rs. ");
		l3.setFont(new Font("Arial", Font.PLAIN, 20));
		l3.setBounds(24, 293, 156, 30);

		JLabel lblNewLabel = new JLabel("SELL ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(413, 220, 87, 30);

		// ----------ALL INPUT FIELDS--------------------------

		JTextField PID = new JTextField();
		PID.setBounds(339, 130, 150, 30);
		PID.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField PNAME = new JTextField();
		PNAME.setEditable(false);
		PNAME.setBounds(190, 223, 150, 30);
		PNAME.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField PRICE_PER_ITEM = new JTextField();
		PRICE_PER_ITEM.setEditable(false);
		PRICE_PER_ITEM.setBounds(190, 297, 150, 28);
		PRICE_PER_ITEM.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField STOCK = new JTextField();
		STOCK.setEditable(false);
		STOCK.setBounds(190, 358, 150, 30);
		STOCK.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField SELLID = new JTextField();
		SELLID.setBounds(561, 223, 167, 31);
		SELLID.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField Quantity = new JTextField();
		Quantity.setBounds(561, 296, 167, 30);
		Quantity.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField AMOUNT = new JTextField();
		AMOUNT.setEditable(false);
		AMOUNT.setBounds(561, 358, 167, 30);
		AMOUNT.setFont(new Font("Arial", Font.PLAIN, 20));

		JButton submit = new JButton("Add to cart");

		// --------------------------------------OK BUTTON-------------------

		JButton OK = new JButton("OK");
		OK.setFont(new Font("Arial", Font.PLAIN, 23));
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String prodId = PID.getText();

				if (prodId.length() == 0)
					JOptionPane.showMessageDialog(OK, "Enter a valid Product ID");

				else {
					try {
						// Connection

						Connection connection = DriverManager
								.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");

						Statement stmt = connection.createStatement();

						String sql = "SELECT prodName, pricePerItem, stock FROM Product where prodId='" + prodId + "'";
						ResultSet rs = stmt.executeQuery(sql);
						// STEP 5: Extract data from result set
						while (rs.next()) {
							// Retrieve by column name
							String prodName = rs.getString("prodName");
							PNAME.setText(prodName);

							String pricePerItem = rs.getString("pricePerItem");
							PRICE_PER_ITEM.setText(pricePerItem);

							stock = rs.getInt("stock");
							String stck = Integer.toString(stock);
							STOCK.setText(stck);

							if (stock == 0) {
								submit.setEnabled(false);
								JOptionPane.showMessageDialog(OK, "Product Out of Stock. Please refill ASAP");
							}

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
		OK.setBounds(518, 127, 77, 35);
		OK.setBorder(new BevelBorder(BevelBorder.RAISED));

		// ----------ADD ALL ENTITIES TO THE FORM-----------------
		f.getContentPane().add(title);
		f.getContentPane().add(l1);
		f.getContentPane().add(l2);
		f.getContentPane().add(l3);

		f.getContentPane().add(PID);
		f.getContentPane().add(PNAME);
		f.getContentPane().add(PRICE_PER_ITEM);

		f.getContentPane().add(STOCK);

		f.getContentPane().add(OK);

		// --------------------------RESET BUTTON-----------------------------
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 23));
		reset.setBounds(436, 443, 100, 42);
		f.getContentPane().add(reset);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PID.setText("");
				PNAME.setText("");
				PRICE_PER_ITEM.setText("");
				STOCK.setText("");
				Quantity.setText("");
				AMOUNT.setText("");
				SELLID.setText("");
			}
		});
		reset.setBorder(new BevelBorder(BevelBorder.RAISED));

		// ----------------------------------SUBMIT BUTTON---------------------------

		submit.setFont(new Font("Arial", Font.PLAIN, 23));
		submit.setBounds(250, 443, 126, 42);
		f.getContentPane().add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String prodName = PNAME.getText();
				String prodId = PID.getText();
				String pricePerItem = PRICE_PER_ITEM.getText();

				String Sellid = "";
				String qty = "";
				int qtyint = 0;

				try {
					qty = Quantity.getText();
					qtyint = Integer.valueOf(qty);
					if (qtyint == 0)
						JOptionPane.showMessageDialog(submit, "Quantity can't be zero");
					if (qtyint > stock) {
						JOptionPane.showMessageDialog(submit, "You can't sell more than the stock available");

					}

				} catch (Exception E) {
					JOptionPane.showMessageDialog(submit, "Enter valid Quantity to be bought!");

				}

				Sellid = SELLID.getText();
				if (Sellid.length() == 0)
					JOptionPane.showMessageDialog(submit, "Add a SellId, Example:- Name of customer, etc.");
				
				java.util.Date date = new java.util.Date();

				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());

				double Amount = qtyint * Double.valueOf(pricePerItem);
				String amt = Double.toString(Amount);

				AMOUNT.setText(amt);

				if(qtyint<stock && qtyint!=0 && Sellid.length()!=0)
				{
					try {
						// Connection

						Connection connection = DriverManager
								.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");

						PreparedStatement sta = connection.prepareStatement(
								"insert into Sell(sellId,prodId,prodName,qtyBought,pricePerItem,amount,timeWhenSold,dateWhenSold) values(?,?,?,?,?,?,?,?)");
						sta.setString(1, Sellid);
						sta.setString(2, prodId);
						sta.setString(3, prodName);
						sta.setInt(4, qtyint);
						sta.setString(5, pricePerItem);
						sta.setString(6, amt);
						sta.setTimestamp(7, sqlTime);
						sta.setDate(8, sqlDate);

						sta.executeUpdate();
						JOptionPane.showMessageDialog(submit, "Added to Cart Succesfully");

					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}

			}
		});

		submit.setBorder(new BevelBorder(BevelBorder.RAISED));

		JLabel l6 = new JLabel("Amount");
		l6.setFont(new Font("Arial", Font.PLAIN, 20));
		l6.setBounds(413, 360, 100, 20);
		f.getContentPane().add(l6);

		f.getContentPane().add(AMOUNT);

		JLabel l5 = new JLabel("Quantity bought");
		l5.setFont(new Font("Arial", Font.PLAIN, 20));
		l5.setBounds(397, 293, 157, 20);
		f.getContentPane().add(l5);

		f.getContentPane().add(Quantity);

		JLabel l4 = new JLabel("Stock available");
		l4.setFont(new Font("Arial", Font.PLAIN, 20));
		l4.setBounds(24, 360, 150, 20);
		f.getContentPane().add(l4);

		f.getContentPane().add(SELLID);
		SELLID.setColumns(10);

		f.getContentPane().add(lblNewLabel);

		f.setSize(800, 600);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
	}

	public static void main(String args[]) {

		new Sell_product();
	}
}
