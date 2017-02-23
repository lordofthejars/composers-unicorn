package org.music;

import org.awaitility.Awaitility;
import org.junit.Test;
import org.music.boundary.Composers;
import org.music.boundary.Gramophone;
import org.music.entity.Language;
import org.music.entity.Opera;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class GramophoneTest {


    @Test
    public void should_play_operas() throws InterruptedException {
        // Given:
        final Opera nozzeDiFigaro = Composers.OperaFactory
                .createOpera("Le Nozze di Figaro")
                .language(Language.ITALIAN)
                .librettist("Lorenzo Da Ponte")
                .roles("Count Almaviva", "Countess Rosina", "Susanna", "Figaro")
                .build();

        Gramophone gramophone = new Gramophone();

        // When:
        gramophone.play(nozzeDiFigaro);

        // Then:
        TimeUnit.SECONDS.sleep(3);
        assertThat(gramophone.getCurrentOpera()).isEqualTo(nozzeDiFigaro);

    }

    @Test
    public void should_play_operas_version_2() {
        // Given:
        final Opera nozzeDiFigaro = Composers.OperaFactory
                .createOpera("Le Nozze di Figaro")
                .language(Language.ITALIAN)
                .librettist("Lorenzo Da Ponte")
                .roles("Count Almaviva", "Countess Rosina", "Susanna", "Figaro")
                .build();

        Gramophone gramophone = new Gramophone();

        // When:
        gramophone.play(nozzeDiFigaro);

        // Then:
        await().atMost(5, TimeUnit.SECONDS).until(gramophone::isPlaying);
        assertThat(gramophone.getCurrentOpera()).isEqualTo(nozzeDiFigaro);

    }


}
