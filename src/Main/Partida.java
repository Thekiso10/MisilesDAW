package Main;

import java.util.ArrayList;
import java.util.Iterator;

import Extras.Historial;
import Jugar.Equipo;
import Jugar.MensageAtaque;
import Jugar.Planetas;

public class Partida {
	//Craer Equipos
	private Equipo equipo;
	private Planetas planeta;
	private ArrayList<MensageAtaque> ListaMsg;
	private int numEquiposVivos;
	private String ganador;
	//Constantes
	private final int TAMAÑO_NOMBRE = 6;
	
	public Partida() {
		this.equipo = new Equipo();
		this.planeta = new Planetas();
		this.ListaMsg = new ArrayList<MensageAtaque>();
	}
	
	public void Combate(int bombas_of, String nombre_AT,  String planeta_AT, int tipoAta, int vidasAta, Equipo equipo_Def) {
		MensageAtaque msg = new MensageAtaque(bombas_of, nombre_AT, planeta_AT, tipoAta, vidasAta, equipo_Def);
		ListaMsg.add(msg);
	}
	
	public int CrearEquipos(ArrayList<Equipo>listaEquipos, String nombreEquipo, String nombrePlanetas) {
		int correcto = 0;
		String[] comprobar = {nombrePlanetas.toLowerCase(), ""};
		if (nombreEquipo.trim().length() != 0 && nombrePlanetas.trim().length() != 0) {
			if(nombreEquipo.length() <= TAMAÑO_NOMBRE) {
				if(equipo.ComprobarNombre(listaEquipos, nombreEquipo) ) {
					if(planeta.ComprobarNombre(comprobar)) {
						nombrePlanetas = nombrePlanetas.substring(0,1).toUpperCase() + nombrePlanetas.substring(1).toLowerCase();
						Planetas p = new Planetas(Integer.parseInt(comprobar[1]), nombrePlanetas);
						Equipo e = new Equipo(nombreEquipo,p);
						e.GenerarColores();
						listaEquipos.add(e);
					}else {
						correcto = -2;
					}
				}else {
					correcto = -1;
				}
			}else {
				correcto = -4;
			}
		}else {
			correcto = -3;
		}
		
		return correcto;
	}
	
	public int comprobarEquiposVivos(ArrayList<Equipo> equipos, ArrayList<String> Text) {
		this.numEquiposVivos = equipos.size();
		
		Iterator<Equipo> item = equipos.iterator();
		while (item.hasNext()) {
			Equipo equipo = (Equipo) item.next();
			if (equipo.getVidas() > 0) {
				if(equipo.getPlanetas().getTipo() == 5) 
					equipo.setMisiles_por_ronda((equipo.getMisiles_por_ronda() + 10));
				
				Text.add("El equipo "+equipo.getNombre_equipo()+" sigue con vida.\n");
			}else {
				Text.add("El equipo "+equipo.getNombre_equipo()+" ha caido.\n");
				item.remove();
				numEquiposVivos--;
			}
		}
		
		return numEquiposVivos;
	}
	
	public void finalizarPartida(ArrayList<Equipo> equipos, int rondas) {
		
		Historial historial = new Historial();
		
		if (this.numEquiposVivos == 1) {
			this.ganador = equipos.get(0).getNombre_equipo();
			historial.CargarVarHistorial(Equipo.getNumero_equipos(), rondas, ganador, equipos.get(0).getPlanetas().getNombre());
			historial.GuardarHistorial();
			historial.EjecutarXML();
		}
		
		//Limpiar objectos
		equipos.clear();
	}
	
	//Pasar el error al crear equipos
	public String CodigoError(int codigo) {
		String text = "";
		
		switch (codigo) {
		case -1:
			text = "Nombre incorrecto";
			break;
		
		case -2:
			text = "El nombre del planeta esta mal escrito";
			break;
		
		case -3:	
			text = "Ninguno de los campos puede estar vacio";
			break;
			
		case -4:
			text = "El tamaño maximo de caracteres es "+TAMAÑO_NOMBRE;
			break;
			
		default:
			text = "Ha ocurido un error";
			break;
		}
		
		return text;
	}
	
	//texto de la parte del menu
	public ArrayList<String> ObtenerMenu() {
		ArrayList<String> menu = new ArrayList<>();
		
		menu.add("| Jugar |");
		menu.add("| Reglas del juego |");
		menu.add("| Información |");
		menu.add("| Opciones de Sonido |");
		menu.add("| Opciones de Historial |");
		menu.add("| Salir |");
		
		return menu;
	}
	
	public ArrayList<String> ObtenerMenuInfo() {
		ArrayList<String> info = new ArrayList<>();
		
		info.add("<html><center>Este juego ha estado desarrolado por Diogo entertainment©.<br>El Autor material es Diogo Assuncao.<br>Este juego esta bajo la protecion<br>del Copyright 2018-2019</center></html>");		
		info.add("Versión 3.1");
		info.add("Contacto: diego_lyoko@hotmail.es");
		
		return info;
	}
	
	public ArrayList<String> ObtenerMenuReglas() {
		ArrayList<String> regla = new ArrayList<>();
		
		regla.add("<html><ul>Reglas del juego</ul><li>Número de vidas por equipo: 200</li><li>Número de misiles por ronda: 50</li><li>Los misiles de defensa cuestan el doble. Ejemplo: 10 misiles de ataque, te quedan 40 restantes, es decir 40/2 = 20 misiles de defensa</li></html>");
		
		return regla;
	}
	
	public String OtenerPlanetas() {
		String text = "";
			
		text = "<1> Normal:\n\t-Sin modificaciones\n<2> Rojo:\n\t-Ataca del doble al verde\n\t-Ataca la mitad al azul\n";
		text = text.concat("<3> Azul:\n\t-Ataca del doble al azul\n\t-Ataca la mitad al rojo\n<4> Verde:\n\t-Ataca del doble al azul\n\t-Ataca la mitad al rojo\n");
		text = text.concat("<5> Gigante:\n\t-Doble de vida\n\t-Empieza solo con 10 misiles va acumulando 10 cada ronda\n<6> Enano:\n\t-Mitat de vida\n\t-Probabilidad de esquivar cada misiles del 50%\n");
		text = text.concat("<7> Venus:\n\t-tiene un 15% de que su ataque se amplificado pero,\n\t-un 15% de que su defensa pierde la mitad de poder\n<8> Marte:\n\t-Tiene un 30% de que puede absorver misiles, eso significa que se puede curar\n\t-comienza con vida aleatoria entre 50-250\n");
		text = text.concat("<9> Pluton: <->\n\t-tu daño depende de la diferencia de vida que hay entre el y el objectivo.\n<10> Europa: <->\n\t-Obtiene 10 misiles defensivos EXTRA.\n\t-tiene un 10% de posibilidades de que su ataque falle.\n");
		
		return text;
	}
	
	public ArrayList<MensageAtaque> getListaMsg(){
		return ListaMsg;
		
	}

	public int getNumEquiposVivos() {
		return numEquiposVivos;
	}

	public void setNumEquiposVivos(int numEquiposVivos) {
		this.numEquiposVivos = numEquiposVivos;
	}
	
	public String getGanador() {
		return ganador;
	}
	
}
