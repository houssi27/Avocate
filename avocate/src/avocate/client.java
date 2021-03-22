package avocate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class client extends JFrame {

	private JPanel contentPane;
	public DefaultTableModel model;
	private String[] entetes = {"رقم الهاتف","الإسم","اللقب","id"};
	private JTable table;
	private JTextField textField;
	private JLabel nom, prenom, telephone, nombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client frame = new client();
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
	public client() {
		setResizable(false);
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
		
		JLabel lblNewLabel = new JLabel("الزبــــــــــــــــــــــــائن");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 890, 91);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 103, 400, 559);
		panel.add(scrollPane);
		
		model = new DefaultTableModel(null, entetes);
		table = new JTable(model);
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		 DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	      renderer.setHorizontalAlignment(JLabel.RIGHT);
	      column.setCellRenderer(renderer);
	      column1.setCellRenderer(renderer);
	      column2.setCellRenderer(renderer);
	      column3.setCellRenderer(renderer);
	      
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nom.setText((String)table.getValueAt(table.getSelectedRow(), 2));
				prenom.setText((String)table.getValueAt(table.getSelectedRow(), 1));
				telephone.setText((String)table.getValueAt(table.getSelectedRow(), 0));
				//nom.setText((String)table.getValueAt(table.getSelectedRow(), 3));
				connector c = new connector();
				Statement statement;
				try {
					statement = c.connector1().createStatement();
					ResultSet r = statement.executeQuery("SELECT count(*) from affaire WHERE id_c ='"+Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),3))+"'");
					while(r.next())
		         {
						nombre.setText(r.getString("count(*)"));
		         }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c.close();
				
			}
		});
		scrollPane.setViewportView(table);
		
		connector c = new connector();
		Statement statement;
		try {
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery("SELECT * from client");
			
			while (model.getRowCount()!=0) model.removeRow(0);
			while(r.next())
         {
				String a1 = r.getString("tel");
				String a2 = r.getString("prenom");
				String a3 = r.getString("nom");
				String a4 = r.getString("id_client");
				model.addRow(new Object[]{a1,a2,a3,a4});
         }
			table.repaint();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		c.close();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(418, 283, 466, 323);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("معلومات الزبون :");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(291, 6, 169, 33);
		panel_2.add(lblNewLabel_1);
		
		JButton detail = new JButton("");
		detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0) {
					JOptionPane.showMessageDialog(null,"يجب إختيار زبون أولا");
				}
				else {
					details a3 = new details(Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),3)),Integer.parseInt(nombre.getText()));
					a3.setVisible(true);
					dispose();
				}
				
			}
		});
		detail.setIcon(new ImageIcon("image/seo.png"));
		detail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		detail.setBounds(34, 204, 100, 80);
		panel_2.add(detail);
		
		JLabel lblNewLabel_2 = new JLabel("الإسم :");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(399, 51, 61, 25);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("اللقـب :");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(399, 88, 61, 25);
		panel_2.add(lblNewLabel_3);
		
		prenom = new JLabel("");
		prenom.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		prenom.setHorizontalAlignment(SwingConstants.RIGHT);
		prenom.setBounds(19, 56, 377, 25);
		panel_2.add(prenom);
		
		nom = new JLabel("");
		nom.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		nom.setHorizontalAlignment(SwingConstants.RIGHT);
		nom.setBounds(19, 88, 377, 25);
		panel_2.add(nom);
		
		JButton naffaire = new JButton("");
		naffaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0) {
					JOptionPane.showMessageDialog(null,"يجب إختيار زبون أولا");
				}
				else {
					ajoutaffaire1 a4 = new ajoutaffaire1(Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),3)));
					a4.setVisible(true);
					dispose();
				}
				
			}
		});
		naffaire.setIcon(new ImageIcon("image/writing.png"));
		naffaire.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		naffaire.setBounds(190, 204, 100, 80);
		panel_2.add(naffaire);
		
		JButton modifier = new JButton("");
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0) {
					JOptionPane.showMessageDialog(null,"يجب إختيار زبون أولا");
				}
				else {
					modifierclient a3 = new modifierclient(Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),3)));
					a3.setVisible(true);
					dispose();
				}
				
			}
		});
		modifier.setIcon(new ImageIcon("image/user.png"));
		modifier.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		modifier.setBounds(335, 204, 100, 80);
		panel_2.add(modifier);
		
		JLabel lblNewLabel_4 = new JLabel("التفاصيل");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(34, 288, 100, 33);
		panel_2.add(lblNewLabel_4);
		
		JLabel label = new JLabel("قضية جديدة");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		label.setBounds(190, 288, 100, 33);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("تصحيح المعلومات");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(335, 288, 100, 33);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("رقم الهاتف :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_2.setBounds(352, 125, 108, 25);
		panel_2.add(label_2);
		
		telephone = new JLabel("");
		telephone.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		telephone.setHorizontalAlignment(SwingConstants.RIGHT);
		telephone.setBounds(19, 125, 346, 25);
		panel_2.add(telephone);
		
		JLabel label_4 = new JLabel("عدد القضايا :");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_4.setBounds(352, 162, 108, 25);
		panel_2.add(label_4);
		
		nombre = new JLabel("");
		nombre.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		nombre.setHorizontalAlignment(SwingConstants.RIGHT);
		nombre.setBounds(265, 162, 100, 25);
		panel_2.add(nombre);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==0) {
						JOptionPane.showMessageDialog(null,"يجب إختيار زبون أولا");
				}
				else {
					connector c = new connector();
				try {
					PreparedStatement statement1 = c.connector1().prepareStatement("DELETE FROM client WHERE id_client ='"+(String)table.getValueAt(table.getSelectedRow(), 3)+"'");
	
						int a= JOptionPane.showConfirmDialog(null, "هل تريدي حقا حذف هذا زبون ؟");
						if(a==JOptionPane.YES_OPTION) {
							statement1.execute();
					}
					
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
		btnNewButton_2.setIcon(new ImageIcon("image/rubbish-bin.png"));
		btnNewButton_2.setBounds(6, 6, 61, 50);
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("العودة الى الصفحة الرئيسية          ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accueil a1 = new accueil();
				a1.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("image/return.png"));
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNewButton.setBounds(613, 618, 271, 44);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("إضافة زبون");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(727, 224, 141, 25);
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajoutclient a2 = new ajoutclient();
				a2.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("image/add-user.png"));
		btnNewButton_1.setBounds(727, 103, 141, 124);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("البحث :");
		lblNewLabel_6.setIcon(new ImageIcon("image/worker.png"));
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(493, 103, 180, 33);
		panel.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				connector c = new connector();
				Statement statement;
				try {
					statement = c.connector1().createStatement();
					ResultSet r = statement.executeQuery("SELECT * from client WHERE nom like '%"+textField.getText().toString()+"%' OR prenom like '%"+textField.getText().toString()+ "%' OR tel like '%"+textField.getText().toString()+ "%'");
					
					while (model.getRowCount()!=0) model.removeRow(0);
					while(r.next())
		         {
						String a1 = r.getString("tel");
						String a2 = r.getString("prenom");
						String a3 = r.getString("nom");
						String a4 = r.getString("id_client");
						model.addRow(new Object[]{a1,a2,a3,a4});
		         }
					table.repaint();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c.close();
			}
		});
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(470, 148, 203, 44);
		panel.add(textField);
		textField.setColumns(10);
	}
}
