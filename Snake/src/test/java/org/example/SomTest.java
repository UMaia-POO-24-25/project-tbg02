package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SomTest {


@BeforeEach
void setUp() {
    Som.clip = null; // Reset the static variable before each test
}

    @Test
    void testPlaySound() throws Exception {
        // Mock de dependências
        InputStream mockInputStream = mock(InputStream.class);
        AudioInputStream mockAudioInputStream = mock(AudioInputStream.class);
        Clip mockClip = mock(Clip.class);

        // Stub para métodos estáticos
        try (var mockedAudioSystem = Mockito.mockStatic(AudioSystem.class)) {
            mockedAudioSystem.when(() -> AudioSystem.getAudioInputStream(any(InputStream.class)))
                    .thenReturn(mockAudioInputStream);
            mockedAudioSystem.when(AudioSystem::getClip).thenReturn(mockClip);

            // Chamada do método
            Som.playSound("/sounds/start.wav");

            // Verificar comportamentos
            verify(mockClip).open(mockAudioInputStream);
            verify(mockClip).start();
        }
    }

    @Test
    void testPlaySoundLoop() throws Exception {
        // Mock dependencies
        InputStream mockInputStream = mock(InputStream.class);
        AudioInputStream mockAudioInputStream = mock(AudioInputStream.class);
        Clip mockClip = mock(Clip.class);

        // Stub static methods
        try (var mockedAudioSystem = Mockito.mockStatic(AudioSystem.class)) {
            mockedAudioSystem.when(() -> AudioSystem.getAudioInputStream(any(InputStream.class)))
                    .thenReturn(mockAudioInputStream);
            mockedAudioSystem.when(AudioSystem::getClip).thenReturn(mockClip);

            // Execute method
            Som.playSoundLoop("/sounds/start.wav");

            // Verify behavior
            verify(mockClip).open(mockAudioInputStream);
            verify(mockClip).loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

@Test
void testStopSound() {
    // Mock existing clip
    Clip mockClip = mock(Clip.class);
    when(mockClip.isRunning()).thenReturn(true);
    Som.clip = mockClip;

    // Execute method
    Som.stopSound();

    // Verify behavior
    verify(mockClip).stop();
}

}
