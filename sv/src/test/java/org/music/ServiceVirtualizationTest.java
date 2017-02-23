package org.music;

import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.music.boundary.ComposersGateway;
import org.music.entity.Composer;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceVirtualizationTest {

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureOrSimulationMode("getcomposers.json");

    @Test
    public void should_get_composers_from_composers_microservice() throws URISyntaxException, IOException {

        // Given:
        ComposersGateway composersGateway = new ComposersGateway("192.168.99.100", 8081);

        // When:
        Composer composer = composersGateway.getComposer("Ludwig van Beethoven");

        // Then:
        assertThat(composer.getName()).isEqualTo("Ludwig van Beethoven");
    }

}
