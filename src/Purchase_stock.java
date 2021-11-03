import javax.swing.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Purchase_stock {
	JFrame f;

	public void addStock(String prodId, String prodName, String pricePerItem, int stock, java.sql.Date sqlDate, java.sql.Timestamp sqlTime) throws SQLException {
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");

		PreparedStatement sta = connection.prepareStatement("insert into product values(?,?,?,?,?,?)");
		sta.setString(1, prodId);
		sta.setString(2, prodName);
		sta.setString(3, pricePerItem);
		sta.setInt(4, stock);
		sta.setDate(5, sqlDate);
		sta.setTimestamp(6, sqlTime);

		sta.executeUpdate();
	}
	
	public void purchase() {

		f = new JFrame();
		f.getContentPane().setBackground(new Color(240, 255, 240));

		JLabel title = new JLabel("Add Product to Store");
		title.setForeground(new Color(0, 0, 205));
		title.setBackground(new Color(224, 255, 255));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(29, 26, 717, 50);
		title.setFont(new Font("Serif", Font.PLAIN, 34));

		JLabel l1 = new JLabel("Product ID: ");
		l1.setBackground(new Color(255, 255, 255));
		l1.setFont(new Font("Arial", Font.PLAIN, 20));
		l1.setBounds(164, 122, 118, 28);

		JLabel l2 = new JLabel("Product Name: ");
		l2.setFont(new Font("Arial", Font.PLAIN, 20));
		l2.setBounds(164, 191, 157, 28);

		JLabel l3 = new JLabel("Price/Item in Rs. ");
		l3.setFont(new Font("Arial", Font.PLAIN, 20));
		l3.setBounds(164, 263, 157, 28);

		JLabel l4 = new JLabel("Stock added");
		l4.setFont(new Font("Arial", Font.PLAIN, 20));
		l4.setBounds(164, 335, 118, 24);

		// ----------ALL INPUT FIELDS--------------------------

		JTextField PID = new JTextField();
		PID.setBounds(395, 125, 173, 28);
		PID.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField PNAME = new JTextField();
		PNAME.setBounds(395, 194, 173, 28);
		PNAME.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField PRICE_PER_ITEM = new JTextField();
		PRICE_PER_ITEM.setBounds(395, 266, 173, 28);
		PRICE_PER_ITEM.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField STOCK = new JTextField();
		STOCK.setBounds(395, 336, 173, 28);
		STOCK.setFont(new Font("Arial", Font.PLAIN, 20));

		// ----------------------------------SUBMIT BUTTON---------------------------

		JButton submit = new JButton("Add to Shop");
		submit.setForeground(SystemColor.controlText);
		submit.setFont(new Font("Arial", Font.PLAIN, 23));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String prodId = PID.getText();

				String prodName = PNAME.getText();

				String pricePerItem = "";

				String Stock = "";

				int stock = 0;

				try {
					Stock = STOCK.getText();
					stock = Integer.valueOf(Stock);
				} catch (Exception E) {
					JOptionPane.showMessageDialog(submit, "Enter valid amount of stock, " + E);

				}

				try {
					pricePerItem = PRICE_PER_ITEM.getText();
					double ppi = Double.valueOf(pricePerItem);
				} catch (Exception E) {
					JOptionPane.showMessageDialog(submit, "Enter valid amount of Price per Item, " + E);

				}

				// -------JDBC CONNECTION

				if (prodId.length() == 0)
					JOptionPane.showMessageDialog(submit, "Enter a valid alphanumeric Product ID");
				if (prodName.length() == 0)
					JOptionPane.showMessageDialog(submit, "Enter Valid Product Name");

				else {
					java.util.Date date = new java.util.Date();

					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());

					try {
						// Connection
						
						addStock(prodId, prodName, pricePerItem, stock, sqlDate, sqlTime);
						
						JOptionPane.showMessageDialog(submit, "Added to store successfully");

					}
					catch (java.sql.SQLIntegrityConstraintViolationException e1) {
						JOptionPane.showMessageDialog(submit, "Duplicate entry!");
					}	catch (Exception exception) {
						exception.printStackTrace();
					}

				}

			}
		});
		submit.setBounds(138, 422, 173, 50);

		// --------------------------RESET BUTTON-----------------------------
		JButton reset = new JButton("Reset");
		reset.setBackground(SystemColor.text);
		reset.setFont(new Font("Arial", Font.PLAIN, 23));
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PID.setText("");
				PNAME.setText("");
				PRICE_PER_ITEM.setText("");
				STOCK.setText("");
			}
		});
		reset.setBounds(411, 422, 144, 50);

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

		f.setSize(800, 600);
		f.getContentPane().setLayout(null);
		f.setVisible(true);

	}

//	public static void main(String args[]) {
//
//		new Purchase_stock();
//	}
}
