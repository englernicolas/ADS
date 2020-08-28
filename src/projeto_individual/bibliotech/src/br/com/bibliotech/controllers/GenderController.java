package br.com.bibliotech.controllers;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.Gender;
import br.com.bibliotech.domains.User;
import br.com.bibliotech.services.GenderService;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("gender")
public class GenderController extends UtilRest {
    @GET
    @Path("/get")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenders() {
        try {
            List<Gender> genderList = new ArrayList<Gender>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            GenderService genderService = new GenderService(connection);

            genderList = genderService.getGenders();

            dbConnection.closeConnection();
            
            return this.buildResponse(genderList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os gêneros! \n Erro: \n" + e.getMessage());
        }
    }
}
