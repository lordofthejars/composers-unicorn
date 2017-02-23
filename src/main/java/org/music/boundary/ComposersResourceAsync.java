package org.music.boundary;

import org.music.entity.Composer;

import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ComposersResourceAsync {

    private Composers composers = new Composers();

    // Method for just academic purposes, not intend to be run nor be followed
    public void composersInfoAsync(@Suspended final AsyncResponse asyncResponse,
                                   @PathParam("composer") String composer) {


        CompletableFuture<Composer> findComposers = CompletableFuture.supplyAsync(() -> findComposer(composer));
        CompletableFuture<URL> findImage = CompletableFuture.supplyAsync(() ->  findImage(composer));


        findComposers.thenCombine(findImage, (c, i) -> new ComposerInfo(c, i))
                     .thenAccept(info -> {
                         asyncResponse.resume(info);
                     });

    }

    private Composer findComposer(String nameComposer) {
        // No exception handling
        try {
            TimeUnit.SECONDS.sleep(new Random(1).nextInt());
            return composers.findComposerByName(nameComposer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private URL findImage(String nameComposer) {
        try {
            TimeUnit.SECONDS.sleep(new Random(3).nextInt());
            // Mocked URL for academic purposes
            return new URL("http://localhost/images/" + URLEncoder.encode(nameComposer) + ".jpg");
        } catch (InterruptedException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static class ComposerInfo {
        private Composer composer;
        private URL url;

        public ComposerInfo(Composer composer, URL url) {
            this.composer = composer;
            this.url = url;
        }

        public Composer getComposer() {
            return composer;
        }

        public URL getUrl() {
            return url;
        }
    }

}
