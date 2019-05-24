package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Extras.Music;
import Jugar.Equipo;

public class CrearEquipos {
	
	private int numEquipos;
	private Music music;
	private Partida partida;
	private ArrayList<Equipo> listaEquipo;
	
	public CrearEquipos(int numEquipos, Music music) {
		
		this.numEquipos = numEquipos;
		this.music = music;
		this.partida = new Partida();
		this.listaEquipo = new ArrayList<Equipo>();
		
		@SuppressWarnings("unused")
		Marco m = new Marco();
	 }
	
	class Marco extends JFrame{
		private static final long serialVersionUID = 1L;

		public Marco() { 
			 
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setSize(800,600);
			 //Bloquear que no se pueda redimencionar la ventana
			 setResizable(false);
			 //Colocar en medio de la pantalla
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			 setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			 
			 CrearTeams ct = new CrearTeams(0);
			 
			 add(ct); 
			 setVisible(true);
		 }
	 }
	
	class CrearTeams extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private int equiposCreados;
		//Colores
		private Color newBlack 	= Color.decode("#222222");
		private Color newBlack3 = Color.decode("#3a3a3a");
		//Botones
		private JButton b1;
		private JButton play;
		//label
		private JLabel logo;
		private JTextArea textArea;
		private JTextArea nombres;
		private JTextField getTeam;
		private JTextField getPlanet;
		
		public CrearTeams(int equiposCreados) {
			this.equiposCreados = equiposCreados;
			setLayout(null);
			setBackground(Color.black);
			//Definir el logo 
			this.logo = new JLabel();
			logo.setIcon(new ImageIcon("res/images/logoCrearEquipos.png"));
			logo.setBackground(Color.red);
			logo.setBounds(70,15,750,100);
			add(logo);
			//Texto explicativo de los planetas
			this.textArea = new JTextArea(1, 1);
			textArea.setEditable(false);
			textArea.append(partida.OtenerPlanetas());
			Border borderArea = BorderFactory.createLineBorder(newBlack3, 1);
			textArea.setBounds(68,128,650,50);
			textArea.setBackground(newBlack);
			textArea.setForeground(Color.green);
			textArea.setFont(new Font("Courier New",Font.PLAIN,18));
			JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
			scrollPane.setBounds(70,135,650,150);
			scrollPane.setBackground(newBlack);
			scrollPane.setBorder(borderArea);
			scrollPane.setOpaque(true);
			add(scrollPane);
			//Inputs de texto
			JLabel textTeam = new JLabel("<html>Introduce el nombre<br><center>del equipo</center></html>");
			textTeam.setBackground(newBlack);
			textTeam.setForeground(Color.green);
			textTeam.setFont(new Font("Courier New",Font.PLAIN,15));
			textTeam.setBounds(20,300,270,50);
			add(textTeam);
			JLabel textPlanet = new JLabel("<html>Introduce el nombre<br><center>del planeta</center></html>");
			textPlanet.setBackground(newBlack);
			textPlanet.setForeground(Color.green);
			textPlanet.setFont(new Font("Courier New",Font.PLAIN,15));
			textPlanet.setBounds(20,360,270,50);
			add(textPlanet);
			this.getTeam = new JTextField();
			getTeam.setBackground(newBlack);
			getTeam.setForeground(Color.green);
			getTeam.setFont(new Font("Courier New",Font.PLAIN,14));
			getTeam.setBounds(200,300,250,50);
			add(getTeam);
			this.getPlanet = new JTextField();
			getPlanet.setBackground(newBlack);
			getPlanet.setForeground(Color.green);
			getPlanet.setFont(new Font("Courier New",Font.PLAIN,14));
			getPlanet.setBounds(200,360,250,50);
			add(getPlanet);
			//Boton de crear equipos
			this.b1 = new JButton("Crear equipos");
			Border borderButt = BorderFactory.createLineBorder(Color.green, 1);
			b1.setBorder(borderButt);
			b1.setBackground(newBlack3);
			b1.setForeground(Color.green);
			b1.setFont(new Font("Courier New",Font.BOLD,21));
			b1.setBounds(115,440,250,50);
			b1.addActionListener(this);
			add(b1);
			//textArea para mostrar equipos
			this.nombres = new JTextArea();
			nombres.setEditable(false);
			Border borderNombre = BorderFactory.createLineBorder(newBlack3, 2);
			nombres.setBounds(460,300,260,265);
			nombres.setBackground(newBlack);
			nombres.setForeground(Color.green);
			nombres.setFont(new Font("Courier New",Font.BOLD,17));
			nombres.setBorder(borderNombre);
			for (Equipo equipo : listaEquipo) {
				nombres.append(equipo.getNombre_equipo()+" <"+equipo.getPlanetas().getNombre()+">\n");
			}
			add(nombres);
		}
		
		@Override
		public void actionPerformed(ActionEvent boton) {
			//Cogemos el div padre
			JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
			if(boton.getSource() == this.b1) {
				music.PlayBotonMenu();
				int ComprobarEquipo = partida.CrearEquipos(listaEquipo, getTeam.getText(), getPlanet.getText());
				if(ComprobarEquipo == 0) {
					equiposCreados++;
					if(equiposCreados >= numEquipos) {
						b1.setVisible(false);
						play = new JButton("JUGAR");
						Border borderButt = BorderFactory.createLineBorder(Color.green, 1);
						play.setBorder(borderButt);
						play.setBackground(newBlack3);
						play.setForeground(Color.green);
						play.setFont(new Font("Courier New",Font.BOLD,21));
						play.setBounds(115,440,250,50);
						play.addActionListener(this);
						add(play);
						nombres.append(listaEquipo.get(listaEquipo.size()-1).getNombre_equipo()+" <"+listaEquipo.get(listaEquipo.size()-1).getPlanetas().getNombre()+">\n");
						getTeam.setText("");
						getPlanet.setText("");
					}else {
						jframe.remove(this);
						
						CrearTeams ct = new CrearTeams(equiposCreados);
						
						jframe.add(ct);
						jframe.setVisible(true);
					}
				}else {
					String text = partida.CodigoError(ComprobarEquipo);
					JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else if(boton.getSource() == this.play) {
				music.PlayBotonMenu();
				//ocultamos el JFrame del menu para invocar al de crear equipos
				jframe.setVisible(false);
				jframe.remove(this);
				//Cargamos al de crear Equipos
				Combate combate = new Combate(listaEquipo, music);
			}
		}
		
 	}
}
