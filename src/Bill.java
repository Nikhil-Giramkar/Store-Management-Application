import javax.swing.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.border.*;


public class Bill {
	JFrame f;
	
	public Bill() {
		f = new JFrame();
		
		JLabel title = new JLabel("Create Bill");
		title.setForeground(new Color(153, 50, 204));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(267, 30, 250, 50);
		title.setFont(new Font("Serif", Font.PLAIN, 34));
		f.add(title);
		
		JLabel l1 = new JLabel("Sell ID of customer: ");
		l1.setBackground(new Color(255, 255, 255));
		l1.setFont(new Font("Arial", Font.PLAIN, 20));
		l1.setBounds(164, 150, 185, 28);
		f.add(l1);
		
		JTextField SID = new JTextField();
		SID.setBounds(395, 150, 173, 28);
		SID.setFont(new Font("Arial", Font.PLAIN, 20));
		f.add(SID);
		
		JButton create = new JButton("Create");
		create.setFont(new Font("Arial", Font.PLAIN, 23));
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					String sid = SID.getText();
					float total = 0;
					
					Connection connection = DriverManager
							.getConnection("jdbc:mysql://localhost:3306/store_management", "root", "myroot");
					
					Statement stmt = connection.createStatement();
					
					String sql = "SELECT prodName,amount FROM Sell where sellId='" + sid + "'";
					ResultSet rs = stmt.executeQuery(sql);
					
					File file = new File("Bill.txt");
					// if file doesnt exists, then create it
                    if (!file.exists()) 
                    {
                             file.createNewFile();
                    }
                    
                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    Date today = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formatter1 = new SimpleDateFormat("h:mm a");
                    
                    bw.write("\t\t\tStore Management Application\t\t\t");
                    bw.newLine();
                    bw.write("Date: " + formatter.format(today));
                    bw.newLine();
                    bw.write("Time: " + formatter1.format(today));
                    bw.newLine();
                    bw.newLine();
                    bw.write("Sell ID: " + sid);
                    bw.newLine();
                    bw.newLine();
                    bw.write("\tProduct Name\t\t\t|\t\tAmount");
                    bw.newLine();
                    bw.write("\t--------------------------------|-----------------------");
                    bw.newLine();
                    
                    while(rs.next())
                    {
                    	String prodName = rs.getString("prodName");
                    	String amt = rs.getString("amount");
                    	float amount = Float.parseFloat(amt);
                    	total += amount;
                    	bw.write("\t" + prodName + "\t\t\t|\t\t" + amt);
                    	bw.newLine();
                    	bw.flush();
                    }
                    
                    bw.write("\t--------------------------------|-----------------------");
                    bw.newLine();
                    bw.write("\tTotal Amount:\t\t\t|\t\t" + total);
                    
                    
                    bw.flush();
                    bw.close();
                    
				}catch(Exception exception) {
					exception.printStackTrace();
				}
				JOptionPane.showMessageDialog(create, "Bill ready!");
			}
		});
		create.setBounds(300, 250, 173, 50);
		f.add(create);
		
		f.setSize(800, 600);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
	}
	
	public static void main(String args[])
	{
		new Bill();
	}
}
