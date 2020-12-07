import javax.swing.*;


import java.sql.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Home_page {
	JFrame f;

	Home_page(){
		
		f = new JFrame();
		f.getContentPane().setBackground(new Color(255, 255, 240));
		
		 
		 

		 
		 JLabel title = new JLabel("Store Management Application");
		 title.setForeground(new Color(153, 50, 204));
		 title.setHorizontalAlignment(SwingConstants.CENTER);
		 title.setBounds(147, 11, 470, 50);
		 f.getContentPane().add(title);
		 title.setFont(new Font("Serif", Font.PLAIN, 34));
		 
		 
		 
		 
		 
		 //------------------------Sell product BUTTON---------------------------------

		 JButton sell_button = new JButton("Sell product");
		 sell_button.setForeground(new Color(255, 127, 80));
		 sell_button.setBackground(new Color(255, 228, 196));
		 sell_button.setBounds(442, 137, 204, 83);
		 f.getContentPane().add(sell_button);
		 sell_button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		new Sell_product().f.setVisible(true);
		 		
		 	}
		 });
		 sell_button.setFont(new Font("Arial", Font.PLAIN, 23));
		 sell_button.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));
		 
		 
		 //------------------------Add new product BUTTON---------------------------------
		 
		 JButton purchase_button = new JButton("Add to store");
		 purchase_button.setForeground(new Color(46, 139, 87));
		 purchase_button.setBackground(new Color(240, 255, 240));
		 purchase_button.setBounds(79, 137, 204, 83);
		 f.getContentPane().add(purchase_button);
		 purchase_button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		new Purchase_stock().f.setVisible(true);
		 		
		 	}
		 });
		 purchase_button.setFont(new Font("Arial", Font.PLAIN, 23));
		 purchase_button.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));
		 
		 
		 
		 
		 //------------------------DELETE BUTTON---------------------------------

		 JButton delete_button = new JButton("Delete from cart");
		 delete_button.setBackground(new Color(255, 192, 203));
		 delete_button.setForeground(new Color(255, 0, 0));
		 delete_button.setBounds(442, 345, 204, 83);
		 f.getContentPane().add(delete_button);
		 delete_button.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		new Delete_Product().f.setVisible(true);
		 		
		 	}
		 });
		 delete_button.setFont(new Font("Arial", Font.PLAIN, 23));
		 delete_button.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));
		 
		 
		 
		 //------------------------Refill stock BUTTON---------------------------------

		 JButton refill_stock = new JButton("Refill Stock");
		 refill_stock.setForeground(new Color(0, 0, 205));
		 refill_stock.setBackground(new Color(224, 255, 255));
		 refill_stock.setBounds(79, 345, 204, 83);
		 f.getContentPane().add(refill_stock);
		 refill_stock.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		new Refill_stock().f.setVisible(true);
		 		
		 	}
		 });
		 refill_stock.setFont(new Font("Arial", Font.PLAIN, 23));
		 refill_stock.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));




		f.setSize(800,600);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
		
	}

	public static void main(String args[]) {
		
		new Home_page();
	}
}
