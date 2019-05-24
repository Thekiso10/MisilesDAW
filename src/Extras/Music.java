package Extras;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.*;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class Music {
	
	private float volume;
	private boolean Mute;
	private boolean stop;
	
	public Music(int volumen) {
		this.volume = volumen;
		this.Mute = true;
		this.stop = false;
	}
	
	//Sonidos
	public void PlayBotonMenu() {
		play("/songs/Select_Menu.wav");
	}
	
	public void PlayVoiceTransmition() {
		play("/songs/vozMujer_transmition.wav");
	}
	
	public void PlayShoot() {
		play("/songs/disparo.wav");
	}
	
	public void PlayDied() {
		play("/songs/lose.wav");
	}
	
	public void PlayEnd() {
		play("/songs/end.wav");
	}
	
	public void PlayWinSurvival() {
		play("/songs/win.wav");
	}
	//Musica
	public void PlayMusicaMenu() {
		play("/songs/Musica_1.wav");
	}
	
	private void play(String filePath) {
        final File file = new File(getClass().getResource(filePath).getFile());
        
        if(Mute == true) {

	        try (final AudioInputStream in = getAudioInputStream(file)) {
	
	            final AudioFormat outFormat = getOutFormat(in.getFormat());
	            final Info info = new Info(SourceDataLine.class, outFormat);
	
	            try (final SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
	
	                if (line != null) {
	                    line.open(outFormat);
	                    FloatControl gainControl = 
	                    	    (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
	                    	gainControl.setValue((float) 6*(volume/100)); 
	                    line.start();
	                    stream(getAudioInputStream(outFormat, in), line);
	                    line.stop();
	                }
	            }
	
	        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
	            throw new IllegalStateException(e);
	        }
        }
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();

        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line) 
        throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            if(getStop()) {
            	this.stop = false;
            	break;
            }
            line.write(buffer, 0, n);
        }
    }
    
    //Setters
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public float getVolume() {
		return volume;
	}
	
	public boolean getMute() {
		return Mute;
	}
	
	public void Stop() {
		this.stop = true;
	}
	
	public boolean getStop() {
		return this.stop;
	}

	public void setMute(boolean mute) {
		Mute = mute;
	}
}
