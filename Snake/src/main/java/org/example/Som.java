package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import javax.sound.sampled.Clip;
import java.io.InputStream;

public class Som {
    private static Clip clip;

    public static void playSound(String soundFile) {
        try {
            InputStream audioSrc = Som.class.getResourceAsStream(soundFile);
            if (audioSrc == null) {
                throw new RuntimeException("Ficheiro de som não encontrado : " + soundFile);
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Erro ao abrir som.");
            ex.printStackTrace();
        }
    }
    public static void playSoundLoop(String soundFile) {
        try {
            InputStream audioSrc = Som.class.getResourceAsStream(soundFile);
            if (audioSrc == null) {
                throw new RuntimeException("Ficheiro de som não encontrado : " + soundFile);
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audioSrc));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Erro ao abrir som.");
            ex.printStackTrace();
        }
    }

    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}