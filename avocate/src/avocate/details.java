package avocate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class details extends JFrame {

	private JPanel contentPane,panel_3;
	private DefaultTableModel model;
	private String[] entetes = {"تاريخ الجلسة","رقم القضية"};
	private JTable table;
	private DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JLabel naw3,jiha,siyed,date2,date,did,ra9m,label1;
	String a11=null,a22=null,a33=null,a44=null,a55=null,a66=null,a77=null,a88=null,a99=null,a007=null;
			int a000=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					details frame = new details(2,1);
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
	public details(int i2, int i3) {
		setResizable(false);
		
		connector c = new connector();
		Statement statement;
		try {
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery("SELECT * from client WHERE id_client ="+Integer.toString(i2));
			while(r.next())
         {
				a11 = r.getString("nom");
				a22 = r.getString("prenom");
				a33 = r.getString("tel");
         }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//-----------------------------------------------------------------------------
		
		
		//**************
		c.close();//****
		//**************
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 204));
		panel_1.setBounds(0, 0, 890, 91);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("تفـــــــــــــاصيل");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 890, 91);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 315, 421, 251);
		panel.add(scrollPane);
		
		model = new DefaultTableModel(null, entetes);
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				connector c = new connector();
				Statement statement;
				String a11=null,a22=null,a33=null;
				try {
					statement = c.connector1().createStatement();
					ResultSet r = statement.executeQuery("SELECT * from affaire WHERE num='"+table.getValueAt(table.getSelectedRow(), 1)+"'");
					while(r.next())
		         {
						a44 = r.getString("type");
						a55 = r.getString("etat");
						a66 = r.getString("nom");
						a77 = r.getString("date_inscription");
						a88 = r.getString("total");
						a99 = r.getString("vers");
						a007 = r.getString("jiha");
						a000= r.getInt("pay");
		         }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c.close();
				ra9m.setText((String)table.getValueAt(table.getSelectedRow(),1));
				naw3.setText(a44);
				jiha.setText(a007);
				date2.setText((String)table.getValueAt(table.getSelectedRow(),0));
				date.setText(a77);
				did.setText(a55);
				siyed.setText(a66);
				t1.setText(a88);
				t2.setText(a99);
				int sum = Integer.parseInt(a88)-Integer.parseInt(a99);
				t3.setText(Integer.toString(sum));
				if(a000==1) {
					CardLayout cl = (CardLayout)(panel_3.getLayout());
				    cl.show(panel_3, "pan2");
				    label1.setText(a88);
				}
				else {
					CardLayout cl = (CardLayout)(panel_3.getLayout());
				    cl.show(panel_3, "pan1");
				}
			}
		});
		scrollPane.setViewportView(table);
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		 DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	      renderer.setHorizontalAlignment(JLabel.RIGHT);
	      column.setCellRenderer(renderer);
	      column1.setCellRenderer(renderer);
	      

			try {
				statement = c.connector1().createStatement();
				ResultSet r = statement.executeQuery("SELECT * from affaire WHERE id_c="+i2);
				
				while (model.getRowCount()!=0) model.removeRow(0);
				while(r.next())
	         {
					String a1 = r.getString("date_affaire");
					String a2 = r.getString("num");
					model.addRow(new Object[]{a1,a2});
	         }
				table.repaint();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			c.close();
			
		
		JLabel label_5 = new JLabel("رقم القضية :");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		label_5.setBounds(759, 103, 125, 40);
		panel.add(label_5);
		
		ra9m = new JLabel("");
		ra9m.setForeground(Color.BLUE);
		ra9m.setHorizontalAlignment(SwingConstants.CENTER);
		ra9m.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		ra9m.setBounds(443, 103, 304, 40);
		panel.add(ra9m);
		
		JLabel label_7 = new JLabel("تاريخ الجلسة :");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		label_7.setBounds(733, 286, 146, 40);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("تاريخ التسجيل :");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		label_8.setBounds(733, 338, 146, 40);
		panel.add(label_8);
		
		JLabel label_10 = new JLabel("نوع القضية :");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		label_10.setBounds(754, 182, 125, 40);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("جهة القضائية :");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		label_11.setBounds(738, 234, 141, 40);
		panel.add(label_11);
		
		naw3 = new JLabel("");
		naw3.setHorizontalAlignment(SwingConstants.RIGHT);
		naw3.setForeground(Color.BLUE);
		naw3.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		naw3.setBounds(443, 182, 282, 40);
		panel.add(naw3);
		
		jiha = new JLabel("");
		jiha.setHorizontalAlignment(SwingConstants.RIGHT);
		jiha.setForeground(Color.BLUE);
		jiha.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		jiha.setBounds(443, 234, 282, 40);
		panel.add(jiha);
		
		date2 = new JLabel("");
		date2.setHorizontalAlignment(SwingConstants.RIGHT);
		date2.setForeground(Color.BLUE);
		date2.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		date2.setBounds(443, 286, 282, 40);
		panel.add(date2);
		
		date = new JLabel("");
		date.setHorizontalAlignment(SwingConstants.RIGHT);
		date.setForeground(Color.BLUE);
		date.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		date.setBounds(443, 338, 282, 40);
		panel.add(date);
		
		did = new JLabel("");
		did.setForeground(Color.BLUE);
		did.setHorizontalAlignment(SwingConstants.RIGHT);
		did.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		did.setBounds(733, 390, 146, 40);
		panel.add(did);
		
		siyed = new JLabel("");
		siyed.setForeground(Color.BLUE);
		siyed.setHorizontalAlignment(SwingConstants.RIGHT);
		siyed.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		siyed.setBounds(443, 390, 282, 40);
		panel.add(siyed);
		
		panel_3 = new JPanel();
		panel_3.setBounds(463, 440, 421, 200);
		panel.add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_3.add(panel_4, "pan1");
		
		JLabel label_13 = new JLabel("مصاريف الأتعاب :");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_13.setBounds(246, 6, 169, 25);
		panel_4.add(label_13);
		
		JLabel label_18 = new JLabel("مبلغ الإجمالي :");
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_18.setBounds(312, 43, 103, 40);
		panel_4.add(label_18);
		
		JLabel label_19 = new JLabel("الدفعة الأولى :");
		label_19.setHorizontalAlignment(SwingConstants.RIGHT);
		label_19.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_19.setBounds(312, 89, 103, 40);
		panel_4.add(label_19);
		
		t1 = new JTextField();
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setForeground(new Color(0, 0, 0));
		t1.setFont(new Font("Dialog", Font.BOLD, 20));
		t1.setColumns(10);
		t1.setBounds(107, 43, 193, 40);
		panel_4.add(t1);
		
		t2 = new JTextField();
		t2.setText("\n");
		t2.setEditable(false);
		t2.setHorizontalAlignment(SwingConstants.CENTER);
		t2.setForeground(new Color(0, 102, 0));
		t2.setFont(new Font("Dialog", Font.BOLD, 20));
		t2.setColumns(10);
		t2.setBounds(107, 90, 193, 40);
		panel_4.add(t2);
		
		JLabel label_22 = new JLabel("الدفعة الأولى :");
		label_22.setHorizontalAlignment(SwingConstants.RIGHT);
		label_22.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_22.setBounds(312, 141, 103, 40);
		panel_4.add(label_22);
		
		t3 = new JTextField();
		t3.setHorizontalAlignment(SwingConstants.CENTER);
		t3.setForeground(new Color(204, 0, 0));
		t3.setFont(new Font("Dialog", Font.BOLD, 20));
		t3.setColumns(10);
		t3.setBounds(107, 142, 193, 40);
		panel_4.add(t3);
		
		JButton btnNewButton = new JButton("دفع الكلي ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int z = JOptionPane.showConfirmDialog(null, "هل أنت متأكدةأن الزبون دفع كل المبلغ ؟");
				if(z == JOptionPane.YES_OPTION) {
					connector c = new connector();
					try {
					String query = "UPDATE affaire SET pay=? where num=?";
					PreparedStatement statement1 = c.connector1().prepareStatement(query);
						statement1.setInt(1, 1);
						statement1.setString(2, (String) table.getValueAt(table.getSelectedRow(), 1));
						statement1.executeUpdate();
						details a999999 = new details(i2,i3);
						a999999.setVisible(true);
						dispose();
						
					    
					}
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					c.close();
				}
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNewButton.setBounds(6, 142, 89, 39);
		panel_4.add(btnNewButton);
		
		JButton button = new JButton("تغيير المبلغ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(t1.getText()) < Integer.parseInt(t2.getText())) {
					JOptionPane.showMessageDialog(null, "المبلغ الإجمالي اصغر من الدفعة الأولى !");
				}
				else {
					connector c = new connector();
					try {
					String query = "UPDATE affaire SET total=? where num=?";
					PreparedStatement statement1 = c.connector1().prepareStatement(query);
					int a= JOptionPane.showConfirmDialog(null, "؟ "+t1.getText().toString()+" إلى "+a88+" هل تريدي حقا المبلغ من ");
					if(a==JOptionPane.YES_OPTION) {
						statement1.setString(1, t1.getText());
						statement1.setString(2, (String) table.getValueAt(table.getSelectedRow(), 1));
						statement1.executeUpdate();
						details a1 = new details(i2,i3);
						a1.setVisible(true);
						dispose();
					}
					
					}
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					c.close();
				}
				
				
			}
		});
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		button.setBounds(6, 43, 89, 39);
		panel_4.add(button);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_3.add(panel_5, "pan2");
		
		JLabel label_20 = new JLabel("مصاريف الأتعاب مدفوعة");
		label_20.setForeground(new Color(0, 102, 0));
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		label_20.setBounds(6, 20, 409, 48);
		panel_5.add(label_20);
		
		JLabel label_21 = new JLabel("مبلغ الإجمالي :");
		label_21.setForeground(new Color(0, 102, 0));
		label_21.setHorizontalAlignment(SwingConstants.RIGHT);
		label_21.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_21.setBounds(285, 101, 130, 40);
		panel_5.add(label_21);
		
		label1 = new JLabel("");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setForeground(new Color(0, 102, 0));
		label1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label1.setBounds(16, 101, 257, 40);
		panel_5.add(label1);
		
		JButton button_2 = new JButton("الرجوع");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client a4 = new client();
				a4.setVisible(true);
				dispose();
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		button_2.setBounds(293, 578, 138, 62);
		panel.add(button_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 103, 421, 200);
		panel.add(panel_2);
		
		JLabel label = new JLabel("معلومات الزبون :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setBounds(246, 6, 169, 33);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("الإسم :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_1.setBounds(354, 51, 61, 25);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("اللقـب :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_2.setBounds(354, 88, 61, 25);
		panel_2.add(label_2);
		
		JLabel prenom = new JLabel("");
		prenom.setHorizontalAlignment(SwingConstants.RIGHT);
		prenom.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		prenom.setBounds(6, 56, 345, 25);
		panel_2.add(prenom);
		
		JLabel nom = new JLabel("");
		nom.setHorizontalAlignment(SwingConstants.RIGHT);
		nom.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		nom.setBounds(6, 88, 345, 25);
		panel_2.add(nom);
		
		JLabel label_27 = new JLabel("رقم الهاتف :");
		label_27.setHorizontalAlignment(SwingConstants.RIGHT);
		label_27.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_27.setBounds(307, 125, 108, 25);
		panel_2.add(label_27);
		
		JLabel tel = new JLabel("");
		tel.setHorizontalAlignment(SwingConstants.RIGHT);
		tel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		tel.setBounds(6, 125, 314, 25);
		panel_2.add(tel);
		
		JLabel label_29 = new JLabel("عدد القضايا :");
		label_29.setHorizontalAlignment(SwingConstants.RIGHT);
		label_29.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_29.setBounds(307, 162, 108, 25);
		panel_2.add(label_29);
		
		JLabel nombre = new JLabel("");
		nombre.setHorizontalAlignment(SwingConstants.RIGHT);
		nombre.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		nombre.setBounds(220, 162, 100, 25);
		panel_2.add(nombre);
		
		nom.setText(a11);
		prenom.setText(a22);
		tel.setText(a33);
		nombre.setText(Integer.toString(i3));
		
		JButton button_3 = new JButton("العودة الى الصفحة الرئيسية");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accueil a4 = new accueil();
				a4.setVisible(true);
				dispose();
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		button_3.setBounds(10, 578, 271, 62);
		panel.add(button_3);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0) {
					JOptionPane.showMessageDialog(null,"يجب إختيار قضية أولا");
			}
			else {
				connector c = new connector();
			try {
				PreparedStatement statement1 = c.connector1().prepareStatement("DELETE FROM affaire WHERE num ='"+(String)table.getValueAt(table.getSelectedRow(), 1)+"'");

					int a= JOptionPane.showConfirmDialog(null, "هل تريدي حقا حذف هذه القضية ؟");
					if(a==JOptionPane.YES_OPTION) {
						statement1.execute();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			c.close();
			details a1 = new details(i2,i3-1);
			a1.setVisible(true);
			dispose();
			}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("image/rubbish-bin.png"));
		btnNewButton_1.setBounds(463, 103, 59, 56);
		panel.add(btnNewButton_1);
		
	}
}
