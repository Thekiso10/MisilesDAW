package Jugar;

public class MensageAtaque {

	private int bombas_of;
	private int tipoAta;
	private int vidasAta;
	private String mensage;
	private String NombreAt;
	private String planetaAta;
	
	private Equipo EquipoDef;
	
	public MensageAtaque(int B_of, String NombreAt, String planetaAta, int tipoAta, int vidasAta, Equipo NombreDef) {
		
		this.bombas_of = B_of;
		this.NombreAt = NombreAt;
		this.planetaAta = planetaAta;
		this.EquipoDef = NombreDef;
		this.tipoAta = tipoAta;
		this.vidasAta = vidasAta;
		
	}
	//Constructor por defecto
	public void GenerarAtaque(int isHit, int bombasDef) {
		String texto="";
		
		texto = texto.concat("|<< Equipo "+this.NombreAt+", del planeta "+this.planetaAta+", ataca al equipo "+this.EquipoDef.getNombre_equipo()+", del planeta "+ this.EquipoDef.getPlanetas().getNombre() +", con "+this.bombas_of+" misiles >>|");
		
		switch (isHit) {
		case 0:
			texto = texto.concat("\n|<< Equipo "+this.NombreAt+" ha acertado su ataque >>|");
			break;
		case 1:
			texto = texto.concat("\n|<< Equipo "+this.EquipoDef.getNombre_equipo()+" ha esquivado el ataque >>|");
			break;	
		case 2:
			texto = texto.concat("\n|<< Equipo "+this.NombreAt+" ha falado su ataque >>|");
			break;	
		case 3:
			texto = texto.concat("\n|<< Equipo "+this.EquipoDef.getNombre_equipo()+" ha absorvido el ataque >>|");
			break;	
		case 4:
			texto = texto.concat("\n|<< Equipo "+this.NombreAt+" ha tenido el ataque amplificado >>|");
			break;	
		case 5:
			texto = texto.concat("\n|<< Equipo "+this.NombreAt+" ha perdido la mitat de su defensa >>|");
			break;	
		default:
			switch (isHit) {
			case 6:
				texto = texto.concat("\n|<<El ataque del equipo "+this.NombreAt+" es poco eficaz >>|");
				break;

			case 7:
				texto = texto.concat("\n|<<El ataque del equipo "+this.NombreAt+" es algo eficaz >>|");
				break;
			case 8:
				texto = texto.concat("\n|<<El ataque del equipo "+this.NombreAt+" es mucho eficaz >>|");
				break;	
			}
			break;
		}
		
		texto = texto.concat("\n|<< Como el equipo "+this.EquipoDef.getNombre_equipo()+" tenia "+bombasDef+" misiles defensivos su vida es "+this.EquipoDef.getVidas()+" >>|\n");
		
		this.mensage = texto;
		
	}
	
	public String getMensage() {
		return mensage;
	}

	public String getNombreAt() {
		return NombreAt;
	}

	public Equipo getEquipoDef() {
		return EquipoDef;
	}
	
	public int getBombas_of() {
		return bombas_of;
	}
	public int getTipoAta() {
		return tipoAta;
	}
	
	public int getVidasAta() {
		return vidasAta;
	}
}