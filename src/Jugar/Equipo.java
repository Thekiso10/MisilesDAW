package Jugar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Equipo {

	private int vidas;
	private int misiles_por_ronda;
	private int bombas_def;
	private String nombre_equipo;
	private Color color;
	
	private Planetas planeta;
	
	private static int numero_equipos = 0; //Atrivuto incognito
	
	//constructor parcial
	public Equipo () {
	
	}
	
	public Equipo (String nombre, Planetas tipo) {
		this.nombre_equipo = "Team "+nombre;
		this.misiles_por_ronda = tipo.inicializarEstadisticas(false);
		this.vidas = tipo.inicializarEstadisticas(true);
		this.planeta = tipo;
		
		numero_equipos++;
	}
	//Combate
	
	public void CalcularMisilesDef(int bombas_def) {
		float resultado;
		int extra = 0;
		
		if (planeta.getTipo() == 10) {
			extra = 10;
		}
		
		resultado = Math.round((bombas_def / 2)) + extra;
		
		this.bombas_def = (int)resultado;
	}
	
	public int Combate(int bombas_of, int tipoAta, int vidasAta) {
		float valor_ataque = 1;
		int resultado = 0;
		
		if(this.vidas > 0) {
			//Probalidad Ataquente
			if(tipoAta == 10) {
				if(Probabilidad(10)) 
					return 2;
			}else if(tipoAta == 7) {
				if(Probabilidad(15)) {
					valor_ataque = 1.5f;
					resultado = 4;
				}	
			}
			//Probalidad Defensivo
			if(planeta.getTipo() == 6) {
				if(Probabilidad(50))
					return 1;
			}else if(planeta.getTipo() == 8) {
				if(Probabilidad(30))
					this.vidas += bombas_of;
					return 3;
			}
			
			switch (planeta.getTipo()) {
			case 2:
				if(tipoAta == 4) {valor_ataque = 2; resultado = 8;}
				if(tipoAta == 3) {valor_ataque = 0.5f; resultado = 6;}
				break;
			case 3:
				if(tipoAta == 2) {valor_ataque = 2; resultado = 8;}
				if(tipoAta == 4) {valor_ataque = 0.5f; resultado = 6;}
				break;
			case 4:
				if(tipoAta == 3) {valor_ataque = 2; resultado = 8;}
				if(tipoAta == 2) {valor_ataque = 0.5f; resultado = 6;}
				break;
			case 7:
				if(Probabilidad(15)) this.bombas_def /= 2; resultado = 5;
				break;
			case 9:
				float[] valores = {this.vidas, vidasAta};	
				CalcularPotencia(valores);
				valor_ataque = valores[0];
				resultado = (int) valores[1];
				break;
			default:
				break;
			}
			
			float damage = (bombas_of * valor_ataque) - this.bombas_def;
			
			if (damage > 0) {
				if(this.vidas - (int) Math.round(damage) >= 0) {
					this.vidas -= (int) Math.round(damage);
				}else{
					this.vidas = 0;
				}
				
				this.bombas_def = 0;
			}else {
				damage *= -1;
				this.bombas_def = (int) Math.round(damage);
			}
			
		}
		
		return resultado;
	}
	
	private boolean Probabilidad (int percent) {
		boolean isHit = false;
		Random rnd = new Random();
		
		int cien = rnd.nextInt(101);
		
		if (percent >= cien)
			isHit = true;
			
		return isHit;
	}
	
	private void CalcularPotencia(float [] valor) {
		// vida enemiga - tu vida
		float diferencia = valor[1] - valor[0]; 
		
		if(diferencia <= 0) {
			valor[0] = 0.5f;
			valor[1] = 6;
		}else if (diferencia >= 1 && diferencia <= 50) {
			valor[0] = 1;
			valor[1] = 0;
		}else if (diferencia >= 51 && diferencia <= 75){
			valor[0] = 1.25f;
			valor[1] = 7;
		}else {
			valor[0] = 1.5f;
			valor[1] = 8;
		}
		
	}
	
	//Fin Combate
	
	public boolean ComprobarNombre(ArrayList<Equipo> equipos, String Nombre) {
		int i=0;
		boolean Bien = true;
		String NombreTeam = "";
		String NombrePos = "";
		if(!Nombre.isEmpty()) {
			while(i != equipos.size() && Bien == true) {
				NombreTeam = equipos.get(i).nombre_equipo.toLowerCase();
				NombrePos = "team " + Nombre.toLowerCase();
				if (NombreTeam.equals(NombrePos)) {
					Bien = false;
				}
				NombrePos = "";
				i++;
			}
		}else {
			Bien = false;
		}
		return Bien;
	}
	
	public void GenerarColores() {
		Random rnd = new Random();
		
		int r = rnd.nextInt(256);
		int g = rnd.nextInt(256);
		int b = rnd.nextInt(256);
		
		this.color = new Color(r,g,b);
	}
	
	//getters and setters
	public int getVidas() {
		return vidas;
	}

	public int getMisiles_por_ronda() {
		return misiles_por_ronda;
	}

	public int getBombas_def() {
		return bombas_def;
	}
	
	public static int getNumero_equipos() {
		return numero_equipos;
	}

	public String getNombre_equipo() {
		return nombre_equipo;
	}

	public void setMisiles_por_ronda(int misiles_por_ronda) {
		this.misiles_por_ronda = misiles_por_ronda;
	}
	
	public Planetas getPlanetas() {
		return planeta;
	}
	
	public Color getColor() {
		return color;
	}
	
}