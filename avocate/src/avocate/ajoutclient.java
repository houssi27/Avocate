package avocate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ajoutclient extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JTextField prenom;
	private JTextField tel;

	/**
	 * Launch the application.
	 */
	
	Boolean test(String a){
		Boolean z=false; 
		connector c = new connector();
		Statement statement;
		try {
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery("SELECT * from client WHERE tel ='"+a+"'");

			while(r.next())
         {
				z = true; 
				
         }
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		c.close();
		
		return z;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajoutclient frame = new ajoutclient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ajoutclient() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 343);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 204));
		panel_1.setBounds(0, 0, 440, 74);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("إضافة زبون");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 42));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 428, 62);
		panel_1.add(lblNewLabel);
		
		JLabel label = new JLabel("الإسم :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label.setBounds(353, 89, 81, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("اللقـب :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_1.setBounds(353, 155, 81, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("رقم الهاتف :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_2.setBounds(353, 212, 81, 25);
		panel.add(label_2);
		
		nom = new JTextField();
		nom.setHorizontalAlignment(SwingConstants.RIGHT);
		nom.setBounds(10, 86, 331, 40);
		panel.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setHorizontalAlignment(SwingConstants.RIGHT);
		prenom.setColumns(10);
		prenom.setBounds(10, 148, 331, 40);
		panel.add(prenom);
		
		tel = new JTextField();
		tel.setHorizontalAlignment(SwingConstants.CENTER);
		tel.setColumns(10);
		tel.setBounds(10, 205, 331, 40);
		panel.add(tel);
		
		JButton btnNewButton = new JButton("تأكيد الإضافة");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nom.getText().toString().equals("") || prenom.getText().toString().equals("") || tel.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "يجب ملئ كل المعلومات");
				}
				else {
					connector c = new connector();
					try {
						PreparedStatement statement1 = c.connector1().prepareStatement("INSERT INTO client (nom, prenom, tel) VALUES (?,?,?)");
						statement1.setString(1, nom.getText());
						statement1.setString(2, prenom.getText());
						statement1.setString(3, tel.getText());
						statement1.execute();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					c.close();
					client a1 = new client();
					a1.setVisible(true);
					dispose();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("image/confirm (1).png"));
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNewButton.setBounds(71, 265, 152, 40);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("إلغاء  ");
		btnNewButton_1.setIcon(new ImageIcon("image/delete (1).png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client a1 = new client();
				a1.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNewButton_1.setBounds(235, 265, 134, 40);
		panel.add(btnNewButton_1);
	}
}
