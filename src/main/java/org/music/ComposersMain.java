package org.music;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.music.boundary.Composers;
import org.music.boundary.ComposersResource;
import org.music.entity.Composer;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class ComposersMain {

    public static void main(String... args) throws Exception {

        Swarm swarm = new Swarm();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addClasses(ComposersResource.class, Composers.class);
        deployment.addPackage(Composer.class.getPackage());
        deployment.addAllDependencies();
        swarm.start().deploy(deployment);

    }

}
