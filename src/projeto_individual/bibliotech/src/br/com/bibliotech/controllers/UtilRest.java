package br.com.bibliotech.controllers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

public class UtilRest {
    public Response buildResponse(Object result) {
        try {
            String response = new Gson().toJson(result);
            return Response.ok(response).build();
        } catch(Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse(e.getMessage());
        }
    }

    public Response buildErrorResponse(String str) {
        ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);

        rb = rb.entity(str);
        rb = rb.type("text/plain");

        return rb.build();
    }
}