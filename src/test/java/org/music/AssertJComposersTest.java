package org.music;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.music.boundary.Composers;
import org.music.boundary.ComposersResourceAsync;
import org.music.entity.Composer;
import org.music.entity.Era;
import org.music.entity.Opera;

import javax.ws.rs.container.AsyncResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.awaitility.Awaitility.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AssertJComposersTest {

    @Test
    public void should_find_composer_by_name() {

        // Given:
        Composers composers = new Composers();

        // When:
        final Composer mozart = composers.findComposerByName("Wolfgang Amadeus Mozart");

        // Then:
        assertThat(mozart.getName()).isEqualTo("Wolfgang Amadeus Mozart");
        assertThat(mozart).returns("Wolfgang Amadeus Mozart", Composer::getName);
        assertThat(mozart.getEra()).isEqualTo(Era.CLASSICAL);
        assertThat(mozart.getBirthdate()).isEqualTo(LocalDate.of(1756, 1, 27));
        assertThat(mozart.getDied()).isEqualTo(LocalDate.of(1791, 12, 5));
    }

    @Test
    public void should_find_operas_by_composer_name() {

        // Given:
        Composers composers = new Composers();

        // When:
        final List<Opera> operas = composers.findOperasByComposerName("Wolfgang Amadeus Mozart");

        // Then:
        assertThat(operas)
                .hasSize(2)
                .extracting(Opera::getName)
                .containsExactlyInAnyOrder("Die Zauberflöte", "Don Giovanni");

    }

    @Test
    public void should_find_composer_by_name_soft_assertions() {

        // Given:
        Composers composers = new Composers();

        // When:
        final Composer mozart = composers.findComposerByName("Wolfgang Amadeus Mozart");

        // Then:
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(mozart.getName()).isEqualTo("Wolfgang Amadeus Mozart");
            softly.assertThat(mozart.getEra()).isEqualTo(Era.CLASSICAL);
            softly.assertThat(mozart.getBirthdate()).isEqualTo(LocalDate.of(1756, 1, 27));
            softly.assertThat(mozart.getDied()).isEqualTo(LocalDate.of(1791, 12, 5));
         });
    }

    @Test
    public void should_throw_exception_if_composer_not_found_version_3() {
        // Given:
        Composers composers = new Composers();

        // When:
        // Then:
        assertThatThrownBy(
                () -> composers.findComposerByName("Antonio Salieri")
            )
            .isInstanceOf(IllegalArgumentException.class)
            .withFailMessage("Composer Antonio Salieri is not found");

        // When:
        Throwable thrown = catchThrowable(() -> composers.findComposerByName("Antonio Salieri"));

        // Then:
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                          .withFailMessage("Composer Antonio Salieri is not found");

    }

    @Test
    public void should_get_composer_and_image_async() {

        // Given:
        final AsyncResponse asyncResponse = mock(AsyncResponse.class);
        ArgumentCaptor<ComposersResourceAsync.ComposerInfo> responseArgumentCaptor = ArgumentCaptor.forClass(ComposersResourceAsync.ComposerInfo.class);

        ComposersResourceAsync composersResourceAsync = new ComposersResourceAsync();

        // When:
        composersResourceAsync.composersInfoAsync(asyncResponse, "Wolfgang Amadeus Mozart");

        // Then:
        given().ignoreExceptions()
                .await().atMost(10, TimeUnit.SECONDS)
                .until(() -> {
                        verify(asyncResponse).resume(responseArgumentCaptor.capture());
                });

        final ComposersResourceAsync.ComposerInfo composerInfo = responseArgumentCaptor.getValue();
        assertThat(composerInfo.getUrl())
                .hasHost("localhost")
                .hasPath("/images/Wolfgang+Amadeus+Mozart.jpg");

    }

}
