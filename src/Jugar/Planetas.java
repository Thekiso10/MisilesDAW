package Jugar;

import java.util.Random;

public class Planetas {
	
	private int tipo;
	private String nombre;
	
	public Planetas() {
		
	}
	
	public Planetas(int tipo, String nombre) {
		
		this.tipo = tipo;
		this.nombre = nombre;
	}
	
	public int inicializarEstadisticas(boolean isVida) {
		int valores = 0;
		
		if(isVida) {
			valores = 200;
			
			if(this.tipo == 5) {
				valores *= 2;
			}else if (this.tipo == 6) {
				valores /= 2;
			}
			
			if(this.tipo == 8) {
				Random rnd = new Random();
				
				valores = rnd.nextInt(251)+50;
			}
			
		}else {
			valores = 50;
			if(this.tipo == 5) {
				valores = 10;
			}
		}
		
		return valores;
	}
	
	public boolean ComprobarNombre(String[] comprobar) {
		boolean correcto = false;
		int i = 0;
		String[] nombrePlanetas = {"normal","rojo","azul","verde","gigante","enano","venus","marte","pluton","europa"};
		if(!comprobar[0].isEmpty()) {
			while (!correcto && i != nombrePlanetas.length) {
				
				if(comprobar[0].equals(nombrePlanetas[i])) {
					correcto = true;
					comprobar[1] = Integer.toString(i + 1);
				}
				
				i++;
			}
		}
		return correcto;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
}
