package Controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ConnectionFactory.ConnectionFactory;
import view.Morte;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.DropMode;

public class Batalha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane txtpnC;
	private int inimigo_vida;
	private int arma_dano;
	private String inimigo_nome;
	private int personagem_vida = 0;
	private int pocao_efeito; 
	private int personagem_vidamax;
	private int pocao_quantidade;
	private int inimigo_dano;
	private int inimigo_id;
	
	//private int textou = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Batalha frame = new Batalha();
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
	
	public Batalha() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setForeground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 6));
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 451, 984, 210);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 6));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(640, 0, 344, 210);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Atacar");
		btnNewButton.setFont(new Font("Eras Bold ITC", Font.PLAIN, 15));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					atacar();
					contraataque();
				}
			});
		btnNewButton.setBounds(49, 36, 89, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Usar Poção");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Eras Bold ITC", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				curar();
				contraataque();
			}
		});
		btnNewButton_1.setBounds(181, 36, 136, 23);
		panel_1.add(btnNewButton_1);

//		JTextPane txtpnC = new JTextPane();
//		txtpnC.setDropMode(DropMode.INSERT);
//		txtpnC.getText();
//		txtpnC.setBackground(new Color(0, 0, 0));
//		txtpnC.setForeground(new Color(255, 255, 255));
//		txtpnC.setBounds(20, 10, 610, 189);
//		panel.add(txtpnC);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Batalha.class.getResource("/imagens/Untitled 11-10-2024 07-40-57.png")));
		lblNewLabel.setBounds(521, 152, 150, 240);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Batalha.class.getResource("/imagens/Untitled 11-10-2024 08-05-11.png")));
		lblNewLabel_1.setBounds(132, 218, 279, 233);
		contentPane.add(lblNewLabel_1);
		
		JLabel Wallpaper = new JLabel("");
		Wallpaper.setIcon(new ImageIcon(Batalha.class.getResource("/imagens/Design sem nome.png")));
		Wallpaper.setBounds(0, -125, 984, 661);
		contentPane.add(Wallpaper);
		
		
		
		worker.execute();
	}
	
		public void Geradordeinimigos() {
			try {
					Random random = new Random();
					Connection conexao = ConnectionFactory.createConnection();
					  String sql5 = "SELECT * FROM inimigo WHERE id_inimigo = (SELECT MAX(id_inimigo) FROM inimigo);";
					  PreparedStatement limitador = conexao.prepareStatement(sql5);
					  ResultSet resultado_limite = limitador.executeQuery();
					  
					  int id_maximo = 0;
					  while (resultado_limite.next()) {
						  id_maximo = resultado_limite.getInt("id_inimigo");
					  }
					  
					  inimigo_id = random.nextInt(id_maximo ) + 1;
				   
					  
				     String sql4 = "SELECT nome, vida, dano FROM inimigo WHERE id_inimigo = ?;";
				     PreparedStatement inimigo = conexao.prepareStatement(sql4);
				     inimigo.setInt(1, inimigo_id);
				     ResultSet resultado_inimigo = inimigo.executeQuery();
				   
				
				     int inimigo_vidamax = 0;
				
				     
				
					 while (resultado_inimigo.next()) {
					
					     inimigo_nome = resultado_inimigo.getString("nome");
					     inimigo_vidamax = resultado_inimigo.getInt("vida");
					     inimigo_dano = resultado_inimigo.getInt("dano");
					     inimigo_vida = inimigo_vidamax;
					 }
				 JOptionPane.showMessageDialog(null, "Um " + inimigo_nome + " Apareceu!");
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
		public void contraataque() {

	        	if (personagem_vida <= inimigo_dano) {
	        		personagem_vida = 0;
	        	}else if (personagem_vida > inimigo_dano) {
	        		personagem_vida = personagem_vida - inimigo_dano;
	        	}
	        	
	        	String texto = "Você foi atacado! \nSua vida atual:" + personagem_vida;
	        	JOptionPane.showMessageDialog(null, texto);
		        if(personagem_vida <= 0) {
				    new Morte().setVisible(true);
				    dispose();
				    
				}else if( personagem_vida > 0) {
					if(inimigo_vida <= 0) {
						Geradordeinimigos();
					}else if(personagem_vida <= 0) {
						dispose();
					}
				}
	   }
	
	
		public void curar() {
			String texto = "";
			if(pocao_quantidade > 0) {
				if (personagem_vida + pocao_efeito > personagem_vidamax && pocao_efeito + personagem_vida < pocao_efeito + personagem_vidamax) {
	        		personagem_vida = personagem_vidamax;
	        		pocao_quantidade = pocao_quantidade - 1;
	        		texto = "você curou toda a vida e agora tem " + pocao_quantidade + "poções! Vida atual:" + personagem_vida;
	        	}else if (personagem_vida + pocao_efeito < personagem_vidamax) {
	        		personagem_vida = personagem_vida + pocao_efeito;
	        		pocao_quantidade = pocao_quantidade - 1;
	        		texto = "você curou 10 de vida e agora tem " + pocao_quantidade + " poções! Vida atual:" + personagem_vida;
	        	}else {
	        		texto = "Vida já cheia";
	        	}
			}else {
				texto = "Você não tem nenhuma poção";
			}

        	JOptionPane.showMessageDialog(null, texto);
		}

		
		public void atacar() {
				Random random = new Random();
				int chance = random.nextInt(100) + 1;
		        if (chance >= 1 && chance <= 75) {
		        	inimigo_vida = inimigo_vida - arma_dano;
	
		        	
		        	String texto = "Você atacou e causou " + arma_dano + " de dano ao " + inimigo_nome + "!" + " \n vida do ininimigo:" + inimigo_vida;
		        	JOptionPane.showMessageDialog(null, texto);
		        	
		        }else {
		        	
		        	String texto = "Você errou o ataque";
		        	JOptionPane.showMessageDialog(null, texto);
		        	
		        }
			}
		
	
	
	
	SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		   @Override
		   
		   
		   public Void doInBackground() {
			    try {
				      Connection conexao = ConnectionFactory.createConnection();
				      //String sql1 = "SELECT id_inimigo, nome, vida, dano FROM inimigo;";
				      String sql1 = "SELECT nome, vida, arma_id, pocao_id FROM personagem;";
				      String sql2 = "SELECT nome, danoa FROM arma WHERE id_arma = ?;";
				      String sql3 = "SELECT nome, descricao, quantidade, efeito FROM pocao WHERE id_pocao = ?;";
				     
				     
				      //variáveis de personagem
				      PreparedStatement personagem = conexao.prepareStatement(sql1);
				      ResultSet resultado_personagem = personagem.executeQuery();
				     
				      String personagem_nome = "";

				      int personagem_arma = 0;
				      int personagem_pocao = 0;
				     
				      while (resultado_personagem.next()) {
				     
					      personagem_nome = resultado_personagem.getString("nome");
					      personagem_vidamax = resultado_personagem.getInt("vida");
					      personagem_arma = resultado_personagem.getInt("arma_id");
					      personagem_pocao = resultado_personagem.getInt("pocao_id");
					     
				      }
				      personagem_vida = personagem_vidamax;
				      //teste
				      //JOptionPane.showConfirmDialog(null, "nome" + personagem_nome + "vida" + personagem_vida + "arma" + personagem_arma + "pocao" + personagem_pocao );
				     
				     
				      //variáveis de dano
				      PreparedStatement arma = conexao.prepareStatement(sql2);
				      arma.setInt(1, personagem_arma);
				      ResultSet resultado_arma = arma.executeQuery();
				     
				      String arma_nome = "";
				     
				      while (resultado_arma.next()) {
				     
					      arma_nome = resultado_arma.getString("nome");
					      arma_dano = resultado_arma.getInt("danoa");
				     
				      }
				     
				      //variáveis de poção
				      PreparedStatement pocao = conexao.prepareStatement(sql3);
				      pocao.setInt(1, personagem_pocao);
				      ResultSet resultado_pocao = pocao.executeQuery();
				     
				      String pocao_nome = "";
				      String pocao_descricao = "";

				     
				      while (resultado_pocao.next()) {
				     
					      pocao_nome = resultado_pocao.getString("nome");
					      pocao_descricao = resultado_pocao.getString("descricao");
					      pocao_quantidade = resultado_pocao.getInt("quantidade");
					      pocao_efeito = resultado_pocao.getInt("efeito");
				     
				      }
				     
				    	 if( inimigo_vida <= 0) {
					    	 Geradordeinimigos();
					     }
				     //if (personagem_vida > 0) {

//				    	  	if (inimigo_vida <= 0) {
//				    		  while(inimigo_vida <= 0) {
//						    	  Random random = new Random();
//						    	  
//						    	  String sql5 = "SELECT * FROM inimigo WHERE id_inimigo = (SELECT MAX(id_inimigo) FROM inimigo);";
//						    	  PreparedStatement limitador = conexao.prepareStatement(sql5);
//						    	  ResultSet resultado_limite = limitador.executeQuery();
//						    	  
//						    	  int id_maximo = 0;
//						    	  while (resultado_limite.next()) {
//						    		  id_maximo = resultado_limite.getInt("id_inimigo");
//						    	  }
//						    	  
//						    	  int inimigo_id = random.nextInt(id_maximo ) + 1;
//							     
//						    	  
//							       String sql4 = "SELECT nome, vida, dano FROM inimigo WHERE id_inimigo = ?;";
//							       PreparedStatement inimigo = conexao.prepareStatement(sql4);
//							       inimigo.setDouble(1, inimigo_id);
//							       ResultSet resultado_inimigo = inimigo.executeQuery();
//							     
//		
//							       int inimigo_vidamax = 0;
//		
//							       
//						     
//						       while (resultado_inimigo.next()) {
//						     
//							       inimigo_nome = resultado_inimigo.getString("nome");
//							       inimigo_vidamax = resultado_inimigo.getInt("vida");
//							       inimigo_dano = resultado_inimigo.getInt("dano");
//							       inimigo_vida = inimigo_vidamax;
//						       }
//						       JOptionPane.showMessageDialog(null, "Um " + inimigo_nome + " Apareceu!");
//						       while (inimigo_vida > 0 ) {
//							        if ( acao != 0 ) {
//							        	if (personagem_vida < inimigo_dano) {
//							        		personagem_vida = 0;
//							        	}else if (personagem_vida > inimigo_dano) {
//							        		personagem_vida = personagem_vida - inimigo_dano;
//							        	}
//							        	
//							        	String texto = "Você foi atacado!";
//							        	JOptionPane.showMessageDialog(null, texto);
//							        	acao = 0;
//							        }
//						       }
//				    	  	}
//				    	 }
				      
			} catch (SQLException e) {
			e.printStackTrace();
	
			}
		       return null;
		   }
		};
}
