package avocate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import javafx.scene.control.ComboBox;

import javax.swing.ImageIcon;

public class ajoutaffaire1 extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JTextField prenom;
	private JTextField tel;
	private JTextField np;
	private JTextField num;
	private JTextField t1;
	private JTextField t2;
	public int a;
	private JComboBox etat, naw3, jiha;
	private JDateChooser date1,date2;
	private JFormattedTextField dd;
	private JFormattedTextField ddd;
	private MaskFormatter mf1;

	/**
	 * Launch the application.
	 */
	private String dateString(Date a) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(a);
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajoutaffaire1 frame = new ajoutaffaire1(2);
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
	public ajoutaffaire1(int i1) {
		setResizable(false);
		
		connector c = new connector();
		Statement statement;
		//-------------------------------------------NAW3 EL 9ADIYA----------------------
		try {
			String query2 = "select count(*) from type_affaire";
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery(query2);
			if(r.next()) {  a = Integer.parseInt(r.getString("count(*)"));}
		} catch (SQLException e2) {
			System.err.println(e2.getMessage());
		} 
		String[] box = new String[a];
		
		try {
			String query2 = "select naw3 from type_affaire";
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery(query2);

			int j = 0;
			while (r.next()) {
				box[j] = r.getString("naw3");
				j++;
			}

		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
		} 
		
		//---------------------------------------Tribunal-----------------------------
		
		try {
			String query2 = "select count(*) from tribunal";
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery(query2);
			if(r.next()) {  a = Integer.parseInt(r.getString("count(*)"));}
		} catch (SQLException e2) {
			System.err.println(e2.getMessage());
		} 
		String[] box2 = new String[a];
		
		try {
			String query2 = "select tri from tribunal";
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery(query2);

			int j = 0;
			while (r.next()) {
				box2[j] = r.getString("tri");
				j++;
			}

		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
		} 

		
		//-----------------------------------------------------------------------------
		String a1=null,a2=null,a3=null;
		try {
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery("SELECT * from client WHERE id_client ="+Integer.toString(i1));
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
		//**************
		c.close();//****
		//**************
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 455, 666);
		setSize(455, 666);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 204));
		panel_1.setBounds(0, 0, 445, 74);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("إضافة قضية");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 42));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 433, 62);
		panel_1.add(lblNewLabel);
		
		JLabel label = new JLabel("الإسم :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label.setBounds(353, 89, 81, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("اللقـب :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_1.setBounds(359, 130, 81, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("رقم الهاتف :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_2.setBounds(359, 170, 81, 25);
		panel.add(label_2);
		
		nom = new JTextField();
		nom.setEditable(false);
		nom.setHorizontalAlignment(SwingConstants.RIGHT);
		nom.setBounds(10, 86, 331, 30);
		panel.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setEditable(false);
		prenom.setHorizontalAlignment(SwingConstants.RIGHT);
		prenom.setColumns(10);
		prenom.setBounds(10, 128, 331, 30);
		panel.add(prenom);
		
		tel = new JTextField();
		tel.setEditable(false);
		tel.setHorizontalAlignment(SwingConstants.CENTER);
		tel.setColumns(10);
		tel.setBounds(10, 168, 331, 30);
		panel.add(tel);
		
		JButton btnNewButton = new JButton("تأكيد الإضافة");
		btnNewButton.setIcon(new ImageIcon("image/confirm (1).png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(num.getText().toString().equals("") || np.getText().toString().equals("") || dd.getText().toString().equals("  -  -    ") || ddd.getText().toString().equals("  -  -    ") || t1.getText().toString().equals("") || t2.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "يجب ملئ كل المعلومات");
				}
				else {
					if(Integer.parseInt(t1.getText().toString())< Integer.parseInt(t2.getText().toString())) {
						JOptionPane.showMessageDialog(null, "السعر الإجمالي اصغر من الدفعة الأولى !");
					}
					else {
						
						connector c = new connector();
						try {
							String sql22="INSERT INTO affaire (num, etat, type, nom, date_affaire, date_inscription, id_c, total, vers, jiha,pay) VALUES (?,?,?,?,?,?,?,?,?,?,0)";
							if(Integer.parseInt(t1.getText().toString())== Integer.parseInt(t2.getText().toString())) {
								sql22="INSERT INTO affaire (num, etat, type, nom, date_affaire, date_inscription, id_c, total, vers, jiha,pay) VALUES (?,?,?,?,?,?,?,?,?,?,1)";
							}
							PreparedStatement statement1 = c.connector1().prepareStatement(sql22);
							
							statement1.setString(1, num.getText());
							statement1.setString(2, (String)etat.getSelectedItem());
							statement1.setString(3, (String)naw3.getSelectedItem());
							statement1.setString(4, np.getText());
							statement1.setString(5, dd.getText());
							statement1.setString(6, ddd.getText());
							statement1.setInt(7, i1);
							statement1.setInt(8, Integer.parseInt(t1.getText()));
							statement1.setInt(9, Integer.parseInt(t2.getText()));
							statement1.setString(10, (String)jiha.getSelectedItem());
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
				
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNewButton.setBounds(71, 588, 152, 40);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("إلغاء");
		btnNewButton_1.setIcon(new ImageIcon("image/delete (1).png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client a1 = new client();
				a1.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNewButton_1.setBounds(235, 588, 134, 40);
		panel.add(btnNewButton_1);
		
		JLabel label_3 = new JLabel("تاريخ الجلسة :");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_3.setBounds(348, 285, 83, 25);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("تاريخ التسجيل :");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_4.setBounds(322, 322, 109, 25);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("رقم القضية :");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_5.setBounds(348, 359, 83, 25);
		panel.add(label_5);
		
		etat = new JComboBox();
		etat.setModel(new DefaultComboBoxModel(new String[] {"في حق", "ضـــــد"}));
		etat.setMaximumRowCount(2);
		etat.setBounds(348, 396, 83, 27);
		panel.add(etat);
		
		JLabel label_6 = new JLabel("الإسم و اللقـب :");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_6.setBounds(239, 396, 107, 25);
		panel.add(label_6);
		
		JLabel label_8 = new JLabel("نوع القضية :");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_8.setBounds(336, 207, 95, 25);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("جهة القضائية :");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_9.setBounds(336, 244, 95, 25);
		panel.add(label_9);
		
		naw3 = new JComboBox();
		naw3.setModel(new DefaultComboBoxModel(box));
		naw3.setBounds(10, 208, 314, 27);
		panel.add(naw3);
		
		jiha = new JComboBox();
		jiha.setModel(new DefaultComboBoxModel(box2));
		jiha.setBounds(10, 245, 314, 27);
		panel.add(jiha);
		
		np = new JTextField();
		np.setHorizontalAlignment(SwingConstants.RIGHT);
		np.setBounds(10, 395, 230, 30);
		panel.add(np);
		np.setColumns(10);
		
		num = new JTextField();
		num.setHorizontalAlignment(SwingConstants.RIGHT);
		num.setColumns(10);
		num.setBounds(194, 359, 130, 26);
		panel.add(num);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 435, 421, 145);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_7 = new JLabel("مصاريف الأتعاب :");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_7.setBounds(246, 6, 169, 25);
		panel_2.add(label_7);
		
		JLabel label_10 = new JLabel("مبلغ الإجمالي :");
		label_10.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(312, 43, 103, 40);
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("الدفعة الأولى :");
		label_11.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setBounds(312, 89, 103, 40);
		panel_2.add(label_11);
		
		t1 = new JTextField();
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setForeground(new Color(0, 102, 0));
		t1.setFont(new Font("Dialog", Font.BOLD, 20));
		t1.setBounds(6, 43, 294, 40);
		panel_2.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setHorizontalAlignment(SwingConstants.CENTER);
		t2.setForeground(new Color(0, 102, 0));
		t2.setFont(new Font("Dialog", Font.BOLD, 20));
		t2.setColumns(10);
		t2.setBounds(6, 90, 294, 40);
		panel_2.add(t2);
		
		nom.setText(a1);
		prenom.setText(a2);
		tel.setText(a3);
		
try {
			
			mf1 = new MaskFormatter("##-##-####");
			 //mf1.setPlaceholderCharacter('_');
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}
		
		
	
		
		dd = new JFormattedTextField(mf1);
		dd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		dd.setHorizontalAlignment(SwingConstants.CENTER);
		dd.setBounds(172, 285, 152, 26);
		panel.add(dd);
		dd.setColumns(10);
		
		ddd = new JFormattedTextField(mf1);
		ddd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ddd.setHorizontalAlignment(SwingConstants.CENTER);
		ddd.setBounds(172, 322, 152, 26);
		panel.add(ddd);
		ddd.setColumns(10);
		
		JLabel lblddmmyyyy = new JLabel("(dd-mm-yyyy)");
		lblddmmyyyy.setForeground(Color.LIGHT_GRAY);
		lblddmmyyyy.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblddmmyyyy.setBounds(92, 291, 76, 16);
		panel.add(lblddmmyyyy);
		
		JLabel label_12 = new JLabel("(dd-mm-yyyy)");
		label_12.setForeground(Color.LIGHT_GRAY);
		label_12.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label_12.setBounds(92, 328, 76, 16);
		panel.add(label_12);
	}
}
