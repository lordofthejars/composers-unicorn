package org.music;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.music.boundary.Composers;
import org.music.entity.Composer;
import org.music.entity.Era;
import org.music.entity.Opera;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JUnitComposersTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_find_composer_by_name() {

        // Given:
        Composers composers = new Composers();

        // When:
        final Composer mozart = composers.findComposerByName("Wolfgang Amadeus Mozart");

        // Then:
        assertEquals("Name of the composer", "Wolfgang Amadeus Mozart", mozart.getName());
        assertEquals("Era of the composer", Era.CLASSICAL, mozart.getEra());
        assertEquals("Birthdate of the composer", LocalDate.of(1756, 1, 27), mozart.getBirthdate());
        assertEquals("Died date of the composer", LocalDate.of(1791, 12, 5), mozart.getDied());

    }

    @Test
    public void should_find_operas_by_composer_name() {

        // Given:
        Composers composers = new Composers();

        // When:
        final List<Opera> operas = composers.findOperasByComposerName("Wolfgang Amadeus Mozart");

        // Then:
        assertEquals("Number of Operas", 2, operas.size());
        assertEquals("Magic Flute Opera", "Die Zauberflöte", operas.get(0).getName());
        assertEquals("Don Giovanni Opera", "Don Giovanni", operas.get(1).getName());

    }

    @Test
    public void should_find_operas_by_composer_name_version_2() {

        // Given:
        Composers composers = new Composers();

        // When:
        final List<Opera> operas = composers.findOperasByComposerName("Wolfgang Amadeus Mozart");

        // Then:
        assertEquals("Number of Operas", 2, operas.size());
        assertTrue("Contains All Operas", getOperaNames(operas).containsAll(Arrays.asList("Die Zauberflöte", "Don Giovanni")));

    }

    private List<String> getOperaNames(List<Opera> operas) {
        return operas.stream()
                .map(Opera::getName)
                .collect(Collectors.toList());
    }

    @Test
    public void should_throw_exception_if_composer_not_found() {
        // Given:
        Composers composers = new Composers();

        // When:
        try {
            final Composer salieri = composers.findComposerByName("Antonio Salieri");
            fail();
        } catch (IllegalArgumentException e) {

            // Then:
            assertEquals("Composer Antonio Salieri is not found", e.getMessage());
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_if_composer_not_found_version_2() {
        // Given:
        Composers composers = new Composers();

        // When:
        final Composer salieri = composers.findComposerByName("Antonio Salieri");

    }

    @Test
    public void should_throw_exception_if_composer_not_found_version_3() {
        // Given:
        Composers composers = new Composers();

        // When:
        thrown.expect(IllegalArgumentException.class);
        final Composer salieri = composers.findComposerByName("Antonio Salieri");

    }

}
