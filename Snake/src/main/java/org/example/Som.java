package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import javax.sound.sampled.Clip;
import java.io.InputStream;

/**
 * A classe Som é responsável por tocar arquivos de som no jogo.
 * Ela utiliza a API javax.sound.sampled para carregar e tocar sons.
 * O método playSound carrega um arquivo de som e o toca.
 * Se um som já estiver tocando, ele será interrompido antes de tocar o novo som.
 */

public class Som {
    public static Clip clip;

    public static void playSound(String soundFile) {
        try {
            InputStream audioSrc = Som.class.getResourceAsStream(soundFile);
            if (audioSrc == null) {
                throw new RuntimeException("Ficheiro de som não encontrado : " + soundFile);
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip newClip = AudioSystem.getClip();
            newClip.open(audioInputStream);
            newClip.start();
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            clip = newClip;
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
            Clip newClip = AudioSystem.getClip();
            newClip.open(audioInputStream);
            newClip.loop(Clip.LOOP_CONTINUOUSLY);
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
            clip = newClip;
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