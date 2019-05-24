package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.Border;

import Extras.Historial;
import Extras.Music;
import Hilos.HiloMusica;  

 public class Menu {
	 
	 private Partida partida;
	 private Music music;
	 private HiloMusica musica;
	 
	 public Menu() {
		 this.partida = new Partida();
		 this.music = new Music(100);
		 //Cargar musica
		 this.musica = new HiloMusica(this.music);
		 musica.start();
		 //Cargar JFrame
		 @SuppressWarnings("unused")
		 Marco m = new Marco();
	 }
	 
	 class Marco extends JFrame{
		private static final long serialVersionUID = 1L;

		public Marco() { 
			 
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setSize(340,455);
			 //Bloquear que no se pueda redimencionar la ventana
			 setResizable(false);
			 //Colocar en medio de la pantalla
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			 setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			 PanelMenu pm = new PanelMenu(partida.ObtenerMenu());
			 
			 add(pm); 
			 setVisible(true);
		 }
	 }
	 
	 class PanelMenu extends JPanel implements ActionListener{
		private static final long serialVersionUID = 1L;
		//Colores
		private Color newBlack 	= Color.decode("#222222");
		private Color newBlack2 = Color.decode("#444444");
		private Color newBlack3 = Color.decode("#3a3a3a");
		//Botones
		private JButton b1;
		private JButton b2;
		private JButton b3;
		private JButton b4;
		private JButton b5;
		private JButton b6;
		
		public PanelMenu(ArrayList<String> menu) {
			setLayout(null);
			setBackground(Color.black);
			JLabel l1 = new JLabel("Bienvenido a War Planet"); 
			JLabel l2 = new JLabel("Diogo entertainment©"); 
			l1.setBounds(5,5,325,60);
			l2.setBounds(0,385,340,50);
			l1.setHorizontalAlignment(JLabel.CENTER);
			l2.setHorizontalAlignment(JLabel.CENTER);
			l1.setFont(new Font("Courier New",Font.PLAIN,21));
			l2.setFont(new Font("Courier New",Font.PLAIN,13));
			l1.setForeground(Color.green);
			l2.setForeground(Color.green);
			
			Border border = BorderFactory.createLineBorder(Color.green, 2);
			l1.setBorder(border);
			l1.setBackground(this.newBlack3);
			l1.setOpaque(true);
			
			add(l1);
			add(l2);
			
			this.b1=new JButton(menu.get(0)); 
			this.b2=new JButton(menu.get(1)); 
			this.b3=new JButton(menu.get(2)); 
			this.b4=new JButton(menu.get(3)); 
			this.b5=new JButton(menu.get(4));
			this.b6=new JButton(menu.get(5));
			b1.setBounds(5,70,325,50);   
			b2.setBounds(5,125,325,50);
			b3.setBounds(5,180,325,50);   
			b4.setBounds(5,235,325,50);
			b5.setBounds(5,290,325,50);
			b6.setBounds(5,345,325,50);
			b1.setBackground(this.newBlack);
			b1.setForeground(Color.green);
			b2.setBackground(this.newBlack);
			b2.setForeground(Color.green);
			b3.setBackground(this.newBlack);
			b3.setForeground(Color.green);
			b4.setBackground(this.newBlack);
			b4.setForeground(Color.green);
			b5.setBackground(this.newBlack);
			b5.setForeground(Color.green);
			b6.setBackground(this.newBlack);
			b6.setForeground(Color.green);
			
			Border borderButton = BorderFactory.createLineBorder(newBlack2, 2);
			b1.setBorder(borderButton);
			b2.setBorder(borderButton);
			b3.setBorder(borderButton);
			b4.setBorder(borderButton);
			b5.setBorder(borderButton);
			b6.setBorder(borderButton);
			
	        add(b1);         
	        add(b2); 
	        add(b3);         
	        add(b4);
	        add(b5);
	        add(b6);
			
	        b1.addActionListener(this);
	        b2.addActionListener(this);
	        b3.addActionListener(this);
	        b4.addActionListener(this);
	        b5.addActionListener(this);
	        b6.addActionListener(this);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent boton) {
			//Cogemos el div padre
			JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
			
			if(boton.getSource() == this.b1) {
				music.PlayBotonMenu();
				jframe.remove(this);
				
				PanelPreJuego prj = new PanelPreJuego();
				
				jframe.add(prj);
				jframe.setVisible(true);
			}else if(boton.getSource() == this.b2){
				music.PlayBotonMenu();
				jframe.remove(this);
				
				PanelReglas pr = new PanelReglas();
				
				jframe.add(pr);
				jframe.setVisible(true);
				
			}else if(boton.getSource() == this.b3){
				music.PlayBotonMenu();
				jframe.remove(this);
				
				PanelInfo pi = new PanelInfo();
				
				jframe.add(pi);
				jframe.setVisible(true);
			}else if(boton.getSource() == this.b4){
				music.PlayBotonMenu();
				jframe.remove(this);
				
				PanelMusica pm = new PanelMusica();
				
				jframe.add(pm);
				jframe.setVisible(true);
			}else if(boton.getSource() == this.b5){
				music.PlayBotonMenu();
				jframe.remove(this);
				
				PanelHistorial ph = new PanelHistorial();
				
				jframe.add(ph);
				jframe.setVisible(true);
			}else if(boton.getSource() == this.b6) {
				music.PlayBotonMenu();
				jframe.remove(this);
				
				System.exit(0);
			}
			
		}
		
		
	}
	 	class PanelPreJuego extends JPanel implements ActionListener {
			private static final long serialVersionUID = 1L;
			//Colores
			private Color newBlack 	= Color.decode("#222222");
			private Color newBlack3 = Color.decode("#3a3a3a");
			//Botones
			private JButton play;
			private JButton b1;
			private JRadioButton b3;
			private JRadioButton b4;
			private JRadioButton b5;
			private JRadioButton b6;
			private JRadioButton b7;
			private JRadioButton b8;
			private JRadioButton b9;
			private JRadioButton b10;
			private ButtonGroup group;
			
			public PanelPreJuego() {
				
				setLayout(null);
				setBackground(Color.black);
				
				//Imagen
				JLabel img1	= new JLabel();				
				img1.setIcon(new ImageIcon("res/images/logoMisiles.png"));
				img1.setBounds(20,0,340,220);
				//Seleccionar numeros de equipos
				b3 = new JRadioButton("3 Equipos",true);
		        b4 = new JRadioButton("4 Equipos");
		        b5 = new JRadioButton("5 Equipos");
		        b6 = new JRadioButton("6 Equipos");
		        b7 = new JRadioButton("7 Equipos");
		        b8 = new JRadioButton("8 Equipos");
		        b9 = new JRadioButton("9 Equipos");
		        b10 = new JRadioButton("10 Equipos");
		        //Para poder obtener el numero de equipos 
		        b3.setActionCommand("3"); b4.setActionCommand("4");
		        b5.setActionCommand("5"); b6.setActionCommand("6");
		        b7.setActionCommand("7"); b8.setActionCommand("8");
		        b9.setActionCommand("9"); b10.setActionCommand("10");
		        
		        b3.setBounds(45,155,105,25);
		        b4.setBounds(45,190,105,25);
		        b5.setBounds(45,225,105,25);
		        b6.setBounds(45,260,105,25);
		        b7.setBounds(200,155,105,25);
		        b8.setBounds(200,190,105,25);
		        b9.setBounds(200,225,105,25);
		        b10.setBounds(200,260,105,25);
		        
		        b3.setBackground(Color.black); b4.setBackground(Color.black);
		        b5.setBackground(Color.black); b6.setBackground(Color.black);
		        b7.setBackground(Color.black); b8.setBackground(Color.black);
		        b9.setBackground(Color.black); b10.setBackground(Color.black);
		        
		        b3.setForeground(Color.green); b4.setForeground(Color.green);
		        b5.setForeground(Color.green); b6.setForeground(Color.green);
		        b7.setForeground(Color.green); b8.setForeground(Color.green);
		        b9.setForeground(Color.green); b10.setForeground(Color.green);
		        
		        b3.setFont(new Font("Courier New",Font.PLAIN,13)); b4.setFont(new Font("Courier New",Font.PLAIN,13));
		        b5.setFont(new Font("Courier New",Font.PLAIN,13)); b6.setFont(new Font("Courier New",Font.PLAIN,13));
		        b7.setFont(new Font("Courier New",Font.PLAIN,13)); b8.setFont(new Font("Courier New",Font.PLAIN,13));
		        b9.setFont(new Font("Courier New",Font.PLAIN,13)); b10.setFont(new Font("Courier New",Font.PLAIN,13));
		        
		        group = new ButtonGroup();
		        group.add(b3);
		        group.add(b4);
		        group.add(b5);
		        group.add(b6);
		        group.add(b7);
		        group.add(b8);
		        group.add(b9);
		        group.add(b10);
		        
		        add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);add(b10);
		        
		        this.play = new JButton("Jugar");
		        play.setBounds(55,298,235,40); 
		        play.setBackground(this.newBlack);
		        play.setForeground(Color.green);
		        play.addActionListener(this);
				add(play);
		        
				//Boton para volver al menu
				this.b1=new JButton("Volver al menu"); 
				b1.setBounds(5,345,325,50); 
				b1.setBackground(this.newBlack);
				b1.setForeground(Color.green);
				add(b1);
				b1.addActionListener(this);
				//label de la compañia
				JLabel l1 = new JLabel("Bienvenido a War Planet"); 
				JLabel l2 = new JLabel("Diogo entertainment©"); 
				l1.setBounds(5,5,325,60);
				l2.setBounds(0,385,340,50);
				l1.setHorizontalAlignment(JLabel.CENTER);
				l2.setHorizontalAlignment(JLabel.CENTER);
				l1.setFont(new Font("Courier New",Font.PLAIN,21));
				l2.setFont(new Font("Courier New",Font.PLAIN,13));
				l1.setForeground(Color.green);
				l2.setForeground(Color.green);
				
				Border border = BorderFactory.createLineBorder(Color.green, 2);
				l1.setBorder(border);
				l1.setBackground(this.newBlack3);
				l1.setOpaque(true);
				
				add(img1);
				add(l1);
				add(l2);
			}
			
			@Override
			public void actionPerformed(ActionEvent boton) {
				//Cogemos el div padre
				JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
				
				if(boton.getSource() == this.b1) {
					music.PlayBotonMenu();
					jframe.remove(this);
					
					PanelMenu pm = new PanelMenu(partida.ObtenerMenu());
					
					jframe.add(pm);
					jframe.setVisible(true);
				}else if(boton.getSource() == this.play) {
					music.PlayBotonMenu();
					try {
						int numEquipos = Integer.parseInt(group.getSelection().getActionCommand());
						//ocultamos el JFrame del menu para invocar al de crear equipos
						jframe.setVisible(false);
						jframe.remove(this);
						//Cargamos al de crear Equipos
						CrearEquipos ce = new CrearEquipos(numEquipos, music);
						
					} catch (Exception e) {
						e.printStackTrace();
						JLabel label = new JLabel("Ha ocurido un error inesperado. Has clic en si para volver a intentar");
						JOptionPane.showMessageDialog(null, label, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
	 	}
	 
	 	class PanelReglas extends JPanel implements ActionListener{
			private static final long serialVersionUID = 1L;
			//Colores
			private Color newBlack 	= Color.decode("#222222");
			private Color newBlack3 = Color.decode("#3a3a3a");
			//Botones
			private JButton b1;
	 		
	 		public PanelReglas() {
	 			setLayout(null);
				setBackground(Color.black);
				
				//Panel de las reglas
				JLabel img1	= new JLabel();
				JLabel rgl1 = new JLabel(partida.ObtenerMenuReglas().get(0));
				
				img1.setIcon(new ImageIcon("res/images/logoMisiles.png"));
				img1.setBounds(20,0,340,220);
				
				rgl1.setBounds(-15,125,340,200);
				rgl1.setFont(new Font("Courier New",Font.PLAIN,13));
				rgl1.setForeground(Color.green);
				
				//Boton para volver al menu
				this.b1=new JButton("Volver al menu"); 
				b1.setBounds(5,345,325,50); 
				b1.setBackground(this.newBlack);
				b1.setForeground(Color.green);
				add(b1);
				b1.addActionListener(this);
				//label de la compaï¿½ia
				JLabel l1 = new JLabel("Bienvenido a War Planet"); 
				JLabel l2 = new JLabel("Diogo entertainment©"); 
				l1.setBounds(5,5,325,60);
				l2.setBounds(0,385,340,50);
				l1.setHorizontalAlignment(JLabel.CENTER);
				l2.setHorizontalAlignment(JLabel.CENTER);
				l1.setFont(new Font("Courier New",Font.PLAIN,21));
				l2.setFont(new Font("Courier New",Font.PLAIN,13));
				l1.setForeground(Color.green);
				l2.setForeground(Color.green);
				
				Border border = BorderFactory.createLineBorder(Color.green, 2);
				l1.setBorder(border);
				l1.setBackground(this.newBlack3);
				l1.setOpaque(true);
				
				add(img1);
				add(rgl1);
				
				add(l1);
				add(l2);
	 		}
			@Override
			public void actionPerformed(ActionEvent boton) {
				//Cogemos el div padre
				JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
				
				if(boton.getSource() == this.b1) {
					music.PlayBotonMenu();
					jframe.remove(this);
					
					PanelMenu pm = new PanelMenu(partida.ObtenerMenu());
					
					jframe.add(pm);
					jframe.setVisible(true);
				}
			}
	 		
	 	}
	 
	 	class PanelInfo extends JPanel implements ActionListener {
			private static final long serialVersionUID = 1L;
			//Colores
			private Color newBlack 	= Color.decode("#222222");
			private Color newBlack3 = Color.decode("#3a3a3a");
			//Botones
			private JButton b1;
			
			public PanelInfo() {
				
				setLayout(null);
				setBackground(Color.black);
				
				
				//Panel informativo
				JLabel img1	= new JLabel();
				
				JLabel info1 = new JLabel(partida.ObtenerMenuInfo().get(0));
				JLabel info2 = new JLabel(partida.ObtenerMenuInfo().get(1));
				JLabel info3 = new JLabel(partida.ObtenerMenuInfo().get(2));
				
				img1.setIcon(new ImageIcon("res/images/logoMisiles.png"));
				img1.setBounds(20,0,340,220);
				
				info1.setBounds(0,125,340,200);
				info1.setFont(new Font("Courier New",Font.PLAIN,13));
				info1.setForeground(Color.green);
				
				info2.setBounds(250,280,100,100);
				info2.setFont(new Font("Courier New",Font.PLAIN,11));
				info2.setForeground(Color.green);
				info3.setBounds(7,280,280,100);
				info3.setFont(new Font("Courier New",Font.PLAIN,11));
				info3.setForeground(Color.green);
				
				//Boton para volver al menu
				this.b1=new JButton("Volver al menu"); 
				b1.setBounds(5,345,325,50); 
				b1.setBackground(this.newBlack);
				b1.setForeground(Color.green);
				add(b1);
				b1.addActionListener(this);
				//label de la compañia
				JLabel l1 = new JLabel("Bienvenido a War Planet"); 
				JLabel l2 = new JLabel("Diogo entertainment©"); 
				l1.setBounds(5,5,325,60);
				l2.setBounds(0,385,340,50);
				l1.setHorizontalAlignment(JLabel.CENTER);
				l2.setHorizontalAlignment(JLabel.CENTER);
				l1.setFont(new Font("Courier New",Font.PLAIN,21));
				l2.setFont(new Font("Courier New",Font.PLAIN,13));
				l1.setForeground(Color.green);
				l2.setForeground(Color.green);
				
				Border border = BorderFactory.createLineBorder(Color.green, 2);
				l1.setBorder(border);
				l1.setBackground(this.newBlack3);
				l1.setOpaque(true);
				
				add(img1);
				add(info1);
				add(info2);
				add(info3);
				
				add(l1);
				add(l2);
				
			}
			
			@Override
			public void actionPerformed(ActionEvent boton) {
				//Cogemos el div padre
				JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
				
				if(boton.getSource() == this.b1) {
					music.PlayBotonMenu();
					jframe.remove(this);
					
					PanelMenu pm = new PanelMenu(partida.ObtenerMenu());
					
					jframe.add(pm);
					jframe.setVisible(true);
				}
			}
			
	 	}
	 	
	 	class PanelMusica extends JPanel implements ActionListener, AdjustmentListener{
	 		private static final long serialVersionUID = 1L;
			//Colores
			private Color newBlack 	= Color.decode("#222222");
			private Color newBlack3 = Color.decode("#3a3a3a");
			private Color newGreen  = Color.decode("#00b900"); 
			//Botones
			private JButton b1;
			private JButton mute;
			private JButton reset;
	 		//Panel de escoger volumen
			private JScrollBar barra;
			private JTextArea ta;
			private JLabel textSonidoM;
			private JLabel textSonido;
			private String text;
			private String textBoton;
			private String textArea;
			
	 		public PanelMusica() {
	 			
	 			setLayout(null);
				setBackground(Color.black);
				
				int volumen = (int) music.getVolume() - 1;
				textArea = String.valueOf(volumen + 1);
	 			//Panel para mutear
				barra = new JScrollBar(JScrollBar.HORIZONTAL,volumen,1,0,100);
				barra.setBounds(10,177,170,20); 
				barra.setBackground(newBlack3);
				barra.addAdjustmentListener(this);
				
				this.textSonido = new JLabel("<html>Escoge el volumen</html>"); 
				textSonido.setBounds(10,135,200,60);
				textSonido.setFont(new Font("Courier New",Font.PLAIN,13));
				textSonido.setForeground(Color.green);
				
				Border borderArea = BorderFactory.createLineBorder(newGreen, 1);
				ta = new JTextArea(textArea,1,1);
				ta.setBorder(borderArea);
				ta.setBounds(225,167,60,37);
				ta.setFont(new Font("Courier New",Font.PLAIN,32));
				ta.setForeground(Color.green);
				ta.setBackground(this.newBlack);
				ta.setEditable(false);
				//Botones para mutear/No mutear
				this.mute = new JButton();
				
				if (!music.getMute()) {
					text = "<html>Ahora mismo tienes el audio muteado</html>";
					textBoton = "desmutear";
					mute.setBounds(202,215,108,40); 
				}else {
					text = "<html>Ahora mismo tienes el audio desmuteado</html>";
					textBoton = "mutear";
					mute.setBounds(205,215,100,40); 
				}
				
				mute.setText(textBoton);
				mute.setBackground(this.newBlack);
				mute.setForeground(Color.green);
				mute.addActionListener(this);
				
				this.textSonidoM = new JLabel(text); 
				textSonidoM.setBounds(10,205,200,60);
				textSonidoM.setFont(new Font("Courier New",Font.PLAIN,13));
				textSonidoM.setForeground(Color.green);
				
				add(textSonidoM);
				add(mute);
				//Boton para recetar la musica
				this.reset = new JButton("Resetear musica");
				reset.setBounds(25,275,280,50); 
				reset.setBackground(this.newBlack);
				reset.setForeground(Color.green);
				reset.addActionListener(this);
				add(reset);
	 			//Boton para volver al menu
				this.b1 = new JButton("Volver al menu"); 
				b1.setBounds(5,345,325,50); 
				b1.setBackground(this.newBlack);
				b1.setForeground(Color.green);
				add(b1);
				b1.addActionListener(this);
				//label de la compañia
				JLabel img1 = new JLabel();
				JLabel l1 = new JLabel("Bienvenido a War Planet"); 
				JLabel l2 = new JLabel("Diogo entertainment©"); 
				l1.setBounds(5,5,325,60);
				l2.setBounds(0,385,340,50);
				l1.setHorizontalAlignment(JLabel.CENTER);
				l2.setHorizontalAlignment(JLabel.CENTER);
				l1.setFont(new Font("Courier New",Font.PLAIN,21));
				l2.setFont(new Font("Courier New",Font.PLAIN,13));
				l1.setForeground(Color.green);
				l2.setForeground(Color.green);
				
				img1.setIcon(new ImageIcon("res/images/logoMisiles.png"));
				img1.setBounds(20,0,340,220);
				
				Border border = BorderFactory.createLineBorder(Color.green, 2);
				l1.setBorder(border);
				l1.setBackground(this.newBlack3);
				l1.setOpaque(true);
				
				add(textSonido);
				add(barra);
				add(img1);
				add(ta);
				add(l1);
				add(l2);
	 		}
	 		
			@Override
			public void actionPerformed(ActionEvent boton) {
				//Cogemos el div padre
				JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
				
				if(boton.getSource() == this.b1) {
					music.PlayBotonMenu();
					jframe.remove(this);
					
					PanelMenu pm = new PanelMenu(partida.ObtenerMenu());
					
					jframe.add(pm);
					jframe.setVisible(true);
				}
				
				if(boton.getSource() == this.mute) {
					music.PlayBotonMenu();
					music.setMute(!music.getMute());
					
					if(mute.getBackground() != Color.green) {
						mute.setBackground(Color.green);
						mute.setForeground(this.newBlack);
					}else {
						mute.setBackground(this.newBlack);
						mute.setForeground(Color.green);
					}
				}
				
				if(boton.getSource() == this.reset) {
					music.PlayBotonMenu();
					musica.stopMusicaMenu();
					try {
						//Paramos el sistema para que se vea la transicion
						TimeUnit.SECONDS.sleep(1);
						musica = new HiloMusica(music);
						musica.start();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void adjustmentValueChanged(AdjustmentEvent event) {
				if(event.getSource() == barra) {
					int nuevoVolumen = barra.getValue() + 1;
					textArea = String.valueOf(nuevoVolumen);
					ta.setText(textArea);
					music.setVolume(nuevoVolumen);
				}
			}
	 		
	 	}
	 	
	 	class PanelHistorial extends JPanel implements ActionListener {
			private static final long serialVersionUID = 1L;
			//Colores
			private Color newBlack 	= Color.decode("#222222");
			private Color newBlack3 = Color.decode("#3a3a3a");
			//Botones
			private JButton b1;
			private JButton bRanking;
			private JButton bHistorial;
			
			public PanelHistorial() {
				
				setLayout(null);
				setBackground(Color.black);
				
				//Imagen
				JLabel img1	= new JLabel();				
				img1.setIcon(new ImageIcon("res/images/logoMisiles.png"));
				img1.setBounds(20,0,340,220);
				//Panel de los botones
				this.bRanking = new JButton("Ver Ranking");
				this.bHistorial = new JButton("Ver Historial");
				JLabel text = new JLabel("Escoge la opcion que prefieras");
				text.setBounds(5,158,325,50);
				text.setHorizontalAlignment(JLabel.CENTER);
				text.setFont(new Font("Courier New",Font.PLAIN,14));
				text.setForeground(Color.green);
				
				bRanking.setBounds(5,200,325,50);
				bRanking.setBackground(this.newBlack);
				bRanking.setForeground(Color.green);
				
				bHistorial.setBounds(5,255,325,50);
				bHistorial.setBackground(this.newBlack);
				bHistorial.setForeground(Color.green);
				
				bRanking.addActionListener(this);
				bHistorial.addActionListener(this);
				//Boton para volver al menu
				this.b1=new JButton("Volver al menu"); 
				b1.setBounds(5,345,325,50); 
				b1.setBackground(this.newBlack);
				b1.setForeground(Color.green);
				add(b1);
				b1.addActionListener(this);
				//label de la compañia
				JLabel l1 = new JLabel("Bienvenido a War Planet"); 
				JLabel l2 = new JLabel("Diogo entertainment©"); 
				l1.setBounds(5,5,325,60);
				l2.setBounds(0,385,340,50);
				l1.setHorizontalAlignment(JLabel.CENTER);
				l2.setHorizontalAlignment(JLabel.CENTER);
				l1.setFont(new Font("Courier New",Font.PLAIN,21));
				l2.setFont(new Font("Courier New",Font.PLAIN,13));
				l1.setForeground(Color.green);
				l2.setForeground(Color.green);
				
				Border border = BorderFactory.createLineBorder(Color.green, 2);
				l1.setBorder(border);
				l1.setBackground(this.newBlack3);
				l1.setOpaque(true);
				
				add(img1);
				add(l1);
				add(l2);
				add(text);
				add(bRanking);
				add(bHistorial);
			}
			
			@Override
			public void actionPerformed(ActionEvent boton) {
				//Cogemos el div padre
				JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
				
				if(boton.getSource() == this.b1) {
					music.PlayBotonMenu();
					jframe.remove(this);
					
					PanelMenu pm = new PanelMenu(partida.ObtenerMenu());
					
					jframe.add(pm);
					jframe.setVisible(true);
				}else if(boton.getSource() == this.bHistorial) {
					music.PlayBotonMenu();
					jframe.remove(this);
					
					PanelInfoHistorial pm = new PanelInfoHistorial(true);
					
					jframe.add(pm);
					jframe.setVisible(true);
				}else if(boton.getSource() == this.bRanking) {
					music.PlayBotonMenu();
					jframe.remove(this);
					
					PanelInfoHistorial pm = new PanelInfoHistorial(false);
					
					jframe.add(pm);
					jframe.setVisible(true);
				}
			}
			
	 	}
	 	
	 	class PanelInfoHistorial extends JPanel implements ActionListener {
			private static final long serialVersionUID = 1L;
			//Colores
			private Color newBlack 	= Color.decode("#222222");
			private Color newBlack3 = Color.decode("#3a3a3a");
			//Botones
			private JButton b1;
			//ArrayList
			private ArrayList<String> ganadores = new ArrayList<String>();
			
			public PanelInfoHistorial(boolean historial) {
				
				setLayout(null);
				setBackground(Color.black);
				
				JTextArea textArea = new JTextArea(1, 1);
				
				textArea.setEditable(false);
				
				Historial hist = new Historial();
				if(!historial) {
					ganadores = hist.MostrarRanking();
					textArea.append("\t<| --- Ranking --- |>\n");
					for (String win : ganadores) {
						textArea.append(win + "\n");
					}
				}else if (historial){
					ArrayList<Historial> listHistorial = new ArrayList<Historial>();
					listHistorial = hist.MostrarHistorial();
					if (listHistorial.size() != 0) {
						textArea.append("\t<| --- Historial de Ganadores --- |>\n");
						for (Historial lista : listHistorial) {
							textArea.append(" ----------------------------------------"+"\n");
							textArea.append(" |<Nombre de Equipo>  ->  "+lista.getNombreEquipo()+"\n");
							textArea.append(" |<Nombre de Planeta> -> "+lista.getNombrePlaneta()+"\n");
							textArea.append(" |<Numero de Equipos> -> "+lista.getNumeroEquipos()+"\n");
							textArea.append(" |<Numero de Rondas>  -> "+lista.getNumeroRondas()+"\n");
							textArea.append(" |<Fecha>  -> "+lista.getMostrarFecha()+"\n");
							textArea.append(" ----------------------------------------"+"\n");
						}
					}else {
						textArea.append("\t<| --- No hay registro de ningún ganador --- |>\n");
					}
				}
				
				Border borderArea = BorderFactory.createLineBorder(Color.green, 2);
				textArea.setBounds(5,70,325,60);
				textArea.setBackground(Color.black);
				textArea.setForeground(Color.green);
				JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
				scrollPane.setBounds(7,72,323,270);
				scrollPane.setBackground(Color.black);
				scrollPane.setBorder(borderArea);
				scrollPane.setOpaque(true);
				add(scrollPane);
				
				//Boton para volver al menu
				this.b1=new JButton("Volver atras"); 
				b1.setBounds(5,345,325,50); 
				b1.setBackground(this.newBlack);
				b1.setForeground(Color.green);
				add(b1);
				b1.addActionListener(this);
				//label de la compañia
				JLabel l1 = new JLabel("Bienvenido a War Planet"); 
				JLabel l2 = new JLabel("Diogo entertainment©"); 
				l1.setBounds(5,5,325,60);
				l2.setBounds(0,385,340,50);
				l1.setHorizontalAlignment(JLabel.CENTER);
				l2.setHorizontalAlignment(JLabel.CENTER);
				l1.setFont(new Font("Courier New",Font.PLAIN,21));
				l2.setFont(new Font("Courier New",Font.PLAIN,13));
				l1.setForeground(Color.green);
				l2.setForeground(Color.green);
				
				Border border = BorderFactory.createLineBorder(Color.green, 2);
				l1.setBorder(border);
				l1.setBackground(this.newBlack3);
				l1.setOpaque(true);
				
				add(l1);
				add(l2);
			}
			
			@Override
			public void actionPerformed(ActionEvent boton) {
				//Cogemos el div padre
				JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
				
				if(boton.getSource() == this.b1) {
					music.PlayBotonMenu();
					jframe.remove(this);
					
					PanelHistorial ph = new PanelHistorial();
					
					jframe.add(ph);
					jframe.setVisible(true);
				}
			}
	 	}
}
 
 