package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Extras.Music;
import Jugar.Equipo;
import Jugar.MensageAtaque;

public class Combate {
	
	private ArrayList<Equipo> listaEquipo;
	private Music music;
	private Partida partida;

	private int Rondas;
	private int orden;
	
	public Combate(ArrayList<Equipo> listaEquipo, Music music) {
		this.music = music;
		this.partida = new Partida();
		this.listaEquipo = listaEquipo;
		
		this.orden = 0;
		this.Rondas = 1;
		
		@SuppressWarnings("unused")
		Marco m = new Marco();
	}
	
	class Marco extends JFrame{
		private static final long serialVersionUID = 1L;
		
		public Marco() { 
			 
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setSize(1100,600);
			 //Bloquear que no se pueda redimencionar la ventana
			 setResizable(false);
			 //Colocar en medio de la pantalla
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			 setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			 
			 PantallaCombate ct = new PantallaCombate();
			 
			 add(ct); 
			 setVisible(true);
		 }
	}
	
	class PantallaCombate extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		//Color
		private Color newBlack  = Color.decode("#3a3a3a");
		private Color newBlack2 = Color.decode("#222222");
		//botones
		private JButton Ata;
		private JButton Def;
		private JButton sum1;
		private JButton sum10;
		private JButton sum25;
		private JButton rest1;
		private JButton rest10;
		private JButton rest25;
		//Area
		private JTextPane panel1;
		private ButtonGroup group;
		//misiles
		private JLabel misiles;
		private JLabel RestMisiles;
		
		private int misilesRestantes;
		private int misilesEscogidos;
		private ArrayList<Equipo> Enemigos = new ArrayList<Equipo>(listaEquipo);
		
