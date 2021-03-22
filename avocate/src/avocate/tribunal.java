package avocate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class tribunal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel model;
	private String[] entetes = {"الجهة القضائية                                  "};
	private JTextField textField;
	private JLabel t1;

	/**
	 * Launch the application.
	 */
	
	Boolean test(String a){
		Boolean z=false; 
		connector c = new connector();
		Statement statement;
		try {
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery("SELECT * from tribunal WHERE tri ='"+a+"'");

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
					tribunal frame = new tribunal();
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
	public tribunal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 677, 465);
		setSize(677, 465);
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
		panel_1.setBounds(0, 0, 667, 82);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("الجهة القضائية");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 655, 70);
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 94, 330, 333);
		panel.add(scrollPane);
		
		model = new DefaultTableModel(null, entetes);
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				t1.setText((String)table.getValueAt(table.getSelectedRow(), 0));
			}
		});
		scrollPane.setViewportView(table);
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(0); 
		 DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	      renderer.setHorizontalAlignment(JLabel.RIGHT);
	      column.setCellRenderer(renderer);
		
		connector c = new connector();
		Statement statement;
		try {
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery("SELECT * from tribunal");
			
			while (model.getRowCount()!=0) model.removeRow(0);
			while(r.next())
         {
				String a1 = r.getString("tri");
				model.addRow(new Object[]{a1});
         }
			table.repaint();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		c.close();
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(423, 146, 238, 40);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("إضافة جهة قضائية :");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(468, 94, 193, 40);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connector c = new connector();
				try {
					PreparedStatement statement1 = c.connector1().prepareStatement("INSERT INTO tribunal (tri) VALUES (?)");
					if(textField.getText().toString().equals("")) {
						JOptionPane.showMessageDialog(null,"يجب إدخال جهة القضية أولا");
					}
					else if (test(textField.getText().toString())) {
						JOptionPane.showMessageDialog(null,"جهة قضائية موجودة");
					}
					else {
						statement1.setString(1, textField.getText());
						statement1.execute();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c.close();
				textField.setText("");
				table.repaint();
				tribunal a1 = new tribunal();
				a1.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("image/confirm (1).png"));
		btnNewButton.setBounds(348, 146, 63, 40);
		panel.add(btnNewButton);
		
		JButton button = new JButton("العودة الى الصفحة الرئيسية          ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accueil a1 = new accueil();
				a1.setVisible(true);
				dispose();
			}
		});
		button.setIcon(new ImageIcon("/Users/fd/eclipse-workspace/avocate/src/return.png"));
		button.setHorizontalAlignment(SwingConstants.RIGHT);
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		button.setBounds(390, 383, 271, 44);
		panel.add(button);
		
		JLabel label = new JLabel("حذف جهة قضائية :");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		label.setBounds(468, 198, 193, 40);
		panel.add(label);
		
		t1 = new JLabel("");
		t1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		t1.setHorizontalAlignment(SwingConstants.RIGHT);
		t1.setBounds(423, 250, 238, 40);
		panel.add(t1);
		
		JButton b1 = new JButton("");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connector c = new connector();
				try {
					PreparedStatement statement1 = c.connector1().prepareStatement("DELETE FROM tribunal WHERE tri ='"+t1.getText().toString()+"'");
					if(t1.getText().toString().equals("")) {
						JOptionPane.showMessageDialog(null,"يجب إختيار جهة القضية أولا");
					}
					else {
						int a= JOptionPane.showConfirmDialog(null, "هل تريدي حقا حذف هذه الجهة القضائية ؟");
						if(a==JOptionPane.YES_OPTION) {
							statement1.execute();
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c.close();
				textField.setText("");
				tribunal a1 = new tribunal();
				a1.setVisible(true);
				dispose();
			}
		});
		b1.setIcon(new ImageIcon("image/rubbish-bin (1).png"));
		b1.setBounds(348, 250, 63, 40);
		panel.add(b1);
	}
}
