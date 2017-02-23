package org.music;

import org.junit.Test;
import org.music.boundary.Composers;
import org.music.entity.Composer;
import org.music.entity.Era;
import org.music.entity.Language;
import org.music.entity.Opera;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.text.IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertThat;

public class HamcrestComposersTest {

    @Test
    public void should_find_composer_by_name() {

        // Given:
        Composers composers = new Composers();

        // When:
        final Composer mozart = composers.findComposerByName("Wolfgang Amadeus Mozart");

        // Then:
        assertThat(mozart.getName(), is(equalToIgnoringWhiteSpace("Wolfgang Amadeus Mozart")));
        assertThat(mozart.getEra(), is(equalTo(Era.CLASSICAL)));
        assertThat(mozart.getBirthdate(), is(equalTo(LocalDate.of(1756, 1, 27))));
        assertThat(mozart.getDied(), is(equalTo(LocalDate.of(1791, 12, 5))));

    }

    @Test
    public void should_find_operas_by_composer_name() {

        // Given:
        Composers composers = new Composers();

        // When:
        final List<Opera> operas = composers.findOperasByComposerName("Ludwig van Beethoven");

        // Then:
        final Opera fidelio = Composers.OperaFactory
                .createOpera("Fidelio")
                .language(Language.GERMAN)
                .librettist("Georg Friedrich Treitschke")
                .roles("Florestan", "Leonore", "Rocco")
                .build();

        assertThat(operas, hasSize(1));
        assertThat(getOperaNames(operas), hasItem("Fidelio"));
        assertThat(operas, hasItem(fidelio));

    }

    private List<String> getOperaNames(List<Opera> operas) {
        return operas.stream()
                .map(Opera::getName)
                .collect(Collectors.toList());
    }

}
