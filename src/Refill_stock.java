import javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.*;

public class Refill_stock {
	JFrame f;

	Refill_stock() {

		f = new JFrame();
		f.getContentPane().setBackground(new Color(224, 255, 255));

		JLabel title = new JLabel("Refill Stock");
		title.setForeground(new Color(153, 50, 204));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(267, 30, 250, 50);
		title.setFont(new Font("Serif", Font.PLAIN, 34));

		JLabel l1 = new JLabel("Product ID: ");
		l1.setFont(new Font("Arial", Font.PLAIN, 20));
		l1.setBounds(221, 136, 114, 22);

		JLabel l2 = new JLabel("Product Name: ");
		l2.setFont(new Font("Arial", Font.PLAIN, 20));
		l2.setBounds(25, 220, 150, 20);

		JLabel l3 = new JLabel("Price/Item(Rs.): ");
		l3.setFont(new Font("Arial", Font.PLAIN, 20));
		l3.setBounds(25, 273, 150, 20);

		JLabel l4 = new JLabel("Stock available:");
		l4.setFont(new Font("Arial", Font.PLAIN, 20));
		l4.setBounds(25, 327, 150, 20);

		JLabel l5 = new JLabel("Stock Added now:");
		l5.setFont(new Font("Arial", Font.PLAIN, 20));
		l5.setBounds(385, 242, 165, 20);

		// ----------ALL INPUT FIELDS--------------------------

		JTextField PID = new JTextField();
		PID.setBounds(345, 128, 150, 30);
		PID.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField PNAME = new JTextField();
		PNAME.setEditable(false);
		PNAME.setBounds(185, 215, 150, 30);
		PNAME.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField PRICE_PER_ITEM = new JTextField();
		PRICE_PER_ITEM.setEditable(false);
		PRICE_PER_ITEM.setBounds(185, 271, 150, 30);
		PRICE_PER_ITEM.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField STOCK = new JTextField();
		STOCK.setEditable(false);
		STOCK.setBounds(185, 325, 150, 30);
		STOCK.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField Stock_Added = new JTextField();
		Stock_Added.setBounds(558, 235, 150, 30);
		Stock_Added.setFont(new Font("Arial", Font.PLAIN, 20));

		JTextField Total_Stock = new JTextField();
		Total_Stock.setEditable(false);
		Total_Stock.setBounds(558, 291, 153, 30);
		Total_Stock.setFont(new Font("Arial", Font.PLAIN, 20));

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

							int stock = rs.getInt("stock");
							String stck = Integer.toString(stock);
							STOCK.setText(stck);

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
		OK.setBounds(517, 125, 78, 33);
		OK.setBorder(new BevelBorder(BevelBorder.RAISED));

		// ----------ADD ALL ENTITIES TO THE FORM-----------------
		f.getContentPane().add(title);
		f.getContentPane().add(l1);
		f.getContentPane().add(l2);
		f.getContentPane().add(l3);
		f.getContentPane().add(l4);
		f.getContentPane().add(l5);

		f.getContentPane().add(PID);
		f.getContentPane().add(PNAME);
		f.getContentPane().add(PRICE_PER_ITEM);
		f.getContentPane().add(Stock_Added);

		f.getContentPane().add(STOCK);

		f.getContentPane().add(OK);

		// --------------------------RESET BUTTON-----------------------------
		JButton reset = new JButton("Reset");
		reset.setFont(new Font("Arial", Font.PLAIN, 23));
		reset.setBounds(470, 416, 108, 44);
		f.getContentPane().add(reset);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PID.setText("");
				PNAME.setText("");
				PRICE_PER_ITEM.setText("");
				STOCK.setText("");
				Stock_Added.setText("");
				Total_Stock.setText("");
			}
		});
		reset.setBorder(new BevelBorder(BevelBorder.RAISED));

		// ----------------------------------SUBMIT BUTTON---------------------------

		JButton submit = new JButton("Refill");
		submit.setFont(new Font("Arial", Font.PLAIN, 23));
		submit.setBounds(242, 416, 114, 44);
		f.getContentPane().add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int stock_addedint = 0;

				String prodId = PID.getText();

				try {
					String stock_added = Stock_Added.getText();
					stock_addedint = Integer.valueOf(stock_added);
					if (stock_addedint == 0)
						JOptionPane.showMessageDialog(submit, "Stock to be added can't be zero");

				} catch (Exception E) {
					JOptionPane.showMessageDialog(submit, "Enter valid stock value, " + E);

				}

				int total_stock = stock_addedint + Integer.valueOf(STOCK.getText());
				String total = String.valueOf(total_stock);
				Total_Stock.setText(total);

				try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_management",
							"root", "myroot");
					PreparedStatement sta = connection
							.prepareStatement("UPDATE Product SET stock = ? WHERE prodId = ?");
					sta.setInt(1, total_stock);
					sta.setString(2, prodId);
					sta.executeUpdate();
					JOptionPane.showMessageDialog(submit, "Form Submitted Succesfully");

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});

		submit.setBorder(new BevelBorder(BevelBorder.RAISED));

		JLabel l6 = new JLabel("Total Stock now");
		l6.setFont(new Font("Arial", Font.PLAIN, 20));
		l6.setBounds(385, 293, 150, 20);
		f.getContentPane().add(l6);

		f.getContentPane().add(Total_Stock);

		f.setSize(800, 600);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
	}

	public static void main(String args[]) {

		new Refill_stock();
	}
}
