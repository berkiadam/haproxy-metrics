package com.berkiadam.monitoring.testapp.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")

@Consumes({"application/json"})
public class TestAppService {


    private static final Logger logger = LoggerFactory.getLogger(TestAppService.class);


    @GET
    @Path("slowresponse/{delay}")
    public String getSlowResponse(@PathParam("delay") Long delay) {


        if (delay != null && delay > 0) {

            logger.info("Delay: " + delay);

            try {
                Thread.sleep(delay);
            } catch (Exception e) {
            }

        }


        return "OK";


    }


    @GET
    @Path("slowresponse/{delay}/{code}")
    public Response getSlowResponseWithHttpCode(@PathParam("delay") Long delay, @PathParam("code") int httpCode, @QueryParam("ingress") String  ingressName) {


        logger.info("ingress name: " + ingressName);

        String responseBody = null;

        if (httpCode >= 100 && httpCode <600) {


            if (delay != null && delay > 0) {

                logger.info("Delay: " + delay);

                try {
                    Thread.sleep(delay);
                } catch (Exception e) {
                }

            }



            if (httpCode < 200) {

                responseBody = "Informational";

            } else if (httpCode < 300) {

                responseBody = "Success";

            } else if (httpCode < 400) {

                responseBody = "Redirection";

            } else if (httpCode < 500) {

                responseBody = "Client Error";

            } else {

                responseBody = "Server Error";
            }


        } else {

            responseBody = "Illegal http code in request";
            httpCode = 400;
        }


        return Response.status(httpCode).entity(responseBody).type(MediaType.APPLICATION_JSON).build();


    }



}
