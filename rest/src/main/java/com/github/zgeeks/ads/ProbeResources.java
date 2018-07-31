package com.github.zgeeks.ads;

import org.springframework.context.annotation.Configuration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static javax.ws.rs.core.Response.status;

@Path("/probe")
@Configuration
public class ProbeResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProbe() {
        return status(Status.OK).build();
    }
}
