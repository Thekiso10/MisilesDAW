package Hilos;

import Extras.Music;

public class HiloMusica extends Thread {
	
	private Music musica;
	private boolean detenerMusicaMenu;
	
	public HiloMusica(Music music) {
		this.detenerMusicaMenu = false;
		this.musica = music;
	}
	
	public void run() {
		while(!detenerMusicaMenu) {
			musica.PlayMusicaMenu();
		}
	}
	
	public void stopMusicaMenu() {
		this.detenerMusicaMenu = true;
		this.musica.Stop();
	}
};
