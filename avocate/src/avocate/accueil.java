package avocate;

import java.awt.BorderLayout;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayInputStream;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class accueil extends JFrame {
	
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accueil frame = new accueil();
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
	public accueil() {
		setResizable(false);
		
		/*try {
			String s=new String ("السلام".getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
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
		
		JLabel label = new JLabel("الزبائن");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(137, 283, 200, 43);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 204));
		panel_1.setBounds(0, 0, 890, 91);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("الصفحة الرئيسية");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 890, 91);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.RED);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tribunal a2 = new tribunal();
				a2.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("image/justice.png"));
		btnNewButton.setBounds(137, 385, 200, 150);
		panel.add(btnNewButton);
		
		JButton affaire = new JButton("");
		affaire.setIcon(new ImageIcon("image/law.png"));
		affaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affaire a1 = new affaire();
				a1.setVisible(true);
				dispose();
			}
		});
		affaire.setBounds(557, 134, 200, 150);
		panel.add(affaire);
		
		JLabel label_1 = new JLabel("القضايا الغير مدفوعة");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(557, 283, 200, 43);
		panel.add(label_1);
		
		JButton client = new JButton("");
		client.setIcon(new ImageIcon("image/jury.png"));
		client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client a1 = new client();
				a1.setVisible(true);
				dispose();
			}
		});
		client.setBounds(137, 134, 200, 150);
		panel.add(client);
		
		JLabel label_2 = new JLabel("الجهات القضائية");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(137, 535, 200, 43);
		panel.add(label_2);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon("image/archive.png"));
		button_2.setBounds(557, 385, 200, 150);
		panel.add(button_2);
		
		JLabel label_3 = new JLabel("الأرشيف");
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(557, 535, 200, 43);
		panel.add(label_3);
	}
}