		public PantallaCombate() {
			setLayout(null);
			setBackground(listaEquipo.get(orden).getColor());
			//Definir variables
			Enemigos.remove(orden);
			misilesEscogidos = 0;
			misilesRestantes = listaEquipo.get(orden).getMisiles_por_ronda();
			//Logo
			JLabel logoCom = new JLabel();
			Border borderLogo = BorderFactory.createLineBorder(Color.green, 2);
			logoCom.setIcon(new ImageIcon("res/images/logoCombate.png"));
			logoCom.setBounds(125,10,830,100);
			logoCom.setBorder(borderLogo);
			add(logoCom);
			//banner
			Border borderLabel = BorderFactory.createLineBorder(Color.green, 1);
			JLabel labelTeam = new JLabel(" "+listaEquipo.get(orden).getNombre_equipo() + " <"+listaEquipo.get(orden).getPlanetas().getNombre()+"> |");
			labelTeam.setBounds(160,120,755,50);
			labelTeam.setForeground(Color.green);
			labelTeam.setFont(new Font("Courier New",Font.PLAIN,19));
			labelTeam.setBackground(this.newBlack);
			labelTeam.setBorder(borderLabel);
			labelTeam.setOpaque(true);
			
			JLabel rondas = new JLabel(" Rondas : " + Integer.toString(Rondas));
			rondas.setBounds(925,120,130,50);
			rondas.setForeground(Color.green);
			rondas.setFont(new Font("Courier New",Font.PLAIN,19));
			rondas.setBackground(this.newBlack);
			rondas.setBorder(borderLabel);
			rondas.setOpaque(true);
			
			JLabel labelVidas = new JLabel("Vidas: " + Integer.toString(listaEquipo.get(orden).getVidas()) +" | Misiles Restantes: ");
			labelVidas.setBounds(530,120,400,50);
			labelVidas.setForeground(Color.green);
			labelVidas.setFont(new Font("Courier New",Font.PLAIN,19));
			labelVidas.setBackground(this.newBlack);
			
			this.RestMisiles = new JLabel(Integer.toString(misilesRestantes));
			RestMisiles.setBounds(885,120,50,50);
			RestMisiles.setForeground(Color.green);
			RestMisiles.setFont(new Font("Courier New",Font.PLAIN,19));
			RestMisiles.setBackground(this.newBlack);
			
			
			add(labelVidas);
			add(RestMisiles);
			add(labelTeam);
			add(rondas);
			//Area lista dinamica
			JLabel text1 = new JLabel("Equipos para Atacar");
			text1.setBounds(125,190,230,20);
			text1.setForeground(Color.green);
			text1.setHorizontalAlignment(JLabel.CENTER);
			text1.setFont(new Font("Courier New",Font.PLAIN,14));
			add(text1);
			
			this.panel1 = new JTextPane();
			panel1.setBounds(125,210,230,350);
			panel1.setBackground(this.newBlack);
			panel1.setBorder(borderLogo);
			panel1.setOpaque(true);
			panel1.setEditable(false);
			add(panel1);
			GenerarListaDin(Enemigos);
			//Decidir misiles
			this.misiles = new JLabel("0");
			misiles.setBounds(480,190,100,35);
			misiles.setForeground(Color.green);
			misiles.setFont(new Font("Courier New",Font.BOLD,21));
			misiles.setHorizontalAlignment(JLabel.CENTER);
			misiles.setBackground(this.newBlack2);
			misiles.setBorder(borderLogo);
			misiles.setOpaque(true);
			add(misiles);
			
			JLabel panel2 = new JLabel();
			panel2.setBounds(370,230,320,200);
			panel2.setBackground(this.newBlack);
			panel2.setBorder(borderLogo);
			panel2.setOpaque(true);
			add(panel2);
			
			this.sum1 = new JButton("+1");
			sum1.setBounds(15,15,75,75);
			sum1.setForeground(Color.green);
			sum1.setFont(new Font("Courier New",Font.BOLD,28));
			sum1.setHorizontalAlignment(JLabel.CENTER);
			sum1.setBackground(this.newBlack2);
			sum1.setBorder(borderLogo);
			sum1.setOpaque(true);
			
			this.sum10 = new JButton("+10");
			sum10.setBounds(123,15,75,75);
			sum10.setForeground(Color.green);
			sum10.setFont(new Font("Courier New",Font.BOLD,28));
			sum10.setHorizontalAlignment(JLabel.CENTER);
			sum10.setBackground(this.newBlack2);
			sum10.setBorder(borderLogo);
			sum10.setOpaque(true);
			
			this.sum25 = new JButton("+25");
			sum25.setBounds(230,15,75,75);
			sum25.setForeground(Color.green);
			sum25.setFont(new Font("Courier New",Font.BOLD,28));
			sum25.setHorizontalAlignment(JLabel.CENTER);
			sum25.setBackground(this.newBlack2);
			sum25.setBorder(borderLogo);
			sum25.setOpaque(true);
			
			this.rest1 = new JButton("-1");
			rest1.setBounds(15,110,75,75);
			rest1.setForeground(Color.green);
			rest1.setFont(new Font("Courier New",Font.BOLD,28));
			rest1.setHorizontalAlignment(JLabel.CENTER);
			rest1.setBackground(this.newBlack2);
			rest1.setBorder(borderLogo);
			rest1.setOpaque(true);
			
			this.rest10 = new JButton("-10");
			rest10.setBounds(123,110,75,75);
			rest10.setForeground(Color.green);
			rest10.setFont(new Font("Courier New",Font.BOLD,28));
			rest10.setHorizontalAlignment(JLabel.CENTER);
			rest10.setBackground(this.newBlack2);
			rest10.setBorder(borderLogo);
			rest10.setOpaque(true);
			
			this.rest25 = new JButton("-25");
			rest25.setBounds(230,110,75,75);
			rest25.setForeground(Color.green);
			rest25.setFont(new Font("Courier New",Font.BOLD,28));
			rest25.setHorizontalAlignment(JLabel.CENTER);
			rest25.setBackground(this.newBlack2);
			rest25.setBorder(borderLogo);
			rest25.setOpaque(true);
			
			sum1.addActionListener(this);
			sum10.addActionListener(this);
			sum25.addActionListener(this);
			rest1.addActionListener(this);
			rest10.addActionListener(this);
			rest25.addActionListener(this);
			
			panel2.add(sum1);
			panel2.add(sum10);
			panel2.add(sum25);
			panel2.add(rest1);
			panel2.add(rest10);
			panel2.add(rest25);
			//Botones de ataque y defensa
			this.Ata = new JButton("ATACAR");
			this.Def = new JButton("DEFENDER");
			
			Ata.setBounds(370,450,320,50);
			Ata.setForeground(Color.green);
			Ata.setFont(new Font("Courier New",Font.BOLD,28));
			Ata.setHorizontalAlignment(JLabel.CENTER);
			Ata.setBackground(this.newBlack2);
			Ata.setBorder(borderLogo);
			Ata.setOpaque(true);
			
			Def.setBounds(370,510,320,50);
			Def.setForeground(Color.green);
			Def.setFont(new Font("Courier New",Font.BOLD,28));
			Def.setHorizontalAlignment(JLabel.CENTER);
			Def.setBackground(this.newBlack2);
			Def.setBorder(borderLogo);
			Def.setOpaque(true);
			
			Ata.addActionListener(this);
			Def.addActionListener(this);
			
			add(Ata);
			add(Def);
			//Panel de estatico
			JTextArea panel3 = new JTextArea();
			panel3.setBounds(700,190,310,370);
			panel3.setBackground(this.newBlack);
			panel3.setForeground(Color.green);
			panel3.setFont(new Font("Courier New",Font.BOLD,13));
			panel3.setBorder(borderLogo);
			panel3.setOpaque(true);
			panel3.setEditable(false);
			
			for (Equipo equipo : listaEquipo) {
				panel3.append("\n");
				panel3.append(" "+equipo.getNombre_equipo() + " <"+equipo.getPlanetas().getNombre()+"> | vidas: "+equipo.getVidas() + "\n");
			}
			
			add(panel3);
		}
		
