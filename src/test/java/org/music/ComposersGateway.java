package org.music;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.music.entity.Composer;

import java.io.IOException;
import java.net.URL;

public class ComposersGateway {

    private final Gson gson = new Gson();
    private final OkHttpClient client = new OkHttpClient();

    private String host;
    private int port;

    public ComposersGateway(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Composer getComposer(String name) {
        try {
            URL url = new URL("http", this.host, this.port, "/" + name);
            Request request = new Request.Builder().url(url).get().build();
            final Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return gson.fromJson(response.body().charStream(), Composer.class);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
