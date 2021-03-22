package avocate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class modifierclient extends JFrame {
	
	private JPanel contentPane;
	private JTextField nom1;
	private JTextField prenom1;
	private JTextField tel1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifierclient frame = new modifierclient(2);
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
	public modifierclient(int i) {
		setResizable(false);
		
		connector c = new connector();
		Statement statement;
		String a1=null,a2=null,a3=null;
		try {
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery("SELECT * from client WHERE id_client ="+Integer.toString(i));
			while(r.next())
         {
				a1 = r.getString("nom");
				a2 = r.getString("prenom");
				a3 = r.getString("tel");
         }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		c.close();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 343);
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
		
		JLabel lblNewLabel = new JLabel("تصحيح معلومات الزبون");
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
		
		nom1 = new JTextField();
		nom1.setHorizontalAlignment(SwingConstants.RIGHT);
		nom1.setBounds(10, 86, 331, 40);
		panel.add(nom1);
		nom1.setColumns(10);
		
		prenom1 = new JTextField();
		prenom1.setHorizontalAlignment(SwingConstants.RIGHT);
		prenom1.setColumns(10);
		prenom1.setBounds(10, 148, 331, 40);
		panel.add(prenom1);
		
		tel1 = new JTextField();
		tel1.setHorizontalAlignment(SwingConstants.CENTER);
		tel1.setColumns(10);
		tel1.setBounds(10, 205, 331, 40);
		panel.add(tel1);
		
		JButton btnNewButton = new JButton("  تأكيد  ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nom1.getText().toString().equals("") || prenom1.getText().toString().equals("") || tel1.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "يجب ملئ كل المعلومات");
				}
				else {
					connector c = new connector();
					try {
					String query = "UPDATE client SET nom=?,prenom=?,tel=? where id_client=?";
					PreparedStatement statement1 = c.connector1().prepareStatement(query);
					statement1.setString(1, nom1.getText());
					statement1.setString(2, prenom1.getText());
					statement1.setString(3, tel1.getText());
					statement1.setInt(4, i);
					statement1.executeUpdate();
					}
					catch (SQLException e1) {
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
		
		nom1.setText(a1);
		prenom1.setText(a2);
		tel1.setText(a3);
	}

}