		@Override
		public void actionPerformed(ActionEvent boton) {

			if(boton.getSource() == this.sum1) {
				music.PlayBotonMenu();
				if (misilesRestantes >= 1) {
					misilesEscogidos++;
					misilesRestantes--;
					misiles.setText(Integer.toString(misilesEscogidos));
					RestMisiles.setText(Integer.toString(misilesRestantes));
				}
			}else if (boton.getSource() == this.sum10) {
				music.PlayBotonMenu();
				if (misilesRestantes - 10 >= 0) {
					misilesEscogidos+=10;
					misilesRestantes-=10;
					misiles.setText(Integer.toString(misilesEscogidos));
					RestMisiles.setText(Integer.toString(misilesRestantes));
				}
			}else if (boton.getSource() == this.rest1) {
				music.PlayBotonMenu();
				if (misilesEscogidos >= 1) {
					misilesEscogidos--;
					misilesRestantes++;
					misiles.setText(Integer.toString(misilesEscogidos));
					RestMisiles.setText(Integer.toString(misilesRestantes));
				}
			}else if (boton.getSource() == this.rest10) {
				music.PlayBotonMenu();
				if (misilesEscogidos >= 1 && (misilesEscogidos-10) >= 0) {
					misilesEscogidos-=10;
					misilesRestantes+=10;
					misiles.setText(Integer.toString(misilesEscogidos));
					RestMisiles.setText(Integer.toString(misilesRestantes));
				}
			}else if (boton.getSource() == this.sum25) {
				music.PlayBotonMenu();
				if (misilesRestantes - 25 >= 0) {
					misilesEscogidos+=25;
					misilesRestantes-=25;
					misiles.setText(Integer.toString(misilesEscogidos));
					RestMisiles.setText(Integer.toString(misilesRestantes));
				}
			}else if (boton.getSource() == this.rest25) {
				music.PlayBotonMenu();
				if (misilesEscogidos >= 1 && (misilesEscogidos-25) >= 0) {
					misilesEscogidos-=25;
					misilesRestantes+=25;
					misiles.setText(Integer.toString(misilesEscogidos));
					RestMisiles.setText(Integer.toString(misilesRestantes));
				}
			}else if(boton.getSource() == this.Ata) {
				music.PlayBotonMenu();
				try {
					int EquipoEscogido = Integer.parseInt(group.getSelection().getActionCommand());
					//Coger misiles y actulizar la pantalla
					int misilesEsc = Integer.parseInt(misiles.getText());
					misilesEscogidos = 0;
					misiles.setText("0");
					//Ejecutar ataque
					partida.Combate(misilesEsc, listaEquipo.get(orden).getNombre_equipo(), listaEquipo.get(orden).getPlanetas().getNombre(), listaEquipo.get(orden).getPlanetas().getTipo(),  listaEquipo.get(orden).getVidas(), Enemigos.get(EquipoEscogido));
					//Actulizar pantalla y eliminar enemigo atacado
					Enemigos.remove(EquipoEscogido);
					panel1.removeAll();
					panel1.repaint();
					GenerarListaDin(Enemigos);
					JLabel textLabel = new JLabel("Ataque ejecutado correctamente");
					JOptionPane.showMessageDialog(null, textLabel, "Felicidades", JOptionPane.INFORMATION_MESSAGE);
					//Comprobar si hay mas enemigos y si aun tienes misiles 
					if (misilesRestantes == 0){
						RenovarPantalla();
					}
					
					if(Enemigos.size() == 0) {
						Ata.setEnabled(false);
					}
					
				} catch (Exception e) {
					JLabel label = new JLabel("Ha ocurido un error inesperado. Has clic en si para volver a intentar");
					JOptionPane.showMessageDialog(null, label, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(boton.getSource() == this.Def) {
				//Coger misiles defensivos
				music.PlayBotonMenu();
				int misilesEsc = Integer.parseInt(misiles.getText());
				if(misilesEsc != 0) {
					listaEquipo.get(orden).CalcularMisilesDef(misilesEsc);
					JLabel textLabel = new JLabel("Defensa ejecutado correctamente");
					JOptionPane.showMessageDialog(null, textLabel, "Felicidades", JOptionPane.INFORMATION_MESSAGE);
					RenovarPantalla();
				}else {
					JLabel label02 = new JLabel("No te puedes defender con 0 misiles");
					JOptionPane.showMessageDialog(null, label02, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public void GenerarListaDin(ArrayList<Equipo> team) {
			int i = 0;
			int y = 5;
			boolean inicia = true;
			
			ArrayList<JRadioButton> radioBut = new ArrayList<JRadioButton>();
			this.group = new ButtonGroup();
			
			
			for (Equipo teams : team) {
				JRadioButton boton = new JRadioButton(teams.getNombre_equipo() + " <"+teams.getPlanetas().getNombre()+">",inicia);
				radioBut.add(boton);
				radioBut.get(i).setActionCommand(Integer.toString(i));
				radioBut.get(i).setBounds(5,y,220,25);
				radioBut.get(i).setBackground(newBlack);
				radioBut.get(i).setForeground(Color.green);
				radioBut.get(i).setFont(new Font("Courier New",Font.PLAIN,13));
				group.add(radioBut.get(i));
				panel1.add(radioBut.get(i));
				
				inicia = false;
				
				y += 30;
				i++;
			}
			
		}
		
		public void RenovarPantalla() {
			JLabel textLabel2 = new JLabel("Se ha acabado tu turno " + listaEquipo.get(orden).getNombre_equipo());
			JOptionPane.showMessageDialog(null, textLabel2, "Fin de turno", JOptionPane.INFORMATION_MESSAGE);
			orden++;
			
			JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
			//Resetear pantalla
			jframe.remove(this);
				
			if(orden == listaEquipo.size()) {
				ExeMensages em = new ExeMensages();
				jframe.add(em);
				jframe.setVisible(true);
			}else {
				PantallaCombate pc = new PantallaCombate();
				jframe.add(pc);
				jframe.setVisible(true);
			}
		}
		
	}
	
	class ExeMensages extends JPanel implements ActionListener {
		
		private static final long serialVersionUID = 1L;
		private Color newBlack 	= Color.decode("#222222");
		private Color newBlack3 = Color.decode("#3a3a3a");
		private Color newGreen = Color.decode("#013220");
		private Color newGray = Color.decode("#383838");
		
		JTextArea area;
		JButton b1;
		JButton b2;
		
		public ExeMensages() {
			setLayout(null);
			setBackground(newGreen);
			
			JLabel text = new JLabel("Ejecución del Combate");
			Border border = BorderFactory.createLineBorder(newBlack, 2);
			text.setBackground(newGray);
			text.setFont(new Font("Courier New",Font.BOLD,25));
			text.setForeground(Color.green);
			text.setBorder(border);
			text.setHorizontalAlignment(JLabel.CENTER);
			text.setBounds(100,10,900, 35);
			text.setOpaque(true);
			add(text);
			
			this.area = new JTextArea();
			area.setEditable(false);
			Border borderArea = BorderFactory.createLineBorder(newBlack3, 2);
			area.setBounds(50,50,1000,450);
			area.setBackground(newBlack);
			area.setForeground(Color.green);
			area.setFont(new Font("Courier New",Font.PLAIN,18));
			JScrollPane scrollPane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
			scrollPane.setBounds(50,50,1000,450);
			scrollPane.setBackground(newBlack);
			scrollPane.setBorder(borderArea);
			scrollPane.setOpaque(true);
			add(scrollPane);
			
			
			this.b1 = new JButton("Cargar Mensages");
			Border borderButt = BorderFactory.createLineBorder(newBlack, 2);
			b1.setBorder(borderButt);
			b1.setBackground(newBlack3);
			b1.setForeground(Color.green);
			b1.setFont(new Font("Courier New",Font.BOLD,21));
			b1.setBounds(350,510,400,50);
			b1.addActionListener(this);
			b1.setOpaque(true);
			add(b1);
		}

		@Override
		public void actionPerformed(ActionEvent boton) {
			
			if (boton.getSource() == this.b1) {
				music.PlayBotonMenu();
				b1.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Cargando mensages, puede tardar unos segundos", "Cargando", JOptionPane.INFORMATION_MESSAGE);
				CargarMSG();
			}else if (boton.getSource() == this.b2) {
				music.PlayBotonMenu();
				JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
				jframe.remove(this);
				if(partida.getNumEquiposVivos() <= 1) {
					if(listaEquipo.size() == 1) {
						partida.finalizarPartida(listaEquipo, Rondas);
						JOptionPane.showMessageDialog(null, "Tenemos un ganador\n"+partida.getGanador()+" has entrado el registro mas exclusivo del universo", "Felicidades", JOptionPane.INFORMATION_MESSAGE);
					}
					//ocultamos el JFrame del menu para invocar al de crear equipos
					jframe.setVisible(false);
					jframe.remove(this);
					//cargar otra ves el menu
					Menu m = new Menu();
				}else {
					orden = 0;
					Rondas++;
					
					PantallaCombate em = new PantallaCombate();
					
					jframe.add(em);
					jframe.setVisible(true);
				}
			}
			
		}
		
		public void CargarMSG() {
			ArrayList<String> text = new ArrayList<String>();
			
			for (MensageAtaque orden : partida.getListaMsg()) {
				int isHit;
				int bd = orden.getEquipoDef().getBombas_def();
				isHit = orden.getEquipoDef().Combate(orden.getBombas_of(), orden.getTipoAta(), orden.getVidasAta());
				orden.GenerarAtaque(isHit, bd);

				area.append(orden.getMensage());
				area.append("\n");
			}
			//Limpiar los mensages
			partida.getListaMsg().clear();
			
			area.append("fin de la ronda\nAhora comprobaremos cuantos equipos quedan en pie\n\n");
			int equiposVivos = partida.comprobarEquiposVivos(listaEquipo, text);
			
			for (String str : text) {
				area.append(str);
			}
			
			if(equiposVivos > 1) {
				this.b2 = new JButton("Jugar");
			}else {
				this.b2 = new JButton("Acabar Juegos");
				area.append("\nFin del juego\n");
				if (equiposVivos == 1) {
					area.append("Hay Ganador, y es el "+listaEquipo.get(0).getNombre_equipo());
				}else {
					area.append("No hay ganador, se ha sobrevivido ningun equipo");
				}
				
				music.PlayEnd();
			}
			
			Border borderButt = BorderFactory.createLineBorder(newBlack, 2);
			b2.setBorder(borderButt);
			b2.setBackground(newBlack3);
			b2.setForeground(Color.green);
			b2.setFont(new Font("Courier New",Font.BOLD,21));
			b2.setBounds(350,510,400,50);
			b2.addActionListener(this);
			b2.setOpaque(true);
			
			remove(b1);
			repaint(350,510,400,50);
			add(b2);
			
		}
	}	
}
