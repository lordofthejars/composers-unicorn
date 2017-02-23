package org.music.boundary;

import org.music.entity.Opera;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Gramophone {

    private Opera currentOpera;

    public void play(final Opera opera) {

        // Not thread safe,.... for for sake of simplicity is enough
        Runnable playable = () -> {
            try {
                TimeUnit.SECONDS.sleep(new Random(2).nextInt());
                currentOpera = opera;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread  = new Thread(playable);
        thread.start();

    }

    public boolean isPlaying() {
        // Not thread safe,.... for for sake of simplicity is enough
        return this.currentOpera != null;
    }

    public Opera getCurrentOpera() {
        return currentOpera;
    }
}
