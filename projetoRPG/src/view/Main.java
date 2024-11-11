package view;



import java.awt.Color;

import java.awt.EventQueue;



import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import Controller.Batalha;



import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import javax.swing.ImageIcon;

import java.awt.Font;



public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	
	
	/**
	
	* Launch the application.
	
	*/
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
		
			public void run() {
			
				try {
				
					Main frame = new Main();
					
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
	
public Main() {
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	setBounds(100, 100, 800, 600);
	
	contentPane = new JPanel();
	
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
	setResizable(false);
	
	
	
	setContentPane(contentPane);
	
	contentPane.setLayout(null);
	
	
	
	JButton btnNewButton = new JButton("- Jogar -");
	
	btnNewButton.setFont(new Font("Eras Bold ITC", Font.PLAIN, 20));
	
	btnNewButton.setForeground(Color.WHITE);
	
	btnNewButton.setContentAreaFilled(false);
	
	btnNewButton.setOpaque(true);
	
	btnNewButton.setBackground(new Color(54, 0, 73));
	
	btnNewButton.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
		
			new Batalha().setVisible(true);
		
		}
	
	});
	
	btnNewButton.setBounds(-12, 379, 116, 39);
	
	contentPane.add(btnNewButton);
	
	
	
	JButton btnSair = new JButton("- Sair -");
	
	btnSair.setFont(new Font("Eras Bold ITC", Font.PLAIN, 20));
	
	btnSair.setForeground(Color.WHITE);
	
	btnSair.setContentAreaFilled(false);
	
	btnSair.setOpaque(true);
	
	btnSair.setBackground(new Color(54, 0, 73));
	
	btnSair.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
		
			System.exit(0);
		
		}
	
	});
	
	btnSair.setBounds(-2, 431, 99, 39);
	
	contentPane.add(btnSair);
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/imagens/pixil-frame-0 (4).png")));
	lblNewLabel.setBounds(111, 72, 561, 353);
	contentPane.add(lblNewLabel);
	
	
	JLabel Wallpaper = new JLabel("");
	
	Wallpaper.setIcon(new ImageIcon(Main.class.getResource("/imagens/login_infdungeon.png")));
	
	Wallpaper.setBounds(0, 0, 784, 561);
	
	contentPane.add(Wallpaper);
	

	
	
	}
}
