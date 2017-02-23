package org.music.boundary;


import org.music.entity.Composer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class ComposersResource {

    @Inject
    Composers composers;

    @GET
    @Path("{composer}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findComposer(@PathParam("composer") String composerName) {
        final Composer composer = composers.findComposerByName(composerName);

        return Response.ok(composer).build();
    }

}
