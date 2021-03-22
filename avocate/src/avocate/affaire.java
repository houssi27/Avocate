package avocate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class affaire extends JFrame {

	private JPanel contentPane;

	public DefaultTableModel model;
	private String[] entetes = {"المبلغ المتبقي","رقم الهاتف","الإسم","اللقب","رقم القضية"};
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					affaire frame = new affaire();
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
	public affaire() {
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
		
		JLabel lblNewLabel = new JLabel("القضايا الغير المدفوعة");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 890, 91);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 874, 509);
		panel.add(scrollPane);
		
		
		model = new DefaultTableModel(null, entetes);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);
		TableColumn column2 = columnModel.getColumn(2);
		TableColumn column3 = columnModel.getColumn(3);
		TableColumn column4 = columnModel.getColumn(4);
		 DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	      renderer.setHorizontalAlignment(JLabel.RIGHT);
	      column.setCellRenderer(renderer);
	      column1.setCellRenderer(renderer);
	      column2.setCellRenderer(renderer);
	      column3.setCellRenderer(renderer);
	      column4.setCellRenderer(renderer);
	      
		
		JButton button = new JButton("العودة الى الصفحة الرئيسية      ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accueil a45 = new accueil();
				a45.setVisible(true);
				dispose();
			}
		});
		button.setIcon(new ImageIcon("image/return.png"));
		button.setHorizontalAlignment(SwingConstants.RIGHT);
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		button.setBounds(621, 618, 263, 44);
		panel.add(button);
		//textArea.setHorizontalAlignment(SwingConstants.RIGHT);
		
		connector c = new connector();
		Statement statement;
		try {
			statement = c.connector1().createStatement();
			ResultSet r = statement.executeQuery("SELECT * from client,affaire where id_client=id_c and pay=0");
			while (model.getRowCount()!=0) model.removeRow(0);
			while(r.next())
         {
				String a1 = r.getString("tel");
				String a2 = r.getString("prenom");
				String a3 = r.getString("nom");
				String a4 = r.getString("num");
				int a5 = r.getInt("total");
				int a6 = r.getInt("vers");
				
				model.addRow(new Object[]{a5-a6,a1,a2,a3,a4});
         }
			table.repaint();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		c.close();
		
		
		
	}
}
