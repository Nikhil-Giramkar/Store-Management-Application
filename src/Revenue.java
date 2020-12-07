import javax.swing.*;


import java.sql.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.*;



public class Revenue {
	JFrame f;
	
	
	Revenue(){
		
		f = new JFrame();
		

		
		JLabel title = new JLabel("Revenue");
		title.setBounds(126, 11, 122, 50);
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		
		JLabel l1 = new JLabel("Product ID: ");
		l1.setBounds(36, 74, 100, 20);
		
		JLabel l2 = new JLabel("Product Name: "); 
		l2.setBounds(25, 145, 100, 20); 
	
		
		JLabel l3 = new JLabel("Price/Item in Rs. ");
		l3.setBounds(25, 176, 100, 20);
		
		JLabel l4 = new JLabel("Stock available");
		l4.setBounds(25, 207, 100, 20);
		
		JLabel l5 = new JLabel("Quantity to be bought");
		l5.setBounds(25, 238, 136, 20);
		
		JLabel l6 = new JLabel("Amount");
		l6.setBounds(25, 269, 100, 20);
		
		
		//----------ALL INPUT FIELDS--------------------------
		
		JTextField PID = new JTextField();
		PID.setBounds(171, 72, 150, 20);
		
		JTextField PNAME = new JTextField();
		PNAME.setBounds(171, 145, 150, 20);
	
		
		JTextField PRICE_PER_ITEM = new JTextField();
		PRICE_PER_ITEM.setBounds(171, 176, 150, 20);
		
		JTextField STOCK = new JTextField();
	    STOCK.setBounds(171, 207, 150, 20);	
	    
	    
	    JTextField Quantity = new JTextField();
	    Quantity.setBounds(171, 238, 150, 20);	
	    
	    JTextField Amount = new JTextField();
	    Amount.setBounds(171, 269, 150, 20);	
	    

	
	    //--------------------------------------OK BUTTON-------------------
					 
	    JButton OK = new JButton("OK");
		  OK.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  
		  		String prodId = PID.getText();
		  		
		  		if(prodId.length()==0)
		  			JOptionPane.showMessageDialog(OK, "Enter a valid Product ID");
		  		
		  	}
		  });
		  OK.setBounds(133,105,71,30);
		  OK.setBorder(new BevelBorder(BevelBorder.RAISED));
		  	
		  		
		 //----------------------------------SUBMIT BUTTON--------------------------- 
		  
		  JButton submit = new JButton("Submit");
		  submit.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
	
		  		
		  		
		  		
				try {
			  		String qty = Quantity.getText();
			  		int qtyint = Integer.valueOf(qty);
			  		if(qtyint==0)
			  			JOptionPane.showMessageDialog(submit, "Quantity can't be zero");
			  		
			  		}catch(Exception E) {
			  			JOptionPane.showMessageDialog(submit, "Enter valid Quantity to be bought, "+E);

			  		}
		  	
		  	
		  
		  	/*		try {
		  				//Connection
		  				
		  				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_teacher_app","root","12345");
		  		
		  			PreparedStatement sta = connection.prepareStatement("insert into student_teacher_gui values(?,?,?,?,?,?,?)");
		  			sta.setString(1, full_name);
		  			sta.setInt(2, roll_no);
		  			sta.setString(3, residence);
		  			sta.setString(4, Gender);
		  			sta.setString(5, BG);
		  			sta.setString(6, Rh);
		  			sta.setString(7, designation);
		  			
		  			sta.executeUpdate();
		  			JOptionPane.showMessageDialog(submit, "Form Submitted Succesfully");
		  			
		  			}catch(Exception exception) {
		  				exception.printStackTrace();
		  			}
		  			
*/
		  	
		  		
		  	}
		  });
		  submit.setBounds(61,311,100,30);
		  
		  submit.setBorder(new BevelBorder(BevelBorder.RAISED));
		  	
		  
		 //--------------------------RESET BUTTON----------------------------- 
		  JButton reset = new JButton("Reset");
		  reset.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		PID.setText("");
		  		PNAME.setText("");
		  		PRICE_PER_ITEM.setText("");
		  		STOCK.setText("");
		  		
		  		
		  		JOptionPane.showMessageDialog(reset, "Form reset successfully");
		  		
		  	}
		  });
		  reset.setBounds(198,311,100,30);
		  reset.setBorder(new BevelBorder(BevelBorder.RAISED));
		  	
		  
		 
		//----------ADD ALL ENTITIES TO THE FORM-----------------
		f.getContentPane().add(title);
		f.getContentPane().add(l1);
		f.getContentPane().add(l2);
		f.getContentPane().add(l3);
		f.getContentPane().add(l4);
		f.getContentPane().add(l5);
		f.getContentPane().add(l6);

	
		f.getContentPane().add(PID); 
	    f.getContentPane().add(PNAME);
		f.getContentPane().add(PRICE_PER_ITEM);
		f.getContentPane().add(Quantity);
		f.getContentPane().add(Amount);

		
		f.getContentPane().add(STOCK);
	
		f.getContentPane().add(submit);
		f.getContentPane().add(reset);
		
		f.getContentPane().add(OK);


		
			f.setSize(800,500);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
	}

	public static void main(String args[]) {
		
		new Revenue();
	}
}

