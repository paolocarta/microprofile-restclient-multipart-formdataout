package org.acme.rest.client.multipart;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static java.nio.charset.StandardCharsets.UTF_8;

@Path("/client")
public class MultipartClientResource {

    @Inject
    @RestClient
    MultipartService service;

    @POST
    @Path("/multipart")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendFile() throws Exception {

        MultipartFormDataOutput outputData = new MultipartFormDataOutput();

        outputData.addFormData("filenames", "greeting, greeting2", MediaType.TEXT_PLAIN_TYPE);

        byte[] file1Content = "the content".getBytes(UTF_8);
        outputData.addFormData("file1", file1Content, MediaType.APPLICATION_OCTET_STREAM_TYPE);

        byte[] file2Content = "the second content".getBytes(UTF_8);
        outputData.addFormData("file2", file2Content, MediaType.APPLICATION_OCTET_STREAM_TYPE);

        return service.sendMultipartData(outputData);
    }
}