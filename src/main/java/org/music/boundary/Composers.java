package org.music.boundary;

import org.music.entity.Composer;
import org.music.entity.Era;
import org.music.entity.Language;
import org.music.entity.Opera;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class Composers {


    private Map<String, Composer> composers = new HashMap<>();

    public Composers() {
        final Opera magicFlute = OperaFactory
                .createOpera("Die Zauberflöte")
                .language(Language.GERMAN)
                .librettist("Emanuel Schikaneder")
                .roles("Tamino", "Papageno", "Pamina", "The Queen of the Night", "Sarastro")
                .build();

        final Opera donGiovanni = OperaFactory
                .createOpera("Don Giovanni")
                .language(Language.ITALIAN)
                .librettist("Lorenzo Da Ponte")
                .roles("Don Giovanni", "Leporello", "Il Commendatore", "Donna Anna")
                .build();

        final Composer mozart = ComposerFactory
                .createComposer("Wolfgang Amadeus Mozart")
                .era(Era.CLASSICAL)
                .birthdate(LocalDate.of(1756, 1, 27))
                .died(LocalDate.of(1791, 12, 5))
                .operas(magicFlute, donGiovanni)
                .build();

        final Opera fidelio = OperaFactory
                .createOpera("Fidelio")
                .language(Language.GERMAN)
                .librettist("Georg Friedrich Treitschke")
                .roles("Florestan", "Leonore", "Rocco")
                .build();

        final Composer beethoven = ComposerFactory
                .createComposer("Ludwig van Beethoven")
                .era(Era.ROMANTIC)
                .birthdate(LocalDate.of(1770, 12, 17))
                .died(LocalDate.of(1827, 3, 26))
                .operas(fidelio)
                .build();


        composers.put(mozart.getName(), mozart);
        composers.put(beethoven.getName(), beethoven);

    }

    public Composer findComposerByName(String composerName) {
        final Composer composer = composers.get(composerName);
        if (composer == null) {
            throw new IllegalArgumentException(String.format("Composer %s is not found", composerName));
        }
        return composer;
    }

    public List<Opera> findOperasByComposerName(String composerName) {
        Composer composer = findComposerByName(composerName);

        return composer.getOperas();

    }

    public static class ComposerFactory {
        private Composer composer;

        private ComposerFactory() {}

        public static ComposerFactory createComposer(String name) {
            ComposerFactory composersFactory = new ComposerFactory();
            composersFactory.composer = new Composer();
            composersFactory.composer.setName(name);

            return composersFactory;
        }

        public ComposerFactory era(Era era) {
            composer.setEra(era);
            return this;
        }

        public ComposerFactory birthdate(LocalDate birthdate) {
            composer.setBirthdate(birthdate);
            return this;
        }

        public ComposerFactory died(LocalDate died) {
            composer.setDied(died);
            return this;
        }

        public ComposerFactory operas(Opera...operas) {
            Arrays.stream(operas)
                    .forEach(composer::addOpera);

            return this;
        }

        public Composer build() {
            return composer;
        }

    }

    public static class OperaFactory {

        private Opera opera;

        private OperaFactory() {}

        public static OperaFactory createOpera(String name) {
            OperaFactory operaFactory = new OperaFactory();
            operaFactory.opera = new Opera();
            operaFactory.opera.setName(name);

            return operaFactory;
        }

        public OperaFactory language(Language language) {
            opera.setLanguage(language);
            return this;
        }

        public OperaFactory librettist(String librettist) {
            opera.setLibrettist(librettist);
            return this;
        }

        public OperaFactory roles(String... roles) {
            Arrays.stream(roles)
                    .forEach(opera::addRole);

            return this;
        }

        public Opera build() {
            return opera;
        }

    }

}
