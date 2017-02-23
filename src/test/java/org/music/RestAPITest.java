package org.music;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

public class RestAPITest {

    @Test
    public void should_find_composer() throws IOException, URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/");
        uriBuilder.setPath("Ludwig van Beethoven");
        final Content bodyContent = Request.Get(uriBuilder.build())
                .execute().returnContent();

        String body =  bodyContent.asString();

        assertThat(body).contains("\"name\":\"Ludwig van Beethoven\"")
                        .contains("\"librettist\":\"Georg Friedrich Treitschke\"");

    }

    @Test
    public void should_find_composer_version_2() {
            given()
                .when()
                .get("{composer}", "Ludwig van Beethoven")
                .then()
                .assertThat()
                .body("name", is("Ludwig van Beethoven"))
                .body("operas.size()", is(1))
                .body("operas.name", hasItems("Fidelio"));
    }

}
