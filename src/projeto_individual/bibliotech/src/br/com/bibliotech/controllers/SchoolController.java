package br.com.bibliotech.controllers;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.School;
import br.com.bibliotech.domains.User;
import br.com.bibliotech.services.SchoolService;
import br.com.bibliotech.services.UserService;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("school")
public class SchoolController extends UtilRest {
    @POST
    @Path("/create")
    @Consumes("application/*")
    public Response create(String params) {
        try {
            School school = new Gson().fromJson(params, School.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            SchoolService schoolService = new SchoolService(connection);
            boolean response = schoolService.createSchool(school);

            String msg;
            
            if (response){
                msg = "Escola cadastrada com sucesso!";
            }else {
                msg = "Erro ao cadastrar escola.";
            }
            
            dbConnection.closeConnection();
            
            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar cadastrar a escola! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/list")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<School> schoolList = new ArrayList<School>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            SchoolService schoolService = new SchoolService(connection);

            schoolList = schoolService.list();

            dbConnection.closeConnection();

            return this.buildResponse(schoolList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar as escolas! \n Erro: \n" + e.getMessage());
        }
    }
}
